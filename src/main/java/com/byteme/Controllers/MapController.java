package com.byteme.Controllers;

import com.byteme.Models.ConfigRepository;
import com.byteme.Models.MapBoard;
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
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;

import java.io.IOException;

/**
 * Created by Siddarth on 9/13/2015.
 */
public class MapController extends Controller implements Initializable {
    private ConfigRepository configRepository = ConfigRepository.getInstance();

    private MapBoard board;

    private int numPlayers;
<<<<<<< HEAD
    private static int currentPlayer = 1;
    private int freeTurn = 0;
=======
    private int currentPlayer = 1;
    private int freeLand = 0;
    private int passNumber;
    public static boolean buy = false;
>>>>>>> dkim630m4
    @FXML
    private Label playerLabel;
    @FXML
    private GridPane map;
    @FXML
    private Button pass;

    /**
     * Runs right before the map screen is shown.
     * We create the map according to the map configuration
     * and give the tiles certain properties.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        board = new MapBoard();

        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                ImageView tile = new ImageView(board.getTile(i, j).imagePath());
                tile.setOnMouseClicked(this::tileChosen);
                map.add(tile, j, i); // Place the image on the grid
            }
        }

        // Force center tile to be Town.png
        // Make the town tile run "goToTown()"
        ImageView townImage = new ImageView("/images/Town.png");
        townImage.setOnMouseClicked((MouseEvent e) -> goToTown());
        map.add(townImage, 4, 2);

        playerLabel.setText(String.format("Player %d - %s", currentPlayer, configRepository.getPlayerConfig(currentPlayer - 1).getName()));

        pass.setOnMouseClicked((MouseEvent e) -> {
            try {
                updatePlayer();
            } catch (IOException e1) {
                System.out.println(e1);
            }
        });
    }

    /**
     * Runs when a certain tile is clicked.
     * Saves tile chosen to player.
     * @param event MouseEvent containing information on what was clicked.
     */
    public void tileChosen(MouseEvent event) {
<<<<<<< HEAD
        if (freeTurn < numPlayers*2) {
            freeTurn++;
            // Get the square being clicked
=======
        // Get the square being clicked
        if (freeLand < numPlayers * 2) {
            freeLand++;
>>>>>>> dkim630m4
            ImageView tile = (ImageView) event.getSource();

            //TODO: Save which tile was clicked by which player (currentPlayer is a static variable of this class)
            System.out.println("Player " + currentPlayer + ": " + map.getRowIndex(tile) + ", " + map.getColumnIndex(tile));

            setColorTile(configRepository.getPlayerConfig(currentPlayer).getColor(), tile);

            // Update the player label to the next player
            currentPlayer = (currentPlayer + 1 == numPlayers) ? numPlayers : (currentPlayer + 1) % numPlayers;
            playerLabel.setText(String.format("Player %d: %s", currentPlayer, configRepository.getPlayerConfig(currentPlayer - 1).getName()));
            passNumber = 0;
        } else if (buy) { //change boolean
            if (configRepository.getPlayerConfig(currentPlayer).getMoney() >= 300) {
                configRepository.getPlayerConfig(currentPlayer).payMoney(300);
                int current = configRepository.getPlayerConfig(currentPlayer).getMoney();
                System.out.println("You now have " + current + " money.");
            } else {
                System.out.println("You cannot buy! You only have " + configRepository.getPlayerConfig(currentPlayer).getMoney() + " dollars!");
            }
            passNumber = 0;
        } else {
<<<<<<< HEAD
            System.out.println("No more Free turns.");
=======
            System.out.println("You can't click this!");
            passNumber = 0;
>>>>>>> dkim630m4
        }
    }

    /**
     * Runs when player clicks the Town.
     * Changes scene to Town
     */
    public void goToTown() {
        try {
            freeLand = numPlayers * 2;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Town.fxml"));
            Parent root = loader.load();
            TownController controller = loader.getController();
            controller.setStage(stage);
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void updatePlayer() throws IOException {
        passNumber++;
        if (passNumber == numPlayers) {
            System.out.println("Selection phase is over!");
            setNewScene("/fxml/placeholder.fxml");
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

    public void setColorTile(Color color, ImageView imageView) {
        //ColorAdjust adjust = new ColorAdjust(color.getHue(),color.getSaturation(),color.getBrightness(), 0.5);
        DropShadow ds = new DropShadow( 70, 0, 0, color );
        imageView.setEffect(ds);
    }
}
