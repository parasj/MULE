package com.byteme.Models;

import com.byteme.Schema.PlayerConfigParams;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * MULE
 */
public class LandGrantStore implements Serializable {
    private ConfigRepository configRepository;

    public LandGrantStore(ConfigRepository configRepository) {
        this.configRepository = configRepository;
        players = new ArrayList<>(configRepository.getPlayers());
        currentPlayer = 0;
        currentPropertyCount = 0;
    }

    private List<PlayerConfigParams> players;
    private int currentPlayer;
    private int currentPropertyCount;

    public void reinit() {
        players = new ArrayList<>(configRepository.getPlayers());
    }

    public void incrPlayer() {
        if (currentPlayer == players.size() - 1)
            incrPropertyCount();
        currentPlayer = (currentPlayer + 1) % players.size();
    }

    public PlayerConfigParams getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    private void incrPropertyCount() {
        currentPropertyCount++;
    }

    public int getCurrentPropertyCount() {
        return currentPropertyCount;
    }

    public int getCurrentPlayerId() {
        return currentPlayer;
    }
}
