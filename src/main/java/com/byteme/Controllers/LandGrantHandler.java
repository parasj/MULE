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
    private final LandGrantStore s = MULEStore.getInstance().getLandGrantStore();
    private final static MapStateStore m = MULEStore.getInstance().getMapStateStore();

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
            getBoardController().setColorTile(tile, s.getCurrentPlayer());
            checkIfDone();
        }
    }

    private void checkIfDone() {
        // Land Grant is only 2 turns per player
        s.incrPlayer();
        if (s.getCurrentPropertyCount() < MAX_PROPERTIES) {
            getBoardController().setPlayer(s.getCurrentPlayer());
        } else {
            getBoardController().setPlayer(ConfigRepository.getInstance().getFirstPlayerConfig());
            m.setCurrentPlayer(1);
            getBoardController().updateState(MapControllerStates.LAND_PURCHASE);
        }
    }

    @Override
    public void stateChanged() {
        getBoardController().getPhaseLabel().setText("Land Grant");
        getBoardController().setPlayer(s.getCurrentPlayer());
        getBoardController().getMoneyLabel().setText("");
        getBoardController().getRoundLabel().setText("");
        getBoardController().getTimerLabel().setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    @Override
    public void tick() {}
}
