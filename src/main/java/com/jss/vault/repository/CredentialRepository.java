package com.jss.vault.repository;

import com.jss.vault.domain.Credential;

import java.util.List;

public interface CredentialRepository {
    Credential save(final Credential credential);
    Credential findOne(final String id);
    List<Credential> listAll();
}
