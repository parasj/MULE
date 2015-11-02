package com.byteme.Controllers;

import com.byteme.Schema.MapControllerStates;

/**
 * MULE
 */
public class TownController {

    //goes to map when map square clicked
    public void goToMap() {
        MasterController.getInstance().map();
    }

    //Similar
    public void goToPub() {
        MasterController.getInstance().getBoardController().updateState(MapControllerStates.TURN_OVER, true);
        MasterController.getInstance().pubScene();
    }

    //Similar
    public void goToStore() {
        MasterController.getInstance().store();
    }

}