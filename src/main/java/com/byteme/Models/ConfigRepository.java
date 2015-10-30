package com.byteme.Models;

import com.byteme.Schema.*;

import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;

/**
 * MULE
 */

public class ConfigRepository implements Serializable {

    private GameConfigParams gameConfigParams;
    private Map<Integer, PlayerConfigParams> playerConfigList;

    public ConfigRepository() {
        playerConfigList = new HashMap<>(3);
    }

    // Getters and Setters
    public GameConfigParams getGameConfig() {
        return gameConfigParams;
    }
    public void setGameConfig(GameConfigParams config) {
        gameConfigParams = config;
    }
    public int getTotalPlayers() {
        return gameConfigParams.getNumPlayers();
    }
    public PlayerConfigParams getPlayerConfig(int id) {
        return playerConfigList.get(id);
    }
    public void setPlayerConfig(PlayerConfigParams player, int id) {
        playerConfigList.put(id, player);
    }
    public Collection<PlayerConfigParams> getPlayers() {
        return playerConfigList.values();
    }
    public PlayerConfigParams getFirstPlayerConfig() {
        return playerConfigList.get(1);
    }
}