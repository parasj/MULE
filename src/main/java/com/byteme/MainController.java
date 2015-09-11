package com.byteme;

import javafx.scene.Parent;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Siddarth on 9/10/2015.
 */
public class MainController {

    public void openPlayerConfig() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/ConfigureScreen.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setOpacity(1);
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 450, 450));
            stage.showAndWait();

    }

    public void openTemp() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/placeholder.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("My New Stage Title");
        stage.setScene(new Scene(root, 450, 450));
        stage.showAndWait();
    }
}
