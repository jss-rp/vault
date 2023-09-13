package com.jss.vault;

import com.jss.vault.ui.component.DatabaseOptionsPanel;
import com.jss.vault.ui.component.MainFrame;
import com.jss.vault.ui.component.NewDBPanel;

import javax.swing.*;
import java.awt.*;

public class Application {

    public static void main(String[] args) {
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
        MainFrame.start(root);
    }
}
