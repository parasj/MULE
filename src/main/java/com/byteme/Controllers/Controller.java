package com.byteme.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Siddarth on 9/21/2015.
 */
public abstract class Controller {

    Stage stage;

    /**
     * Changes the scene of the current stage to the one specified
     * @param fxmlFile A string containing the location of the new fxml file
     * @throws IOException fxml file load failed
     */
    public void setNewScene(String fxmlFile) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.setStage(stage);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Sets this controller instance's stage
     * @param stage The stage to be set to the controller
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Run this whenever we want to show a temporary screen for
     * things that still need to be created
     * @throws IOException if placeholder.fxml is not found
     */
    public void openTemp() throws IOException {
        setNewScene("/fxml/placeholder.fxml");
    }
}
