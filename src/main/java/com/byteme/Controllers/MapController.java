package com.byteme.Controllers;

import com.byteme.Models.ConfigRepository;
import com.byteme.Models.MapBoard;
import com.byteme.Schema.PlayerConfigParams;
import com.byteme.Schema.Property;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import javax.swing.border.Border;
import java.util.ArrayList;
import java.util.Timer;


/**
 * Created by Siddarth on 9/13/2015.
 */
public class MapController implements Initializable {
    private static ConfigRepository configRepository = ConfigRepository.getInstance();

    private int numPlayers;
    private int currentPlayer;

    private int freeTurn = 0;
    private int freeLand = 0;
    private int passNumber;
    private boolean[][] mapSpots;
    public boolean buy = false;
    private int currentPhase;
    // 0 = Land Grant
    private Timer timer;

    @FXML
    private Label playerLabel;
    @FXML
    private Label phase;
    @FXML
    private GridPane map;
    @FXML
    private Button pass;


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
        for (int a = 0; a < numPlayers; a++) {
            ArrayList<Property> y = configRepository.getPlayerConfig(currentPlayer).getProperties();
            for (Property z : y) {
                mapSpots[z.getRow()][z.getColumn()] = true;
            }
        }

        // Force center tile to be Town.png
        // Make the town tile run "goToTown()"
        ImageView townImage = new ImageView("/images/Town.png");
        townImage.setOnMouseClicked((MouseEvent e) -> goToTown());
        map.add(townImage, 4, 2);

        // Setup the Pass button
        pass.setOnMouseClicked((MouseEvent e) -> updatePlayer());

        // Create the player turn timer
        timer = new Timer();

        // Initialize game state for when the map is loaded for the first time
        numPlayers = configRepository.getTotalPlayers();
        currentPlayer = 1;
        playerLabel.setText(String.format("Player %d %s", currentPlayer, configRepository.getPlayerConfig(currentPlayer).getName()));
        phase.setText("Land Grant");
        currentPhase = 0; // Land Grant
    }

    /**
     * Runs when a tile is clicked.
     * Saves tile chosen to player.
     * @param event MouseEvent containing information on what was clicked.
     */
    public void tileChosen(MouseEvent event) {

        // Get the square being clicked
        BorderPane tile = (BorderPane) event.getSource();

        if (currentPhase == 0) {
            // Change tile background color to player color
            System.out.println(map.getRowIndex(tile) + " " + map.getColumnIndex(tile));
            setColorTile(tile);

            //TODO: Save which tile was clicked by which player (currentPlayer is a static variable of this class)
            System.out.println("Player " + currentPlayer + ": " + map.getRowIndex(tile) + ", " + map.getColumnIndex(tile));

            // Update the player label to the next player
            currentPlayer = (currentPlayer + 1 == numPlayers) ? numPlayers : (currentPlayer + 1) % numPlayers;
            playerLabel.setText(String.format("Player %d %s", currentPlayer, configRepository.getPlayerConfig(currentPlayer).getName()));
        }



        if (buy) {
            if (freeLand < numPlayers * 2) {

                //TODO: Save which tile was clicked by which player (currentPlayer is a static variable of this class)
                System.out.println("Player " + currentPlayer + ": " + map.getRowIndex(tile) + ", " + map.getColumnIndex(tile));

                if (setColorTile(tile)) {
                    freeLand++;
                }

                // Update the player label to the next player
                currentPlayer = (currentPlayer + 1 == numPlayers) ? numPlayers : (currentPlayer + 1) % numPlayers;
                playerLabel.setText(String.format("Player %d %s", currentPlayer, configRepository.getPlayerConfig(currentPlayer - 1).getName()));
                passNumber = 0;
            } else { //change boolean
                if (configRepository.getPlayerConfig(currentPlayer - 1).getMoney() >= 300) {
                    if (setColorTile(tile)) {
                        configRepository.getPlayerConfig(currentPlayer).payMoney(300);
                    }
                    int current = configRepository.getPlayerConfig(currentPlayer).getMoney();
                    System.out.println("You now have " + current + " money.");
                } else {
                    System.out.println("You cannot buy! You only have " + configRepository.getPlayerConfig(currentPlayer).getMoney() + " dollars!");
                }
                passNumber = 0;
            }
        }
    }

    /**
     * Runs when player clicks the Town.
     * Changes scene to Town
     */
    public void goToTown() {
        MasterController.getInstance().town();
    }

    /**
     * Updates the player label to next player's name.
     * Increments currentPlayer.
     */
    public void updatePlayer() {
        passNumber++;
        if (passNumber == numPlayers) {
            System.out.println("Selection phase is over!");
            MasterController.getInstance().map();
        }
        currentPlayer = (currentPlayer + 1 == numPlayers) ? numPlayers : (currentPlayer + 1) % numPlayers;
        playerLabel.setText(String.format("Player %d %s", currentPlayer, configRepository.getPlayerConfig(currentPlayer).getName()));

    }

    /**
     * Sets the color of a tile when clicked by a player.
     * Only does so if tile is not already owned.
     * @param tile The tile whose color must be set.
     * @return Whether the tile was set or not
     */
    private boolean setColorTile(BorderPane tile) {
        int row = map.getRowIndex(tile);
        int column = map.getColumnIndex(tile);
        if (!mapSpots[row][column]) {
            String color = configRepository.getPlayerConfig(currentPlayer).getColor();
            tile.setStyle("-fx-border-color: " + color.toLowerCase() + ";" + "-fx-border-width: 2px;");
            mapSpots[row][column] = true;
            return true;
        } else {
            System.out.println("Tile at " + row + " , " + column + " is taken already");
            return false;
        }
    }

}
