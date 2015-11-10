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
     *
     * @param name
     * @param race
     * @param color
     * @param money
     * @param properties
     * @param order
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
     *
     * @return
     */
    public int getOrder() {
        return order;
    }

    /**
     *
     * @param order
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public Race getRace() {
        return race;
    }

    /**
     *
     * @return
     */
    public String getColor() {
        return color;
    }

    /**
     *
     * @return
     */
    public int getMoney() {
        return money;
    }

    /**
     *
     * @param money
     */
    public void setMoney(int money) {
        if (money < 0) {
            this.money = 0;
        } else {
            this.money = money;
        }
    }

    /**
     *
     * @param cost
     */
    public void payMoney(int cost) {
        if (money > cost) {
            this.money = this.money - cost;
        } else {
            throw new IllegalArgumentException("Cost is less than money");
        }
    }

    /**
     *
     * @param cost
     */
    public void makeMoney(int cost) {
        this.money = this.money + cost;
    }

    /**
     *
     * @return
     */
    public ArrayList<Property> getProperties() {
        return properties;
    }

    /**
     *
     * @param properties
     */
    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    /**
     *
     * @param property
     */
    public void addProperty(Property property) {
        if (property != null) {
            this.properties.add(property);
        } else {
            throw new IllegalArgumentException("Property cannot be null!");
        }
    }

    /**
     *
     * @param property
     * @return
     */
    public boolean removeProperty(Property property) {
        return this.properties.remove(property);
    }

    /**
     *
     * @return
     */
    public String toString() {
        return color + " " + race + " named " + name;
    }

    /**
     *
     * @return
     */
    public int calcScore() {
        return (this.money + 500 * properties.size()) + this.crystite + this.energy + this.food + this.smithore;
    }

    /**
     *
     */
    public void calcTimeLeft() {
        //TODO calculate the time left
        this.timeLeft = 50;
    }

    /**
     *
     * @return
     */
    public int getTimeLeft() {
        return timeLeft;
    }

    /**
     *
     * @param timeLeft
     */
    public void setTimeLeft(int timeLeft) {
        if (timeLeft < 0) {
            this.timeLeft = 0;
        } else {
            this.timeLeft = timeLeft;
        }
    }

    /**
     *
     * @return
     */
    public int getFood() {
        return food;
    }

    /**
     *
     * @param food
     */
    public void setFood(int food) {
        if (food < 0) {
            this.food = 0;
        } else {
            this.food = food;
        }
    }

    /**
     *
     * @return
     */
    public int getEnergy() {
        return energy;
    }

    /**
     *
     * @param energy
     */
    public void setEnergy(int energy) {
        if (energy < 0) {
            this.energy = 0;
        } else {
            this.energy = energy;
        }
    }

    /**
     *
     * @return
     */
    public int getSmithore() {
        return smithore;
    }

    /**
     *
     * @param smithore
     */
    public void setSmithore(int smithore) {
        if (smithore < 0) {
            this.smithore = 0;
        } else {
            this.smithore = smithore;
        }
    }

    /**
     *
     * @return
     */
    public int getCrystite() {
        return crystite;
    }

    /**
     *
     * @param crystite
     */
    public void setCrystite(int crystite) {
        if (crystite < 0) {
            this.crystite = 0;
        } else {
            this.crystite = crystite;
        }
    }

    /**
     *
     */
    public void addFood() {
        this.food++;
    }

    /**
     *
     */
    public void addEnergy() {
        this.energy++;
    }

    /**
     *
     */
    public void addSmithore() {
        this.smithore++;
    }

    /**
     *
     */
    public void addCrystite() {
        this.crystite++;
    }

    /**
     *
     */
    public void subFood() {
        setFood(food - 1);
    }

    /**
     *
     */
    public void subEnergy() {
        setEnergy(energy - 1);
    }

    /**
     *
     */
    public void subSmithore() {
        setSmithore(smithore - 1);
    }

    /**
     *
     */
    public void subCrystite() {
        setCrystite(crystite - 1);
    }

    /**
     *
     * @return
     */
    public String getResources() {
        return "Player: " + getName() + "\n"
                + "Food: " + getFood() + "\n"
                + "Energy: " + getEnergy() + "\n"
                + "Smithore: " + getSmithore() + "\n"
                + "Crystite: " + getCrystite() + "\n"
                + "==================================================";
    }

    /**
     * CompareTo override
     * @param otherPlayer
     * @return compare to value
     */
    @Override
    public int compareTo(PlayerConfigParams otherPlayer) {
        return (this.calcScore() - otherPlayer.calcScore());
    }

    /**
     *
     * @return
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
