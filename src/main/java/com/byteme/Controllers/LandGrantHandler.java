package com.byteme.Controllers;

import com.byteme.Models.MapStateStore;
import com.byteme.Schema.MapControllerStates;
import javafx.fxml.Initializable;

/**
 * Created by rishav on 10/2/2015.
 */
public class LandGrantHandler extends MapStateHandler implements Initializable{
    private MapStateStore s = MapStateStore.getInstance();

    public void handlePass() {
        alertsLabel.setVisible(false);
        if (s.getCurrentState() == MapControllerStates.LAND_PURCHASE) {
            s.setPurchaseOpportunities(s.getPurchaseOpportunities() + 1);
        }
        s.setPassCounter(s.getPassCounter() + 1);
        if (s.getPassCounter() >= s.getNumPlayers()) {
            s.setPassCounter(0);
            if (s.getCurrentState() == MapControllerStates.LAND_GRANT || s.getCurrentState() == MapControllerStates.LAND_PURCHASE || s.getCurrentState() == MapControllerStates.START)
                s.setCurrentState(MapControllerStates.GAME_START);
            else
                s.setCurrentState(MapControllerStates.GAME_START);
            phaseLabel.setText("Selection phase is over!");
            changePlayer(1);
            s.setTimeLeft(pubController.calcTimeLeft(null));
            incRound();
            rerender();
            initTimer();
        }
//        Not used in Land Grant Stage
//        } else {
//            if (s.getCurrentState() == MapControllerStates.GAME_START || s.getCurrentState() == MapControllerStates.SELECTION_OVER)
//                pubController.goToMap();
//            else {
//                s.getInstance().setTimeLeft(0);
//                changePlayer();
//                MasterController.getInstance().map();
//            }
//        }
    }
}
