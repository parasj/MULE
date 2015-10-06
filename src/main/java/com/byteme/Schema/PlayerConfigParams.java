package com.byteme.Schema;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by parasjain on 9/9/15.
 */
public class PlayerConfigParams implements Comparable<PlayerConfigParams> {
    private String name;
    private Race race;
    private String color;
    private int money;
    private int timeLeft;

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
        //TODO Take into account resources
        return (this.money + 500 * properties.size());
    }

    public void calcTimeLeft() {
        //TODO calculate the time left
        this.timeLeft = 25;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    @Override
    public int compareTo(PlayerConfigParams otherPlayer) {
        return (this.calcScore() - otherPlayer.calcScore());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerConfigParams that = (PlayerConfigParams) o;

        if (money != that.money) return false;
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
        result = 31 * result + order;
        result = 31 * result + (properties != null ? properties.hashCode() : 0);
        return result;
    }
}
