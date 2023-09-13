package com.jss.vault.ui.event;

import com.jss.vault.ui.component.LoginPanel;
import com.jss.vault.ui.component.MainFrame;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

@Slf4j
public class LoadButtonEvent implements ActionListener {

    private final Component parent;

    public LoadButtonEvent(final Component parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        var fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
        fileChooser.setFileFilter(new FileNameExtensionFilter(".kdbx file is a password database(KeePass Password Safe)", "kdbx"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if (fileChooser.showOpenDialog(this.parent) == JFileChooser.APPROVE_OPTION) {
            final File file = new File("%s".formatted(fileChooser.getSelectedFile().getAbsolutePath()));

            if (file.exists()) {
                MainFrame.setPanel(new LoginPanel(file));
            } else {
                fileChooser.cancelSelection();
                JOptionPane.showMessageDialog(this.parent, "This database doesn't exists already");
                this.actionPerformed(event);
            }
        }
    }
}
