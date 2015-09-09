package com.byteme.Config;

import com.byteme.EnumHumanizer;

/**
 * Created by parasjain on 9/9/15.
 */
public enum Difficulty {
    BEGINNER, STANDARD, TOURNAMENT;

    @Override
    public String toString() {
        return EnumHumanizer.humanizeEnum(super.toString());
    }
}
