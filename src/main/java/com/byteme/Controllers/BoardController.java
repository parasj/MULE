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

/**
 * MULE.
 */
public class BoardController implements Initializable, CanTick {
    //Variables stored here, will be used and saved/loaded later
    /**
     * timer of type GlobalTimer.
     */
    private static final GlobalTimer timer = GlobalTimer.getInstance();
    /**
     * cost of type int.
     */
    private static final int cost = 300;

    /**
     * A static value added to the map.
     */
    private static final int MAPTAKEINNUM = 4;

    /**
     * possibleMaps of type MapBoard.
     */
    private MapBoard possibleMaps;
    /**
     * mapSpots of type boolean[][].
     */
    private boolean[][] mapSpots;
    /**
     * bps of type BorderPane[][].
     */
    private BorderPane[][] bps;

    /**
     * state of type MapControllerStates.
     */
    private MapControllerStates state;
    /**
     * childController of type MapStateHandler.
     */
    private MapStateHandler childController;

    /***
     * FXML UI items
     ***/
    /**
     * playerLabel of type Label.
     */
    @FXML
    private Label playerLabel;
    /**
     * moneyLabel of type Label.
     */
    @FXML
    private Label moneyLabel;
    /**
     * phaseLabel of type Label.
     */
    @FXML
    private Label phaseLabel;
    /**
     * alertsLabel of type Label.
     */
    @FXML
    private Label alertsLabel;
    /**
     * roundLabel of type Label.
     */
    @FXML
    private Label roundLabel;
    /**
     * map of type GridPane.
     */
    @FXML
    private GridPane map;
    /**
     * timerLabel of type Label.
     */
    @FXML
    private Label timerLabel;

    // implementations of MapStateHandler, handles each phase of the game
    /**
     * landPurchaseHandler of type MapStateHandler.
     */
    private final MapStateHandler landPurchaseHandler
        = new LandPurchaseHandler(this);
    /**
     * gameStartHandler of type MapStateHandler.
     */
    private final MapStateHandler gameStartHandler = new GameStartHandler(this);
    /**
     * landGrantHandler of type MapStateHandler.
     */
    private final MapStateHandler landGrantHandler = new LandGrantHandler(this);
    /**
     * emptyHandler of type MapStateHandler.
     */
    private final MapStateHandler emptyHandler = new EmptyHandler(this);
    /**
     * turnOverHandler of type MapStateHandler.
     */
    private final MapStateHandler turnOverHandler = new TurnOverHandler(this);
    /**
     * placeMuleHandler of type MapStateHandler.
     */
    private final MapStateHandler placeMuleHandler = new PlaceMuleHandler(this);

    /**
     *
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
     * @param location of type URL.
     * @param resources of type ResourceBundle.
     */
    @Override
    public final void initialize(final URL location,
        final ResourceBundle resources) {
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
        mapSpots = new boolean[possibleMaps.getHeight()][possibleMaps
            .getWidth()];
        this.bps = new BorderPane[possibleMaps.getHeight()][possibleMaps
            .getWidth()];

        // inject images
        for (int i = 0; i < possibleMaps.getHeight(); i++) {
            for (int j = 0; j < possibleMaps.getWidth(); j++) {
                ImageView img = new ImageView(possibleMaps.getTile(i, j)
                    .imagePath());
                BorderPane bp = new BorderPane();
                bp.setCenter(img);
                bp.setOnMouseClicked(this::tileChosen);
                map.add(bp, j, i);
                bps[i][j] = bp;
            }
        }
    }

    // Keep track of which tiles have a player's color on them

// --Commented out by Inspection START (11/10/2015 12:00 AM):
//    /**
//     *
//     */
//    public final void reCalcPlayerProperties() {
//        getConfigRepository().getPlayers().forEach((player) ->
//                player.getProperties().forEach((z) ->
//                        mapSpots[z.getRow()][z.getColumn()] = true));
//    }
// --Commented out by Inspection STOP (11/10/2015 12:00 AM)

