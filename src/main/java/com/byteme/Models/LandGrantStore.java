package com.byteme.Models;

import com.byteme.Schema.PlayerConfigParams;

import java.util.ArrayList;
import java.util.List;

/**
 * MULE
 */
public class LandGrantStore {
    private final static ConfigRepository configRepository = ConfigRepository.getInstance();

    private static final LandGrantStore ourInstance = new LandGrantStore();
    public static LandGrantStore getInstance() {
        return ourInstance;
    }

    private LandGrantStore() {
        players = new ArrayList<PlayerConfigParams>(configRepository.getPlayers());
        currentPlayer = 0;
        currentPropertyCount = 0;
    }

    private List<PlayerConfigParams> players;
    private int currentPlayer;
    private int currentPropertyCount;

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
