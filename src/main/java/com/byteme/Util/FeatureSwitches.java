package com.byteme.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * MULE
 */
public class FeatureSwitches {
    private static final FeatureSwitches OUR_INSTANCE = new FeatureSwitches();
    public static FeatureSwitches getInstance() {
        return OUR_INSTANCE;
    }

    private final Map<String, Boolean> switches;

    private FeatureSwitches() {
        switches = new HashMap<>();
        switches.put("assay_office", false);
    }

    public boolean isSet(String key) {
        return switches.get(key);
    }
}
