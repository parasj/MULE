package com.byteme.Models;

/**
 * MULE
 */
public interface ConfigStore {
    void save(ConfigRepository repo);
    void restore();
}
