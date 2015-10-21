package com.byteme;

import com.byteme.Controllers.MasterController;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Launches the welcome screen
        MasterController controller = MasterController.getInstance();
        controller.setStage(stage);
        controller.startGame();
        URL resource = getClass().getResource("/music/stupidblues01.mp3");
        Media media = new Media(resource.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
        mediaPlayer.play();
        stage.setTitle("M.U.L.E. - ByteMe");
        stage.setResizable(false);
        stage.show();
    }
}