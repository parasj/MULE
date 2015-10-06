package com.byteme.Models;

/**
 * MULE
 */
public class GameStartStore {
    private static final GameStartStore ourInstance = new GameStartStore();

    private int currentPlayer;

    public static GameStartStore getInstance() {
        return ourInstance;
    }


    private GameStartStore() {
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void incCurrentPlayer() {
        currentPlayer = (currentPlayer + 1) % ConfigRepository.getInstance().getTotalPlayers();
    }
}
