package com.byteme.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Daniel on 9/18/2015.
 */
public class TownController {

    public void goToMap() {
        MasterController.getInstance().map();

    }

    public void goToPub() {
        MasterController.getInstance().pubScene();
    }

}