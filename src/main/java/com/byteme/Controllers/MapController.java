package com.byteme.Controllers;

import com.byteme.Config.ConfigRepository;
import com.byteme.Controllers.MainController;
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
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;

import java.io.IOException;

/**
 * Created by Siddarth on 9/13/2015.
 */
public class MapController implements Initializable {
    private ConfigRepository configRepository = ConfigRepository.getInstance();

    private Stage stage;
    private int numPlayers;
    private int currentPlayer = 1;
    private int freeLand = 0;
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

        String P = "/images/Plain.png";
        String M1 = "/images/Mountain.png";
        String M2 = "/images/Mountain.png";
        String M3 = "/images/Mountain.png";
        String R = "/images/River.png";
        String Town = "/images/Town.png";
        String White = "/images/white.png";

        String[][] standardMap = {{P,P,M1,P,R,P,M3,P,P},
                                    {P,M1,P,P,R,P,P,P,M3},
                                    {M3,P,P,P,Town,P,P,P,M1},
                                    {P,M2,P,P,R,P,M2,P,P},
                                    {P,P,M2,P,R,P,P,P,M2}};

        for (int i = 0; i < standardMap.length; i++) {
            for (int j = 0; j < standardMap[i].length; j++) {
                ImageView tile = new ImageView(standardMap[i][j]);
                tile.setOnMouseClicked((MouseEvent e) -> tileChosen(e)); // Run tileChosen() when we click on a tile
                map.add(tile, j, i); // Place the image on the grid
            }
        }

        // Make the town tile run "goToTown()" instead of "tileChosen(e)"
        map.getChildren().get(23).setOnMouseClicked((MouseEvent e) -> goToTown());

    }

    /**
     * Runs when a certain tile is clicked.
     * Saves tile chosen to player.
     * @param event MouseEvent containing information on what was clicked.
     */
    public void tileChosen(MouseEvent event) {

        // Get the square being clicked
        if (freeLand < numPlayers * 2) {
            freeLand++;
            ImageView tile = (ImageView) event.getSource();

            //TODO: Save which tile was clicked by which player (currentPlayer is a static variable of this class)
            System.out.println("Player " + currentPlayer + ": " + map.getRowIndex(tile) + ", " + map.getColumnIndex(tile));

            setColorTile(configRepository.getPlayerConfig(currentPlayer).getColor(), tile);

            // Update the player label to the next player
            currentPlayer = (currentPlayer + 1 == numPlayers) ? numPlayers : (currentPlayer + 1) % numPlayers;
            playerLabel.setText(String.format("Player %d: %s", currentPlayer, configRepository.getPlayerConfig(currentPlayer - 1).getName()));
        } else {
            System.out.println("You can't click this!");
        }

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

    public void setColorTile(Color color, ImageView imageView) {
        //ColorAdjust adjust = new ColorAdjust(color.getHue(),color.getSaturation(),color.getBrightness(), 0.5);
        DropShadow ds = new DropShadow( 70, 0, 0, color );
        imageView.setEffect(ds);
    }
}
