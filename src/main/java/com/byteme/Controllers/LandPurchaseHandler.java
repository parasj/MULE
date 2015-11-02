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
    private final LandPurchaseStore s;
    private final MapStateStore m;

    public LandPurchaseHandler(BoardController boardController) {
        super(boardController);
        s = MULEStore.getInstance().getLandPurchaseStore();
        m = MULEStore.getInstance().getMapStateStore();
    }

    @Override
    public void handlePass() {
        s.incrPropertyCount();
        checkIfDone();
    }

    @Override
    public void handleTownButtonClicked() {
        log("Cannot go to town during land purchase phase!");
    }

    @Override
    public void tileChosen(MouseEvent event) {
        getBoardController().clearOwnedMessage();
        BorderPane tile = (BorderPane) event.getSource();
        if (getBoardController().owned(tile))
            getBoardController().ownedMessage(); // Property is owned, just display warning
        else {
            // Change tile background color to player color
            if (s.getCurrentPlayer().getMoney() >= BoardController.getCost()) {
                s.incrPropertyCount();
                getBoardController().setColorTile(tile, s.getCurrentPlayer());
                s.getCurrentPlayer().payMoney(BoardController.getCost());
                checkIfDone();
            } else {
                log("You do not have enough money!");
                getBoardController().getPhaseLabel().setText("Out of Money");
            }
        }
    }

    private void checkIfDone() {
        // Land Purchase is only 2 turns per player
        if (s.getCurrentPropertyCount() <= 2 * s.getPlayers().size()) {
            m.setCurrentPlayer(m.getCurrentPlayer() + 1);
            s.incrPlayer();
            getBoardController().setPlayer(s.getCurrentPlayer());
            getBoardController().setMoney(s.getCurrentPlayer());
        } else {
            MULEStore.getInstance().getMapStateStore().sortPlayers();
            m.setCurrentPlayer(0);
            getBoardController().setPlayer(m.getPlayerAt(0));
            getBoardController().setMoney(m.getPlayerAt(0));
            m.getPlayerAt(0).calcTimeLeft();
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
        getBoardController().setPlayer(s.getCurrentPlayer());
        getBoardController().setMoney(s.getCurrentPlayer());
    }

    @Override
    public void tick() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
