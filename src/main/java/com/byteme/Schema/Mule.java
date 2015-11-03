package com.byteme.Schema;

import java.io.Serializable;

/**
 * Mule
 */
public class Mule implements Serializable {
    private MuleType type;

    /**
     *
     * @param type
     */
    public Mule(MuleType type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public MuleType getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(MuleType type) {
        if (type != null) {
            this.type = type;
        } else {
            throw new IllegalArgumentException("Type is null!");
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Mule{" +
                "type=" + type +
                '}';
    }
}
