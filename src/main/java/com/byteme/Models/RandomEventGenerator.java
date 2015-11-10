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
     * Gets a random event either good or bad.
     * @return The random event.
     */
    public RandomEvent getEvent() {
        return getEvent(false);
    }

    /**
     * Gets a random event with choice of good or bad.
     * @param onlyGood Only good events
     * @return The random event
     */
    public RandomEvent getEvent(boolean onlyGood) {
        if (!flipCoin()) return RandomEvent.NOTHING;
        if (onlyGood) return getRandomEvent(false);
        return getRandomEvent(true);
    }

    /**
     * Get the random event.
     * @param includeBad Should there be bad events as well
     * @return The Random Event
     */
    private RandomEvent getRandomEvent(boolean includeBad) {
        RandomEvent randomEvent = RandomEvent.getRandomEvent();
        while ((!includeBad && randomEvent.isGood()) || randomEvent.equals(RandomEvent.NOTHING))
            randomEvent = RandomEvent.getRandomEvent();
        return randomEvent;
    }

    /**
     * Flips a coin.
     * @return The coin result
     */
    private boolean flipCoin() {
        int flip = random.getInt(100);
        System.out.println("Probability flipped: " + flip);
        return flip <= PROB;
    }
}
