package com.byteme;

import com.byteme.Controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Launches the welcome screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/StartGame.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();
        controller.setStage(stage);
        Scene gameStart = new Scene(root);
        stage.setTitle("M.U.L.E. - ByteMe");
        stage.setScene(gameStart);
        stage.setResizable(false);
        stage.show();
    }
}