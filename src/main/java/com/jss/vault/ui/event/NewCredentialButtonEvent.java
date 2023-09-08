package com.jss.vault.ui.event;

import com.jss.vault.config.KeePassManagerFactory;
import com.jss.vault.domain.Credential;
import com.jss.vault.repository.CredentialRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewCredentialButtonEvent implements ActionListener {
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final CredentialRepository credentialRepository;

    public NewCredentialButtonEvent(
        final JTextField usernameField,
        final JPasswordField passwordField,
        final CredentialRepository repository) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.credentialRepository = repository;
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        final String username = usernameField.getText();
        final String password = new String(passwordField.getPassword());
        final Credential credential = new Credential(null, null, username, password, null, null);
        credentialRepository.save(credential);
    }
}
