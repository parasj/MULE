package com.byteme.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by parasjain on 9/18/15.
 */
public class FeatureSwitches {
    private static FeatureSwitches ourInstance = new FeatureSwitches();
    public static FeatureSwitches getInstance() {
        return ourInstance;
    }

    private Map<String, Boolean> switches;

    private FeatureSwitches() {
        switches = new HashMap<>();
        switches.put("assay_office", false);
    }

    public boolean isSet(String key) {
        return switches.get(key);
    }
}
