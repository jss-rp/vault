package com.jss.vault;

import com.jss.vault.ui.component.DatabaseOptionsPanel;

import javax.swing.*;
import java.awt.*;

public class Application {

    public static void main(String[] args) {
        final DatabaseOptionsPanel databaseOptionsPanel = new DatabaseOptionsPanel();

        final JFrame frame = new JFrame("Credentials Vault");

        frame.setContentPane(databaseOptionsPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(640, 480);
        frame.setLocation(new Point(0, 0));
        frame.setVisible(true);
    }
}
