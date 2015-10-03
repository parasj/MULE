package com.byteme.Controllers;

import com.byteme.Models.LandGrantStore;
import com.byteme.Schema.MapControllerStates;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by rishav on 10/2/2015.
 */
public class LandGrantHandler extends MapStateHandler {
    private LandGrantStore s = LandGrantStore.getInstance();

    private static final int MAX_PROPERTIES = 2;

    public LandGrantHandler(BoardController boardController) {
        super(boardController);
    }

    public void handlePass() {
        s.incrPlayer();
        checkIfDone();
    }

    @Override
    public void handleTownButtonClicked() {
        log("Cannot go to town during land grant phase!");
    }

    @Override
    public void tileChosen(MouseEvent event) {
        BorderPane tile = (BorderPane) event.getSource();
        if (getBoardController().owned(tile))
            getBoardController().owned(tile); // Property is owned, just display warning
        else {
            // Change tile background color to player color
            getBoardController().setColorTile(tile, s.getCurrentPlayer());
            checkIfDone();
        }
    }

    private void checkIfDone() {
        // Land Grant is only 2 turns per player
        if (s.getCurrentPropertyCount() < MAX_PROPERTIES) {
            s.incrPlayer();
            getBoardController().setPlayer(s.getCurrentPlayer());
        } else {
            getBoardController().updateState(MapControllerStates.LAND_PURCHASE);
        }
    }

    @Override
    public void stateChanged() {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    @Override
    public void tick() {}
}
