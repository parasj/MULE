package com.byteme.Controllers;

import com.byteme.Models.ConfigRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Daniel on 9/18/2015.
 */
public class TownController implements Initializable{
    @FXML
    private Rectangle pub;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pub.setOnMouseClicked((MouseEvent e) -> goToPub());
    }

    public void goToMap() {
        MasterController.getInstance().map();
    }

    public void goToPub() {
        MasterController.getInstance().pubScene();
    }
}