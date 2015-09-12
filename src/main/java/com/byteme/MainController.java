package com.byteme;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;

import java.io.IOException;

/**
 * Created by Siddarth on 9/10/2015.
 */
public class MainController {

    public void startGame() throws IOException {
        // Opens save/load game data screen
        setNewScene("fxml/LoadGame.fxml");
    }

    public void loadConfigureScreen() throws IOException {
        // Opens Game Configuration settings
        setNewScene("fxml/GameConfig.fxml");
    }

    public void saveGameConfig() throws IOException {
        String difficulty = selectedDifficultyButton().getId();
        int players = (int) numPlayers.getValue();
        String map = (String) mapType.getValue();
        System.out.println("Difficulty: " + difficulty + "\nNumber Players: " + players + "\nMap: " + map);
        //TODO: Save difficulty information

        setNewScene("fxml/PlayerConfig.fxml");
    }

    public void savePlayerConfig() throws IOException {
        String name = playerName.getText();
        String race = (String) playerRace.getValue();
        Color color = playerColor.getValue();
        System.out.println("Name: " + name + "\nRace: " + race + "\nColor: " + color);

        //TODO: Save player configuration information

        openTemp();
    }

    public void openTemp() throws IOException {
        setNewScene("fxml/placeholder.fxml");
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

    private Stage stage;

    // Form & Game Elements

    // GameConfig
    @FXML
    private RadioButton difficultyEasy, difficultyNormal, difficultyHard;
    @FXML
    private Slider numPlayers;
    @FXML
    private ChoiceBox mapType;

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

    // Player Config
    @FXML
    private TextField playerName;
    @FXML
    private ChoiceBox playerRace;
    @FXML
    private ColorPicker playerColor;
}
