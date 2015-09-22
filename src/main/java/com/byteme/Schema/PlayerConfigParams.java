package com.byteme.Schema;

import javafx.scene.paint.Color;

/**
 * Created by parasjain on 9/9/15.
 */
public class PlayerConfigParams {
    private String name;
    private Race race;
    private String color;
    private int money;

    public PlayerConfigParams(String name, Race race, String color, int money) {
        this.name = name;
        this.race = race;
        this.color = color;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public Race getRace() {
        return race;
    }

    public String getColor() {
        return color;
    }

    public int getMoney() {return money;}

    public void payMoney(int cost) {
        money = money - cost;
    }

    public String toString() {
        return color + " " + race + " named " + name;
    }
}
