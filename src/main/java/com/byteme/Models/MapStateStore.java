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
     * Creates a MapStateStore object.
     * @param configRepository The config repository
     */
    public MapStateStore(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    /**
     * Gets the current state.
     * @return The current state
     */
    public MapControllerStates getCurrentState() {
        return currentState;
    }

    /**
     * Whether isFromTownGoToPub or not.
     * @return isFromTownGoToPub
     */
    public boolean isFromTownGoToPub() {
        return fromTownGoToPub;
    }

    /**
     * Sets FromTownGoToPub.
     * @param fromTownGoToPub True or False
     */
    public void setFromTownGoToPub(boolean fromTownGoToPub) {
        this.fromTownGoToPub = fromTownGoToPub;
    }

    /**
     * Gets the current player.
     * @return The current player
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Sets the current player.
     * @param currentPlayer The current player
     */
    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer % configRepository.getTotalPlayers();
    }

    /**
     * Gets the current round.
     * @return The current round
     */
    public int getCurrentRound() {
        return currentRound;
    }

    /**
     * Sets the current round.
     * @param currentRound The current round
     */
    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    /**
     * Gets the pass counter.
     * @return The pass counter
     */
    public int getPassCounter() {
        return passCounter;
    }

    /**
     * Sets the pass counter.
     * @param passCounter The pass counter
     */
    public void setPassCounter(int passCounter) {
        this.passCounter = passCounter;
    }

    /**
     * Gets the purchase opportunities.
     * @return The purchase opportunities
     */
    public int getPurchaseOpportunities() {
        return purchaseOpportunities;
    }

    /**
     * Sets the purchase opportunities.
     * @param purchaseOpportunities The number of purchase opportunities
     */
    public void setPurchaseOpportunities(int purchaseOpportunities) {
        this.purchaseOpportunities = purchaseOpportunities;
    }

    /**
     * Get the number of players.
     * @return The number of players.
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * Set the number of players.
     * @param numPlayers The number of players.
     */
    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    /**
     * Set the current state.
     * @param currentState The current state
     */
    public void setCurrentState(MapControllerStates currentState) {
        if (currentState != null) {
            this.currentState = currentState;
        }
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

    /**
     * Sorts the players.
     */
    public void sortPlayers() {
        Collections.sort(players);
        for (int index = 0; index < players.size(); index++) {
            players.get(index).setOrder(index + 1);
        }
    }

    /**
     * Gets the player at an index.
     * @param index The index
     * @return The player at that index
     */
    public PlayerConfigParams getPlayerAt(int index) {
        if (index < players.size()) {
            return players.get(index);
        } else {
            throw new ArrayIndexOutOfBoundsException(index + " is less than size: " + players.size());
        }
    }

    /**
     * Sets the players to a new list of players.
     */
    public void refresh() {
        this.players = new ArrayList<>(configRepository.getPlayers());
    }

    /**
     * Gets the list of players.
     * @return A list of players
     */
    public ArrayList<PlayerConfigParams> getPlayers() {
        return players;
    }

    /**
     * Refreshes the player list.
     */
    public void reinitialize() {
        refresh();
    }

    /**
     * Increments the round by one.
     */
    public void incrementRound() {
        currentRound++;
    }
}
