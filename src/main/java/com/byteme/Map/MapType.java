package com.byteme.Map;

import com.byteme.EnumHumanizer;

/**
 * Created by parasjain on 9/9/15.
 */
public enum MapType {
    STANDARD, RANDOM;


    @Override
    public String toString() {
        return EnumHumanizer.humanizeEnum(super.toString());
    }
}
