package com.jss.vault.ui.component;

import com.jss.vault.domain.Credential;
import com.jss.vault.repository.CredentialRepository;

import javax.swing.*;
import java.awt.*;

public class CredentialsMenuPanel extends JPanel {

    public CredentialsMenuPanel(final CredentialRepository repository) {
        final DefaultListModel<Credential> listModel = new DefaultListModel<>();
        final JList<Credential> list = new JList<>(listModel);
        var scrollPane = new JScrollPane(list);
        var newCredentialPanel = new NewCredentialPanel(listModel, repository);

        repository.listAll().forEach(listModel::addElement);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(newCredentialPanel, BorderLayout.EAST);

        list.addListSelectionListener(selection -> {
            final Credential selectedCredential = list.getSelectedValue();
            newCredentialPanel.setTitle(selectedCredential.title());
            newCredentialPanel.setUsername(selectedCredential.username());
            newCredentialPanel.setPassword(selectedCredential.password());
            newCredentialPanel.setUrl(selectedCredential.url());
            newCredentialPanel.setNotes(selectedCredential.notes());
        });
    }

    private static class NewCredentialPanel extends JPanel {
        private final JTextField titleField = new JTextField(22);
        private final JTextField usernameField = new JTextField(10);
        private final JPasswordField passwordField = new JPasswordField(10);
        private final JTextField urlField = new JTextField(22);
        private final JTextArea noteField = new JTextArea(5, 22);


        public NewCredentialPanel(final DefaultListModel<Credential> list, final CredentialRepository repository) {
            var titleLabelPanel = new JPanel();

            titleLabelPanel.setLayout(new BoxLayout(titleLabelPanel, BoxLayout.LINE_AXIS));
            titleLabelPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Title"),
                BorderFactory.createEmptyBorder(3, 5, 3, 5)));
            titleLabelPanel.add(titleField);

            var usernameLabelPanel = new JPanel();

            usernameLabelPanel.setLayout(new BoxLayout(usernameLabelPanel, BoxLayout.LINE_AXIS));
            usernameLabelPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Username"),
                BorderFactory.createEmptyBorder(3, 5, 3, 5)));
            usernameLabelPanel.add(usernameField);

            var passwordLabelPanel = new JPanel();

            passwordLabelPanel.setLayout(new BoxLayout(passwordLabelPanel, BoxLayout.LINE_AXIS));
            passwordLabelPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Password"),
                BorderFactory.createEmptyBorder(3, 5, 3, 5)));
            passwordLabelPanel.add(passwordField);

            var urlPanel = new JPanel();


            urlPanel.setLayout(new BoxLayout(urlPanel, BoxLayout.LINE_AXIS));
            urlPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("URL"),
                BorderFactory.createEmptyBorder(3, 5, 3, 5)));
            urlPanel.add(urlField);

            var notesPanel = new JPanel();
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

        public void setTitle(final String title) {
            this.titleField.setText(title);
        }

        public void setUsername(final String username) {
            this.usernameField.setText(username);
        }

        public void setPassword(final String password) {
            this.passwordField.setText(password);
        }

        public void setUrl(final String url) {
            this.urlField.setText(url);
        }

        public void setNotes(final String notes) {
            this.noteField.setText(notes);
        }
    }
}
