package com.jss.vault.ui.component;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

@Slf4j
public class MainFrame extends JFrame {
    public MainFrame(final JPanel panel) {
        super("Credential Vault");
        setContentPane(panel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setSize(640, 480);
        setVisible(true);
        log.debug("Main frame started");
    }

    public void setPanel(final JPanel panel) {
        setContentPane(panel);
        repaint();
        revalidate();
    }
}
