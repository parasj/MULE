package com.byteme.Controllers;

import com.byteme.Models.ConfigRepository;
import com.byteme.Models.MULEStore;
import com.byteme.Models.MapBoard;
import com.byteme.Schema.MapControllerStates;
import com.byteme.Schema.PlayerConfigParams;
import com.byteme.Schema.Property;
import com.byteme.Util.CanTick;
import com.byteme.Util.GlobalTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

import static com.byteme.Schema.MapControllerStates.*;


public class BoardController implements Initializable, CanTick {
    //Variables stored here, will be used and saved/loaded later
    private final static GlobalTimer timer = GlobalTimer.getInstance();
    private final static int cost = 300;

    private MapBoard possibleMaps;
    private boolean[][] mapSpots;
    private BorderPane[][] bps;

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

    // implementations of MapStateHandler, handles each phase of the game
    private final MapStateHandler landPurchaseHandler = new LandPurchaseHandler(this);
    private final MapStateHandler gameStartHandler = new GameStartHandler(this);
    private final MapStateHandler landGrantHandler = new LandGrantHandler(this);
    private final MapStateHandler emptyHandler = new EmptyHandler(this);
    private final MapStateHandler turnOverHandler = new TurnOverHandler(this);
    private final MapStateHandler placeMuleHandler = new PlaceMuleHandler(this);

    /**
     * Sets up game state and clock
     */
    public BoardController() {
        updateState(START, false);
        timer.setTickHandler(this);
    }


    /****
     * Initialize
     ****/

    //Initializes everything when the controller is initialized
    /**
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log("Initializing");
        setPlayer(getConfigRepository().getFirstPlayerConfig());
        initBoard();
        initRiver();
        initBoardCleanup();
        updateState(LAND_GRANT, false);
    }

    //Makes map and stores BorderPanes in an array

    /**
     *
     */
    private void initBoard() {
        possibleMaps = new MapBoard();
        mapSpots = new boolean[possibleMaps.getHeight()][possibleMaps.getWidth()];
        this.bps = new BorderPane[possibleMaps.getHeight()][possibleMaps.getWidth()];

        // inject images
        for (int i = 0; i < possibleMaps.getHeight(); i++) {
            for (int j = 0; j < possibleMaps.getWidth(); j++) {
                ImageView img = new ImageView(possibleMaps.getTile(i, j).imagePath());
                BorderPane bp = new BorderPane();
                bp.setCenter(img);
                bp.setOnMouseClicked(this::tileChosen);
                map.add(bp, j, i);
                bps[i][j] = bp;
            }
        }
    }

    // Keep track of which tiles have a player's color on them

    /**
     *
     */
    public void reCalcPlayerProperties() {
        getConfigRepository().getPlayers().forEach((player) ->
                player.getProperties().forEach((z) ->
                        mapSpots[z.getRow()][z.getColumn()] = true));
    }

    // Force center tile to be Town.png and center line to be river
    // Make the town tile run "goToTown()"
    /**
     *
     */
    private void initRiver() {
        ImageView townImage = new ImageView("/images/Town.png");
        townImage.setOnMouseClicked((MouseEvent e) -> townButtonClicked());
        map.add(townImage, 4, 2);
        // TODO: hardcode the river
    }

    //Changes values of labels to off

    /**
     *
     */
    private void initBoardCleanup() {
        alertsLabel.setVisible(false);
        timerLabel.setVisible(true);
    }


    /****
     * Timer
     ****/

    //Uses tick from children controller based on phase
    /**
     *
     */
    @Override
    public void tick() {
        childController.tick();
    }


    //Gets current state

    /**
     *
     * @return
     */
    public MapControllerStates getState() {
        return state;
    }

    /****
     * Data Binding
     ****/

    //Changes current state of game outside of class, true when loading from save file

