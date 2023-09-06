package com.jss.vault.ui.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DatabaseOptionsPanel extends JPanel {
    private final JButton createButton = new JButton("New ");
    private final JButton selectButton = new JButton("Open...");

    public DatabaseOptionsPanel() {
        createButton.setPreferredSize(new Dimension(100, 40));
        selectButton.setPreferredSize(new Dimension(100, 40));

        final ButtonsPanel buttonsPanel = new ButtonsPanel(createButton, selectButton);

        this.setLayout(new GridBagLayout());
        this.add(buttonsPanel);
    }

    public DatabaseOptionsPanel(
        final ActionListener onCreateButton,
        final ActionListener onSelectButton) {
        this.createButton.addActionListener(onCreateButton);
        this.selectButton.addActionListener(onSelectButton);
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
