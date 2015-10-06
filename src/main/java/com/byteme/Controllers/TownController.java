package com.byteme.Controllers;

import com.byteme.Models.MapStateStore;
import com.byteme.Schema.MapControllerStates;

/**
 * MULE
 */
public class TownController {

    public void goToMap() {
        MasterController.getInstance().map();
    }

    public void goToPub() {
        MasterController.getInstance().getBoardController().updateState(MapControllerStates.TURN_OVER);
        MasterController.getInstance().pubScene();
    }

}