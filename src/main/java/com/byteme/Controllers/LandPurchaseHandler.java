package com.byteme.Controllers;

import com.byteme.Models.LandGrantStore;
import com.byteme.Models.LandPurchaseStore;
import com.byteme.Schema.MapControllerStates;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by rishav on 10/2/2015.
 */
public class LandPurchaseHandler extends MapStateHandler {
    private LandPurchaseStore s = LandPurchaseStore.getInstance();

    public LandPurchaseHandler(BoardController boardController) {
        super(boardController);
    }

    @Override
    public void handlePass() {
        s.incrPlayer();
        checkIfDone();
    }

    @Override
    public void handleTownButtonClicked() {
        log("Cannot go to town during land purchase phase!");
    }

    @Override
    public void tileChosen(MouseEvent event) {
        s.setCurrentPropertyCount(0);
        BorderPane tile = (BorderPane) event.getSource();
        if (getBoardController().owned(tile))
            getBoardController().ownedMessage(); // Property is owned, just display warning
        else {
            // Change tile background color to player color
            getBoardController().setColorTile(tile, s.getCurrentPlayer());
            checkIfDone();
            if (s.getCurrentPlayer().getMoney() > getBoardController().getCost()) {
                s.getCurrentPlayer().payMoney(getBoardController().getCost());
            } else {
                log("You do not have enough money!");
            }
        }
    }

    private void checkIfDone() {
        // Land Purchase is only 2 turns per player
        if (s.getCurrentPropertyCount() < s.getPlayers().size()) {
            s.incrPlayer();
            getBoardController().setPlayer(s.getCurrentPlayer());
        } else {
            getBoardController().updateState(MapControllerStates.GAME_START);
        }
    }

    @Override
    public void stateChanged() {

    }

    @Override
    public void tick() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getBoardController().getPhaseLabel().setText("Property Selection");
    }
}
