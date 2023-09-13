package com.jss.vault.ui.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DatabaseOptionsPanel extends JPanel {

    public DatabaseOptionsPanel(final ActionListener onNewButton) {
        final JButton newButton = new JButton("New ");
        newButton.setPreferredSize(new Dimension(100, 40));

        final JButton selectButton = new JButton("Open...");
        selectButton.setPreferredSize(new Dimension(100, 40));

        final ButtonsPanel buttonsPanel = new ButtonsPanel(newButton, selectButton);

        this.setLayout(new GridBagLayout());
        this.add(buttonsPanel);

        newButton.addActionListener(onNewButton);
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
