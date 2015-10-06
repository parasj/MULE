package com.byteme.Models;

import com.byteme.Schema.MapControllerStates;
import com.byteme.Schema.PlayerConfigParams;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by parasjain on 10/1/15.
 */
public class MapStateStore {
    private static MapStateStore ourInstance = new MapStateStore();

    public static MapStateStore getInstance() {
        return ourInstance;
    }

    public MapControllerStates getCurrentState() {
        return currentState;
    }

    public void setCurrentState(MapControllerStates currentState) {
        this.currentState = currentState;
    }

    private MapControllerStates currentState;

    public boolean isFromTownGoToPub() {
        return fromTownGoToPub;
    }

    public void setFromTownGoToPub(boolean fromTownGoToPub) {
        this.fromTownGoToPub = fromTownGoToPub;
    }

    private boolean fromTownGoToPub;

    private int currentPlayer;
    private int currentRound;

    private int passCounter; // Used to determine when to stop property selection immediately
    private int purchaseOpportunities; // Used to determine duration of full property selection
    private int numPlayers;
    public ArrayList<PlayerConfigParams> players;
    public ConfigRepository r = ConfigRepository.getInstance();

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer % r.getTotalPlayers();
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public int getPassCounter() {
        return passCounter;
    }

    public void setPassCounter(int passCounter) {
        this.passCounter = passCounter;
    }

    public int getPurchaseOpportunities() {
        return purchaseOpportunities;
    }

    public void setPurchaseOpportunities(int purchaseOpportunities) {
        this.purchaseOpportunities = purchaseOpportunities;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    @Override
    public String toString() {
        return "MapStateStore{" +
                "currentPlayer=" + currentPlayer +
                ", currentRound=" + currentRound +
                ", passCounter=" + passCounter +
                ", purchaseOpportunities=" + purchaseOpportunities +
                ", numPlayers=" + numPlayers + '}';
    }

    private MapStateStore() {
    }

    public void sortPlayers() {
        Collections.sort(players);
    }

    public PlayerConfigParams getPlayerAt(int index) {
        return players.get(index);
    }


    //TODO FIX me
    public void refresh() {
        this.players = new ArrayList<>(r.getPlayers());
    }
}
