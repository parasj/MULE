package com.byteme.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Daniel on 9/18/2015.
 */
public class TownController implements Initializable {
    private Stage stage;
    @FXML
    private Rectangle landoffice;
    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        landoffice.setOnMouseClicked((MouseEvent e) -> goToMap());
    }
    /**
     * Runs when player clicks the Town.
     * Changes scene to Town
     */
    public void goToMap() {
        try {
            MapController.buy = true;
            setNewScene("/fxml/Map.fxml");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    /**
     * Changes the scene of the current stage to the one specified
     * @param fxmlFile A string containing the location of the new fxml file
     * @throws IOException fxml file load failed
     */
    public void setNewScene(String fxmlFile) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        MapController controller = loader.getController();
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
}
