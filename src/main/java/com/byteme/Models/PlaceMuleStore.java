package com.byteme.Models;

import com.byteme.Schema.Mule;

import java.io.Serializable;

/**
 * MULE
 */
public class PlaceMuleStore implements Serializable{

    private Mule mule;

    /**
     *
     */
    public PlaceMuleStore() {}

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
    public void setMule(Mule mule) {
        if (mule != null) {
            this.mule = mule;
        } else {
            throw new IllegalArgumentException("Mule is null!");
        }
    }

    /**
     *
     */
    public void reinit() {}
}
