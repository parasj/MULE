package com.byteme.Models;

import java.io.Serializable;

/**
 * MULE
 */
public class GameStartStore implements Serializable {
    private final ConfigRepository configRepository;
    private int currentPlayer;

    /**
     * Creates a GameStartStore object with a given config.
     * @param configRepository The config repository.
     */
    public GameStartStore(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    /**
     * Gets the instance of GameStartStore.
     * @return The instance of GameStartStore.
     */
    public static GameStartStore getInstance() {
        return MULEStore.getInstance().getGameStartStore();
    }

    /**
     * Gets the current player number.
     * @return The current player
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Sets the current player number.
     * @param currentPlayer The desired current player.
     */
    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Increments the current player.
     */
    public void incrementCurrentPlayer() {
        setCurrentPlayer((getCurrentPlayer() + 1) % configRepository.getTotalPlayers());
    }
}
