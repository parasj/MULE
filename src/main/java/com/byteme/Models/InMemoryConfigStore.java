package com.byteme.Models;

import java.util.logging.Logger;

/**
 * Created by parasjain on 9/9/15.
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
