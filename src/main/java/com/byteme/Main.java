package com.byteme;

import com.byteme.Controllers.MasterController;
import com.byteme.Models.MULEStore;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Launches the welcome screen
        MULEStore.getInstance().bootstrap();
        MasterController controller = MasterController.getInstance();
        controller.setStage(stage);
        controller.startGame();
        //Music
        URL resource = getClass().getResource("/music/bensound-scifi.mp3");
        Media media = new Media(resource.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        mediaPlayer.play();
        stage.setTitle("M.U.L.E. - ByteMe");
        stage.setResizable(false);
        stage.show();
    }
}