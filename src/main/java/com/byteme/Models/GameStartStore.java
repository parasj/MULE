package com.byteme.Models;

import com.byteme.Schema.PlayerConfigParams;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by rishavbose365 on 10/5/2015.
 */
public class GameStartStore {
    private static GameStartStore ourInstance = new GameStartStore();

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
