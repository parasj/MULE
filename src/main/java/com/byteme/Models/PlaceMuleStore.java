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

    private static Mule mule;
    private static  boolean toStore;

    private PlaceMuleStore() {
    }

    public Mule getMule() {
        return mule;
    }

    public static void setMule(Mule mule) {
        PlaceMuleStore.mule = mule;
    }

    public static boolean getToStore() {
        return toStore;
    }

    public static void setToStore(boolean toStore) {
        PlaceMuleStore.toStore = toStore;
    }
}
