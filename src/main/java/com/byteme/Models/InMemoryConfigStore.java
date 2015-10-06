package com.byteme.Models;

import java.util.logging.Logger;

/**
 * MULE
 */

// TODO: implement saving functionality

public class InMemoryConfigStore implements ConfigStore {
    private final static Logger log = Logger.getLogger(InMemoryConfigStore.class.getName());

    public void save(ConfigRepository repo) {
        // log.info("Saving game configuration");
    }

    public void restore() {

    }
}
