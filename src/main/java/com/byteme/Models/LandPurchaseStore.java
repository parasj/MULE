package com.byteme.Models;

import com.byteme.Schema.PlayerConfigParams;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * MULE
 */
public class LandPurchaseStore implements Serializable {
    private List<PlayerConfigParams> players;
    private int currentPlayer;
    private int currentPropertyCount;
    private final ConfigRepository configRepository;

    /**
     * Creates a LandPurchaseStore object.
     * @param configRepository The config repository
     */
    public LandPurchaseStore(ConfigRepository configRepository) {
        this.configRepository = configRepository;
        players = new ArrayList<>(configRepository.getPlayers());
        currentPlayer = 0;
        currentPropertyCount = 0;
    }

    /**
     * Increments the player by one.
     */
    public void incrementPlayer() {
        if (currentPlayer == players.size() - 1) {
            incrementPropertyCount();
        }
        currentPlayer = (currentPlayer + 1) % players.size();
    }

    /**
     * Returns the current player.
     * @return The current player
     */
    public PlayerConfigParams getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    /**
     * Increases the current property count by one.
     */
    public void incrementPropertyCount() {
        currentPropertyCount++;
    }

    /**
     * Gets the current property count.
     * @return The property count
     */
    public int getCurrentPropertyCount() {
        return currentPropertyCount;
    }

    /**
     * Gets the current player's ID.
     * @return The current player's ID
     */
    public int getCurrentPlayerId() {
        return currentPlayer;
    }

    /**
     * Gets the list of players.
     * @return List of players.
     */
    public List<PlayerConfigParams> getPlayers() {
        return players;
    }

    /**
     * Sets the current property count.
     * @param currentPropertyCount The current property count
     */
    public void setCurrentPropertyCount(int currentPropertyCount) {
        this.currentPropertyCount = currentPropertyCount;
    }

    /**
     * Sets the ID of the current player.
     * @param currentPlayer Which player is current player
     */
    public void setCurrentPlayerId(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Resets the player list.
     */
    public void reinitialize() {
        players = new ArrayList<>(configRepository.getPlayers());
    }
}
