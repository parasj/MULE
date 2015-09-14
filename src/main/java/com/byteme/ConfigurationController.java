package com.byteme;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Siddarth on 9/13/2015.
 */
public class ConfigurationController {

    private Stage stage;
    private static int currentPlayer = 1;
    private static int numPlayers;
    private static String difficulty;
    private static String map;

    // GAME CONFIGURATION SCREEN VARIABLES & METHODS
    @FXML
    private RadioButton difficultyEasy, difficultyNormal, difficultyHard;
    @FXML
    private Slider numPlayersSlider;
    @FXML
    private ChoiceBox mapType;

    /**
     * Runs when player clicks "OK" button on the Game Configuration settings screen.
     * Saves the configuration settings.
     * Opens Player 1 settings configuration screen.
     * @throws IOException if PlayerConfig.fxml is not found
     */
    public void saveGameConfig() throws IOException {
        this.difficulty = selectedDifficultyButton().getId();
        this.numPlayers = (int) numPlayersSlider.getValue();
        this.map = (String) mapType.getValue();

        System.out.println("Difficulty: " + difficulty + "\nNumber Players: " + numPlayers + "\nMap: " + map);
        //TODO: Save difficulty, map, and numPlayer information

        configurePlayerInformation();
    }

    /**
     * Used in saveGameConfig to tell which radio button
     * is selected for game difficulty.
     * @return The selected difficulty level RadioButton
     */
    private RadioButton selectedDifficultyButton() {
        if (difficultyEasy.isSelected()) {
            return difficultyEasy;
        } else if (difficultyNormal.isSelected()) {
            return difficultyNormal;
        } else if (difficultyHard.isSelected()) {
            return difficultyHard;
        } else {
            return null;
        }
    }

    // PLAYER CONFIGURATION SCREEN VARIABLES & METHODS
    @FXML
    private TextField playerName;
    @FXML
    private ChoiceBox playerRace;
    @FXML
    private ColorPicker playerColor;
    @FXML
    private Label playerNumber;

    /**
     * Runs when a new player's settings need to be configured.
     * Opens the player configuration screen and saves their data.
     * Increments currentPlayer
     * @throws IOException if PlayerConfig.fxml is not found
     */
    public void configurePlayerInformation() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PlayerConfig.fxml"));
        Parent root = loader.load();
        ConfigurationController controller = loader.getController();
        controller.setStage(stage);
        controller.updatePlayerLabel();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        currentPlayer++;
    }

    /**
     * Changes the current player label to
     * reflect the current player.
     */
    public void updatePlayerLabel() {
        playerNumber.setText("Player " + currentPlayer);
    }

    /**
     * Runs when player presses "OK" on player configuration screen.
     * Saves the player's configuration settings.
     * Opens another instance of player configuration screen if there
     * are more players to be configured.
     * Otherwise, opens map.
     * @throws IOException if either PlayerConfig.fxml or Map.fxml are not found
     */
    public void savePlayerConfig() throws IOException {

        //TODO: Make more efficient by just resetting fields and changing the label instead of creating new controller and opening new scene
        String name = playerName.getText();
        String race = (String) playerRace.getValue();
        Color color = playerColor.getValue();

        System.out.println("Name: " + name + "\nRace: " + race + "\nColor: " + color);
        //TODO: Save player configuration information

        if (currentPlayer <= numPlayers) {
            configurePlayerInformation();
        } else {
            currentPlayer = 1;
            // Go to Map screen.
            // Note that the map gets a MapController and not a ConfigurationController or a MainController
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Map.fxml"));
            Parent root = loader.load();
            MapController controller = loader.getController();
            controller.setStage(stage);
            controller.setNumPlayers(numPlayers);
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }

    }

    /**
     * Sets this controller instance's stage
     * @param stage The stage to be set to the controller
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
