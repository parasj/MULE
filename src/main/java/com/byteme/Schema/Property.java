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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Property property = (Property) o;

        if (column != property.column) return false;
        if (row != property.row) return false;
        if (!owner.equals(property.owner)) return false;
        return !(mule != null ? !mule.equals(property.mule) : property.mule != null);

    }

    @Override
    public int hashCode() {
        int result = column;
        result = 31 * result + row;
        result = 31 * result + owner.hashCode();
        result = 31 * result + (mule != null ? mule.hashCode() : 0);
        return result;
    }
}
