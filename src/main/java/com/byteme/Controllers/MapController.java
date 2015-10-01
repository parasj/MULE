package com.byteme.Controllers;

import com.byteme.Models.ConfigRepository;
import com.byteme.Models.MapBoard;
import com.byteme.Schema.Property;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Siddarth on 9/13/2015.
 */
public class MapController implements Initializable {
    private static ConfigRepository configRepository = ConfigRepository.getInstance();
    private Timer timer;
    private boolean[][] mapSpots;
    private int passCounter; // Used to determine when to stop property selection immediately
    private int purchaseOpportunities;// Used to determine duration of full property selection
    private int numPlayers;
    private int currentPlayer;
    private int currentRound;
    private int currentPhase;
    // 1 = Land Grant
    // 2 = Land Purchase
    // 3 = Selection Phase Over

    @FXML
    private Label playerLabel;
    @FXML
    private Label moneyLabel;
    @FXML
    private Label phaseLabel;
    @FXML
    private Label alertsLabel;
    @FXML
    private GridPane map;

    //Constructor with all params
    public MapController(Timer timer, boolean[][] mapSpots, int passCounter, int purchaseOpportunities, int numPlayers,
                         int currentPlayer, int currentRound, int currentPhase) {
        this.timer = timer;
        this.mapSpots = mapSpots;
        this.passCounter = passCounter;
        this.purchaseOpportunities = purchaseOpportunities;
        this.numPlayers = numPlayers;
        this.currentPlayer = currentPlayer;
        this.currentRound = currentRound;
        this.currentPhase = currentPhase;
    }

    //No constructor initializer
    public MapController() {
        this(new Timer(), null, 0, 0, 0, 1, 0, 0);
    }

    /**
     * Runs right before the map screen is shown for the first time.
     * We create the map according to the map configuration
     * and give the tiles certain onClick properties.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
//        configRepository = ConfigRepository.getInstance();
//        timer = MasterController.getMapInstance().timer;
//        mapSpots = MasterController.getMapInstance().mapSpots;
//        passCounter = MasterController.getMapInstance().passCounter; // Used to determine when to stop property selection immediately
//        purchaseOpportunities = MasterController.getMapInstance().purchaseOpportunities; // Used to determine duration of full property selection
//        numPlayers = MasterController.getMapInstance().numPlayers;
//        currentPlayer = MasterController.getMapInstance().currentPlayer;
//        currentRound = MasterController.getMapInstance().currentRound;
//        currentPhase = MasterController.getMapInstance().currentPhase;

        // Create the map
        MapBoard possibleMaps = new MapBoard();
        mapSpots = new boolean[possibleMaps.getHeight()][possibleMaps.getWidth()];

        for (int i = 0; i < possibleMaps.getHeight(); i++) {
            for (int j = 0; j < possibleMaps.getWidth(); j++) {
                ImageView tile = new ImageView(possibleMaps.getTile(i, j).imagePath());
                BorderPane tileContainer = new BorderPane();
                tileContainer.setCenter(tile);
                tileContainer.setOnMouseClicked(this::tileChosen);
                map.add(tileContainer, j, i); // Place the image on the grid
            }
        }

        // Keep track of which tiles have a player's color on them
        for (int a = 0; a < numPlayers; a++) {
            ArrayList<Property> y = configRepository.getPlayerConfig(currentPlayer).getProperties();
            for (Property z : y) {
                mapSpots[z.getRow()][z.getColumn()] = true;
            }
        }

        // Force center tile to be Town.png and center line to be river
        // Make the town tile run "goToTown()"
        ImageView townImage = new ImageView("/images/Town.png");
        townImage.setOnMouseClicked((MouseEvent e) -> goToTown());
        map.add(townImage, 4, 2);
        // TODO: hardcode the river

        // Initialize game state for when the map is loaded for the first time
        timer = new Timer();
        alertsLabel.setVisible(false);
        numPlayers = configRepository.getTotalPlayers();
        currentPlayer = 1;
        playerLabel.setText(String.format("Player %d %s", currentPlayer, configRepository.getPlayerConfig(currentPlayer).getName()));
        moneyLabel.setText("MONEY: " + ConfigRepository.getInstance().getPlayerConfig(currentPlayer).getMoney());
        phaseLabel.setText("Land Grant");
        currentPhase = 1; // Land Grant
        currentRound = 1;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
//                if (MasterController.getInstance().getCurrStage().equals("Map")) {
//                    currentPlayer = MasterController.getMapInstance().currentPlayer;
//                    playerLabel.setText(String.format("Player %d %s", currentPlayer, configRepository.getPlayerConfig(currentPlayer).getName()));
//                    moneyLabel.setText("MONEY: " + ConfigRepository.getInstance().getPlayerConfig(currentPlayer).getMoney());
//                }
            }
        }, 0, 5000);
    }

    /**
     * Runs when a tile is clicked.
     * Saves tile chosen to player.
     * @param event MouseEvent containing information on what was clicked.
     */
    public void tileChosen(MouseEvent event) {

        alertsLabel.setVisible(false);
        // Get the square being clicked
        BorderPane tile = (BorderPane) event.getSource();

        if (currentPhase == 1) {
            // LAND GRANT

            if (!owned(tile)) {
                // Change tile background color to player color
                setColorTile(tile);

                //TODO: Save which tile was clicked by which player (currentPlayer is a static variable of this class)
                System.out.println("Player " + currentPlayer + ": " + map.getRowIndex(tile) + ", " + map.getColumnIndex(tile));

                if (currentPlayer >= numPlayers) {
                    currentRound++;
                }

                // Land Grant is only 2 turns per player.
                if (currentRound >= 3) {
                    phaseLabel.setText("Property Selection");
                    currentPhase++;
                    currentRound = 0;
                }

                // Reset counters and change currentPlayer
                passCounter = 0;
                purchaseOpportunities = 0;
                changePlayer();
            } else {
                // Property is owned, just display warning
                ownedMessage();
            }
        } else if (currentPhase == 2) {
            // PROPERTY PURCHASE/SELECTION

            // Remove cost from player's money
            // TODO: Parse actual cost of each property
            int cost = 300;
            if (cost > ConfigRepository.getInstance().getPlayerConfig(currentPlayer).getMoney()) {
                alertsLabel.setText("This costs more money than you have!");
                alertsLabel.setVisible(true);
            } else {
                if (!owned(tile)) {
                    // Change tile background color to player color
                    setColorTile(tile);

                    ConfigRepository.getInstance().getPlayerConfig(currentPlayer).payMoney(cost);

                    purchaseOpportunities++;
                    passCounter = 0;

                    if (purchaseOpportunities >= 43) {
                        currentPhase = 3;
                        phaseLabel.setText("Selection phase is over!");
                        changePlayer(1);
                    } else {
                        changePlayer();
                    }
                } else {
                    // Property is owned, just display warning
                    ownedMessage();
                }

            }
        }
    }

