package com.byteme.Models;

import com.byteme.Schema.*;

import java.io.Serializable;
import java.util.*;

/**
 * MULE
 */

public class ConfigRepository implements Serializable {

    private GameConfigParams gameConfigParams;
    private Map<Integer, PlayerConfigParams> playerConfigList;


    public ConfigRepository(GameConfigParams gameConfigParams, Map<Integer, PlayerConfigParams> playerConfigList) {
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
     *
     * @return
     */
    public GameConfigParams getGameConfig() {
        return gameConfigParams;
    }

    /**
     *
     * @param config
     */
    public void setGameConfig(GameConfigParams config) {
        if (config != null) {
            gameConfigParams = config;
        }
    }

    /**
     *
     * @return
     */
    public int getTotalPlayers() {
        return gameConfigParams.getNumPlayers();
    }

    /**
     *
     * @param id
     * @return
     */
    public PlayerConfigParams getPlayerConfig(int id) {
        return playerConfigList.get(id);
    }

    /**
     *
     * @param player
     * @param id
     */
    public void setPlayerConfig(PlayerConfigParams player, int id) {
        if (player != null) {
            playerConfigList.put(id, player);
        }
    }

    /**
     *
     * @return
     */
    public Collection<PlayerConfigParams> getPlayers() {
        return playerConfigList.values();
    }

    /**
     *
     * @return
     */
    public PlayerConfigParams getFirstPlayerConfig() {
        return playerConfigList.get(1);
    }

    /**
     *
     */
    public void reinit() {}
}