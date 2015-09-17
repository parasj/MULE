package com.byteme.Controllers;

import com.byteme.Models.ConfigRepository;
import com.byteme.Schema.MapBoard;
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

import java.io.IOException;

/**
 * Created by Siddarth on 9/13/2015.
 */
public class MapController implements Initializable {
    private ConfigRepository configRepository = ConfigRepository.getInstance();

    private Stage stage;
    private int numPlayers;
    private static int currentPlayer = 1;

    private MapBoard board;

    @FXML
    private Label playerLabel;
    @FXML
    private GridPane map;

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
                tile.setOnMouseClicked(this::tileChosen); // Run tileChosen() when we click on a tile
                map.add(tile, j, i); // Place the image on the grid
            }
        }

        // Make the town tile run "goToTown()" instead of "tileChosen(e)"
        map.getChildren().get(23).setOnMouseClicked((MouseEvent e) -> goToTown());

        playerLabel.setText(String.format("Player %d - %s", currentPlayer, configRepository.getPlayerConfig(currentPlayer - 1).getName()));
    }

    /**
     * Runs when a certain tile is clicked.
     * Saves tile chosen to player.
     * @param event MouseEvent containing information on what was clicked.
     */
    public void tileChosen(MouseEvent event) {
        // Get the square being clicked
        ImageView tile = (ImageView) event.getSource();

        //TODO: Save which tile was clicked by which player (currentPlayer is a static variable of this class)
        System.out.println("Player " + currentPlayer + ": " + map.getRowIndex(tile) + ", " + map.getColumnIndex(tile));

        // Update the player label to the next player
        currentPlayer = (currentPlayer + 1 == numPlayers) ? numPlayers : (currentPlayer + 1) % numPlayers;
        playerLabel.setText(String.format("Player %d - %s", currentPlayer, configRepository.getPlayerConfig(currentPlayer - 1).getName()));
    }

    /**
     * Runs when player clicks the Town.
     * Changes scene to Town
     */
    public void goToTown() {
        try {
            setNewScene("/fxml/Town.fxml");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Run this whenever we want to show a temporary screen for
     * things that still need to be created
     * @throws IOException if placeholder.fxml is not found
     */
    public void openTemp() throws IOException {
        setNewScene("/fxml/placeholder.fxml");
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

    /**
     * Sets numPlayers (total in this game) in this MapController
     * Needed when controller is created so that methods can use it.
     * @param num Total Number of Players in this game
     */
    public void setNumPlayers(int num) { this.numPlayers = num; }
}
