package com.byteme.Util;

/**
 * MULE
 */
public class MockedRandomWrapper implements TestableRandomWrapper {

    private int theInt;

    public MockedRandomWrapper(int theInt) {
        this.theInt = theInt;
    }

    public int getInt(int i) {
        return theInt;
    }

}