package com.byteme.Schema;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * MULE
 */
public class PlayerConfigParams implements Comparable<PlayerConfigParams>, Serializable {
    private final String name;
    private final Race race;
    private final String color;
    private int money;
    private int timeLeft;

    private int food;
    private int energy;
    private int smithore;
    private int crystite;
    private int order;
    private ArrayList<Property> properties;

    /**
     * Creates a PlayerConfiguration Parameters object.
     * @param name The name of the player.
     * @param race The race of the player.
     * @param color The color of the player.
     * @param money The money of the player.
     * @param properties The properties of the player.
     * @param order The order of the player.
     */
    public PlayerConfigParams(String name, Race race, String color, int money, ArrayList<Property> properties, int order) {
        this.name = name;
        this.race = race;
        this.color = color;
        this.money = money;
        this.properties = properties;
        this.order = order;
    }

    /**
     * Gets the order of the player.
     * @return The order
     */
    public int getOrder() {
        return order;
    }

    /**
     * Sets the order of the player.
     * @param order The order
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Gets the name of the player.
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the race of the player.
     * @return The race
     */
    public Race getRace() {
        return race;
    }

    /**
     * Gets the color of the player.
     * @return The color
     */
    public String getColor() {
        return color;
    }

    /**
     * Gets the money of the player.
     * @return The money
     */
    public int getMoney() {
        return money;
    }

    /**
     * Sets the money of the player.
     * @param money The money
     */
    public void setMoney(int money) {
        if (money < 0) {
            this.money = 0;
        } else {
            this.money = money;
        }
    }

    /**
     * Makes the player pay money.
     * @param cost Amount of money to be lost.
     */
    public void payMoney(int cost) {
        if (money > cost) {
            this.money = this.money - cost;
        } else {
            throw new IllegalArgumentException("Cost is less than money");
        }
    }

    /**
     * Lets the player make money.
     * @param cost Amount to increase player money by.
     */
    public void makeMoney(int cost) {
        this.money = this.money + cost;
    }

    /**
     * Returns list of properties owned.
     * @return Player's properties.
     */
    public ArrayList<Property> getProperties() {
        return properties;
    }

    /**
     * Sets the list of properties.
     * @param properties The list of properties.
     */
    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    /**
     * Adds a property.
     * @param property The property
     */
    public void addProperty(Property property) {
        if (property != null) {
            this.properties.add(property);
        } else {
            throw new IllegalArgumentException("Property cannot be null!");
        }
    }

    /**
     * Removes a property if possible.
     * @param property The property to remove.
     * @return Whether or not the property was removed.
     */
    public boolean removeProperty(Property property) {
        return this.properties.remove(property);
    }

    public String toString() {
        return color + " " + race + " named " + name;
    }

    /**
     * Calculates the score.
     * @return The score of the player.
     */
    public int calcScore() {
        return (this.money + 500 * properties.size()) + this.crystite + this.energy + this.food + this.smithore;
    }

    /**
     * Calculates the time left.
     */
    public void calcTimeLeft() {
        //TODO calculate the time left
        this.timeLeft = 50;
    }

    /**
     * Gets the time left for the player.
     * @return The time left
     */
    public int getTimeLeft() {
        return timeLeft;
    }

    /**
     * Sets the time left.
     * @param timeLeft The time left
     */
    public void setTimeLeft(int timeLeft) {
        if (timeLeft < 0) {
            this.timeLeft = 0;
        } else {
            this.timeLeft = timeLeft;
        }
    }

    /**
     * Gets the food.
     * @return Amount food
     */
    public int getFood() {
        return food;
    }

    /**
     * Sets the amount of food.
     * @param food The food amount
     */
    public void setFood(int food) {
        if (food < 0) {
            this.food = 0;
        } else {
            this.food = food;
        }
    }

    /**
     * Gets the amount of energy.
     * @return The amount of energy.
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * Sets the amount of energy.
     * @param energy The amount of energy.
     */
    public void setEnergy(int energy) {
        if (energy < 0) {
            this.energy = 0;
        } else {
            this.energy = energy;
        }
    }

    /**
     * Gets the amount of smith ore.
     * @return The amount of smith ore.
     */
    public int getSmithore() {
        return smithore;
    }

    /**
     * Sets the amount of smith ore.
     * @param smithore The amount of smith ore.
     */
    public void setSmithore(int smithore) {
        if (smithore < 0) {
            this.smithore = 0;
        } else {
            this.smithore = smithore;
        }
    }

    /**
     * Gets the amount of crystite.
     * @return The amount of crystite
     */
    public int getCrystite() {
        return crystite;
    }

    /**
     * Sets the amount of crystite.
     * @param crystite The amount of crystite
     */
    public void setCrystite(int crystite) {
        if (crystite < 0) {
            this.crystite = 0;
        } else {
            this.crystite = crystite;
        }
    }

    /**
     * Increments the amount of food.
     */
    public void addFood() {
        this.food++;
    }

    /**
     * Increments the amount of energy.
     */
    public void addEnergy() {
        this.energy++;
    }

    /**
     * Increments the amount of smith ore.
     */
    public void addSmithore() {
        this.smithore++;
    }

    /**
     * Increments the amount of crystite.
     */
    public void addCrystite() {
        this.crystite++;
    }

    /**
     * Decrements the amount of food by one.
     */
    public void subFood() {
        setFood(food - 1);
    }

    /**
     * Decrements the amount of energy by one.
     */
    public void subEnergy() {
        setEnergy(energy - 1);
    }

    /**
     * Decrements the amount of smith ore by one.
     */
    public void subSmithore() {
        setSmithore(smithore - 1);
    }

    /**
     * Decrements the amount of crystite by one.
     */
    public void subCrystite() {
        setCrystite(crystite - 1);
    }

    /**
     * Gets the amount of resources in string form.
     * @return Resources as a string.
     */
    public String getResources() {
        return "Player: " + getName() + "\n"
                + "Food: " + getFood() + "\n"
                + "Energy: " + getEnergy() + "\n"
                + "Smithore: " + getSmithore() + "\n"
                + "Crystite: " + getCrystite() + "\n"
                + "==================================================";
    }

    @Override
    public int compareTo(PlayerConfigParams otherPlayer) {
        return (this.calcScore() - otherPlayer.calcScore());
    }

    /**
     * Get the number of mules.
     * @return The number of mules.
     */
    public int getMuleCount() {
        int muleCount = 0;
        for (Property p : properties) {
            if (p.getMule() != null) {
                muleCount++;
            }
        }
        return muleCount;
    }

    /**
     * hashCode to fix findBugs
     * @return 42
     */
    @Override
    public int hashCode() {
        assert false : "hashCode not designed";
        return 42; // any arbitrary constant will do
    }
}
