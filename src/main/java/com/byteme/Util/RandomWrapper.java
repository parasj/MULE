package com.byteme.Util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * MULE
 */
public class RandomWrapper implements TestableRandomWrapper {
    private Random random;

    public RandomWrapper() {
        random = new SecureRandom();
    }

    public int getInt(int i) {
        return random.nextInt(i);
    }
}
