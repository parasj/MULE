package com.byteme.Config.Schema;

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

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public MapType getMaptype() {
        return maptype;
    }

    public int getNumPlayers() {
        return numplayers;
    }

    public String toString() {
        return "Difficulty: " + difficulty + "\tMap Type: " + maptype + "\tNumber of Players: " + numplayers;
    }
}
