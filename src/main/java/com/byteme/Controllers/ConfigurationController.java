package com.byteme.Controllers;

import com.byteme.Models.*;
import com.byteme.Schema.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * Created by Siddarth on 9/13/2015.
 */
public class ConfigurationController {

    private ConfigRepository configRepository = ConfigRepository.getInstance();
    private final static Logger log = Logger.getLogger(ConfigurationController.class.getName());

    private int currentPlayer = 1;
    private int numPlayers;
    private Difficulty difficulty;
    private MapType map;

    public void loadGameConfiguration() {
        // TODO: Open a previously existing game configuration
        MasterController.getInstance().temp();
    }

    public void newGameConfigureScreen() {
        MasterController.getInstance().gameConfig();
    }

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
     */
    public void saveGameConfig() {
        difficulty = selectedDifficultyButton();
        numPlayers = (int) numPlayersSlider.getValue();
        map = selectedMapType();

        log.info("Difficulty " + difficulty + ", Number Players " + numPlayers + ", Map " + map + "\t" + mapType.getValue());
        configRepository.setGameConfig(new GameConfigParams(difficulty, map, numPlayers));

        MasterController.getInstance().setNumPlayers(numPlayers);
        MasterController.getInstance().playerConfig();
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

    /**
     * Runs when player presses "OK" on player configuration screen.
     * Saves player's configuration settings.
     * If more players, resets values.
     * After all players done, opens map.
     */
    public void savePlayerConfig() {
        String name;
        String race;
        String color;
        int money;
        if (!playerName.getText().equals("")) {
            if (currentPlayer < numPlayers) {

                // Parse player's information
                name = playerName.getText();
                race = (String) playerRace.getValue();
                color = (String) playerColor.getValue();
                money = 400; //TODO: change depending on race

                // Remove color already chosen by another player
                ObservableList<String> remainingChoices = playerColor.getItems();
                remainingChoices.remove(color);
                playerColor.setItems(remainingChoices);

                log.info("Name: " + name + "\nRace: " + race + "\nColor: " + color);
                configRepository.setPlayerConfig(playerConfigParser(name, race, color, money), currentPlayer - 1);

                // Update the player label
                currentPlayer++;
                playerNumber.setText("Player " + currentPlayer);
                // Reset GUI to display default values
                playerName.clear();
                playerRace.getSelectionModel().selectFirst();
                playerColor.getSelectionModel().selectFirst();
            } else {
                // TODO: Create save dialog box for the player to save configuration options
                // Go to Map screen.
                MasterController.getInstance().setNumPlayers(numPlayers);
                MasterController.getInstance().createMap();
                MasterController.getInstance().map();
            }
        } else {
            System.out.println("Set a name for your player!");
        }
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    private PlayerConfigParams playerConfigParser(String name, String race, String color, int money) {
        Race parsedRace = Race.valueOf(race.toUpperCase(Locale.ENGLISH));
        return new PlayerConfigParams(name, parsedRace, color, money, null);
    }
}
