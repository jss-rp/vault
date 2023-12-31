package com.jss.vault.repository.impl;

import com.jss.vault.config.KeePassManagerFactory;
import com.jss.vault.domain.Credential;
import com.jss.vault.repository.CredentialRepository;
import lombok.extern.slf4j.Slf4j;
import org.linguafranca.pwdb.kdbx.simple.SimpleDatabase;
import org.linguafranca.pwdb.kdbx.simple.SimpleEntry;
import org.linguafranca.pwdb.kdbx.simple.SimpleGroup;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

@Slf4j
public class CredentialRepositoryImpl implements CredentialRepository {

    private static KeePassManagerFactory.KeePassManager KEE_PASS_MANAGER;

    public CredentialRepositoryImpl(final KeePassManagerFactory.KeePassManager keePassManager) {
        if (KEE_PASS_MANAGER == null) KEE_PASS_MANAGER = keePassManager;
    }

    @Override
    public Credential save(final Credential credential) {
        try {
            final SimpleDatabase database = KEE_PASS_MANAGER.getDatabase();
            final SimpleGroup group = database.getRootGroup();
            final AtomicReference<SimpleEntry> entry = new AtomicReference<>(database.newEntry());

            group.getEntries().stream()
                .filter(i -> i.getUuid().toString().equalsIgnoreCase(credential.id()))
                .findFirst()
                .ifPresentOrElse(result -> {
                    result.setTitle(credential.title());
                    result.setUsername(credential.username());
                    result.setPassword(credential.password());
                    result.setUrl(credential.url());
                    result.setNotes(credential.notes());
                    entry.set(result);
                }, () -> {
                    entry.get().setTitle(credential.title());
                    entry.get().setUsername(credential.username());
                    entry.get().setPassword(credential.password());
                    entry.get().setUrl(credential.url());
                    entry.get().setNotes(credential.notes());
                    group.addEntry(entry.get());
                });


            KEE_PASS_MANAGER.update();

            return new Credential(
                entry.get().getUuid().toString(),
                credential.title(),
                credential.username(),
                credential.password(),
                credential.notes(),
                credential.url()
            );
        } catch (IOException e) {
            log.error("Fail on saving a new credential. Error", e);
        }

        return credential;
    }

    @Override
    public Credential findOne(final String id) {
        return null;
    }

    @Override
    public List<Credential> listAll() {
        final SimpleGroup rootGroup = KEE_PASS_MANAGER.getDatabase().getRootGroup();
        final Stream<Credential> credentialStream = rootGroup.getEntries().stream().map(entry -> new Credential(
            entry.getUuid().toString(),
            entry.getTitle(),
            entry.getUsername(),
            entry.getPassword(),
            entry.getNotes(),
            entry.getUrl()
        ));

        return credentialStream.toList();
    }
}
