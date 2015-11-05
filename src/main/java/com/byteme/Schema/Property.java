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
    private MapTile maptile;

    /**
     *
     * @param column
     * @param row
     * @param owner
     * @param mapTile
     */
    public Property(int column, int row, PlayerConfigParams owner, MapTile mapTile) {
        this.row = row;
        this.column = column;
        this.owner = owner;
        this.mule = null;
        this.maptile = mapTile;
    }

    /**
     *
     * @return
     */
    public int getColumn() {
        return this.column;
    }

    /**
     *
     * @return
     */
    public int getRow() {
        return this.row;
    }

    /**
     *
     * @return
     */
    public PlayerConfigParams getOwner() {
        return this.owner;
    }

    /**
     *
     * @param column
     */
    public void setColumn(int column) {
        if (column < 0) {
            this.column = 0;
        } else {
            this.column = column;
        }
    }

    /**
     *
     * @param row
     */
    public void setRow(int row) {
        if (row < 0) {
            this.row = 0;
        } else {
            this.row = row;
        }
    }
    public void setOwner(PlayerConfigParams owner) {
        if (owner != null) {
            this.owner = owner;
        } else {
            throw new IllegalArgumentException("Owner can't be null!");
        }
    }

    /**
     *
     * @return
     */
    public Mule getMule() {
        return mule;
    }

    /**
     *
     * @param mule
     */
    public void addMule(Mule mule) {
        if (mule != null) {
            this.mule = mule;
        } else {
            removeMule();
        }
    }

    /**
     *
     */
    public void removeMule() {
        mule = null;
    }

    /**
     *
     * @return
     */
    public boolean hasMule() {
        return this.mule != null;
    }

    /**
     *
     * @return
     */
    public MapTile getMaptile() {
        return maptile;
    }

    /**
     *
     * @param maptile
     */
    public void setMaptile(MapTile maptile) {
        if (maptile != null) {
            this.maptile = maptile;
        } else {
            throw new IllegalArgumentException("Map Tile cannot be null!");
        }
    }

    /**
     *
     * @param o
     * @return
     */
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

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int result = column;
        result = 31 * result + row;
        result = 31 * result + owner.hashCode();
        result = 31 * result + (mule != null ? mule.hashCode() : 0);
        return result;
    }
}
