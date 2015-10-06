package com.byteme.Schema;

/**
 * MULE
 */
public enum MapType {
    STANDARD, RANDOM;


    @Override
    public String toString() {
        return EnumHumanizer.humanizeEnum(super.toString());
    }
}
