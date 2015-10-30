package com.byteme.Models;

import java.io.Serializable;

/**
 * MULE
 */
public class GameStartStore implements Serializable {
    public static GameStartStore getInstance() {
        return MULEStore.getInstance().getGameStartStore();
    }

    private int currentPlayer;

    public GameStartStore() {}

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void incCurrentPlayer() {
        setCurrentPlayer((getCurrentPlayer() + 1) % MULEStore.getInstance().getConfigRepository().getTotalPlayers());
    }

    public void reinit() {}
}
