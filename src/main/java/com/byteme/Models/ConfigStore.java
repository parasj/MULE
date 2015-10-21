package com.byteme.Models;

/**
 * MULE
 */
interface ConfigStore {
    void save(ConfigRepository repo);
    void restore();
}