    // Force center tile to be Town.png and center line to be river
    // Make the town tile run "goToTown()"
    /**
     *
     */
    private void initRiver() {
        ImageView townImage = new ImageView("/images/Town.png");
        townImage.setOnMouseClicked((MouseEvent e) -> townButtonClicked());
        map.add(townImage, MAPTAKEINNUM, 2);
        // Todo: hardcode the river
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
    public final void tick() {
        childController.tick();
    }


    //Gets current state

// --Commented out by Inspection START (11/10/2015 12:00 AM):
//    /**
//     *
//     * @return state, instance of MapControllerStates.
//     */
//    public final MapControllerStates getState() {
//        return state;
//    }
// --Commented out by Inspection STOP (11/10/2015 12:00 AM)

    /****
     * Data Binding
     ****/

    //Changes state of game outside of class, true when loading from save file

    /**
     *
     * @param newState of type MapControllerStates.
     * @param setState of type Boolean.
     */
    public final void updateState(final MapControllerStates newState,
        final boolean setState) {
        log("State updated to: " + newState);
        state = newState;

        if (setState) {
            MULEStore.getInstance().getMapStateStore()
                .setCurrentState(newState);
        }
        if (state == LAND_GRANT) {
            childController = landGrantHandler;
        } else if (state == LAND_PURCHASE) {
            childController = landPurchaseHandler;
        } else if (state == GAME_START) {
            childController = gameStartHandler;
        } else if (state == TURN_OVER) {
            childController = turnOverHandler;
        } else if (state == PLACE_MULE) {
            childController = placeMuleHandler;
        } else {
            childController = emptyHandler;
        }
        childController.stateChanged();
    }

    /****
     * UI Events
     ****/

    //handles pass from child

    /**
     *
     */
    public final void passButtonClicked() {
        childController.handlePass();
    }

    //handles tile chosen from child, similar for other methods

    /**
     *
     * @param event of type MouseEvent.
     */
    public final void tileChosen(final MouseEvent event) {
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
    public final void saveButtonClicked() {
        System.out.println("Save Button clicked!");
        MULEStore.getInstance().save();
    }

    //Recreates the bord with correct outlines where necessary

    /**
     *
     */
    public final void reinitialize() {
        for (PlayerConfigParams player : getConfigRepository().getPlayers()) {
            for (Property property: player.getProperties()) {
                String color = player.getColor();
                BorderPane tile = bps[property.getRow()][property.getColumn()];
                tile.setStyle("-fx-border-color: " + color.toLowerCase() + ";"
                    + "-fx-border-width: 6px;");
                if (property.hasMule()) {
                    tile.setCenter(new ImageView(new Image(getPossibleMaps()
                        .getTile(property.getRow(),
                            property.getColumn()).imagePath(true))));
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
     * @return playerLabel, instance of Label.
     */
    public final Label getPlayerLabel() {
        return playerLabel;
    }

    /**
     *
     * @return moneyLabel, instance of Label.
     */
    public final Label getMoneyLabel() {
        return moneyLabel;
    }

    /**
     *
     * @return phaseLabel, instance of Label.
     */
    public final Label getPhaseLabel() {
        return phaseLabel;
    }

    /**
     *
     * @return alertsLabel, instance of Label.
     */
    public final Label getAlertsLabel() {
        return alertsLabel;
    }

    /**
     *
     * @return roundLabel, instance of Label.
     */
    public final Label getRoundLabel() {
        return roundLabel;
    }

    /**
     *
     * @return mpa of type GridPane.
     */
    public final GridPane getMap() {
        return map;
    }

    /**
     *
     * @return timerLabel, instance of Label.
     */
    public final Label getTimerLabel() {
        return timerLabel;
    }

    /**
     *
     * @param player of type PlayerConfigParams.
     */
    public final void setPlayer(final PlayerConfigParams player) {
        if (player != null) {
            playerLabel.setText(String.format("Player %d %s", player.getOrder(),
                player.getName()));
        } else {
            throw new IllegalArgumentException("Player is null!");
        }
    }

    /**
     *
     * @param player of type PlayerConfigParams.
     */
    public final void setMoney(final PlayerConfigParams player) {
        if (player != null) {
            renderMoney(player.getMoney());
        } else {
            throw new IllegalArgumentException("Player is null!");
        }
    }

    /**
     *
     * @param money of type int.
     */
    public final void renderMoney(final int money) {
        getMoneyLabel().setText(String.format("Money: %6d", money));
    }

    /**
     *
     * @param round of type int.
     */
    public final void renderRound(final int round) {
        getRoundLabel().setText(String.format("Round: %6d", round));
    }

    /**
     *
     * @param timer of type int.
     */
    public final void renderTimer(final int timer) {
        getTimerLabel().setText(String.format("Timer: %6d", timer));
    }

    /**
     *
     * @return cost of buying Resources.
     */
    public static int getCost() {
        return cost;
    }

    /**
     * Sets the color of a tile when clicked by a player.
     * Only does so if tile is not already owned.
     *
     * @param tile The tile whose color must be set.
     * @param player Choosing the tile.
     */
    public final void setColorTile(final BorderPane tile,
        final PlayerConfigParams player) {
        if (tile != null && player != null) {
            int row = GridPane.getRowIndex(tile);
            int column = GridPane.getColumnIndex(tile);
            String color = player.getColor();
            tile.setStyle("-fx-border-color: " + color.toLowerCase() + ";"
                + "-fx-border-width: 6px;");
            player.addProperty(new Property(column, row, player,
                possibleMaps.getTile(row, column)));
            mapSpots[row][column] = true;
        } else {
            throw new IllegalArgumentException("Tile/player is null!");
        }
    }

    //Checks if tile is owned

    /**
     *
     * @param tile of type BoardPane.
     * @return True or False.
     */
    public final boolean owned(final BorderPane tile) {
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
    public final void ownedMessage() {
        alertsLabel.setText("This property is already owned!");
        alertsLabel.setVisible(true);
    }

    //Clears message if owned

    /**
     *
     */
    public final void clearOwnedMessage() {
        alertsLabel.setText("");
        alertsLabel.setVisible(false);
    }


    /****
     * Util functions
     ****/

    /**
     *
     * @param string of type String.
     */
    private void log(final String string) {
        System.out.println(string);
    }

    /**
     *
     * @return instance of MapStateHandler.
     */
    public final MapStateHandler getChildController() {
        return childController;
    }

    /**
     *
     * @return instance of MapStateHandler.
     */
    public final MapStateHandler getLandPurchaseHandler() {
        return landPurchaseHandler;
    }

    /**
     *
     * @return instance of MapStateHandler.
     */
    public final MapStateHandler getGameStartHandler() {
        return gameStartHandler;
    }

    /**
     *
     * @return instance of MapStateHandler.
     */
    public final MapStateHandler getLandGrantHandler() {
        return landGrantHandler;
    }

    /**
     *
     * @return instance of MapStateHandler.
     */
    public final MapStateHandler getEmptyHandler() {
        return emptyHandler;
    }

    /**
     *
     * @return instance of MapStateHandler.
     */
    public final MapStateHandler getTurnOverHandler() {
        return turnOverHandler;
    }

    /**
     *
     * @return instance of MapBoard.
     */
    public final MapBoard getPossibleMaps() {
        return possibleMaps;
    }

    /**
     *
     * @return instance of ConfigRepository.
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
