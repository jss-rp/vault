package com.jss.vault.ui.component.bar.top;

import com.jss.vault.Application;
import com.jss.vault.ui.component.DatabaseOptionsPanel;
import com.jss.vault.ui.component.NewDBPanel;

import javax.swing.*;
import java.awt.*;

public class FileMenu extends JMenu {
    private JMenuItem newFileItem = new JMenuItem("New file");
    private JMenuItem quitItem = new JMenuItem("Quit Vault");

    public FileMenu() {
        super("File");

        onLoginItemSelected();
        quitItem.addActionListener(__ -> Application.quit());

        this.add(newFileItem);
        this.addSeparator();
        this.add(quitItem);
    }

    private void onLoginItemSelected() {
        newFileItem.addActionListener(__ -> Application.changePanel(new DatabaseOptionsPanel(e -> {
            final JPanel root = new JPanel();
            root.setLayout(new GridBagLayout());
            root.add(new NewDBPanel());
            Application.changePanel(root);
        })));
    }
}
