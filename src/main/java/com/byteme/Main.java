package com.byteme;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// based on https://github.com/bmuschko/gradle-javafx-hello-world/blob/master/src/main/java/helloworld/HelloWorld.java

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Launches the welcome screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/StartGame.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();
        controller.setStage(stage);
        Scene gameStart = new Scene(root);
        stage.setTitle("M.U.L.E. - ByteMe");
        stage.setScene(gameStart);
        stage.show();
    }


}
