package com.jss.vault.ui.component;

import com.jss.vault.ui.event.NewDBButtonEvent;

import javax.swing.*;
import java.awt.*;

public class NewDBPanel extends JPanel {

    public NewDBPanel() {
        var usernameLabel = new JLabel("Username: ");
        var passwordLabel = new JLabel("Password: ");
        var usernameField = new JTextField(12);
        var passwordField = new JPasswordField(12);
        var constraints = new GridBagConstraints();
        var createDbButton = new JButton("Create");

        this.setLayout(new GridBagLayout());

        constraints.weightx = 0.3;
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(usernameLabel, constraints);

        constraints.weightx = 0.7;
        constraints.gridx = 1;
        constraints.gridy = 0;
        this.add(usernameField, constraints);


        constraints.weightx = 0.3;
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(passwordLabel, constraints);

        constraints.weightx = 0.7;
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.add(passwordField, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridwidth = 2;
        constraints.weightx = 0.0;
        constraints.insets = new Insets(5,0, 0 ,0);
        constraints.gridx = 0;
        constraints.gridy = 2;

        createDbButton.addActionListener(new NewDBButtonEvent(this, usernameField, passwordField));
        this.add(createDbButton, constraints);
    }
}
