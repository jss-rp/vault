package com.jss.vault.ui.component;

import com.jss.vault.ui.event.LoadButtonEvent;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@Slf4j
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
        selectButton.addActionListener(new LoadButtonEvent(this));
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
