package com.jss.vault.ui.component;

import com.jss.vault.ui.event.NewDBButtonEvent;

import javax.swing.*;
import java.awt.*;

public class NewDBPanel extends JPanel {

    public NewDBPanel() {
        var usernameLabelPanel = new JPanel();
        var usernameField = new JTextField(12);

        usernameLabelPanel.setLayout(new BoxLayout(usernameLabelPanel, BoxLayout.LINE_AXIS));
        usernameLabelPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Username"),
            BorderFactory.createEmptyBorder(3, 5, 3, 5)));
        usernameLabelPanel.add(usernameField);

        var passwordLabelPanel = new JPanel();
        var passwordField = new JPasswordField(12);

        passwordLabelPanel.setLayout(new BoxLayout(passwordLabelPanel, BoxLayout.LINE_AXIS));
        passwordLabelPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Password"),
            BorderFactory.createEmptyBorder(3, 5, 3, 5)));
        passwordLabelPanel.add(passwordField);

        var constraints = new GridBagConstraints();
        var createDbButton = new JButton("Create");
        this.setLayout(new GridBagLayout());

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(usernameLabelPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(passwordLabelPanel, constraints);


        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridwidth = 2;
        constraints.insets = new Insets(5,0, 0 ,0);
        constraints.gridx = 0;
        constraints.gridy = 2;

        createDbButton.addActionListener(new NewDBButtonEvent(this.getParent(), usernameField, passwordField));
        this.add(createDbButton, constraints);
    }
}
