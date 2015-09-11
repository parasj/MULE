package com.byteme;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

// based on https://github.com/bmuschko/gradle-javafx-hello-world/blob/master/src/main/java/helloworld/HelloWorld.java

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Create Player.fxml"));
        Scene player = new Scene(root);
        primaryStage.setTitle("Select Player");
        primaryStage.setScene(player);
        primaryStage.show();
    }
}
