package com.byteme.Controllers;

import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Siddarth on 9/10/2015.
 */
public class MainController extends Controller {

    /**
     * Runs when Player clicks on the screen in the Start screen.
     * Opens the screen asking player whether they want to.
     * open an old game file or create a new one.
     * @throws IOException if LoadGame.fxml is not found
     */
    public void startGame() throws IOException {
        setNewScene("/fxml/LoadGame.fxml");
    }

    /**
     * Runs when Player selects "Create New Game".
     * Opens the screen containing Game Configuration settings.
     * Gives control to the ConfigurationController class.
     * @throws IOException if GameConfig.fxml is not found
     */
    public void loadConfigureScreen() throws IOException {
        // Opens Game Configuration settings
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GameConfig.fxml"));
        Parent root = loader.load();
        ConfigurationController controller = loader.getController();
        controller.setStage(stage);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}