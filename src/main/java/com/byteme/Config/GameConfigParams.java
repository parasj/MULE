package com.byteme.Config;

import com.byteme.Map.MapType;

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

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public MapType getMaptype() {
        return maptype;
    }

    public void setMaptype(MapType maptype) {
        this.maptype = maptype;
    }

    public int getNumPlayers() {
        return numplayers;
    }

    public void setNumPlayers(int numplayers) {
        this.numplayers = numplayers;
    }
}
