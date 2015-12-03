package com.byteme.Injection;

import com.byteme.Controllers.BoardController;
import com.byteme.Controllers.MasterController;

/**
 * Created by rishavbose365 on 12/3/2015.
 */
public class ControllerInjector {
    private static ControllerInjector ourInstance = new ControllerInjector();

    public static ControllerInjector getInstance() {
        return ourInstance;
    }

    private BoardController boardController;

    private MasterController masterController = new MasterController();

    private ControllerInjector() {
        boardController = new BoardController();

        masterController = new MasterController();


    }
}
