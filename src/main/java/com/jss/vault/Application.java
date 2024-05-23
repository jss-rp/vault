package com.jss.vault;

import com.jss.vault.ui.component.DatabaseOptionsPanel;
import com.jss.vault.ui.component.MainFrame;
import com.jss.vault.ui.component.NewDBPanel;

import javax.swing.*;
import java.awt.*;

public class Application implements Runnable {

    private static MainFrame MAIN_FRAME;

    @Override
    public void run() {
        final JPanel root = new JPanel();
        final DatabaseOptionsPanel databaseOptionsPanel = new DatabaseOptionsPanel(e -> {
            root.removeAll();
            root.setLayout(new GridBagLayout());
            root.add(new NewDBPanel());
            root.repaint();
            root.revalidate();
        });

        root.setLayout(new BorderLayout());
        root.add(databaseOptionsPanel, BorderLayout.CENTER);
        MAIN_FRAME = new MainFrame(root);
    }

    public static void changePanel(final JPanel newPanel) {
        MAIN_FRAME.setPanel(newPanel);
    }

    public static void quit() {
        MAIN_FRAME.dispose();
    }

    ;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Application());
    }
}
