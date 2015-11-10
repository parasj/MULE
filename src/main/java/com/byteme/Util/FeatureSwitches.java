package com.byteme.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * MULE
 */
public class FeatureSwitches {
    private static final FeatureSwitches OUR_INSTANCE = new FeatureSwitches();

    /**
     * Gets the instance.
     * @return The instance.
     */
    public static FeatureSwitches getInstance() {
        return OUR_INSTANCE;
    }

    private final Map<String, Boolean> switches;

    /**
     * The constructor.
     */
    private FeatureSwitches() {
        switches = new HashMap<>();
        switches.put("assay_office", false);
    }

    /**
     * Sees if it is a set.
     * @param key The key.
     * @return The boolean.
     */
    public boolean isSet(String key) {
        return switches.get(key);
    }
}
