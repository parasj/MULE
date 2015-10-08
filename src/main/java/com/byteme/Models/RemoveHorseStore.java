package com.byteme.Models;

import com.byteme.Schema.Mule;

/**
 * Created by rishav on 10/7/2015.
 */
public class RemoveHorseStore {
    private static RemoveHorseStore ourInstance = new RemoveHorseStore();

    public static RemoveHorseStore getInstance() {
        return ourInstance;
    }

    private static Mule mule;
    private static  boolean toStore;

    private RemoveHorseStore() {

    }

    public static Mule getMule() {
        return mule;
    }

    public static void setMule(Mule mule) {
        RemoveHorseStore.mule = mule;
    }

    public static boolean isToStore() {
        return toStore;
    }

    public static void setToStore(boolean toStore) {
        RemoveHorseStore.toStore = toStore;
    }
}
