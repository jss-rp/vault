package com.jss.vault.ui.component;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

@Slf4j
public class MainFrame {
    private static final JFrame FRAME = new JFrame("Credential Vault");

    public static void start(final JPanel firstPanel) {
        log.debug("Starting the main frame...");
        FRAME.setContentPane(firstPanel);
        FRAME.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        FRAME.setSize(640, 480);
        FRAME.setVisible(true);
    }

    public static void setPanel(final JPanel panel) {
        FRAME.setContentPane(panel);
        FRAME.repaint();
        FRAME.revalidate();
    }
}
