package com.byteme.Schema;

import java.io.Serializable;

/**
 * Mule
 */
public class Mule implements Serializable {
    private MuleType type;
    public Mule(MuleType type) {
        this.type = type;
    }

    public MuleType getType() {
        return type;
    }

    public void setType(MuleType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Mule{" +
                "type=" + type +
                '}';
    }
}
