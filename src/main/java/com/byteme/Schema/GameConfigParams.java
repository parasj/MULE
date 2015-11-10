package com.byteme.Schema;

import java.io.Serializable;

/**
 * MULE
 */

public class GameConfigParams implements Serializable {
    private final Difficulty difficulty;
    private final MapType mapType;
    private final int numPlayers;

    /**
     * Creates a Game Config Params object.
     * @param diff The difficulty
     * @param type The MapType
     * @param n The number of players
     */
    public GameConfigParams(Difficulty diff, MapType type, int n) {
        difficulty = diff;
        mapType = type;
        numPlayers = n;
    }

    /**
     * Get the difficulty of the game.
     * @return The difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Get the map type of the game.
     * @return The MapType
     */
    public MapType getMapType() {
        return mapType;
    }

    /**
     * Get the number of players.
     * @return The number of players.
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * Turns params to string.
     * @return The string.
     */
    public String toString() {
        return "Difficulty: " + difficulty
                + "\tMap Type: " + mapType + "\tNumber of Players: "
                + numPlayers;
    }
}
