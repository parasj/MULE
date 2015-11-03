package com.byteme.Models;

import com.byteme.Schema.RandomEvent;

import java.security.SecureRandom;

/**
 * MULE
 */
public class RandomEventGenerator {
    private static final SecureRandom random = new SecureRandom();
    private static final int PROB = 27;

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
    private RandomEvent getRandomEvent(boolean includeBad) {
        RandomEvent randomEvent = RandomEvent.getRandomEvent();
        while ((!includeBad && !randomEvent.isGood()) || randomEvent.equals(RandomEvent.NOTHING))
            randomEvent = RandomEvent.getRandomEvent();
        return randomEvent;
    }

    /**
     *
     * @return
     */
    private boolean flipCoin() {
        int flip = random.nextInt(100);
        System.out.println("Probability flipped: " + flip);
        return flip <= PROB;
    }
}
