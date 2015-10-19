package com.byteme.Models;

import com.byteme.Schema.RandomEvent;

import java.security.SecureRandom;

/**
 * MULE
 */
public class RandomEventGenerator {
    private static final SecureRandom random = new SecureRandom();

    public RandomEvent getEvent() {
        return getEvent(false);
    }

    public RandomEvent getEvent(boolean onlyGood) {
        if (!flipCoin(27)) return RandomEvent.NOTHING;
        if (onlyGood) return getRandomEvent(false, false);
        return getRandomEvent(true, false);
    }

    private RandomEvent getRandomEvent(boolean includeBad, boolean includeNothing) {
        RandomEvent evt = RandomEvent.getRandomEvent();
        while ((!includeBad && !evt.isGood()) || (!includeNothing && evt.equals(RandomEvent.NOTHING))) {
            evt = RandomEvent.getRandomEvent();
        }
        return evt;
    }

    private boolean flipCoin(int probability) {
        return random.nextInt(100) <= probability;
    }
}
