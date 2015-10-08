package com.byteme.Models;

import com.byteme.Schema.Mule;

/**
 * Created by rishavbose365 on 10/7/2015.
 */
public class PlaceMuleStore {
    private static PlaceMuleStore ourInstance = new PlaceMuleStore();

    public static PlaceMuleStore getInstance() {
        return ourInstance;
    }

    private Mule mule;
    private boolean toStore;

    private PlaceMuleStore() {
    }

    public Mule getMule() {
        return mule;
    }

    public void setMule(Mule mule) {
        this.mule = mule;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}
