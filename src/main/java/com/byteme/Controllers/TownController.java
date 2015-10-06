package com.byteme.Controllers;

import com.byteme.Models.MapStateStore;
import com.byteme.Schema.MapControllerStates;

/**
 * Created by Daniel on 9/18/2015.
 */
public class TownController {

    public void goToMap() {
        MasterController.getInstance().map();
    }

    public void goToPub() {
        MapStateStore.getInstance().setCurrentState(MapControllerStates.TURN_OVER);
        MasterController.getInstance().pubScene();
    }

}