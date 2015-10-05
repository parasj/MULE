package com.byteme.Models;

import com.byteme.Schema.PlayerConfigParams;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by rishavbose365 on 10/5/2015.
 */
public class GameStartStore {
    private static GameStartStore ourInstance = new GameStartStore();

    public static GameStartStore getInstance() {
        return ourInstance;
    }

    public ConfigRepository r = ConfigRepository.getInstance();

    public ArrayList<PlayerConfigParams> players;
    private GameStartStore() {
        players = new ArrayList<>(r.getPlayers());
    }

    public void sort() {
        Collections.sort(players);
    }

    public PlayerConfigParams get(int index) {
        return players.get(index);
    }


}
