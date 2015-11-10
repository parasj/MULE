package com.byteme.Models;

import com.byteme.Schema.MapControllerStates;
import com.byteme.Schema.PlayerConfigParams;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * MULE
 */
public class MapStateStore implements Serializable {
    private final ConfigRepository configRepository;
    private MapControllerStates currentState;
    private boolean fromTownGoToPub;
    private int currentPlayer;
    private int currentRound = 1;
    private int passCounter; // Used to determine when to stop property selection immediately
    private int purchaseOpportunities; // Used to determine duration of full property selection
    private int numPlayers;
    private ArrayList<PlayerConfigParams> players;

    public MapStateStore(ConfigRepository configRepository, int currentRound, ArrayList<PlayerConfigParams> players) {
        this.currentRound = currentRound;
        this.configRepository = configRepository;
        this.players = players;
    }

    /**
     *
     * @param configRepository
     */
    public MapStateStore(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    /**
     *
     * @return
     */
    public MapControllerStates getCurrentState() {
        return currentState;
    }

    /**
     *
     * @return
     */
    public boolean isFromTownGoToPub() {
        return fromTownGoToPub;
    }

    /**
     *
     * @param fromTownGoToPub
     */
    public void setFromTownGoToPub(boolean fromTownGoToPub) {
        this.fromTownGoToPub = fromTownGoToPub;
    }

    /**
     *
     * @return
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     *
     * @param currentPlayer
     */
    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer % configRepository.getTotalPlayers();
    }

    /**
     *
     * @return
     */
    public int getCurrentRound() {
        return currentRound;
    }

    /**
     *
     * @param currentRound
     */
    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    /**
     *
     * @return
     */
    public int getPassCounter() {
        return passCounter;
    }

    /**
     *
     * @param passCounter
     */
    public void setPassCounter(int passCounter) {
        this.passCounter = passCounter;
    }

    /**
     *
     * @return
     */
    public int getPurchaseOpportunities() {
        return purchaseOpportunities;
    }

    /**
     *
     * @param purchaseOpportunities
     */
    public void setPurchaseOpportunities(int purchaseOpportunities) {
        this.purchaseOpportunities = purchaseOpportunities;
    }

    /**
     *
     * @return
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     *
     * @param numPlayers
     */
    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    /**
     *
     * @param currentState
     */
    public void setCurrentState(MapControllerStates currentState) {
        if (currentState != null) {
            this.currentState = currentState;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "MapStateStore{" +
                "currentPlayer=" + currentPlayer +
                ", currentRound=" + currentRound +
                ", passCounter=" + passCounter +
                ", purchaseOpportunities=" + purchaseOpportunities +
                ", numPlayers=" + numPlayers + '}';
    }

    /**
     *
     */
    public void sortPlayers() {
        Collections.sort(players);
        for (int index = 0; index < players.size(); index++) {
            players.get(index).setOrder(index + 1);
        }
    }

    /**
     *
     * @param index
     * @return
     */
    public PlayerConfigParams getPlayerAt(int index) {
        if (index < players.size()) {
            return players.get(index);
        } else {
            throw new ArrayIndexOutOfBoundsException(index + " is less than size: " + players.size());
        }
    }

    /**
     *
     */
    public void refresh() {
        this.players = new ArrayList<>(configRepository.getPlayers());
    }

    /**
     *
     * @return
     */
    public ArrayList<PlayerConfigParams> getPlayers() {
        return players;
    }

    /**
     *
     */
    public void reinit() {
        refresh();
    }


    public void incRound() {
        currentRound++;
    }
}
