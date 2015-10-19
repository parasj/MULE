package com.byteme;

import com.byteme.Controllers.MasterController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

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

        stage.setTitle("M.U.L.E. - ByteMe");
        stage.setResizable(false);
        stage.show();
    }
}