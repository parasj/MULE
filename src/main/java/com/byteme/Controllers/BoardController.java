package com.byteme.Controllers;

import com.byteme.Models.MapStateStore;

/**
 * Created by parasjain on 10/2/15.
 */
public class BoardController {
    private MapStateController childController;
    private final MapStateStore s = MapStateStore.getInstance();
    private 

    public void passButtonClicked() {
        log("Pass");
        childController.handlePass();
    }

    private void log(String s) {
        System.out.println(s);
    }
}
