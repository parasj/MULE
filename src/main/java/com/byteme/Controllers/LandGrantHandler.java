package com.byteme.Controllers;

import com.byteme.Models.LandGrantStore;
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
public class LandGrantHandler extends MapStateHandler {
    //Players can only buy 2 properties during land grant stage
    /**
     * Maximum number of properties.
     */
    private static final int MAX_PROPERTIES = 2;

    /**
     *
     * @param boardController of type BoardController.
     */
    public LandGrantHandler(final BoardController boardController) {
        super(boardController);
    }

    /**
     *
     */
    @Override
    public final void handlePass() {
        checkIfDone();
    }

    /**
     *
     */
    @Override
    public final void handleTownButtonClicked() {
        log("Cannot go to town during land grant phase!");
    }

    /**
     *
     * @param event of type MouseEvent.
     */
    //Checks if owned, else colors in box
    @Override
    public final void tileChosen(final MouseEvent event) {
        getBoardController().clearOwnedMessage();
        BorderPane tile = (BorderPane) event.getSource();
        if (getBoardController().owned(tile)) {
            getBoardController().ownedMessage();
            // Property is owned, just display warning
        } else {
            // Change tile background color to player color
            getBoardController().setColorTile(tile, getLandGrantStore()
                .getCurrentPlayer());
            checkIfDone();
        }
    }

    /**
     *
     */
    //Sees if phase is over
    private void checkIfDone() {
        // Land Grant is only 2 turns per player
        getLandGrantStore().incrementPlayer();
        if (getLandGrantStore().getCurrentPropertyCount() < MAX_PROPERTIES) {
            getBoardController().setPlayer(getLandGrantStore()
                .getCurrentPlayer());
        } else {
            getBoardController().setPlayer(MULEStore.getInstance()
                .getConfigRepository().getFirstPlayerConfig());
            getMapStateStore().setCurrentPlayer(1);
            getBoardController().updateState(MapControllerStates
                .LAND_PURCHASE, true);
        }
    }

    /**
     *
     */
    //Changes state to next phase and resets labels
    @Override
    public final void stateChanged() {
        getBoardController().getPhaseLabel().setText("Land Grant");
        getBoardController().setPlayer(getLandGrantStore().getCurrentPlayer());
        getBoardController().getMoneyLabel().setText("");
        getBoardController().getRoundLabel().setText("");
        getBoardController().getTimerLabel().setText("");
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
    }

    @Override
    public void tick() {
    }

    /**
     * Gets stores.
     * @return instance of MapStateStore.
     */
    private MapStateStore getMapStateStore() {
        return MULEStore.getInstance().getMapStateStore();
    }

    /**
     * Gets the instance of LandGrantStore.
     * @return instance of LandGrantStore.
     */
    private LandGrantStore getLandGrantStore() {
        return MULEStore.getInstance().getLandGrantStore();
    }
}
