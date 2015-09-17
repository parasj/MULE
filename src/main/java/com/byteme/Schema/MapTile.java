package com.byteme.Schema;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by parasjain on 9/16/15.
 */
public enum MapTile {
    P, M1, M2, M3, R, Town;

    private Map<String, String> imagePath;

    MapTile() {
        imagePath = new HashMap<>();
        imagePath.put("P", "/images/Plain.png");
        imagePath.put("M1", "/images/Mountain.png");
        imagePath.put("M2", "/images/Mountain.png");
        imagePath.put("M3", "/images/Mountain.png");
        imagePath.put("R", "/images/River.png");
        imagePath.put("Town", "/images/Town.png");
    }

    public String imagePath() {
        return imagePath.getOrDefault(this.name(), imagePath.get("P"));
    }

    @Override
    public String toString() {
        return EnumHumanizer.humanizeEnum(super.toString());
    }
}
