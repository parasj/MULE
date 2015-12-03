package com.byteme.Handlers;

import com.byteme.Controllers.BoardController;
import com.byteme.Models.LandPurchaseStore;
import com.byteme.Models.MULEStore;
import com.byteme.Models.MapStateStore;
import com.byteme.Schema.MapControllerStates;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * MULE.
 */
public class LandPurchaseHandler extends MapStateHandler {
    /**
     * landPurchaseStore of type LandPurchaseStore.
     */
    private final LandPurchaseStore landPurchaseStore;
    /**
     * mapStateStore of type MapStateStore.
     */
    private final MapStateStore mapStateStore;

    /**
     * Creates a LandPurchaseHandler.
     * @param boardController of type BoardController.
     */
    public LandPurchaseHandler(final BoardController boardController) {
        super(boardController);
        landPurchaseStore = MULEStore.getInstance().getLandPurchaseStore();
        mapStateStore = MULEStore.getInstance().getMapStateStore();
    }

    //Pass goes to next player
    @Override
    public final void handlePass() {
        landPurchaseStore.incrementPropertyCount();
        checkIfDone();
    }

    @Override
    public final void handleTownButtonClicked() {
        log("Cannot go to town during land purchase phase!");
    }

    //Highlights tile and increments player till done
    @Override
    public final void tileChosen(final MouseEvent event) {
        getBoardController().clearOwnedMessage();
        BorderPane tile = (BorderPane) event.getSource();
        if (getBoardController().owned(tile)) {
            getBoardController().ownedMessage();
        } else {
            // Change tile background color to player color
            if (landPurchaseStore.getCurrentPlayer()
                    .getMoney() >= BoardController.getCost()) {
                landPurchaseStore.incrementPropertyCount();
                getBoardController().setColorTile(tile, landPurchaseStore
                        .getCurrentPlayer());
                landPurchaseStore.getCurrentPlayer()
                    .payMoney(BoardController.getCost());
                checkIfDone();
            } else {
                log("You do not have enough money!");
                getBoardController().getPhaseLabel().setText("Out of Money");
            }
        }
    }

    /**
     * Checks if done.
     */
    private void checkIfDone() {
        // Land Purchase is only 2 turns per player
        if (landPurchaseStore.getCurrentPropertyCount() <= 2
            * landPurchaseStore.getPlayers().size()) {
            mapStateStore
                .setCurrentPlayer(mapStateStore.getCurrentPlayer() + 1);
            landPurchaseStore.incrementPlayer();
            getBoardController().setPlayer(landPurchaseStore
                .getCurrentPlayer());
            getBoardController().setMoney(landPurchaseStore.getCurrentPlayer());
        } else {
            MULEStore.getInstance().getMapStateStore().sortPlayers();
            mapStateStore.setCurrentPlayer(0);
            getBoardController().setPlayer(mapStateStore.getPlayerAt(0));
            getBoardController().setMoney(mapStateStore.getPlayerAt(0));
            mapStateStore.getPlayerAt(0).calcTimeLeft();
            GameStartHandler gameStartHandler
                = (GameStartHandler) getBoardController()
                    .getBoardHandlerFactory().getGameStartHandler();
            gameStartHandler.calculateRandomEvents();
            getBoardController()
                .updateState(MapControllerStates.GAME_START, true);
        }
    }

    @Override
    public final void stateChanged() {
        getBoardController().getPhaseLabel().setText("Purchase Selection");
        getBoardController().getMoneyLabel().setText("");
        getBoardController().getRoundLabel().setText("");
        getBoardController().getTimerLabel().setText("");
        getBoardController().setPlayer(landPurchaseStore.getCurrentPlayer());
        getBoardController().setMoney(landPurchaseStore.getCurrentPlayer());
    }

    @Override
    public final void initialize(final URL location,
        final ResourceBundle resources) {
    }
}
