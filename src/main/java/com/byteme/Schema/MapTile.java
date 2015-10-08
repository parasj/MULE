package com.byteme.Schema;

import java.util.HashMap;
import java.util.Map;

/**
 * MULE
 */
public enum MapTile {
    P, M1, M2, M3, R, Town;

    private final Map<String, String> imagePath;
    private final Map<String, String> imagePathWithMule;

    MapTile() {
        imagePathWithMule = new HashMap<>();
        imagePath = new HashMap<>();

        imagePath.put("P", "/images/Plain.png");
        imagePath.put("M1", "/images/Mountain1.png");
        imagePath.put("M2", "/images/Mountain2.png");
        imagePath.put("M3", "/images/Mountain3.png");
        imagePath.put("R", "/images/River.png");
        imagePath.put("Town", "/images/Town.png");

        imagePathWithMule.put("P", "/images/Plain_mule.png");
        imagePathWithMule.put("M1", "/images/Mountain1_mule.png");
        imagePathWithMule.put("M2", "/images/Mountain2_mule.png");
        imagePathWithMule.put("M3", "/images/Mountain3_mule.png");
        imagePathWithMule.put("R", "/images/River_mule.png");
    }

    public String imagePath() {
        return imagePath(false);
    }

    @Override
    public String toString() {
        return EnumHumanizer.humanizeEnum(super.toString());
    }

    public String imagePath(boolean mule) {
        if (mule) return imagePathWithMule.getOrDefault(this.name(), imagePathWithMule.get("P"));
        else return imagePath.getOrDefault(this.name(), imagePath.get("P"));
    }
}
