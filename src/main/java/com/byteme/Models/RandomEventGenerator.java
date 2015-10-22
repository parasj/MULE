package com.byteme.Models;

import com.byteme.Schema.RandomEvent;

import java.security.SecureRandom;

/**
 * MULE
 */
public class RandomEventGenerator {
    private static final SecureRandom random = new SecureRandom();
    private static final int PROB = 27;

    public RandomEvent getEvent() {
        return getEvent(false);
    }

    public RandomEvent getEvent(boolean onlyGood) {
        if (!flipCoin()) return RandomEvent.NOTHING;
        if (onlyGood) return getRandomEvent(false);
        return getRandomEvent(true);
    }

    private RandomEvent getRandomEvent(boolean includeBad) {
        RandomEvent evt = RandomEvent.getRandomEvent();
        while ((!includeBad && !evt.isGood()) || evt.equals(RandomEvent.NOTHING))
            evt = RandomEvent.getRandomEvent();
        return evt;
    }

    private boolean flipCoin() {
        int flip = random.nextInt(100);
        System.out.println("Probability flipped: " + flip);
        return flip <= PROB;
    }
}
