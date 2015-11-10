package com.byteme.Schema;

/**
 * MULE Race enum.
 */
public enum Race {
    FLAPPER, HUMAN, BONZOID, UGAITE, BUZZITE;

    @Override
    public String toString() {
        return EnumHumanizer.humanizeEnum(super.toString());
    }
}
