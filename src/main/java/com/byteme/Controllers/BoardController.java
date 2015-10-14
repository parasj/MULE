package com.byteme.Controllers;

import com.byteme.Models.ConfigRepository;
import com.byteme.Models.MapBoard;
import com.byteme.Schema.MapControllerStates;
import com.byteme.Schema.PlayerConfigParams;
import com.byteme.Schema.Property;
import com.byteme.Util.CanTick;
import com.byteme.Util.GlobalTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javax.swing.border.Border;
import java.net.URL;
import java.util.ResourceBundle;

import static com.byteme.Schema.MapControllerStates.*;


public class BoardController implements Initializable, CanTick {
    private final static ConfigRepository configRepository = ConfigRepository.getInstance();
    private final static GlobalTimer timer = GlobalTimer.getInstance();
    public final static int cost = 300;

    private MapBoard possibleMaps;
    private boolean[][] mapSpots;
    private boolean[][] mules;

    private MapControllerStates state;
    private MapStateHandler childController;

    /***
     * FXML UI items
     ***/
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
    private final MapStateHandler landPurchaseHandler = new LandPurchaseHandler(this);
    private final MapStateHandler gameStartHandler = new GameStartHandler(this);
    private final MapStateHandler landGrantHandler = new LandGrantHandler(this);
    private final MapStateHandler emptyHandler = new EmptyHandler(this);
    private final MapStateHandler turnOverHandler = new TurnOverHandler(this);
    private final MapStateHandler placeMuleHandler = new PlaceMuleHandler(this);

    public BoardController() {
        updateState(START);
        timer.setTickHandler(this);
    }


    /****
     * Initialize
     ****/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log("Initializing");
        setPlayer(configRepository.getPlayerConfig(1));
        initBoard();
        initRiver();
        initBoardCleanup();
        updateState(LAND_GRANT);
    }

    private void initBoard() {
        possibleMaps = new MapBoard();
        mapSpots = new boolean[possibleMaps.getHeight()][possibleMaps.getWidth()];
        mules = new boolean[possibleMaps.getHeight()][possibleMaps.getWidth()];

        // inject images
        for (int i = 0; i < possibleMaps.getHeight(); i++) {
            for (int j = 0; j < possibleMaps.getWidth(); j++) {
                ImageView img = new ImageView(possibleMaps.getTile(i, j).imagePath());
                BorderPane bp = new BorderPane();
                bp.setCenter(img);
                bp.setOnMouseClicked(this::tileChosen);
                map.add(bp, j, i); // Place the image on the grid
            }
        }
    }

    public void reCalcPlayerProperties() {
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


    /****
     * Timer
     ****/
    @Override
    public void tick() {
        childController.tick();
    }


    /****
     * Data Binding
     ****/
    public void updateState(MapControllerStates newState) {
        log("State updated to: " + newState);
        state = newState;

        if (state == LAND_GRANT)
            childController = landGrantHandler;
        else if (state == LAND_PURCHASE)
            childController = landPurchaseHandler;
        else if (state == GAME_START)
            childController = gameStartHandler;
        else if (state == TURN_OVER)
            childController = turnOverHandler;
        else if (state == PLACE_MULE)
            childController = placeMuleHandler;
        else
            childController = emptyHandler;

        childController.stateChanged();
    }


    /****
     * UI Events
     ****/
    public void passButtonClicked() {
        //log("Pass button clicked");
        childController.handlePass();
    }

    public void tileChosen(MouseEvent event) {
        //log("Tile chosen");
        childController.tileChosen(event);
    }

    private void townButtonClicked() {
        //log("Town button clicked");
        childController.handleTownButtonClicked();
    }


    /****
     * UI Elements
     ****/
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

    public void setMoney(PlayerConfigParams player) {
        renderMoney(player.getMoney());
    }

    public void renderMoney(int m) {
        getMoneyLabel().setText(String.format("Money: %6d", m));
    }

    public void renderRound(int r) {
        getRoundLabel().setText(String.format("Round: %6d", r));
    }

    public void renderTimer(int t) {
        getTimerLabel().setText(String.format("Timer: %6d", t));
    }

    public static int getCost() {
        return cost;
    }

    /**
     * Sets the color of a tile when clicked by a player.
     * Only does so if tile is not already owned.
     *
     * @param tile The tile whose color must be set.
     */
    public void setColorTile(BorderPane tile, PlayerConfigParams player) {
        int row = GridPane.getRowIndex(tile);
        int column = GridPane.getColumnIndex(tile);
        String color = player.getColor();
        tile.setStyle("-fx-border-color: " + color.toLowerCase() + ";" + "-fx-border-width: 6px;");
        player.addProperty(new Property(column, row, player, null, possibleMaps.getTile(row, column)));
        mapSpots[row][column] = true;
    }

    public boolean owned(BorderPane tile) {
        int row = GridPane.getRowIndex(tile);
        int column = GridPane.getColumnIndex(tile);
        return mapSpots[row][column];
    }

    public void ownedMessage() {
        alertsLabel.setText("This property is already owned!");
        alertsLabel.setVisible(true);
    }

    public void clearOwnedMessage() {
        alertsLabel.setText("");
        alertsLabel.setVisible(false);
    }


    /****
     * Util functions
     ****/
    private void log(String s) {
        System.out.println(s);
    }

    public MapStateHandler getChildController() {
        return childController;
    }

    public MapStateHandler getLandPurchaseHandler() {
        return landPurchaseHandler;
    }

    public MapStateHandler getGameStartHandler() {
        return gameStartHandler;
    }

    public MapStateHandler getLandGrantHandler() {
        return landGrantHandler;
    }

    public MapStateHandler getEmptyHandler() {
        return emptyHandler;
    }

    public MapStateHandler getTurnOverHandler() {
        return turnOverHandler;
    }

    public MapBoard getPossibleMaps() {
        return possibleMaps;
    }
}
