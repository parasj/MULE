package com.byteme.Models;

import java.io.Serializable;

/**
 * MULE
 */
public class GameStartStore implements Serializable {
    private final ConfigRepository configRepository;
    private int currentPlayer;

    /**
     *
     * @param configRepository
     */
    public GameStartStore(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    /**
     *
     * @return
     */
    public static GameStartStore getInstance() {
        return MULEStore.getInstance().getGameStartStore();
    }

    /**
     *
     * @return
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     *
     * @param currentPlayer
     */
    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     *
     */
    public void incCurrentPlayer() {
        setCurrentPlayer((getCurrentPlayer() + 1) % configRepository.getTotalPlayers());
    }
}
