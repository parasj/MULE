package com.byteme.Config;

/**
 * Created by parasjain on 9/9/15.
 */

public class GameConfigParams {
    private Difficulty difficulty;
    private MapType maptype;
    private int numplayers;

    public GameConfigParams(Difficulty diff, MapType type, int n) {
        difficulty = diff;
        maptype = type;
        numplayers = n;
    }
}
