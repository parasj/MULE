package com.byteme.Models;

import com.byteme.Schema.*;
import java.util.*;

/**
 * Created by parasjain on 9/9/15.
 */

public class ConfigRepository {
    // private final static Logger log = Logger.getLogger(ConfigRepository.class.getName());

    // Singleton
    private static ConfigRepository instance = null;
    public static ConfigRepository getInstance() {
        if(instance == null) {
            instance = new ConfigRepository();
        }
        return instance;
    }

    private ConfigStore configStore;
    private GameConfigParams gameConfigParams;
    private Map<Integer, PlayerConfigParams> playerConfigList;

    private ConfigRepository() {
        this(new InMemoryConfigStore());
    }

    private ConfigRepository(ConfigStore store) {
        configStore = store;
        //Why 3????
        playerConfigList = new HashMap<>(3);
    }

    public void setGameConfig(GameConfigParams config) {
        // log.info("Saving game config, " + config);
        gameConfigParams = config;
        configStore.save(this);
    }

    public GameConfigParams getGameConfig() {
        return gameConfigParams;
    }

    public void setPlayerConfig(PlayerConfigParams player, int id) {
        // log.info("Saving player " + id + " config, " + player);
        playerConfigList.put(id, player);
        configStore.save(this);
    }

    public PlayerConfigParams getPlayerConfig(int id) {
        return playerConfigList.get(id);
    }
    public int getTotalPlayers() {
        return gameConfigParams.getNumPlayers();
    }
}