package com.byteme.Models;

import com.byteme.Schema.RandomEvent;
import com.byteme.Util.RandomWrapper;
import com.byteme.Util.TestableRandomWrapper;

/**
 * MULE
 */
public class RandomEventGenerator {
    private final TestableRandomWrapper random;
    private static final int PROB = 27;

    public RandomEventGenerator() {
        random = new RandomWrapper();
    }

    public RandomEventGenerator(TestableRandomWrapper random) {
        this.random = random;
    }

    /**
     *
     * @return
     */
    public RandomEvent getEvent() {
        return getEvent(false);
    }

    /**
     *
     * @param onlyGood
     * @return
     */
    public RandomEvent getEvent(boolean onlyGood) {
        if (!flipCoin()) return RandomEvent.NOTHING;
        if (onlyGood) return getRandomEvent(false);
        return getRandomEvent(true);
    }

    /**
     *
     * @param includeBad
     * @return
     */
    public RandomEvent getRandomEvent(boolean includeBad) {
        RandomEvent randomEvent = RandomEvent.getRandomEvent();
        while ((!includeBad && randomEvent.isBad()) || randomEvent.equals(RandomEvent.NOTHING))
            randomEvent = RandomEvent.getRandomEvent();
        return randomEvent;
    }

    /**
     *
     * @return
     */
    private boolean flipCoin() {
        int flip = random.getInt(100);
        System.out.println("Probability flipped: " + flip);
        return flip <= PROB;
    }
}
