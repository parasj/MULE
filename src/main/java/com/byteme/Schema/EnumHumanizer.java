package com.byteme.Schema;

/**
 * Created by parasjain on 9/9/15.
 */
public class EnumHumanizer {
    public static String humanizeEnum(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}