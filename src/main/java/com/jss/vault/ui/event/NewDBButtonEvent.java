package com.jss.vault.ui.event;

import com.jss.vault.config.KeePassManagerFactory;
import com.jss.vault.repository.impl.CredentialRepositoryImpl;
import com.jss.vault.ui.component.CredentialsMenuPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class NewDBButtonEvent implements ActionListener {
    private final Component parent;
    private final JTextField usernameField;
    private final JPasswordField passwordField;

    public NewDBButtonEvent(
        final Component parent,
        final JTextField usernameField,
        final JPasswordField passwordField) {
        this.parent = parent;
        this.usernameField = usernameField;
        this.passwordField = passwordField;
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
        fileChooser.setFileFilter(new FileNameExtensionFilter(".kdbx file is a password database(KeePass Password Safe)", "kdbx"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if (fileChooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
            final String username = this.usernameField.getText();
            final String password = new String(this.passwordField.getPassword());
            final String credential = "%s:%s".formatted(username, password);
            final FileNameExtensionFilter extensionFilter = (FileNameExtensionFilter) fileChooser.getFileFilter();
            final String extension = extensionFilter.getExtensions()[0];
            final File file = new File("%s.%s".formatted(fileChooser.getSelectedFile().getAbsolutePath(), extension));

            try {
                final KeePassManagerFactory.KeePassManager keePassManager = KeePassManagerFactory.create(file, credential);
                this.parent.setVisible(false);
                this.parent.getParent().add(new CredentialsMenuPanel(new CredentialRepositoryImpl(keePassManager)));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
