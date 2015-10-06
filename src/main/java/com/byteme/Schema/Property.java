package com.byteme.Schema;

/**
 * MULE
 */
public class Property {
    private int column;
    private int row;
    private PlayerConfigParams owner;
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
