package com.byteme.Models;

/**
 * Created by parasjain on 9/9/15.
 */
public interface ConfigStore {
    void save(ConfigRepository repo);
    void restore();
}
