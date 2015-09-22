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
public class TownController extends Controller implements Initializable {
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
}
