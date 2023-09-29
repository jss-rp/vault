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
            var titleField = new JTextField(22);

            titleLabelPanel.setLayout(new BoxLayout(titleLabelPanel, BoxLayout.LINE_AXIS));
            titleLabelPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Title"),
                BorderFactory.createEmptyBorder(3, 5, 3, 5)));
            titleLabelPanel.add(titleField);

            var usernameLabelPanel = new JPanel();
            var usernameField = new JTextField(10);

            usernameLabelPanel.setLayout(new BoxLayout(usernameLabelPanel, BoxLayout.LINE_AXIS));
            usernameLabelPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Username"),
                BorderFactory.createEmptyBorder(3, 5, 3, 5)));
            usernameLabelPanel.add(usernameField);

            var passwordLabelPanel = new JPanel();
            var passwordField = new JPasswordField(10);

            passwordLabelPanel.setLayout(new BoxLayout(passwordLabelPanel, BoxLayout.LINE_AXIS));
            passwordLabelPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Password"),
                BorderFactory.createEmptyBorder(3, 5, 3, 5)));
            passwordLabelPanel.add(passwordField);

            var urlPanel = new JPanel();
            var urlField = new JTextField(22);

            urlPanel.setLayout(new BoxLayout(urlPanel, BoxLayout.LINE_AXIS));
            urlPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("URL"),
                BorderFactory.createEmptyBorder(3, 5, 3, 5)));
            urlPanel.add(urlField);

            var notesPanel = new JPanel();
            var noteField = new JTextArea(5, 22);
            JScrollPane scrollPane = new JScrollPane(noteField);

            notesPanel.setLayout(new BoxLayout(notesPanel, BoxLayout.LINE_AXIS));
            notesPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Notes"),
                BorderFactory.createEmptyBorder(3, 5, 3, 5)));
            notesPanel.add(scrollPane);

            final JButton newCredentialButton = new JButton("Save");
            newCredentialButton.setSize(new Dimension(50, 10));

            var layout = new GridBagLayout();
            this.setLayout(layout);

            newCredentialButton.addActionListener(event -> {
                final String title = titleField.getText();
                final String username = usernameField.getText();
                final String password = new String(passwordField.getPassword());
                final String url = urlField.getText();
                final String notes = noteField.getText();
                final Credential credential = new Credential(null, title, username, password, notes, url);
                final Credential saved = repository.save(credential);

                list.addElement(saved);
            });

            var constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.gridwidth = 2;

            this.add(titleLabelPanel, constraints);

            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.gridwidth = 1;

            this.add(usernameLabelPanel, constraints);

            constraints.gridx = 1;
            constraints.gridy = 1;

            this.add(passwordLabelPanel, constraints);

            constraints.gridx = 0;
            constraints.gridy = 2;
            constraints.gridwidth = 2;

            this.add(urlPanel, constraints);

            constraints.gridx = 0;
            constraints.gridy = 3;
            constraints.gridwidth = 2;

            this.add(notesPanel, constraints);

            constraints.gridx = 0;
            constraints.gridy = 4;
            constraints.gridwidth = 2;

            this.add(newCredentialButton, constraints);
        }
    }
}
