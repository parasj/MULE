package com.byteme.Schema;

import java.security.SecureRandom;

/**
 * MULE
 */
public enum RandomEvent {
    /*
    YOU JUST RECEIVED A PACKAGE FROM
     THE GT ALUMNI CONTAINING 3 FOOD AND 2 ENERGY UNITS.
    A WANDERING TECH STUDENT REPAID
     YOUR HOSPITALITY BY LEAVING TWO BARS OF ORE.
    THE MUSEUM BOUGHT YOUR ANTIQUE
     PERSONAL COMPUTER FOR $ 8*m.
    YOU FOUND A DEAD MOOSE RAT AND
     SOLD THE HIDE FOR $2*m.
    FLYING CAT-BUGS ATE THE ROOF OFF
     YOUR HOUSE. REPAIRS COST $4*m.
    MISCHIEVOUS UGA STUDENTS BROKE
     INTO YOUR STORAGE SHED AND STOLE HALF YOUR FOOD.
    YOUR SPACE GYPSY IN-LAWS MADE
     A MESS OF THE TOWN. IT COST YOU $6*m TO CLEAN IT UP.
    ACID RAIN STORM.
     FOOD PRODUCTION INCREASES (4 FOOD). //new events start here
    SPACE PIRATES ATTACK YOU. THEY TAKE 8*m
    METEORITE STRIKE.
     A METEORITE LANDS ON A PLOT AND CAUSES A HIGH ORE DEPOSIT (+9).
    RED SUN.
     IT'S THE TIME OF THE YEAR WHEN THE RED SUN ORBITS, INCREASES ENERGY.
    MEGATRON RETURNS.
     CALVIN JOHNSON COMES BACK TO GATECH AND BRINGS THE HYPE AND CASH TOO.
    STAFFORD RETURNS.
     MATTHEW STAFFORD ALSO COMES BACK TO HAUNT GATECH AND WE LOSE MONEY.
    RADIATION. RADIATION ACCIDENT CAUSES
      YOU TO LOSE MONEY $4*m FOR TREATMENT.
    THE GODDESS OF HARVEST, DEMETER HAS DECIDED TO SHINE HER FACE ON YOU.
     YOU GET +9 FOOD.
    */

    NOTHING, GT_ALUMNI_PACKAGE, TECH_STUDENT_HOSPITALITY,
    MUSEUM_COMPUTER, MOOSE_RAT, FLYING_CAT_BUGS, UGA_STUDENTS, SPACE_IN_LAWS, ACID_RAIN, SPACE_PIRATES,
    METEORITE_STRIKE, RED_SUN, MEGATRON_RETURNS, STAFFORD_RETURNS, RADIATION, DEMETERS_SMILE;

    private boolean isGood;
    private int foodEffect;
    private int energyEffect;
    private int oreEffect;
    private int moneyEffect;
    private String str;
    private static final SecureRandom RANDOM = new SecureRandom();

