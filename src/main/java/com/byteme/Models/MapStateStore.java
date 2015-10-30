package com.byteme.Models;

import com.byteme.Schema.MapControllerStates;
import com.byteme.Schema.PlayerConfigParams;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * MULE
 */
public class MapStateStore implements Serializable{

    public MapControllerStates getCurrentState() {
        return currentState;
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
    private int currentRound = 1;

    private int passCounter; // Used to determine when to stop property selection immediately
    private int purchaseOpportunities; // Used to determine duration of full property selection
    private int numPlayers;
    private ArrayList<PlayerConfigParams> players;

    public MapStateStore() {
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer % MULEStore.getInstance().getConfigRepository().getTotalPlayers();
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

    public void sortPlayers() {
        Collections.sort(players);
        for (int index = 0; index < players.size(); index++) {
            players.get(index).setOrder(index + 1);
        }
    }

    public PlayerConfigParams getPlayerAt(int index) {
        return players.get(index);
    }

    public void refresh() {
//        this.players = new ArrayList<>(MULEStore.getInstance().getConfigRepository().getPlayers());
    }

    public ArrayList<PlayerConfigParams> getPlayers() {
        return players;
    }

    public void reinit() {
        refresh();
    }
}
