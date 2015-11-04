package com.byteme.Schema;

/**
 * MULE
 */
public enum Race {
    FLAPPER, HUMAN, BONZOID, UGAITE, BUZZITE;

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return EnumHumanizer.humanizeEnum(super.toString());
    }
}
