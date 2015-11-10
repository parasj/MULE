package com.byteme.Schema;

/**
 * MULE MapType enum.
 */
public enum MapType {
    STANDARD, RANDOM;

    @Override
    public String toString() {
        return EnumHumanizer.humanizeEnum(super.toString());
    }
}
