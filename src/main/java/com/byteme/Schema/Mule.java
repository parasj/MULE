package com.byteme.Schema;

/**
 * Mule
 */
public class Mule {
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
}
