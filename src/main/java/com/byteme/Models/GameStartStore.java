package com.byteme.Models;

/**
 * Created by rishavbose365 on 10/5/2015.
 */
public class GameStartStore {
    private static final GameStartStore ourInstance = new GameStartStore();

    public static GameStartStore getInstance() {
        return ourInstance;
    }


    private GameStartStore() {
    }

}
