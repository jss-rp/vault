package com.jss.vault.ui.event;

import com.jss.vault.Application;
import com.jss.vault.config.KeePassManagerFactory;
import com.jss.vault.repository.impl.CredentialRepositoryImpl;
import com.jss.vault.ui.component.CredentialsMenuPanel;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

@Slf4j
public class NewDBButtonEvent implements ActionListener {
    private final Component parent;
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JFileChooser fileChooser = new JFileChooser();

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

            if (file.exists()) {
                fileChooser.cancelSelection();
                JOptionPane.showMessageDialog(parent, "This database exists already");
                this.actionPerformed(event);
            } else {
                try {
                    final KeePassManagerFactory.KeePassManager keePassManager = KeePassManagerFactory.create(file, credential);

                    Application.changePanel(new CredentialsMenuPanel(new CredentialRepositoryImpl(keePassManager)));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
