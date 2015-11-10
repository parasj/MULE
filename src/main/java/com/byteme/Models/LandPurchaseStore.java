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
     *
     * @param configRepository
     */
    public LandPurchaseStore(ConfigRepository configRepository) {
        this.configRepository = configRepository;
        players = new ArrayList<>(configRepository.getPlayers());
        currentPlayer = 0;
        currentPropertyCount = 0;
    }

    /**
     *
     */
    public void incrPlayer() {
        if (currentPlayer == players.size() - 1) {
            incrPropertyCount();
        }
        currentPlayer = (currentPlayer + 1) % players.size();
    }

    /**
     *
     * @return
     */
    public PlayerConfigParams getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    /**
     *
     */
    public void incrPropertyCount() {
        currentPropertyCount++;
    }

    /**
     *
     * @return
     */
    public int getCurrentPropertyCount() {
        return currentPropertyCount;
    }

    /**
     *
     * @return
     */
    public int getCurrentPlayerId() {
        return currentPlayer;
    }

    /**
     *
     * @return
     */
    public List<PlayerConfigParams> getPlayers() {
        return players;
    }

    /**
     *
     * @param currentPropertyCount
     */
    public void setCurrentPropertyCount(int currentPropertyCount) {
        this.currentPropertyCount = currentPropertyCount;
    }

    /**
     *
     * @param currentPlayer
     */
    public void setCurrentPlayerId(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     *
     */
    public void reinit() {
        players = new ArrayList<>(configRepository.getPlayers());
    }
}
