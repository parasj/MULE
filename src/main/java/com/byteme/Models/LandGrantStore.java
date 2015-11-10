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
     *
     * @param configRepository
     */
    public LandGrantStore(ConfigRepository configRepository) {
        this.configRepository = configRepository;
        players = new ArrayList<>(configRepository.getPlayers());
        currentPlayer = 0;
        currentPropertyCount = 0;
    }

    /**
     *
     */
    public void reinit() {
        players = new ArrayList<>(configRepository.getPlayers());
    }

    /**
     *
     */
    public void incrPlayer() {
        if (currentPlayer == players.size() - 1)
            incrPropertyCount();
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
    private void incrPropertyCount() {
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
}
