package com.byteme.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

/**
 * Created by Daniel on 9/18/2015.
 */
public class TownController implements Initializable {
    @FXML
    private Rectangle landoffice;

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        landoffice.setOnMouseClicked((MouseEvent e) -> {
            // MapController.buy = true;
            goToMap();
        });
    }

    /**
     * Runs when player clicks the Back button.
     * Changes scene to Map
     */
    @FXML
    private void goToMap() {
        MasterController mc = MasterController.getInstance();
        mc.map();
    }
}