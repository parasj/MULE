package com.byteme.Schema;

/**
 * MULE Race enum.
 */
public enum Race {
    FLAPPER, HUMAN, BONZOID, UGAITE, BUZZITE,
    MARTIAN, PLUTONIAN, ORC, DWARF, ATLANTIAN;

    private int startingMoney;

    static {
        FLAPPER.startingMoney = 1600;
        HUMAN.startingMoney = 600;
        BONZOID.startingMoney = 1000;
        UGAITE.startingMoney = 1000;
        BUZZITE.startingMoney = 1000;

        // extra races
        MARTIAN.startingMoney = 1100;
        PLUTONIAN.startingMoney = 1200;
        ORC.startingMoney = 1300;
        DWARF.startingMoney = 1400;
        ATLANTIAN.startingMoney = 1500;

    }

    public int getStartingMoney() {
        return startingMoney;
    }

    @Override
    public String toString() {
        return EnumHumanizer.humanizeEnum(super.toString());
    }
}
