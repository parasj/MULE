package com.byteme.Models;

import com.byteme.Schema.PlayerConfigParams;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * MULE
 */
public class LandPurchaseStore implements Serializable {
    private final static ConfigRepository configRepository = ConfigRepository.getInstance();

    private List<PlayerConfigParams> players;
    private int currentPlayer;
    private int currentPropertyCount;

    public LandPurchaseStore() {
        players = new ArrayList<>(configRepository.getPlayers());
        currentPlayer = 0;
        currentPropertyCount = 0;
    }

    public void incrPlayer() {
        if (currentPlayer == players.size() - 1) {
            incrPropertyCount();
        }
        currentPlayer = (currentPlayer + 1) % players.size();
    }

    public PlayerConfigParams getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    public void incrPropertyCount() {
        currentPropertyCount++;
    }

    public int getCurrentPropertyCount() {
        return currentPropertyCount;
    }

    public int getCurrentPlayerId() {
        return currentPlayer;
    }

    public List<PlayerConfigParams> getPlayers() {
        return players;
    }

    public void setCurrentPropertyCount(int currentPropertyCount) {
        this.currentPropertyCount = currentPropertyCount;
    }

    public void setCurrentPlayerId(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
