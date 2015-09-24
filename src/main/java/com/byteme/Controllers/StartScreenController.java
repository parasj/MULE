package com.byteme.Controllers;

import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Siddarth on 9/10/2015.
 */
public class StartScreenController {

    /**
     * Runs when player clicks on the screen in the Start screen.
     * Opens the screen asking player whether they want to
     * open an old game file or create a new one.
     */
    public void startGame() {
        MasterController.getInstance().loadGame();
    }
}