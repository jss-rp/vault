package com.jss.vault.ui.component;

import com.jss.vault.Application;
import lombok.extern.slf4j.Slf4j;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;

import static com.jss.vault.ui.component.DatabaseOptionsPanel.NEW_DB_BUTTON;
import static com.jss.vault.ui.component.NewDBPanel.*;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import static org.assertj.swing.launcher.ApplicationLauncher.application;

@Slf4j
public class DatabaseOptionsPanelTest extends AssertJSwingJUnitTestCase {

    FrameFixture frameFixture;

    @Override
    protected void onSetUp() {
        application(Application.class).start();
        frameFixture = findFrame(new GenericTypeMatcher<>(Frame.class) {
            @Override
            protected boolean isMatching(Frame frame) {
                return "Credential Vault".equals(frame.getTitle()) && frame.isShowing();
            }
        }).using(robot());
    }

    @Test
    public void shouldCreateNewDB() throws IOException {
        frameFixture.button(NEW_DB_BUTTON).click();
        frameFixture.textBox(USERNAME_FIELD_NAME).setText("test");
        frameFixture.textBox(PASSWORD_FIELD_NAME).setText("test");
        frameFixture.button(NEW_DB_FIELD_NAME).click();

        frameFixture.fileChooser()
                .selectFile(Files.createTempFile("", "database_test").toFile())
                .approve();
    }
}
