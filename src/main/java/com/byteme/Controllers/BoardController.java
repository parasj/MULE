package com.byteme.Controllers;

import com.byteme.Models.ConfigRepository;
import com.byteme.Models.MapBoard;
import com.byteme.Models.MapStateStore;
import com.byteme.Schema.MapControllerStates;
import com.byteme.Schema.PlayerConfigParams;
import com.byteme.Schema.Property;
import com.byteme.Util.CanTick;
import com.byteme.Util.GlobalTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by parasjain on 10/2/15.
 */
public class BoardController implements Initializable, CanTick {
    private final static ConfigRepository configRepository = ConfigRepository.getInstance();
    private final static MapStateStore s = MapStateStore.getInstance();
    private final static GlobalTimer timer = GlobalTimer.getInstance();

    private MapBoard possibleMaps;
    private boolean[][] mapSpots;

    private MapControllerStates state;
    private MapStateHandler childController;

    /*** FXML UI items ***/
    @FXML
    private Label playerLabel;
    @FXML
    private Label moneyLabel;
    @FXML
    private Label phaseLabel;
    @FXML
    private Label alertsLabel;
    @FXML
    private Label roundLabel;
    @FXML
    private GridPane map;
    @FXML
    private Label timerLabel;

    // implementations of MapStateHandler
    // ...
    // TODO - fill this in



    public BoardController() {
        updateState(MapControllerStates.START);
        timer.setTickHandler(this);
    }


    /**** Initialize ****/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initBoard();
        initRiver();
        initBoardCleanup();
        updateState(MapControllerStates.LAND_GRANT);
    }

    private void initBoard() {
        possibleMaps = new MapBoard();
        mapSpots = new boolean[possibleMaps.getHeight()][possibleMaps.getWidth()];

        // inject images
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
        configRepository.getPlayers().forEach((player) ->
                player.getProperties().forEach((z) ->
                        mapSpots[z.getRow()][z.getColumn()] = true));
    }

    private void initRiver() {
        // Force center tile to be Town.png and center line to be river
        // Make the town tile run "goToTown()"
        ImageView townImage = new ImageView("/images/Town.png");
        townImage.setOnMouseClicked((MouseEvent e) -> townButtonClicked());
        map.add(townImage, 4, 2);
        // TODO: hardcode the river
    }

    private void initBoardCleanup() {
        alertsLabel.setVisible(false);
        timerLabel.setVisible(true);
    }


    /**** Timer ****/
    @Override
    public void tick() {
        log("Tick");
        childController.tick();
    }


    /**** Data Binding ****/
    public void updateState(MapControllerStates newState) {
        state = newState;
        // TODO - switch controller as needed
        // if (state = MapControllerStates.GAME_START)

        childController.stateChanged();
    }


    /**** UI Events ****/
    public void passButtonClicked() {
        log("Pass button clicked");
        childController.handlePass();
    }

    public void tileChosen(MouseEvent event) {
        log("Tile chosen");
        childController.tileChosen(event);
    }

    private void townButtonClicked() {
        log("Town button clicked");
        childController.handleTownButtonClicked();
    }


    /**** UI Elements ****/
    public Label getPlayerLabel() {
        return playerLabel;
    }

    public Label getMoneyLabel() {
        return moneyLabel;
    }

    public Label getPhaseLabel() {
        return phaseLabel;
    }

    public Label getAlertsLabel() {
        return alertsLabel;
    }

    public Label getRoundLabel() {
        return roundLabel;
    }

    public GridPane getMap() {
        return map;
    }

    public Label getTimerLabel() {
        return timerLabel;
    }

    public void setPlayer(PlayerConfigParams player) {
        playerLabel.setText(String.format("Player %d %s", player.getOrder(), player.getName()));
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


    /**** Util functions ****/
    private void log(String s) {
        System.out.println(s);
    }
}
