package com.jss.vault.ui.component;

import javax.swing.*;
import java.awt.*;

public class DatabaseOptionsPanel extends JPanel {

    public DatabaseOptionsPanel() {
        final JButton createButton = new JButton("New ");
        createButton.setPreferredSize(new Dimension(100, 40));

        final JButton selectButton = new JButton("Open...");
        selectButton.setPreferredSize(new Dimension(100, 40));

        final ButtonsPanel buttonsPanel = new ButtonsPanel(createButton, selectButton);

        this.setLayout(new GridBagLayout());
        this.add(buttonsPanel);

        createButton.addActionListener(event -> {
            buttonsPanel.setVisible(false);
            this.add(new NewDBPanel());
        });
    }

    private static class ButtonsPanel extends JPanel {
        public ButtonsPanel(final JButton... buttons) {
            this.setPreferredSize(new Dimension(120,50));
            this.setLayout(new GridLayout(2, 0, 6, 10));

            for (JButton button : buttons) {
                this.add(button);
            }
        }
    }
}
