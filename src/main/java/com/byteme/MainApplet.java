package com.byteme;

import com.byteme.Controllers.MasterController;
import com.byteme.Models.MULEStore;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javax.swing.*;
import java.net.URL;

public class MainApplet extends JApplet {
    protected Scene scene;
    protected Group root;

    @Override
    public final void init() {
        SwingUtilities.invokeLater(this::initSwing);
    }

    private void initSwing() {
        JFXPanel fxPanel = new JFXPanel();
        add(fxPanel);

        Platform.runLater(() -> {
            initFX(fxPanel);
            initApplet(fxPanel);
        });
    }

    private void initFX(JFXPanel fxPanel) {
        root = new Group();
        scene = new Scene(root);
        fxPanel.setScene(scene);
    }

    public void initApplet(JFXPanel fxPanel) {
        // Launches the welcome screen
        MULEStore.getInstance().bootstrap();
        MasterController controller = MasterController.getInstance();
        controller.setFXPanel(fxPanel);
        controller.startGame();
        //Music
        URL resource = getClass().getResource("/music/bensound-scifi.mp3");
        Media media = new Media(resource.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        mediaPlayer.play();
    }
}