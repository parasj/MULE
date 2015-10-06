package com.byteme.Schema;

/**
 * MULE
 */

public class GameConfigParams {
    private final Difficulty difficulty;
    private final MapType maptype;
    private final int numplayers;

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