    /**
     *
     * @param newState
     * @param setState
     */
    public void updateState(MapControllerStates newState, boolean setState) {
        log("State updated to: " + newState);
        state = newState;

        if (setState) {
            MULEStore.getInstance().getMapStateStore().setCurrentState(newState);
        }
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

    //handles pass from child

    /**
     *
     */
    public void passButtonClicked() {
        childController.handlePass();
    }

    //handles tile chosen from child, similar for other methods

    /**
     *
     * @param event
     */
    public void tileChosen(MouseEvent event) {
        childController.tileChosen(event);
    }

    /**
     *
     */
    private void townButtonClicked() {
        //log("Town button clicked");
        childController.handleTownButtonClicked();
    }

    /**
     *
     */
    public void saveButtonClicked() {
        System.out.println("Save Button clicked!");
        MULEStore.getInstance().save();
    }

    //Recreates the bord with correct outlines where necessary

    /**
     *
     */
    public void reinitialize() {
        for (PlayerConfigParams player : getConfigRepository().getPlayers()) {
            for (Property property: player.getProperties()) {
                String color = player.getColor();
                BorderPane tile = bps[property.getRow()][property.getColumn()];
                tile.setStyle("-fx-border-color: " + color.toLowerCase() + ";" + "-fx-border-width: 6px;");
                if (property.hasMule()) {
                    tile.setCenter(new ImageView(new Image(getPossibleMaps().getTile(property.getRow(), property.getColumn()).imagePath(true))));
                }
            }
        }
    }


    /****
     * UI Elements
     ****/

    //getters and setters for labels

    /**
     *
     * @return
     */
    public Label getPlayerLabel() {
        return playerLabel;
    }

    /**
     *
     * @return
     */
    public Label getMoneyLabel() {
        return moneyLabel;
    }

    /**
     *
     * @return
     */
    public Label getPhaseLabel() {
        return phaseLabel;
    }

    /**
     *
     * @return
     */
    public Label getAlertsLabel() {
        return alertsLabel;
    }

    /**
     *
     * @return
     */
    public Label getRoundLabel() {
        return roundLabel;
    }

    /**
     *
     * @return
     */
    public GridPane getMap() {
        return map;
    }

    /**
     *
     * @return
     */
    public Label getTimerLabel() {
        return timerLabel;
    }

    /**
     *
     * @param player
     */
    public void setPlayer(PlayerConfigParams player) {
        if (player != null) {
            playerLabel.setText(String.format("Player %d %s", player.getOrder(), player.getName()));
        } else {
            throw new IllegalArgumentException("Player is null!");
        }
    }

    /**
     *
     * @param player
     */
    public void setMoney(PlayerConfigParams player) {
        if (player != null) {
            renderMoney(player.getMoney());
        } else {
            throw new IllegalArgumentException("Player is null!");
        }
    }

    /**
     *
     * @param money
     */
    public void renderMoney(int money) {
        getMoneyLabel().setText(String.format("Money: %6d", money));
    }

    /**
     *
     * @param round
     */
    public void renderRound(int round) {
        getRoundLabel().setText(String.format("Round: %6d", round));
    }

    /**
     *
     * @param timer
     */
    public void renderTimer(int timer) {
        getTimerLabel().setText(String.format("Timer: %6d", timer));
    }

    /**
     *
     * @return
     */
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
        if (tile != null && player != null) {
            int row = GridPane.getRowIndex(tile);
            int column = GridPane.getColumnIndex(tile);
            String color = player.getColor();
            tile.setStyle("-fx-border-color: " + color.toLowerCase() + ";" + "-fx-border-width: 6px;");
            player.addProperty(new Property(column, row, player, possibleMaps.getTile(row, column)));
            mapSpots[row][column] = true;
        } else {
            throw new IllegalArgumentException("Tile/player is null!");
        }
    }

    //Checks if tile is owned

    /**
     *
     * @param tile
     * @return
     */
    public boolean owned(BorderPane tile) {
        if (tile == null) {
            throw new IllegalArgumentException("Tile is null!");
        }
        int row = GridPane.getRowIndex(tile);
        int column = GridPane.getColumnIndex(tile);
        return mapSpots[row][column];
    }

    //Puts message if owned

    /**
     *
     */
    public void ownedMessage() {
        alertsLabel.setText("This property is already owned!");
        alertsLabel.setVisible(true);
    }

    //Clears message if owned

    /**
     *
     */
    public void clearOwnedMessage() {
        alertsLabel.setText("");
        alertsLabel.setVisible(false);
    }


    /****
     * Util functions
     ****/

    /**
     *
     * @param string
     */
    private void log(String string) {
        System.out.println(string);
    }

    /**
     *
     * @return
     */
    public MapStateHandler getChildController() {
        return childController;
    }

    /**
     *
     * @return
     */
    public MapStateHandler getLandPurchaseHandler() {
        return landPurchaseHandler;
    }

    /**
     *
     * @return
     */
    public MapStateHandler getGameStartHandler() {
        return gameStartHandler;
    }

    /**
     *
     * @return
     */
    public MapStateHandler getLandGrantHandler() {
        return landGrantHandler;
    }

    /**
     *
     * @return
     */
    public MapStateHandler getEmptyHandler() {
        return emptyHandler;
    }

    /**
     *
     * @return
     */
    public MapStateHandler getTurnOverHandler() {
        return turnOverHandler;
    }

    /**
     *
     * @return
     */
    public MapBoard getPossibleMaps() {
        return possibleMaps;
    }

    /**
     *
     * @return
     */
    public final ConfigRepository getConfigRepository() {
        return MULEStore.getInstance().getConfigRepository();
    }

    /**
     *
     */
    public final void render() {
        System.out.println(state);
    }
}
