package com.jss.vault.ui.component;

import com.jss.vault.ui.event.NewDBButtonEvent;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;

@Slf4j
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

        var passwordStrengthBarPanel = new JPanel();
        var passwordEntropyBar = new JProgressBar();

        passwordStrengthBarPanel.setLayout(new BoxLayout(passwordStrengthBarPanel, BoxLayout.LINE_AXIS));
        passwordStrengthBarPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Strength"),
            BorderFactory.createEmptyBorder(3, 5, 3, 5)));
        passwordStrengthBarPanel.add(passwordEntropyBar);


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

        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(passwordStrengthBarPanel, constraints);

        constraints.gridwidth = 2;
        constraints.insets = new Insets(5,0, 0 ,0);
        constraints.gridx = 0;
        constraints.gridy = 3;

        createDbButton.addActionListener(new NewDBButtonEvent(this.getParent(), usernameField, passwordField));
        this.add(createDbButton, constraints);
    }
}
