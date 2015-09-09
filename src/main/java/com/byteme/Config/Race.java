package com.byteme.Config;

import com.byteme.EnumHumanizer;

/**
 * Created by parasjain on 9/9/15.
 */
public enum Race {
    FLAPPER, HUMAN, BONZOID, UGAITE, BUZZITE;

    @Override
    public String toString() {
        return EnumHumanizer.humanizeEnum(super.toString());
    }
}
