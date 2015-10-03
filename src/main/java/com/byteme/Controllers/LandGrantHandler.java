package com.byteme.Controllers;

import com.byteme.Models.LandGrantStore;
import com.byteme.Models.ConfigRepository;
import com.byteme.Models.MapStateStore;
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
//        getBoardController().getAlertsLabel().setVisible(false);
//        if (s.getCurrentState() == MapControllerStates.LAND_PURCHASE) {
//            s.setPurchaseOpportunities(s.getPurchaseOpportunities() + 1);
//        }
//        s.setPassCounter(s.getPassCounter() + 1);
//        if (s.getPassCounter() >= s.getNumPlayers()) {
//            s.setPassCounter(0);
//            if (s.getCurrentState() == MapControllerStates.LAND_GRANT || s.getCurrentState() == MapControllerStates.LAND_PURCHASE || s.getCurrentState() == MapControllerStates.START)
//                s.setCurrentState(MapControllerStates.GAME_START);
//            else
//                s.setCurrentState(MapControllerStates.GAME_START);
//            getBoardController().getPhaseLabel().setText("Selection phase is over!");
//            changePlayer(1);
//            s.setTimeLeft(pubController.calcTimeLeft(null));
//            getBoardController().updateState(MapControllerStates.LAND_PURCHASE);
//        }
////        Not used in Land Grant Stage
////        } else {
////            if (s.getCurrentState() == MapControllerStates.GAME_START || s.getCurrentState() == MapControllerStates.SELECTION_OVER)
////                pubController.goToMap();
////            else {
////                s.getInstance().setTimeLeft(0);
////                changePlayer();
////                MasterController.getInstance().map();
////            }
////        }
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
            getBoardController().setColorTile(tile);
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
    public void stateChanged() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void tick() {

    }
}
