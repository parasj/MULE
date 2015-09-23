package com.byteme.Controllers;

import com.byteme.Models.*;
import com.byteme.Schema.*;
// import com.sun.javaws.exceptions.InvalidArgumentException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * Created by Siddarth on 9/13/2015.
 */
public class ConfigurationController extends Controller{

    private ConfigRepository configRepository = ConfigRepository.getInstance();
    private final static Logger log = Logger.getLogger(ConfigurationController.class.getName());

    private static int currentPlayer = 1;
    private static int numPlayers;
    private static Difficulty difficulty;
    private static MapType map;

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
        difficulty = selectedDifficultyButton();
        numPlayers = (int) numPlayersSlider.getValue();
        map = selectedMapType();

        log.info("Difficulty " + difficulty + ", Number Players " + numPlayers + ", Map " + map + "\t" + mapType.getValue());
        configRepository.setGameConfig(new GameConfigParams(difficulty, map, numPlayers));

        configurePlayerInformation();
    }

    private MapType selectedMapType() {
        switch ((String) mapType.getValue()) {
            default: return MapType.STANDARD;
        }
    }

    /**
     * Used in saveGameConfig to tell which radio button
     * is selected for game difficulty.
     * @return The selected difficulty level RadioButton
     */
    private Difficulty selectedDifficultyButton() {
        if (difficultyEasy.isSelected()) {
            return Difficulty.BEGINNER;
        } else if (difficultyNormal.isSelected()) {
            return Difficulty.STANDARD;
        } else if (difficultyHard.isSelected()) {
            return Difficulty.TOURNAMENT;
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
    private ChoiceBox playerColor;
    @FXML
    private Label playerNumber;
    @FXML
    private Button submitPlayer;

    /**
     * Runs when a new player's settings need to be configured.
     * Opens the player configuration screen and saves their data.
     * Increments currentPlayer
     * @throws IOException if PlayerConfig.fxml is not found
     */
    private void configurePlayerInformation() throws IOException {
        setNewPlayerConfigScene("/fxml/PlayerConfig.fxml");
    }

    /**
     * Custom setNewScene - needed to change the player label
     * @param fxmlFile The FXML containing the PlayerConfig screen
     * @throws IOException if PlayerConfig.fxml is not found
     */
    private void setNewPlayerConfigScene(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        ConfigurationController controller = loader.getController();
        controller.setStage(stage);
        controller.updatePlayerLabel();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Changes the current player label to
     * reflect the current player.
     */
    private void updatePlayerLabel() {
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
        String color;
        String playerChoice = (String) playerColor.getValue();
        for (int i = 0; i < configRepository.getTotalPlayers(); i++) {
            if (configRepository.getPlayerConfig(i).getColor().equals(playerChoice)) {
                submitPlayer.setDisable(true);
            }
        }
        if (playerChoice.equals("Red")) {
            color = "red";
        } else if (playerChoice.equals("Blue")) {
            color = "blue";
        } else if (playerChoice.equals("Green")) {
            color = "green";
        } else if (playerChoice.equals("Yellow")) {
            color = "yellow";
        } else if (playerChoice.equals("Purple")) {
            color = "purple";
        } else {
            color = null;
        }

        int money = 400; //change depending on race

        log.info("Name: " + name + "\nRace: " + race + "\nColor: " + color);
        configRepository.setPlayerConfig(playerConfigParser(name, race, color, money), currentPlayer - 1);

        currentPlayer++;
        if (currentPlayer <= numPlayers) {
            configurePlayerInformation();
        } else {
            currentPlayer = 1;
            // TODO: Create save dialog box for the player to save configuration options
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

    private PlayerConfigParams playerConfigParser(String name, String race, String color, int money) {
        Race parsedRace = Race.valueOf(race.toUpperCase(Locale.ENGLISH));
        return new PlayerConfigParams(name, parsedRace, color, money);
    }
}
