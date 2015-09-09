package com.byteme.Config;

/**
 * Created by parasjain on 9/9/15.
 */

public interface ConfigRepository {
    public void setGameConfig(GameConfigParams config);
    public GameConfigParams getGameConfig();
    public void setPlayerConfig(PlayerConfigParams player, int id);
    public PlayerConfigParams getPlayerConfig(int id);
}