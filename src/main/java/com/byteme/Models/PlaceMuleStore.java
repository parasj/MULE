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

    public static Mule mule;

    private PlaceMuleStore() {
    }

    public static Mule getMule() {
        return mule;
    }

    public static void setMule(Mule mule) {
        PlaceMuleStore.mule = mule;
    }
}
