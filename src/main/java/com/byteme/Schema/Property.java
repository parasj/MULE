package com.byteme.Schema;

/**
 * Created by Rishav on 9/22/2015.
 */
public class Property {
    public int column;
    public int row;
    public PlayerConfigParams owner;
    public Property(int column, int row, PlayerConfigParams owner) {
        this.row = row;
        this.column = column;
        this.owner = owner;
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
}
