package com.byteme.Controllers;

import com.byteme.Schema.MapControllerStates;

/**
<<<<<<< HEAD
 * MULE.
=======
 * This is the MULE game's TownController.
>>>>>>> parent of 1e1d84f... Merge remote-tracking branch 'origin/master'
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
