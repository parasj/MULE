package com.byteme.Config;

/**
 * Created by parasjain on 9/9/15.
 */
public interface ConfigStore {
    public void save(ConfigRepository repo);
    public void restore();
}
