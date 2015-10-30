package com.byteme.Models;

/**
 * MULE
 */
public class MULEStore {
    private static MULEStore ourInstance = new MULEStore();
    public static MULEStore getInstance() {
        return ourInstance;
    }
    private MULEStore() {}


}
