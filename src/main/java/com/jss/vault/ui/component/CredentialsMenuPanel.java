package com.jss.vault.ui.component;

import com.jss.vault.domain.Credential;
import com.jss.vault.repository.CredentialRepository;

import javax.swing.*;
import java.awt.*;

public class CredentialsMenuPanel extends JPanel {

    public CredentialsMenuPanel(final CredentialRepository repository) {
        final DefaultListModel<Credential> listModel = new DefaultListModel<>();
        final JList<Credential> list = new JList<>(listModel);
        final JScrollPane scrollPane = new JScrollPane(list);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(new NewCredentialPanel(listModel, repository), BorderLayout.EAST);
    }

    private static class NewCredentialPanel extends JPanel {
        public NewCredentialPanel(final DefaultListModel<Credential> list, final CredentialRepository repository) {
            final JTextField usernameField = new JTextField();
            final JPasswordField passwordField = new JPasswordField();
            final JButton newCredentialButton = new JButton("Save");

            newCredentialButton.addActionListener(event -> {
                final String username = usernameField.getText();
                final String password = new String(passwordField.getPassword());
                final Credential credential = new Credential(null, null, username, password, null, null);
                final Credential saved = repository.save(credential);

                list.addElement(saved);
            });

            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.add(usernameField);
            this.add(passwordField);
            this.add(newCredentialButton);
        }
    }
}
