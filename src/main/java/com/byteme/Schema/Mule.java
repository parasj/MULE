package com.byteme.Schema;

import java.io.Serializable;

/**
 * Mule
 */
public class Mule implements Serializable {
    private MuleType type;

    /**
     * Creates a Mule.
     * @param type The type of mule.
     */
    public Mule(MuleType type) {
        this.type = type;
    }

    /**
     * Gets the type of the Mule.
     * @return The type of the Mule
     */
    public MuleType getType() {
        return type;
    }

    /**
     * Sets the type of the Mule.
     * @param type The type of the Mule
     */
    public void setType(MuleType type) {
        if (type != null) {
            this.type = type;
        } else {
            throw new IllegalArgumentException("Type is null!");
        }
    }

    @Override
    public String toString() {
        return "Mule{" +
                "type=" + type +
                '}';
    }
}
