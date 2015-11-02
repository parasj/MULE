package com.byteme.Controllers;

import com.byteme.Models.ConfigRepository;
import com.byteme.Models.LandGrantStore;
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
public class LandGrantHandler extends MapStateHandler {
    private static final int MAX_PROPERTIES = 2;

    public LandGrantHandler(BoardController boardController) {
        super(boardController);
    }

    @Override
    public void handlePass() {
        checkIfDone();
    }

    @Override
    public void handleTownButtonClicked() {
        log("Cannot go to town during land grant phase!");
    }

    @Override
    public void tileChosen(MouseEvent event) {
        getBoardController().clearOwnedMessage();
        BorderPane tile = (BorderPane) event.getSource();
        if (getBoardController().owned(tile)) getBoardController().ownedMessage(); // Property is owned, just display warning
        else {
            // Change tile background color to player color
            getBoardController().setColorTile(tile, getS().getCurrentPlayer());
            checkIfDone();
        }
    }

    private void checkIfDone() {
        // Land Grant is only 2 turns per player
        getS().incrPlayer();
        if (getS().getCurrentPropertyCount() < MAX_PROPERTIES) {
            getBoardController().setPlayer(getS().getCurrentPlayer());
        } else {
            getBoardController().setPlayer(MULEStore.getInstance().getConfigRepository().getFirstPlayerConfig());
            getM().setCurrentPlayer(1);
            getBoardController().updateState(MapControllerStates.LAND_PURCHASE, true);
        }
    }

    @Override
    public void stateChanged() {
        getBoardController().getPhaseLabel().setText("Land Grant");
        getBoardController().setPlayer(getS().getCurrentPlayer());
        getBoardController().getMoneyLabel().setText("");
        getBoardController().getRoundLabel().setText("");
        getBoardController().getTimerLabel().setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    @Override
    public void tick() {}

    public MapStateStore getM() {
        return MULEStore.getInstance().getMapStateStore();
    }

    public LandGrantStore getS() {
        return MULEStore.getInstance().getLandGrantStore();
    }
}
