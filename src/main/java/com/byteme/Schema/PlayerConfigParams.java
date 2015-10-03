package com.byteme.Schema;

import java.util.ArrayList;

/**
 * Created by parasjain on 9/9/15.
 */
public class PlayerConfigParams {
    private String name;
    private Race race;
    private String color;
    private int money;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    private int order;
    private ArrayList<Property> properties;

    public PlayerConfigParams(String name, Race race, String color, int money, ArrayList<Property> properties) {
        this.name = name;
        this.race = race;
        this.color = color;
        this.money = money;
        this.properties = properties;
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
}
