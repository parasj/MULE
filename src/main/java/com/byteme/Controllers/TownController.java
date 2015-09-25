package com.byteme.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

/**
 * Created by Daniel on 9/18/2015.
 */
public class TownController {


    public void goToMap() {
        MasterController.getInstance().map();
    }
}