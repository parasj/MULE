package com.byteme.Models;

/**
 * MULE
 */
public class GameStartStore {
    private static final GameStartStore ourInstance = new GameStartStore();

    public static GameStartStore getInstance() {
        return ourInstance;
    }


    private GameStartStore() {
    }

}
