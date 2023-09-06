package com.jss.vault.config;

import com.jss.vault.domain.Credential;
import lombok.extern.slf4j.Slf4j;
import org.linguafranca.pwdb.kdbx.KdbxCreds;
import org.linguafranca.pwdb.kdbx.simple.SimpleDatabase;
import org.linguafranca.pwdb.kdbx.simple.SimpleEntry;
import org.linguafranca.pwdb.kdbx.simple.SimpleGroup;

import java.io.*;

@Slf4j
public class KeePassManagerFactory {
    public static KeePassManager create(final File file, final String credential) throws Exception {
        final KdbxCreds kdbxCreds = new KdbxCreds(credential.getBytes());
        SimpleDatabase database = null;

        try {
            if (file.exists()) {
                final FileInputStream stream = new FileInputStream(file);
                database = SimpleDatabase.load(kdbxCreds, stream);
                stream.close();
            } else {
                database = createDatabase(new FileOutputStream(file), new KdbxCreds(credential.getBytes()));
            }
        } catch (IllegalAccessException e) {
            log.error("Username and/or password are incorrect. Error : ", e) ;
        } catch (EOFException e) {
            log.warn("Corrupted file! Creating another...");
            //noinspection ResultOfMethodCallIgnored
            file.delete();
            createFile(file);
            database = createDatabase(new FileOutputStream(file), kdbxCreds);
        }

        return new KeePassManager(database, kdbxCreds, file);
    }

    private static SimpleDatabase createDatabase(final OutputStream stream, final KdbxCreds credential) throws IOException {
        final SimpleDatabase database = new SimpleDatabase();
        database.save(credential, stream);
        stream.close();

        return database;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void createFile(final File file) throws IOException {
        file.createNewFile();
        file.setWritable(true);
    }

    public static class KeePassManager {
        private final SimpleDatabase database;
        private final KdbxCreds credentials;
        private final File file;

        private KeePassManager(
            final SimpleDatabase database,
            final KdbxCreds credentials,
            final File file) {
            this.database = database;
            this.credentials = credentials;
            this.file = file;
        }

        public void update(final Credential credential) throws IOException {
            final SimpleGroup group = database.getRootGroup();
            final SimpleEntry entry = database.newEntry();

            entry.setTitle(credential.title());
            entry.setUsername(credential.username());
            entry.setPassword(credential.password());
            entry.setUrl(credential.url());
            entry.setNotes(credential.notes());
            group.addEntry(entry);
            database.save(credentials, new FileOutputStream(file));
        }
    }
}
