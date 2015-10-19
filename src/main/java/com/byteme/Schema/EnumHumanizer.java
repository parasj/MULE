package com.byteme.Schema;

/**
 * MULE
 */
class EnumHumanizer {
    public static String humanizeEnum(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
