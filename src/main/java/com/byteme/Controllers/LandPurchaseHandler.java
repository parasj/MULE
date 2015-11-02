package com.byteme.Controllers;

import com.byteme.Models.LandPurchaseStore;
import com.byteme.Models.MULEStore;
import com.byteme.Models.MapStateStore;
import com.byteme.Schema.MapControllerStates;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * MULE
 */
public class LandPurchaseHandler extends MapStateHandler {
    private final LandPurchaseStore landPurchaseStore;
    private final MapStateStore mapStateStore;

    public LandPurchaseHandler(BoardController boardController) {
        super(boardController);
        landPurchaseStore = MULEStore.getInstance().getLandPurchaseStore();
        mapStateStore = MULEStore.getInstance().getMapStateStore();
    }

    //Pass goes to next player
    @Override
    public void handlePass() {
        landPurchaseStore.incrPropertyCount();
        checkIfDone();
    }

    @Override
    public void handleTownButtonClicked() {
        log("Cannot go to town during land purchase phase!");
    }

    //Highlights tile and increments player till done
    @Override
    public void tileChosen(MouseEvent event) {
        getBoardController().clearOwnedMessage();
        BorderPane tile = (BorderPane) event.getSource();
        if (getBoardController().owned(tile))
            getBoardController().ownedMessage(); // Property is owned, just display warning
        else {
            // Change tile background color to player color
            if (landPurchaseStore.getCurrentPlayer().getMoney() >= BoardController.getCost()) {
                landPurchaseStore.incrPropertyCount();
                getBoardController().setColorTile(tile, landPurchaseStore.getCurrentPlayer());
                landPurchaseStore.getCurrentPlayer().payMoney(BoardController.getCost());
                checkIfDone();
            } else {
                log("You do not have enough money!");
                getBoardController().getPhaseLabel().setText("Out of Money");
            }
        }
    }

    //Checks if done
    private void checkIfDone() {
        // Land Purchase is only 2 turns per player
        if (landPurchaseStore.getCurrentPropertyCount() <= 2 * landPurchaseStore.getPlayers().size()) {
            mapStateStore.setCurrentPlayer(mapStateStore.getCurrentPlayer() + 1);
            landPurchaseStore.incrPlayer();
            getBoardController().setPlayer(landPurchaseStore.getCurrentPlayer());
            getBoardController().setMoney(landPurchaseStore.getCurrentPlayer());
        } else {
            MULEStore.getInstance().getMapStateStore().sortPlayers();
            mapStateStore.setCurrentPlayer(0);
            getBoardController().setPlayer(mapStateStore.getPlayerAt(0));
            getBoardController().setMoney(mapStateStore.getPlayerAt(0));
            mapStateStore.getPlayerAt(0).calcTimeLeft();
            GameStartHandler gameStartHandler = (GameStartHandler) getBoardController().getGameStartHandler();
            gameStartHandler.calculateRandomEvents();
            getBoardController().updateState(MapControllerStates.GAME_START, true);
        }
    }

    @Override
    public void stateChanged() {
        getBoardController().getPhaseLabel().setText("Purchase Selection");
        getBoardController().getMoneyLabel().setText("");
        getBoardController().getRoundLabel().setText("");
        getBoardController().getTimerLabel().setText("");
        getBoardController().setPlayer(landPurchaseStore.getCurrentPlayer());
        getBoardController().setMoney(landPurchaseStore.getCurrentPlayer());
    }

    @Override
    public void tick() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
