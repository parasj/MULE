package com.byteme.Schema;

import java.security.SecureRandom;

/**
 * MULE
 */
public enum RoundRandomEvents {
    NOTHING, BOUNTIFUL_HARVEST, LIGHTNING;

    private int foodEffect;
    private int energyEffect;
    private int oreEffect;
    private int moneyEffect;
    private String str;
    private static final SecureRandom RANDOM = new SecureRandom();

    static {
        NOTHING.str = "No round random event occurs.";
        BOUNTIFUL_HARVEST.str = "This round brings a bountiful harvest. All players get a 1 food gift.";
        LIGHTNING.str = "Lightning strikes but it recharges your interstellar fluxcapacitors. Get 1 energy.";

        NOTHING.foodEffect = 0;
        NOTHING.energyEffect = 0;
        NOTHING.oreEffect = 0;
        NOTHING.moneyEffect = 0;

        BOUNTIFUL_HARVEST.foodEffect = 1;
        BOUNTIFUL_HARVEST.energyEffect = 0;
        BOUNTIFUL_HARVEST.oreEffect = 0;
        BOUNTIFUL_HARVEST.moneyEffect = 0;

        LIGHTNING.foodEffect = 0;
        LIGHTNING.energyEffect = 1;
        LIGHTNING.oreEffect = 0;
        LIGHTNING.moneyEffect = 0;
    }

    /**
     * Calculates the food.
     * @param oldFood The old amount of food.
     * @return The current food.
     */
    public int calcFood(int oldFood) {
        return oldFood + foodEffect;
    }

    /**
     * Calculates the amount of ore.
     * @param oldOre The old amount of ore.
     * @return The current amount of ore.
     */
    public int calcOre(int oldOre) {
        return oldOre + oreEffect;
    }

    /**
     * Calculates the amount of energy.
     * @param oldEnergy The old amount of energy.
     * @return The current amount of energy.
     */
    public int calcEnergy(int oldEnergy) {
        return oldEnergy + energyEffect;
    }

    /**
     * Calculates the amount of energy.
     * @param oldMoney The old amount of money.
     * @param round The round number.
     * @return The new amount of money.
     */
    public int calcMoney(int oldMoney, int round) {
        int roundMultiple = RandomEvent.roundMultiple(round);
        str = str.replaceAll("###", "" + Math.abs(moneyEffect * roundMultiple));
        return oldMoney + moneyEffect * roundMultiple;
    }

    /**
     * Gets a RANDOM event.
     * @return The RANDOM event.
     */
    public static RoundRandomEvents getRandomEvent() {
        int x = RANDOM.nextInt(RoundRandomEvents.class.getEnumConstants().length);
        return RoundRandomEvents.class.getEnumConstants()[x];
    }

    @Override
    public String toString() {
        return str;
    }
}
