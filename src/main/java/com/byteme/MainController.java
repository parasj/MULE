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
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Siddarth on 9/10/2015.
 */
public class MainController {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void startGame() throws IOException {
        // After clicking on welcome screen,
        // ask whether to load or create new game data
        setNewScene("fxml/LoadNewGame.fxml");
    }

    public void loadConfigureScreen() throws IOException {
        setNewScene("fxml/ConfigureScreen.fxml");
    }

    public void openPlayerConfig() throws IOException {
        setNewScene("fxml/PlayerConfig.fxml");
    }

    public void openTemp() throws IOException {
        setNewScene("fxml/placeholder.fxml");
    }

    public void setNewScene(String fxmlFile) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        MainController controller = loader.getController();
        controller.setStage(stage);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
