package com.byteme.Schema;

import java.io.Serializable;

/**
 * MULE
 */
public class Property implements Serializable {
    private int column;
    private int row;
    private PlayerConfigParams owner;
    private Mule mule;
    private MapTile mapTile;

    /**
     * Creates a property object.
     * @param column The column
     * @param row The row
     * @param owner The owner
     * @param mapTile The MapTile
     */
    public Property(int column, int row, PlayerConfigParams owner, MapTile mapTile) {
        this.row = row;
        this.column = column;
        this.owner = owner;
        this.mule = null;
        this.mapTile = mapTile;
    }

    /**
     * Gets the column.
     * @return The column
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Gets the row.
     * @return The row
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Gets the owner.
     * @return The owner
     */
    public PlayerConfigParams getOwner() {
        return this.owner;
    }

    /**
     * Sets the column.
     * @param column The column
     */
    public void setColumn(int column) {
        if (column < 0) {
            this.column = 0;
        } else {
            this.column = column;
        }
    }

    /**
     * Sets the row.
     * @param row The row
     */
    public void setRow(int row) {
        if (row < 0) {
            this.row = 0;
        } else {
            this.row = row;
        }
    }

    /**
     * Sets the owner.
     * @param owner The owner
     */
    public void setOwner(PlayerConfigParams owner) {
        if (owner != null) {
            this.owner = owner;
        } else {
            throw new IllegalArgumentException("Owner can't be null!");
        }
    }

    /**
     * Gets the mule.
     * @return The mule
     */
    public Mule getMule() {
        return mule;
    }

    /**
     * Adds a mule.
     * @param mule The mule
     */
    public void addMule(Mule mule) {
        if (mule != null) {
            this.mule = mule;
        } else {
            removeMule();
        }
    }

    /**
     * Removes a mule.
     */
    private void removeMule() {
        mule = null;
    }

    /**
     * Checks if there is a mule.
     * @return Is there a mule?
     */
    public boolean hasMule() {
        return this.mule != null;
    }

    /**
     * Gets the MapTile.
     * @return The MapTile
     */
    public MapTile getMapTile() {
        return mapTile;
    }

    /**
     * Sets the MapTile.
     * @param mapTile The MapTile
     */
    public void setMapTile(MapTile mapTile) {
        if (mapTile != null) {
            this.mapTile = mapTile;
        } else {
            throw new IllegalArgumentException("Map Tile cannot be null!");
        }
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
