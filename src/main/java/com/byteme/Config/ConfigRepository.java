package com.byteme.Config;

import java.util.*;

/**
 * Created by parasjain on 9/9/15.
 */

public class ConfigRepository {
    private ConfigStore configStore;
    private GameConfigParams gameConfigParams;
    private List<PlayerConfigParams> playerConfigList;

    public ConfigRepository() {
        this(new InMemoryConfigStore());
    }

    public ConfigRepository(ConfigStore store) {
        configStore = store;
        playerConfigList = new ArrayList<>();
    }

    public void setGameConfig(GameConfigParams config) {
        gameConfigParams = config;
        configStore.save(this);
    }

    public GameConfigParams getGameConfig() {
        return gameConfigParams;
    }

    public void setPlayerConfig(PlayerConfigParams player, int id) {
        playerConfigList.set(id, player);
        configStore.save(this);
    }

    public PlayerConfigParams getPlayerConfig(int id) {
        return playerConfigList.get(id);
    }
}