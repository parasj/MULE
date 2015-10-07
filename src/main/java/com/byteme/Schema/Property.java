package com.byteme.Schema;

import java.util.ArrayList;

/**
 * MULE
 */
public class Property {
    private int column;
    private int row;
    private PlayerConfigParams owner;
    private Mule mule;
    public Property(int column, int row, PlayerConfigParams owner, Mule mules) {
        this.row = row;
        this.column = column;
        this.owner = owner;
        this.mule = mule;
    }
    public int getColumn() {
        return this.column;
    }
    public int getRow() {
        return this.column;
    }
    public PlayerConfigParams getOwner() {
        return this.owner;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setOwner(PlayerConfigParams owner) {
        this.owner = owner;
    }

    public Mule getMule() {
        return mule;
    }

    public void setMule(Mule mule) {
        this.mule = mule;
    }

    public boolean hasMule() {
        return this.mule != null;
    }
}
