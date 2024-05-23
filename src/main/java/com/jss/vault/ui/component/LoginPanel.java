package com.jss.vault.ui.component;

import com.jss.vault.Application;
import com.jss.vault.config.KeePassManagerFactory;
import com.jss.vault.repository.impl.CredentialRepositoryImpl;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.io.File;

@Slf4j
public class LoginPanel extends JPanel {

    public LoginPanel(final File file) {
        var usernameLabel = new JPanel();
        var usernameField = new JTextField(18);

        usernameLabel.setLayout(new BoxLayout(usernameLabel, BoxLayout.LINE_AXIS));
        usernameLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Username"),
            BorderFactory.createEmptyBorder(3, 5, 3, 5)));
        usernameLabel.add(usernameField);

        var passwordLabel = new JPanel();
        var passwordField = new JPasswordField(18);
        var constraints = new GridBagConstraints();

        passwordLabel.setLayout(new BoxLayout(passwordLabel, BoxLayout.LINE_AXIS));
        passwordLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Password"),
            BorderFactory.createEmptyBorder(3, 5, 3, 5)));
        passwordLabel.add(passwordField);

        this.setLayout(new GridBagLayout());

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(usernameLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(passwordLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(10, 0, 0, 0);

        var loginButton = new JButton("Login");

        loginButton.addActionListener(e -> {
            try {
                final String username = usernameField.getText();
                final String password = new String(passwordField.getPassword());
                final String credential = "%s:%s".formatted(username, password);
                final KeePassManagerFactory.KeePassManager keePassManager = KeePassManagerFactory.create(file, credential);

                Application.changePanel(new CredentialsMenuPanel(new CredentialRepositoryImpl(keePassManager)));
            } catch (Exception e1) {
                constraints.gridx = 0;
                constraints.gridy = 3;

                this.add(new JLabel("The username and/or password are wrong"), constraints);
                this.repaint();
                this.revalidate();
                log.error("Fail on login. Error: ", e1);
            }
        });

        this.add(loginButton, constraints);
    }
}
