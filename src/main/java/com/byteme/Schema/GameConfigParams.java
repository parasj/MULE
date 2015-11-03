package com.byteme.Schema;

import javafx.fxml.Initializable;

import javax.naming.spi.InitialContextFactory;
import java.io.Serializable;

/**
 * MULE
 */

public class GameConfigParams implements Serializable{
    private final Difficulty difficulty;
    private final MapType maptype;
    private final int numplayers;

    /**
     *
     * @param diff
     * @param type
     * @param n
     */
    public GameConfigParams(Difficulty diff, MapType type, int n) {
        difficulty = diff;
        maptype = type;
        numplayers = n;
    }

    /**
     *
     * @return
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     *
     * @return
     */
    public MapType getMaptype() {
        return maptype;
    }

    /**
     *
     * @return
     */
    public int getNumPlayers() {
        return numplayers;
    }

    /**
     *
     * @return
     */
    public String toString() {
        return "Difficulty: " + difficulty + "\tMap Type: " + maptype + "\tNumber of Players: " + numplayers;
    }
}
