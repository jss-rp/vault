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

        repository.listAll().forEach(listModel::addElement);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(new NewCredentialPanel(listModel, repository), BorderLayout.EAST);
    }

    private static class NewCredentialPanel extends JPanel {
        public NewCredentialPanel(final DefaultListModel<Credential> list, final CredentialRepository repository) {
            var titleLabelPanel = new JPanel();
            var titleField = new JTextField(20);

            titleLabelPanel.setLayout(new BoxLayout(titleLabelPanel, BoxLayout.LINE_AXIS));
            titleLabelPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Title"),
                BorderFactory.createEmptyBorder(3, 5, 3, 5)));
            titleLabelPanel.add(titleField);

            var usernameLabelPanel = new JPanel();
            var usernameField = new JTextField(20);

            usernameLabelPanel.setLayout(new BoxLayout(usernameLabelPanel, BoxLayout.LINE_AXIS));
            usernameLabelPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Username"),
                BorderFactory.createEmptyBorder(3, 5, 3, 5)));
            usernameLabelPanel.add(usernameField);

            var passwordLabelPanel = new JPanel();
            var passwordField = new JPasswordField(20);

            passwordLabelPanel.setLayout(new BoxLayout(passwordLabelPanel, BoxLayout.LINE_AXIS));
            passwordLabelPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Password"),
                BorderFactory.createEmptyBorder(3, 5, 3, 5)));
            passwordLabelPanel.add(passwordField);

            final JButton newCredentialButton = new JButton("Save");
            newCredentialButton.setSize(new Dimension(50, 50));
            this.setLayout(new GridLayout(8, 1));

            newCredentialButton.addActionListener(event -> {
                final String title = titleField.getText();
                final String username = usernameField.getText();
                final String password = new String(passwordField.getPassword());
                final Credential credential = new Credential(null, title, username, password, null, null);
                final Credential saved = repository.save(credential);

                list.addElement(saved);
            });


            this.add(titleLabelPanel);
            this.add(usernameLabelPanel);
            this.add(passwordLabelPanel);
            this.add(newCredentialButton);
        }
    }
}
