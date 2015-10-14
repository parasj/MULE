package com.byteme.Schema;

import java.util.ArrayList;

/**
 * MULE
 */
public class PlayerConfigParams implements Comparable<PlayerConfigParams> {
    private final String name;
    private final Race race;
    private final String color;
    private int money;
    private int timeLeft;

    private int food;
    private int energy;
    private int smithore;
    private int crystite;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    private int order;
    private ArrayList<Property> properties;

    public PlayerConfigParams(String name, Race race, String color, int money, ArrayList<Property> properties, int order) {
        this.name = name;
        this.race = race;
        this.color = color;
        this.money = money;
        this.properties = properties;
        this.order = order;
    }

    public String getName()
    {
        return name;
    }

    public Race getRace() {

        return race;
    }

    public String getColor() {
        return color;
    }

    public int getMoney() {
        return money;
    }

    public void payMoney(int cost) {
        money = money - cost;
    }

    public void makeMoney(int cost) {
        money = money + cost;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public void addProperty(Property property) {
        this.properties.add(property);
    }

    public boolean removeProperty(Property property)
    {
        return this.properties.remove(property);
    }

    public String toString()
    {
        return color + " " + race + " named " + name;
    }

    public int calcScore() {
        return (this.money + 500 * properties.size()) + this.crystite + this.energy + this.food + this.smithore;
    }

    public void calcTimeLeft() {
        //TODO calculate the time left
        this.timeLeft = 50;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getSmithore() {
        return smithore;
    }

    public void setSmithore(int smithore) {
        this.smithore = smithore;
    }

    public int getCrystite() {
        return crystite;
    }

    public void setCrystite(int crystite) {
        this.crystite = crystite;
    }

    public void addFood() {
        this.food++;
    }

    public void addEnergy() {
        this.energy++;
    }

    public void addSmithore() {
        this.smithore++;
    }

    public void addCrystite() {
        this.crystite++;
    }

    public void subFood() {
        this.food--;
    }

    public void subEnergy() {
        this.energy--;
    }

    public void subSmithore() {
        this.smithore--;
    }

    public void subCrystite() {
        this.crystite--;
    }

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

    public int getMuleCount() {
        int muleCount = 0;
        for (Property p : properties) {
            if (p.getMule() != null) {
                muleCount++;
            }
        }
        return muleCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerConfigParams that = (PlayerConfigParams) o;

        if (money != that.money) return false;
        if (timeLeft != that.timeLeft) return false;
        if (food != that.food) return false;
        if (energy != that.energy) return false;
        if (smithore != that.smithore) return false;
        if (crystite != that.crystite) return false;
        if (order != that.order) return false;
        if (!name.equals(that.name)) return false;
        if (race != that.race) return false;
        if (!color.equals(that.color)) return false;
        return !(properties != null ? !properties.equals(that.properties) : that.properties != null);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + race.hashCode();
        result = 31 * result + color.hashCode();
        result = 31 * result + money;
        result = 31 * result + timeLeft;
        result = 31 * result + food;
        result = 31 * result + energy;
        result = 31 * result + smithore;
        result = 31 * result + crystite;
        result = 31 * result + order;
        result = 31 * result + (properties != null ? properties.hashCode() : 0);
        return result;
    }
}
