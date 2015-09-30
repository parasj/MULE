package com.byteme.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;


/**
 * Created by Daniel on 9/30/2015.
 */
public class PubController {
    //need to update Player
    public void goToMapFromPub(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Map.fxml"));
            Parent root = (Parent)fxmlLoader.load();
            MapController mapController = fxmlLoader.getController();
            MasterController.getInstance().map();
            mapController.changePlayer();
            //MapController.getInstance().changePlayer(); this doesn't work either
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}