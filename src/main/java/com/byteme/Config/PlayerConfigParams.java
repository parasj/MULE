package com.byteme.Config;

import javafx.scene.paint.Color;

/**
 * Created by parasjain on 9/9/15.
 */
public class PlayerConfigParams {
    private String name;
    private Race race;
    private Color color;

    public PlayerConfigParams(String name, Race race, Color color) {
        this.name = name;
        this.race = race;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Race getRace() {
        return race;
    }

    public Color getColor() {
        return color;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