    static {
        NOTHING.str = "No RANDOM event occurs.";
        GT_ALUMNI_PACKAGE.str = "You just received a "
                + "package from the GT Alumni containing "
                + "3 food and 2 energy units.";
        TECH_STUDENT_HOSPITALITY.str = "A wandering Tech student "
                + "repaid your hospitality by leaving"
                + " two bars of ore.";
        MUSEUM_COMPUTER.str = "The museum bought your "
                + "antique personal computer for $###.";
        MOOSE_RAT.str = "You found a dead moose rat"
                + " and sold the hide for $###.";
        FLYING_CAT_BUGS.str = "Flying cat-bugs are the "
                + "roof off your house. Repairs cost $###.";
        UGA_STUDENTS.str = "Mischievous UGA students broke into "
                + "your storage shed and stole half your food.";
        SPACE_IN_LAWS.str = "Your space gypsy in-laws made a"
                + " mess of the town. It cost you "
                + "$### to clean it up.";
        ACID_RAIN.str = "Acid rain just fell, you get 4 food.";
        SPACE_PIRATES.str = "Space Pirates just attacked you,"
                + " you lose $### ";
        METEORITE_STRIKE.str = "A meteorite just landed on a plot,"
                + " leading to high ore deposit. Get 9 ore.";
        RED_SUN.str = "The red sun is in orbit, your energy increases by 6.";
        MEGATRON_RETURNS.str = "Megatron comes back to Georgia Tech."
                + " You gain a +5 increase in energy and money.";
        STAFFORD_RETURNS.str =  "Matthew Stafford comes to Georgia Tech."
                + " You lose $4.";
        RADIATION.str = "A radiation accident just occurred."
                + " You lose $### for clean up.";
        DEMETERS_SMILE.str = "The goddess of harvest has shone her face on you."
                + " You gain 9 food.";



        NOTHING.isGood = true;
        GT_ALUMNI_PACKAGE.isGood = true;
        TECH_STUDENT_HOSPITALITY.isGood = true;
        MUSEUM_COMPUTER.isGood = true;
        MOOSE_RAT.isGood = true;
        FLYING_CAT_BUGS.isGood = false;
        UGA_STUDENTS.isGood = false;
        SPACE_IN_LAWS.isGood = false;
        ACID_RAIN.isGood = true;
        SPACE_PIRATES.isGood = false;
        METEORITE_STRIKE.isGood = false;
        RED_SUN.isGood = true;
        MEGATRON_RETURNS.isGood = true;
        STAFFORD_RETURNS.isGood = false;
        RADIATION.isGood = false;
        DEMETERS_SMILE.isGood = true;

        NOTHING.foodEffect = 0;
        GT_ALUMNI_PACKAGE.foodEffect = 3;
        TECH_STUDENT_HOSPITALITY.foodEffect = 0;
        MUSEUM_COMPUTER.foodEffect = 0;
        MOOSE_RAT.foodEffect = 0;
        FLYING_CAT_BUGS.foodEffect = 0;
        UGA_STUDENTS.foodEffect = -10; // temp value
        SPACE_IN_LAWS.foodEffect = 0;
        ACID_RAIN.foodEffect = 4;
        SPACE_PIRATES.foodEffect = 0;
        METEORITE_STRIKE.foodEffect = 0;
        RED_SUN.foodEffect = 0;
        MEGATRON_RETURNS.foodEffect = 0;
        STAFFORD_RETURNS.foodEffect = 0;
        RADIATION.foodEffect = 0;
        DEMETERS_SMILE.foodEffect = 9;

        NOTHING.oreEffect = 0;
        GT_ALUMNI_PACKAGE.oreEffect = 0;
        TECH_STUDENT_HOSPITALITY.oreEffect = 2;
        MUSEUM_COMPUTER.oreEffect = 0;
        MOOSE_RAT.oreEffect = 0;
        FLYING_CAT_BUGS.oreEffect = 0;
        UGA_STUDENTS.oreEffect = 0;
        SPACE_IN_LAWS.oreEffect = 0;
        ACID_RAIN.oreEffect = 0;
        SPACE_PIRATES.oreEffect = 0;
        METEORITE_STRIKE.oreEffect = 9;
        RED_SUN.oreEffect = 0;
        MEGATRON_RETURNS.oreEffect = 0;
        STAFFORD_RETURNS.oreEffect = 0;
        RADIATION.oreEffect = 0;
        DEMETERS_SMILE.oreEffect = 0;

        NOTHING.energyEffect = 0;
        GT_ALUMNI_PACKAGE.energyEffect = 2;
        TECH_STUDENT_HOSPITALITY.energyEffect = 0;
        MUSEUM_COMPUTER.energyEffect = 0;
        MOOSE_RAT.energyEffect = 0;
        FLYING_CAT_BUGS.energyEffect = 0;
        UGA_STUDENTS.energyEffect = 0;
        SPACE_IN_LAWS.energyEffect = 0;
        ACID_RAIN.energyEffect = 0;
        SPACE_PIRATES.energyEffect = 0;
        METEORITE_STRIKE.energyEffect = 0;
        RED_SUN.energyEffect = 6;
        MEGATRON_RETURNS.energyEffect = 5;
        STAFFORD_RETURNS.energyEffect = 0;
        RADIATION.energyEffect = 0;
        DEMETERS_SMILE.energyEffect = 0;


        NOTHING.moneyEffect = 0;
        GT_ALUMNI_PACKAGE.moneyEffect = 0;
        TECH_STUDENT_HOSPITALITY.moneyEffect = 0;
        MUSEUM_COMPUTER.moneyEffect = 8;
        MOOSE_RAT.moneyEffect = 2;
        FLYING_CAT_BUGS.moneyEffect = -4;
        UGA_STUDENTS.moneyEffect = 0;
        SPACE_IN_LAWS.moneyEffect = -6;
        ACID_RAIN.moneyEffect = 0;
        SPACE_PIRATES.moneyEffect = -8;
        METEORITE_STRIKE.moneyEffect = 0;
        RED_SUN.moneyEffect = 0;
        MEGATRON_RETURNS.moneyEffect = 5;
        STAFFORD_RETURNS.moneyEffect = -4;
        RADIATION.moneyEffect = -3;
        DEMETERS_SMILE.moneyEffect = 0;
    }

    /**
     * Checks if event is good.
     * @return if event is good
     */
    public boolean isGood() {
        return !isGood;
    }

    /**
     * Checks if event is bad.
     * @return if event is bad
     */
    public boolean isBad() {
        return !isGood();
    }

    /**
     * Calculates the food.
     * @param oldFood The old amount of food.
     * @return The current food.
     */
    public int calcFood(int oldFood) {
        if (this.equals(RandomEvent.UGA_STUDENTS)) {
            return oldFood / 2;
        }
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
        int roundMultiple = roundMultiple(round);
        str = str.replaceAll("###", "" + Math.abs(moneyEffect * roundMultiple));
        return oldMoney + moneyEffect * roundMultiple;
    }

    /**
     * Gets a RANDOM event.
     * @return The RANDOM event.
     */
    public static RandomEvent getRandomEvent() {
        int x = RANDOM.nextInt(RandomEvent.class.getEnumConstants().length);
        return RandomEvent.class.getEnumConstants()[x];
    }

    @Override
    public String toString() {
        return str;
    }

    /**
     * Gets the round multiple.
     * @param round The round number.
     * @return The round multiple
     */
    protected static int roundMultiple(int round) {
        if (round > 0 && round < 4) {
            return 25;
        } else if (round < 8) {
            return 50;
        } else if (round < 12) {
            return 75;
        } else if (round == 12) {
            return 100;
        } else {
            return 0;
        }
    }
}
