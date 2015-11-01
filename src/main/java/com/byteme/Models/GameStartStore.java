package com.byteme.Models;

import java.io.Serializable;

/**
 * MULE
 */
public class GameStartStore implements Serializable {
    public static GameStartStore getInstance() {
        return MULEStore.getInstance().getGameStartStore();
    }
    private ConfigRepository configRepository;
    private int currentPlayer;

    public GameStartStore(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }


    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void incCurrentPlayer() {
        setCurrentPlayer((getCurrentPlayer() + 1) % configRepository.getTotalPlayers());
    }

    public void reinit() {}
}
