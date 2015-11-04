package com.byteme.Controllers;

import com.byteme.Models.GameStartStore;
import com.byteme.Models.MULEStore;
import com.byteme.Models.MapStateStore;
import com.byteme.Models.PlaceMuleStore;
import com.byteme.Schema.MapControllerStates;
import com.byteme.Schema.PlayerConfigParams;
import com.byteme.Schema.Property;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * MULE.
 */
public class PlaceMuleHandler extends MapStateHandler {
    /**
     * gameStartStore of type GameStartStore.
     */
    private final GameStartStore gameStartStore;
    /**
     * placeMuleStore of type PlaceMuleStore.
     */
    private final PlaceMuleStore placeMuleStore;
    /**
     * mapStateStore of type MapStateStore.
     */
    private final MapStateStore mapStateStore;

    /**
     *
     * @param boardController of type BoardController.
     */
    public PlaceMuleHandler(final BoardController boardController) {
        super(boardController);
        gameStartStore = GameStartStore.getInstance();
        placeMuleStore = MULEStore.getInstance().getPlaceMuleStore();
        mapStateStore = MULEStore.getInstance().getMapStateStore();
    }

    /**
     *
     */
    @Override
    public final void handlePass() {
        log("Cannot pass now!");
    }

    /**
     *
     */
    @Override
    public void handleTownButtonClicked() {
    }

    /**
     *
     * @param event
     */
    //Places mule on tile if owned
    @Override
    public final void tileChosen(final MouseEvent event) {
        BorderPane tile = (BorderPane) event.getSource();
        PlayerConfigParams p = mapStateStore
            .getPlayerAt(gameStartStore.getCurrentPlayer());
        int row = GridPane.getRowIndex(tile);
        int column = GridPane.getColumnIndex(tile);
        boolean found = false;
        for (int i = 0; i < p.getProperties().size(); i++) {
            Property a = p.getProperties().get(i);
            if (a.getRow() == row && a.getColumn() == column) {
                a.addMule(placeMuleStore.getMule());
                tile.setCenter(new ImageView(new Image(getBoardController()
                    .getPossibleMaps().getTile(row, column).imagePath(true))));
                found = true;
                break;
            }
        }
        if (!found) {
            log("********************************************");
            log("* Cannot place M.U.L.E here! M.U.L.E lost! *");
            log("********************************************");
        }
        goToStore();
    }

    /**
     *
     */
    //Updates labels
    @Override
    public final void stateChanged() {
        getBoardController().getPhaseLabel().setText("Place Mule");
        getBoardController().setPlayer(mapStateStore
            .getPlayerAt(gameStartStore.getCurrentPlayer()));
        getBoardController().renderMoney(mapStateStore
            .getPlayerAt(gameStartStore.getCurrentPlayer()).getMoney());
        getBoardController().renderRound(mapStateStore.getCurrentRound());
        getBoardController().renderTimer(mapStateStore
            .getPlayerAt(gameStartStore.getCurrentPlayer()).getTimeLeft());
    }

    /**
     *
     * @param money of type int.
     */
    private void setMoney(final int money) {
        getBoardController().renderMoney(money);
    }

    /**
     *
     * @param round of type int.
     */
    private void setRound(final int round) {
        getBoardController().renderRound(round);
    }

    /**
     *
     * @param timer of type int.
     */
    private void setTimer(final int timer) {
        getBoardController().renderTimer(timer);
    }

    /**
     *
     */
    //Counts down
    @Override
    public final void tick() {
        PlayerConfigParams p = mapStateStore
            .getPlayerAt(gameStartStore.getCurrentPlayer());
        if (p.getTimeLeft() > 0) {
            getBoardController().renderTimer(p.getTimeLeft());
            p.setTimeLeft(p.getTimeLeft() - 1);
        } else {
            getBoardController()
                .updateState(MapControllerStates.TURN_OVER, true);
            MasterController.getInstance().pubScene();
        }
    }

    /**
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

    }

    /**
     *
     */
    //Changes state and goes to store
    private void goToStore() {
        MasterController.getInstance().store();
        getBoardController().updateState(MapControllerStates.GAME_START, true);
    }

}
