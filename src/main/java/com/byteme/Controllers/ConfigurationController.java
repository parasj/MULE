package com.byteme.Controllers;

import com.byteme.Models.*;
import com.byteme.Schema.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Locale;

/**
 * MULE
 */
public class ConfigurationController {
<<<<<<< HEAD
    /**
     * FLAPPERNUM of type int.
     */
    private static final int FLAPPERNUM = 1600;
    /**
     * HUMANNUM of type int.
     */
    private static final int HUMANNUM = 600;
    /**
     * DEFAULTNUM of type int.
     */
    private static final int DEFAULTNUM = 1000;
    /**
     * numPlayers of type int.
     */
=======
>>>>>>> origin/master
    private static int numPlayers = -1;
    private int currentPlayer = 1;

    /**
     * Runs when player clicks on the screen in the Start screen.
     * Opens the screen asking player whether they want to
     * open an old game file or create a new one.
     */
    public void startGame() {
        MasterController.getInstance().loadGame();
    }

    /**
     * Load a pre-configured game and player settings file
     */
    public void loadGameConfiguration() {
        System.out.println("Loading Game!");
        MULEStore muleStore = MULEStore.getInstance();
        try {
            muleStore.load();
            MasterController masterController = MasterController.getInstance();
            masterController.createMap(); // The initializable function here changes it all
            masterController.getBoardController().updateState(muleStore.getMapStateStore().getCurrentState(), true);
            masterController.getBoardController().render();
            masterController.getBoardController().reinitialize();
            MasterController.getInstance().map();
        } catch (Exception exception) {
            System.out.print(exception);
        }
    }

    //Goes to configuration screen

    /**
     *
     */
    public void newGameConfigureScreen() {
        MasterController.getInstance().gameConfig();
    }

    /*
        * GAME CONFIGURATION SCREEN VARIABLES & METHODS
        *
        * The following code saves the Game Configuration settings.
        * This includes Difficulty, Number of Players, and the Map Type
     */

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
        Difficulty difficulty = selectedDifficultyButton();
        // TODO: parse map info
        MapType map = selectedMapType();
        int numPlayers = (int) numPlayersSlider.getValue();

        MULEStore.getInstance().reinit();

        System.out.println("========================================================================================================");
        System.out.println("SAVING GAME CONFIGURATION INFORMATION");
        System.out.println("========================================================================================================");
        System.out.println("DIFFICULTY        : " + difficulty);
        System.out.println("NUMBER OF PLAYERS : " + numPlayers);
        System.out.println("MAP               : " + map + "\t" + mapType.getValue());
        getConfigRepository().setGameConfig(new GameConfigParams(difficulty, map, numPlayers));
        ConfigurationController.numPlayers = numPlayers;
        MasterController.getInstance().playerConfig();
    }

    /**
     * Parse MapType
     * @return The selected MapType
     */
    private MapType selectedMapType() {
        // TODO: Give different maps based on player input
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
            // Default to standard difficulty if there's some error
            return Difficulty.STANDARD;
        }
    }

    /*
        * PLAYER CONFIGURATION SCREEN VARIABLES & METHODS
        *
        * The following code saves each player's configuration settings.
        * This includes player name, race, color, and starting money.
    */

    @FXML
    private TextField playerName;
    @FXML
    private ChoiceBox<String> playerRace;
    @FXML
    private ChoiceBox<String> playerColor;
    @FXML
    private Label playerNumber;

    /**
     * Runs when player presses "OK" on player configuration screen.
     * Saves player's configuration settings.
     * If more players, resets values.
     * After all players done, opens map.
     */
    public void savePlayerConfig() {
        // TODO: Fix Default player name for testing
        // Doesn't work, changes config repository despite input and doesn't show up on screen
        // playerName.setText("Bob " + currentPlayer);
        if (!playerName.getText().isEmpty()) {
            String name;
            String race;
            String color;
            int money;

            // Parse player's information
            name = playerName.getText();
            race = playerRace.getValue();
            color = playerColor.getValue();
            money = chooseMoneyAmount(race);

            // Remove color already chosen by another player
            ObservableList<String> remainingChoices = playerColor.getItems();
            remainingChoices.remove(color);
            playerColor.setItems(remainingChoices);

            System.out.println("========================================================================================================");
            System.out.println("SAVING PLAYER CONFIGURATION INFORMATION");
            System.out.println("========================================================================================================");
            System.out.println("NAME       : " + name);
            System.out.println("RACE       : " + race);
            System.out.println("COLOR      : " + color);
            getConfigRepository().setPlayerConfig(playerConfigParser(name, race, color, money, currentPlayer), currentPlayer);

            if (currentPlayer >= numPlayers) {
                // Go to Map screen.
                MULEStore.getInstance().reinit();
                MasterController.getInstance().createMap();
                MasterController.getInstance().map();
                MULEStore.getInstance().getMapStateStore().refresh();
            } else {
                // Update the player label
                currentPlayer++;
                playerNumber.setText("Player " + currentPlayer);
                // Reset GUI to display default values
                playerName.clear();
                playerRace.getSelectionModel().selectFirst();
                playerColor.getSelectionModel().selectFirst();
            }
        } else {
            playerName.setText("Set a name for your player!");
        }
    }

    /**
     * Chooses the starting money for a player based on his race.
     * @param race The race of the player as parsed by the ChoiceBox
     * @return The starting money for the player
     */
<<<<<<< HEAD
    public final int chooseMoneyAmount(String race) {
=======
    private int chooseMoneyAmount(String race) {
>>>>>>> origin/master
        race = race.toLowerCase();

        switch (race) {
            case "flapper":
                return FLAPPERNUM;
            case "human":
                return HUMANNUM;
            default:
                return DEFAULTNUM;
        }
    }

    /**
     * Creates a player configuration based on the player's options
     * @param name The name of the player
     * @param race The race of the player
     * @param color The color of the player
     * @param money The starting money of the player
     * @return A PlayerConfigParams object containing this player's information
     */
    private PlayerConfigParams playerConfigParser(String name, String race, String color, int money, int order) {
        Race parsedRace = Race.valueOf(race.toUpperCase(Locale.ENGLISH));
        return new PlayerConfigParams(name, parsedRace, color, money, new ArrayList<>(), order);
    }

    //Returns config repository where everything is stored

    /**
     *
     * @return
     */
    public ConfigRepository getConfigRepository() {
        ConfigRepository configRepository = MULEStore.getInstance().getConfigRepository();
        if (configRepository == null) {
            throw new IllegalStateException("ConfigRepository is not initialized!");
        }
        return configRepository;
    }
}
