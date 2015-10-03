package com.byteme.Controllers;

import com.byteme.Models.MapBoard;
import com.byteme.Models.MapStateStore;
import com.byteme.Schema.MapControllerStates;

/**
 * Created by parasjain on 10/2/15.
 */
public class BoardController {
    private MapStateHandler childController;
    private final MapStateStore s = MapStateStore.getInstance();
    private final MapBoard possibleMaps = new MapBoard();
    private MapControllerStates state;

    public BoardController() {
        // start at game start
        state = MapControllerStates.GAME_START;
    }



    public void passButtonClicked() {
        log("Pass");
        childController.handlePass();
    }

    private void log(String s) {
        System.out.println(s);
    }
}
