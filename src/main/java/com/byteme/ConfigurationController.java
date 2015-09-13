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

    // Game Configuration
    @FXML
    private RadioButton difficultyEasy, difficultyNormal, difficultyHard;
    @FXML
    private Slider numPlayersSlider;
    @FXML
    private ChoiceBox mapType;

    public void saveGameConfig() throws IOException {
        this.difficulty = selectedDifficultyButton().getId();
        this.numPlayers = (int) numPlayersSlider.getValue();
        this.map = (String) mapType.getValue();
        System.out.println("Difficulty: " + difficulty + "\nNumber Players: " + numPlayers + "\nMap: " + map);
        //TODO: Save difficulty, map, and numPlayer information

        configurePlayerInformation();
    }

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

    // Player Configuration
    @FXML
    private TextField playerName;
    @FXML
    private ChoiceBox playerRace;
    @FXML
    private ColorPicker playerColor;
    @FXML
    private Label playerNumber;

    public void configurePlayerInformation() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/PlayerConfig.fxml"));
        Parent root = loader.load();
        ConfigurationController controller = loader.getController();
        controller.setStage(stage);
        controller.updatePlayerLabel();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        currentPlayer++;
    }

    public void updatePlayerLabel() {
        playerNumber.setText("Player " + currentPlayer);
    }

    public void savePlayerConfig() throws IOException {

        String name = playerName.getText();
        String race = (String) playerRace.getValue();
        Color color = playerColor.getValue();
        System.out.println("Name: " + name + "\nRace: " + race + "\nColor: " + color);

        //TODO: Save player configuration information
        //TODO: Make other players have options too

        if (currentPlayer <= numPlayers) {
            configurePlayerInformation();
        } else {
            currentPlayer = 1;
            // Go to Map screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Map.fxml"));
            Parent root = loader.load();
            MainController controller = loader.getController();
            controller.setStage(stage);
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
