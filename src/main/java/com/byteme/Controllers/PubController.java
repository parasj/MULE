package com.byteme.Controllers;

/**
 * Created by Daniel on 9/30/2015.
 */
public class PubController {
    public void goToMap() {
        //TODO Update the Label in Map.
        MasterController.getMapInstance().nextPlayer();
        MasterController.getInstance().map();
    }

}
