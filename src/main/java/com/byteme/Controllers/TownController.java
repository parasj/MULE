package com.byteme.Controllers;

import com.byteme.Schema.MapControllerStates;

/**
 * This is the MULE game's TownController.
 */
public class TownController {

    /**
     *
     */
    //goes to map when map square clicked
    public final void goToMap() {
        MasterController.getInstance().map();
    }

    /**
     *
     */
    //Similar
    public final void goToPub() {
        MasterController.getInstance()
                        .getBoardController()
                        .updateState(MapControllerStates.TURN_OVER, true);
        MasterController.getInstance().pubScene();
    }

    /**
     *
     */
    //Similar
    public final void goToStore() {
        MasterController.getInstance().store();
    }
}
