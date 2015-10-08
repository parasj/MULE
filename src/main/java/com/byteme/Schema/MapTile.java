package com.byteme.Schema;

import java.util.HashMap;
import java.util.Map;

/**
 * MULE
 */
public enum MapTile {
    P, M1, M2, M3, R, Town;

    private final Map<String, String> imagePath;

    MapTile() {
        imagePath = new HashMap<>();
        imagePath.put("P", "/images/Plain.png");
        imagePath.put("M1", "/images/Mountain1.png");
        imagePath.put("M2", "/images/Mountain2.png");
        imagePath.put("M3", "/images/Mountain3.png");
        imagePath.put("R", "/images/River.png");
        imagePath.put("Town", "/images/Town.png");
    }

    public String imagePath() {
        return imagePath(false);
    }

    @Override
    public String toString() {
        return EnumHumanizer.humanizeEnum(super.toString());
    }

    public String imagePath(boolean mule) {
        String path = imagePath.getOrDefault(this.name(), imagePath.get("P"));
        if (mule) {
            // add a mule to the path
            return "/images/Town.png";
        } else {
            return path;
        }
    }
}
