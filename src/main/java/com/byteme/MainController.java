package com.byteme;

import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Siddarth on 9/10/2015.
 */
public class MainController {

    private Stage stage;

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

    /**
     * Run this whenever we want to show a temporary screen for
     * things that still need to be created
     * @throws IOException if placeholder.fxml is not found
     */
    public void openTemp() throws IOException {
        setNewScene("/fxml/placeholder.fxml");
    }

    /**
     * Runs when player clicks on Town.
     * Takes player to town.
     * @throws IOException
     */
    public void goToTown() throws IOException {
        setNewScene("/fxml/Town.fxml");
    }

    /**
     * Changes the scene of the current stage to the one specified
     * @param fxmlFile A string containing the location of the new fxml file
     * @throws IOException fxml file load failed
     */
    public void setNewScene(String fxmlFile) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        MainController controller = loader.getController();
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