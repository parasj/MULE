package com.byteme.Models;

import com.byteme.Schema.*;

import java.io.Serializable;
import java.util.*;

/**
 * MULE
 */

public class ConfigRepository implements Serializable {

    private GameConfigParams gameConfigParams;
    private final Map<Integer, PlayerConfigParams> playerConfigList;

    /**
     * Constructor.
     * @param gameConfigParams The params.
     * @param playerConfigList The list.
     */
    public ConfigRepository(GameConfigParams gameConfigParams,
                            Map<Integer, PlayerConfigParams> playerConfigList) {
        this.gameConfigParams = gameConfigParams;
        this.playerConfigList = playerConfigList;
    }

    /**
     *
     */
    public ConfigRepository() {
        playerConfigList = new HashMap<>(3);
    }

    // Getters and Setters

    /**
     * Gets the Game Config Parameters.
     * @return Game Config Parameters
     */
    public GameConfigParams getGameConfig() {
        return gameConfigParams;
    }

    /**
     * Sets the game config parameters.
     * @param config The game config parameters
     */
    public void setGameConfig(GameConfigParams config) {
        if (config != null) {
            gameConfigParams = config;
        }
    }

    /**
     * Gets the total number of players.
     * @return The total number of players
     */
    public int getTotalPlayers() {
        return gameConfigParams.getNumPlayers();
    }

    /**
     * Gets the Player Config Parameters of a player by ID.
     * @param id The Player's ID
     * @return The player's config parameters
     */
    public PlayerConfigParams getPlayerConfig(int id) {
        return playerConfigList.get(id);
    }

    /**
     * Sets the player's configuration parameters by ID.
     * @param player  The player's configuration parameters.
     * @param id The player's ID
     */
    public void setPlayerConfig(PlayerConfigParams player, int id) {
        if (player != null) {
            playerConfigList.put(id, player);
        }
    }

    /**
     * Gets the players.
     * @return Collection of players.
     */
    public Collection<PlayerConfigParams> getPlayers() {
        return playerConfigList.values();
    }

    /**
     * Gets the first player's configuration parameters.
     * @return The first player's configuration parameters
     */
    public PlayerConfigParams getFirstPlayerConfig() {
        return playerConfigList.get(1);
    }
}