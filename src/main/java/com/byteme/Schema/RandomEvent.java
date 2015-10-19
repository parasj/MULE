package com.byteme.Schema;

import java.security.SecureRandom;

/**
 * MULE
 */
public enum RandomEvent {
    /*
    YOU JUST RECEIVED A PACKAGE FROM THE GT ALUMNI CONTAINING 3 FOOD AND 2 ENERGY UNITS.
    A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY BY LEAVING TWO BARS OF ORE.
    THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR $ 8*m.
    YOU FOUND A DEAD MOOSE RAT AND SOLD THE HIDE FOR $2*m.
    FLYING CAT-BUGS ATE THE ROOF OFF YOUR HOUSE. REPAIRS COST $4*m.
    MISCHIEVOUS UGA STUDENTS BROKE INTO YOUR STORAGE SHED AND STOLE HALF YOUR FOOD.
    YOUR SPACE GYPSY INLAWS MADE A MESS OF THE TOWN. IT COST YOU $6*m TO CLEAN IT UP.
    */

    NOTHING, GT_ALUMNI_PACKAGE, TECH_STUDENT_HOSPITALITY, MUSEUM_COMPUTER, MOOSE_RAT, FLYING_CAT_BUGS, UGA_STUDENTS, SPACE_INLAWS;

    private boolean isGood;
    private int foodEffect, energyEffect, oreEffect, moneyEffect;
    private static final SecureRandom random = new SecureRandom();

    static {
        NOTHING.isGood = true;
        GT_ALUMNI_PACKAGE.isGood = true;
        TECH_STUDENT_HOSPITALITY.isGood = true;
        MUSEUM_COMPUTER.isGood = true;
        MOOSE_RAT.isGood = true;
        FLYING_CAT_BUGS.isGood = false;
        UGA_STUDENTS.isGood = false;
        SPACE_INLAWS.isGood = false;

        NOTHING.foodEffect = 0;
        GT_ALUMNI_PACKAGE.foodEffect = 3;
        TECH_STUDENT_HOSPITALITY.foodEffect = 0;
        MUSEUM_COMPUTER.foodEffect = 0;
        MOOSE_RAT.foodEffect = 0;
        FLYING_CAT_BUGS.foodEffect = 0;
        UGA_STUDENTS.foodEffect = -10; // temp value
        SPACE_INLAWS.foodEffect = 0;

        NOTHING.oreEffect = 0;
        GT_ALUMNI_PACKAGE.oreEffect = 0;
        TECH_STUDENT_HOSPITALITY.oreEffect = 2;
        MUSEUM_COMPUTER.oreEffect = 0;
        MOOSE_RAT.oreEffect = 0;
        FLYING_CAT_BUGS.oreEffect = 0;
        UGA_STUDENTS.oreEffect = 0;
        SPACE_INLAWS.oreEffect = 0;

        NOTHING.energyEffect = 0;
        GT_ALUMNI_PACKAGE.energyEffect = 2;
        TECH_STUDENT_HOSPITALITY.energyEffect = 0;
        MUSEUM_COMPUTER.energyEffect = 0;
        MOOSE_RAT.energyEffect = 0;
        FLYING_CAT_BUGS.energyEffect = 0;
        UGA_STUDENTS.energyEffect = 0;
        SPACE_INLAWS.energyEffect = 0;

        NOTHING.moneyEffect = 0;
        GT_ALUMNI_PACKAGE.moneyEffect = 0;
        TECH_STUDENT_HOSPITALITY.moneyEffect = 0;
        MUSEUM_COMPUTER.moneyEffect = 8;
        MOOSE_RAT.moneyEffect = 2;
        FLYING_CAT_BUGS.moneyEffect = -4;
        UGA_STUDENTS.moneyEffect = 0;
        SPACE_INLAWS.moneyEffect = -6;
    }

    public boolean isGood() {
        return isGood;
    }

    public int calcFood(int oldFood) {
        if (this.equals(RandomEvent.UGA_STUDENTS)) return oldFood / 2;
        return oldFood + foodEffect;
    }

    public int calcOre(int oldOre) {
        return oldOre + oreEffect;
    }

    public int calcEnergy(int oldEnergy) {
        return oldEnergy + energyEffect;
    }

    public int calcMoney(int oldMoney) {
        return oldMoney + moneyEffect;
    }

    public static RandomEvent getRandomEvent() {
        int x = random.nextInt(RandomEvent.class.getEnumConstants().length);
        return RandomEvent.class.getEnumConstants()[x];
    }
}
