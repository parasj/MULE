package com.byteme.Models;

/**
 * Created by parasjain on 10/1/15.
 */
public class MapStateStore {
    private static MapStateStore ourInstance = new MapStateStore();

    public static MapStateStore getInstance() {
        return ourInstance;
    }

    private int currentPlayer;
    private int currentRound;
    private int currentPhase;

    private int passCounter; // Used to determine when to stop property selection immediately
    private int purchaseOpportunities; // Used to determine duration of full property selection
    private int numPlayers;
    private int timeLeft;

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public int getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(int currentPhase) {
        this.currentPhase = currentPhase;
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

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    @Override
    public String toString() {
        return "MapStateStore{" +
                "currentPlayer=" + currentPlayer +
                ", currentRound=" + currentRound +
                ", currentPhase=" + currentPhase +
                ", passCounter=" + passCounter +
                ", purchaseOpportunities=" + purchaseOpportunities +
                ", numPlayers=" + numPlayers +
                ", timeLeft=" + timeLeft +
                '}';
    }

    private MapStateStore() {

    }
}
