package com.byteme.Controllers;

import com.byteme.Models.ConfigRepository;
import com.byteme.Models.MapBoard;
import com.byteme.Schema.PlayerConfigParams;
import com.byteme.Schema.Property;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;


/**
 * Created by Siddarth on 9/13/2015.
 */
public class MapController implements Initializable {
    private ConfigRepository configRepository = ConfigRepository.getInstance();

    private static int numPlayers;
    private static int freeTurn = 0;
    private static int currentPlayer = 1;
    private int freeLand = 0;
    private int passNumber;
    private boolean[][] mapSpots;
    private Timer timer;
    public static boolean buy = false;

    @FXML
    private Label playerLabel;
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
                tile.setOnMouseClicked(this::tileChosen);
                map.add(tile, j, i); // Place the image on the grid
            }
        }
        for (int a = 0; a < numPlayers; a++) {
            PlayerConfigParams x = configRepository.getPlayerConfig(currentPlayer);
            ArrayList<Property> y = configRepository.getPlayerConfig(currentPlayer).getProperties();
            for (Property z : y) {
                map.add(getImageView(x.getColor()), z.getColumn(), z.getRow());
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

        // Update the player level to Player 1's name
        playerLabel.setText(String.format("Player %d - %s", currentPlayer, configRepository.getPlayerConfig(currentPlayer - 1).getName()));

        // Create the player turn timer
        timer = new Timer();
    }

    /**
     * Runs when a tile is clicked.
     * Saves tile chosen to player.
     * @param event MouseEvent containing information on what was clicked.
     */
    public void tileChosen(MouseEvent event) {
        if (buy) {
            if (freeLand < numPlayers * 2) {

                // Get the square being clicked
                ImageView tile = (ImageView) event.getSource();

                //TODO: Save which tile was clicked by which player (currentPlayer is a static variable of this class)
                System.out.println("Player " + currentPlayer + ": " + map.getRowIndex(tile) + ", " + map.getColumnIndex(tile));

                if (setColorTile(configRepository.getPlayerConfig((currentPlayer - 1)% numPlayers).getColor(), map.getRowIndex(tile), map.getColumnIndex(tile))) {
                    freeLand++;
                }

                // Update the player label to the next player
                currentPlayer = (currentPlayer + 1 == numPlayers) ? numPlayers : (currentPlayer + 1) % numPlayers;
                playerLabel.setText(String.format("Player %d: %s", currentPlayer, configRepository.getPlayerConfig(currentPlayer - 1).getName()));
                passNumber = 0;
            } else { //change boolean
                if (configRepository.getPlayerConfig(currentPlayer - 1).getMoney() >= 300) {
                    ImageView tile = (ImageView) event.getSource();
                    if (setColorTile(configRepository.getPlayerConfig((currentPlayer - 1)% numPlayers).getColor(), map.getRowIndex(tile), map.getColumnIndex(tile))) {
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

    public void updatePlayer() {
        passNumber++;
        if (passNumber == numPlayers) {
            System.out.println("Selection phase is over!");
            MasterController.getInstance().map();
        }
        currentPlayer = (currentPlayer + 1 == numPlayers) ? numPlayers : (currentPlayer + 1) % numPlayers;
        playerLabel.setText(String.format("Player %d: %s", currentPlayer, configRepository.getPlayerConfig(currentPlayer - 1).getName()));

    }

    /**
     * Sets numPlayers (total in this game) in this MapController
     * Needed when controller is created so that methods can use it.
     * @param num Total Number of Players in this game
     */
    public void setNumPlayers(int num) { this.numPlayers = num; }

    private boolean setColorTile(String color, int row, int column) {
        ImageView tile = getImageView(color);
        if (!mapSpots[row][column]) {
            map.add(tile, column, row);
            return true;
        } else {
            System.out.println("Tile at " + row + " , " + column + " is taken already");
            return false;
        }
    }

    private ImageView getImageView(String color) {
        ImageView tile;
        switch (color) {
            case "red":
                tile = new ImageView(new Image("/images/red.png"));
                break;
            case "blue":
                tile = new ImageView(new Image("/images/blue.png"));
                break;
            case "green":
                tile = new ImageView(new Image("/images/green.png"));
                break;
            case "yellow":
                tile = new ImageView(new Image("/images/yellow.png"));
                break;
            case "purple":
                tile = new ImageView(new Image("/images/purple.png"));
                break;
            default:
                throw new IllegalArgumentException("No color configured!");
        }
        return tile;
    }
}
