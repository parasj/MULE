package com.byteme.Schema;

/**
 * MULE
 */
public enum Difficulty {
    BEGINNER, STANDARD, TOURNAMENT;

    @Override
    public String toString() {
        return EnumHumanizer.humanizeEnum(super.toString());
    }
}
