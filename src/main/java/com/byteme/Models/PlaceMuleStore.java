package com.byteme.Models;

import com.byteme.Schema.Mule;

import java.io.Serializable;

/**
 * MULE
 */
public class PlaceMuleStore implements Serializable{

    private Mule mule;

    /**
     * Creates a PlaceMuleStore.
     */
    public PlaceMuleStore() {}

    /**
     * Gets the mule.
     * @return The mule.
     */
    public Mule getMule() {
        return mule;
    }

    /**
     * Sets the mule.
     * @param mule The mule to set to.
     */
    public void setMule(Mule mule) {
        if (mule != null) {
            this.mule = mule;
        } else {
            throw new IllegalArgumentException("Mule is null!");
        }
    }
}
