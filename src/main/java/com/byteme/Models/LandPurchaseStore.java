package com.byteme.Models;

import com.byteme.Schema.PlayerConfigParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rishav on 10/3/2015.
 */
public class LandPurchaseStore {
    private final static ConfigRepository configRepository = ConfigRepository.getInstance();
    private static LandPurchaseStore ourInstance = new LandPurchaseStore();

    private List<PlayerConfigParams> players;
    private int currentPlayer;
    private int currentPropertyCount;

    public static LandPurchaseStore getInstance() {
        return ourInstance;
    }

    private LandPurchaseStore() {
        players = new ArrayList<PlayerConfigParams>(configRepository.getPlayers());
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
}
