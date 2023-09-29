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
        var formCredentialPanel = new FormCredentialPanel(listModel, repository);

        repository.listAll().forEach(listModel::addElement);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(formCredentialPanel, BorderLayout.EAST);

        list.addListSelectionListener(selection -> {
            final Credential selectedCredential = list.getSelectedValue();

            formCredentialPanel.setCredential(selectedCredential);
        });
    }

    private static class FormCredentialPanel extends JPanel {
        private Credential credential;
        private final String emptyUuid = "00000000-0000-0000-0000-000000000000";
        public final JLabel idLabel = new JLabel(emptyUuid);
        private final JTextField titleField = new JTextField(22);
        private final JTextField usernameField = new JTextField(10);
        private final JPasswordField passwordField = new JPasswordField(10);
        private final JTextField urlField = new JTextField(22);
        private final JTextArea noteField = new JTextArea(5, 22);
        final JButton saveCredentialButton = new JButton("Save");


        public FormCredentialPanel(final DefaultListModel<Credential> list, final CredentialRepository repository) {
            var idLabelPanel = new JPanel();
            idLabelPanel.setLayout(new BoxLayout(idLabelPanel, BoxLayout.LINE_AXIS));
            idLabelPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("UUID"),
                BorderFactory.createEmptyBorder(3, 5, 3, 5)));
            idLabelPanel.add(idLabel);

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

            saveCredentialButton.setSize(new Dimension(50, 10));

            var layout = new GridBagLayout();
            this.setLayout(layout);

            saveCredentialButton.addActionListener(event -> {
                final String title = titleField.getText();
                final String username = usernameField.getText();
                final String password = new String(passwordField.getPassword());
                final String url = urlField.getText();
                final String notes = noteField.getText();
                final Credential credential = new Credential(null, title, username, password, notes, url);
                this.credential = repository.save(credential);
                this.idLabel.setText(this.credential.id());
                list.addElement(this.credential);
            });

            var clearFormButton = new JButton("Clear");

            clearFormButton.addActionListener(event -> clearForm());

            var constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.gridwidth = 2;

            this.add(idLabelPanel, constraints);

            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.gridwidth = 2;

            this.add(titleLabelPanel, constraints);

            constraints.gridx = 0;
            constraints.gridy = 2;
            constraints.gridwidth = 1;

            this.add(usernameLabelPanel, constraints);

            constraints.gridx = 1;
            constraints.gridy = 2;

            this.add(passwordLabelPanel, constraints);

            constraints.gridx = 0;
            constraints.gridy = 3;
            constraints.gridwidth = 2;

            this.add(urlPanel, constraints);

            constraints.gridx = 0;
            constraints.gridy = 4;
            constraints.gridwidth = 2;

            this.add(notesPanel, constraints);

            constraints.gridx = 1;
            constraints.gridy = 5;
            constraints.gridwidth = 1;

            this.add(saveCredentialButton, constraints);

            constraints.gridx = 0;
            constraints.gridy = 5;

            this.add(clearFormButton, constraints);
        }


        public void setCredential(final Credential credential) {
            this.credential = credential;

            this.idLabel.setText(credential.id());
            this.titleField.setText(credential.title());
            this.usernameField.setText(credential.username());
            this.passwordField.setText(credential.password());
            this.urlField.setText(credential.url());
            this.noteField.setText(credential.notes());
        }

        private void clearForm() {
            this.credential = null;
            this.idLabel.setText(emptyUuid);
            this.titleField.setText(null);
            this.usernameField.setText(null);
            this.passwordField.setText(null);
            this.urlField.setText(null);
            this.noteField.setText(null);
        }
    }
}
