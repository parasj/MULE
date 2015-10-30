package com.byteme.Models;

import com.byteme.Schema.Mule;

import java.io.Serializable;

/**
 * MULE
 */
public class PlaceMuleStore implements Serializable{

    private Mule mule;

    private PlaceMuleStore() {}

    public Mule getMule() {
        return mule;
    }

    public void setMule(Mule mule) {
        this.mule = mule;
    }
}
