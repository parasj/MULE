package com.byteme.Controllers;

import com.byteme.Models.ConfigRepository;
import com.byteme.Models.MapBoard;
import com.byteme.Models.MapStateStore;
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
    private static MapStateStore s = MapStateStore.getInstance();

    private Timer timer;
    private boolean[][] mapSpots;
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
    @FXML
    private Label timerLabel;
    //Constructor with all params
    private MapController(Timer timer, boolean[][] mapSpots, int passCounter, int purchaseOpportunities, int numPlayers,
                         int currentPlayer, int currentRound, int currentPhase, int timeLeft) {
        this.timer = timer;
        this.mapSpots = mapSpots;

        s.setPassCounter(passCounter);
        s.setPurchaseOpportunities(purchaseOpportunities);
        s.setNumPlayers(numPlayers);
        s.setCurrentPlayer(currentPlayer);
        s.setCurrentRound(currentRound);
        s.setCurrentPhase(currentPhase);
        s.setTimeLeft(timeLeft);
    }

    //No constructor initializer
    public MapController() {
        this(new Timer(), null, 0, 0, 0, 1, 0, 0, 0);
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
        for (int a = 0; a < s.getNumPlayers(); a++) {
            ArrayList<Property> y = configRepository.getPlayerConfig(s.getCurrentPlayer()).getProperties();
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
        timerLabel.setVisible(true);
        s.setNumPlayers(configRepository.getTotalPlayers());
        s.setCurrentPlayer(1);
        rerenderPlayerText();
        phaseLabel.setText("Land Grant");
        s.setCurrentPhase(1); // Land Grant
        s.setCurrentRound(0);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {//IF PHASE 2
                if (s.getCurrentPhase() == 2) {
                    if (s.getTimeLeft() != 0) {
                        s.setTimeLeft(s.getTimeLeft() - 1);
                    } else {
                        //Go to pub and change phase to 3.
                        goToPub();
                        s.setCurrentPhase(3);
                    }
                }
            }
        }, 0, 1000);
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

        if (s.getCurrentPhase() == 1) {
            // LAND GRANT

            if (!owned(tile)) {
                // Change tile background color to player color
                setColorTile(tile);

                //TODO: Save which tile was clicked by which player (currentPlayer is a static variable of this class)
                System.out.println("Player " + s.getCurrentPlayer() + ": " + map.getRowIndex(tile) + ", " + map.getColumnIndex(tile));

                if (s.getCurrentPlayer() >= s.getNumPlayers()) {
                    s.setCurrentRound(s.getCurrentRound() + 1);
                }

                // Land Grant is only 2 turns per player.
                if (s.getCurrentRound() >= 3) {
                    phaseLabel.setText("Property Selection");
                    s.setCurrentPhase(s.getCurrentPhase() + 1);
                    s.setCurrentRound(0);
                }

                // Reset counters and change currentPlayer
                s.setPassCounter(0);
                s.setPurchaseOpportunities(0);
                changePlayer();
            } else {
                // Property is owned, just display warning
                ownedMessage();
            }
        } else if (s.getCurrentPhase() == 2) {
            // PROPERTY PURCHASE/SELECTION

            // Remove cost from player's money
            // TODO: Parse actual cost of each property
            int cost = 300;
            if (cost > ConfigRepository.getInstance().getPlayerConfig(s.getCurrentPlayer()).getMoney()) {
                alertsLabel.setText("This costs more money than you have!");
                alertsLabel.setVisible(true);
            } else {
                if (!owned(tile)) {
                    // Change tile background color to player color
                    setColorTile(tile);

                    ConfigRepository.getInstance().getPlayerConfig(s.getCurrentPlayer()).payMoney(cost);

                    s.setPurchaseOpportunities(s.getPurchaseOpportunities() + 1);
                    s.setPassCounter(0);

                    if (s.getPurchaseOpportunities() >= 43) {
                        s.setCurrentPhase(3);
                        phaseLabel.setText("Selection phase is over!");
                        s.setCurrentRound(s.getCurrentRound() + 1);
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
        if (s.getCurrentPhase() != 1) {
            MasterController.getInstance().town();
        }
    }


    /**
     * Runs when player clicks the Pub.
     * Changes scene to Pub.
     */
    public void goToPub() {
        MasterController.getInstance().pubScene();
    }

    public void pass() {
        alertsLabel.setVisible(false);
        if (s.getCurrentPhase() == 2) {
            s.setPurchaseOpportunities(s.getPurchaseOpportunities() + 1);
        }
        s.setPassCounter(s.getPassCounter() + 1);
        if (s.getPassCounter() >= s.getNumPlayers()) {
            s.setCurrentPhase(3);
            phaseLabel.setText("Selection phase is over!");
            changePlayer(1);
            s.setCurrentRound(s.getCurrentRound() + 1);
        } else {
            changePlayer();
        }
    }

    /**
     * Updates the player label to next player's name.
     * Increments currentPlayer.
     */
    public void changePlayer() {
        changePlayer((s.getCurrentPlayer() + 1 == s.getNumPlayers()) ? s.getNumPlayers() : (s.getCurrentPlayer() + 1) % s.getNumPlayers());
    }

    private void rerenderPlayerText() {
        //System.out.println(this + " " + playerLabel + "  " + moneyLabel);
        if (playerLabel != null) playerLabel.setText(String.format("Player %d %s", s.getCurrentPlayer(), ConfigRepository.getInstance().getPlayerConfig(s.getCurrentPlayer()).getName()));
        if (moneyLabel != null) moneyLabel.setText("MONEY: " + ConfigRepository.getInstance().getPlayerConfig(s.getCurrentPlayer()).getMoney());
    }

    /**
     * Updates the player label to next player's name.
     * Increments currentPlayer.
     * @param playerNumber The number of the player to be set
     */
    public void changePlayer(int playerNumber) {
        // Update current player label and currentPlayer variable
        s.setCurrentPlayer(playerNumber);
        rerenderPlayerText();
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
        String color = configRepository.getPlayerConfig(s.getCurrentPlayer()).getColor();
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

    public void stop() {
        this.timer.cancel();
    }

    public Timer getTimer() {
        return this.timer;
    }

    public int getCurrentPlayer() {
        return s.getCurrentPlayer();
    }

    public void incRound() {
        s.setCurrentRound(s.getCurrentRound() + 1);
    }

    public int getCurrentRound() {
        return s.getCurrentRound();
    }
     public int getTimeLeft() {
         return s.getTimeLeft();
     }

    public void setTimeLeft(int timeLeft) {
        s.setTimeLeft(timeLeft);
    }

    public void rerender() {
        rerenderPlayerText();
        timerLabel.setText("Time Left: " + s.getTimeLeft());
    }
}
