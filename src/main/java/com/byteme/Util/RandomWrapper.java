package com.byteme.Util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * MULE
 */
public class RandomWrapper implements TestableRandomWrapper {
    private final Random random;

    /**
     * The constructor.
     */
    public RandomWrapper() {
        random = new SecureRandom();
    }

    /**
     * Gets the int to return.
     * @param i The int.
     * @return The int.
     */
    public int getInt(int i) {
        return random.nextInt(i);
    }
}
