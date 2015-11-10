package com.byteme.Models;

import com.byteme.Schema.PlayerConfigParams;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * MULE
 */
public class LandGrantStore implements Serializable {
    private final ConfigRepository configRepository;
    private List<PlayerConfigParams> players;
    private int currentPlayer;
    private int currentPropertyCount;

    /**
     * Creates a Land Grant Store.
     * @param configRepository The config repository for the store.
     */
    public LandGrantStore(ConfigRepository configRepository) {
        this.configRepository = configRepository;
        players = new ArrayList<>(configRepository.getPlayers());
        currentPlayer = 0;
        currentPropertyCount = 0;
    }

    /**
     * Creates a new List of Players.
     */
    public void reinitialize() {
        players = new ArrayList<>(configRepository.getPlayers());
    }

    /**
     * Increments the current player.
     */
    public void incrementPlayer() {
        if (currentPlayer == players.size() - 1) {
            incrementPropertyCount();
        }
        currentPlayer = (currentPlayer + 1) % players.size();
    }

    /**
     * Gets the Configuration parameters of the current player.
     * @return The current player's configuration parameters
     */
    public PlayerConfigParams getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    /**
     * Increases the property count by one.
     */
    private void incrementPropertyCount() {
        currentPropertyCount++;
    }

    /**
     * Gets the current property count.
     * @return The current property count.
     */
    public int getCurrentPropertyCount() {
        return currentPropertyCount;
    }

    /**
     * Gets the ID of the current player.
     * @return The ID of the current player
     */
    public int getCurrentPlayerId() {
        return currentPlayer;
    }
}
