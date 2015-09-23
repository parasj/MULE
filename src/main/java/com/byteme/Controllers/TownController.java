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
    @FXML
    private Rectangle landoffice;
    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        landoffice.setOnMouseClicked((MouseEvent e) -> goToMap());
    }
    /**
     * Runs when player clicks the Back button.
     * Changes scene to Map
     */
    public void goToMap() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Map.fxml"));
            Parent root = (Parent) loader.load();
            MapController controller = loader.getController();
            controller.setStage(stage);
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