    /**
     * Runs when player clicks the Town.
     * Changes scene to Town
     */
    public void goToTown() {
        //Changes the Map instance
        MasterController.getInstance().setMapInstance(new MapController(timer, mapSpots, passCounter,
                purchaseOpportunities, numPlayers, currentPlayer, currentRound, currentPhase));
        MasterController.getInstance().town();
    }


    public void pass() {
        alertsLabel.setVisible(false);
        if (currentPhase == 2) {
            purchaseOpportunities++;
        }
        passCounter++;
        if (passCounter >= numPlayers) {
            currentPhase = 3;
            phaseLabel.setText("Selection phase is over!");
            changePlayer(1);
        } else {
            changePlayer();
        }
    }

    /**
     * Updates the player label to next player's name.
     * Increments currentPlayer.
     */
    public void changePlayer() {
        // Update current player label and currentPlayer variable
        currentPlayer = (currentPlayer + 1 == numPlayers) ? numPlayers : (currentPlayer + 1) % numPlayers;
        playerLabel.setText(String.format("Player %d %s", currentPlayer, configRepository.getPlayerConfig(currentPlayer).getName()));
        moneyLabel.setText("MONEY: " + ConfigRepository.getInstance().getPlayerConfig(currentPlayer).getMoney());
    }

    /**
     * Updates the player label to next player's name.
     * Increments currentPlayer.
     * @param playerNumber The number of the player to be set
     */
    public void changePlayer(int playerNumber) {
        // Update current player label and currentPlayer variable
        currentPlayer = playerNumber;
        playerLabel.setText(String.format("Player %d %s", currentPlayer, configRepository.getPlayerConfig(currentPlayer).getName()));
        moneyLabel.setText("MONEY: " + ConfigRepository.getInstance().getPlayerConfig(currentPlayer).getMoney());
    }

    /**
     * Sets the color of a tile when clicked by a player.
     * Only does so if tile is not already owned.
     * @param tile The tile whose color must be set.
     * @return Whether the tile was set or not
     */
    private void setColorTile(BorderPane tile) {
        int row = map.getRowIndex(tile);
        int column = map.getColumnIndex(tile);
        String color = configRepository.getPlayerConfig(currentPlayer).getColor();
        tile.setStyle("-fx-border-color: " + color.toLowerCase() + ";" + "-fx-border-width: 6px;");
        mapSpots[row][column] = true;

    }

    private boolean owned(BorderPane tile) {
        int row = map.getRowIndex(tile);
        int column = map.getColumnIndex(tile);
        return mapSpots[row][column];
    }

    private void ownedMessage() {
        alertsLabel.setText("This property is already owned!");
        alertsLabel.setVisible(true);
    }
    //For use in Pub Method
    public void nextPlayer() {
        //TODO reassign in the code when updating label
        MasterController.getMapInstance().currentPlayer = (this.currentPlayer + 1 == this.numPlayers) ? this.numPlayers : (this.currentPlayer + 1)
                % this.numPlayers;
    }

    public void stop() {
        this.timer.cancel();
    }

    public Timer getTimer() {
        return this.timer;
    }


}
