package com.byteme.Schema;

/**
 * MULE
 */
public class Property {
    private int column;
    private int row;
    private PlayerConfigParams owner;
    private Mule mule;
    private MapTile maptile;
    public Property(int column, int row, PlayerConfigParams owner, MapTile mapTile) {
        this.row = row;
        this.column = column;
        this.owner = owner;
        this.mule = null;
        this.maptile = mapTile;
    }
    public int getColumn() {
        return this.column;
    }
    public int getRow() {
        return this.row;
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

    public void addMule(Mule mule) {
        if (mule != null) {
            this.mule = mule;
        } else {
            removeMule();
        }
    }

    public void removeMule() {
        mule = null;
    }

    public boolean hasMule() {
        return this.mule != null;
    }

    public MapTile getMaptile() {
        return maptile;
    }

    public void setMaptile(MapTile maptile) {
        this.maptile = maptile;
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
