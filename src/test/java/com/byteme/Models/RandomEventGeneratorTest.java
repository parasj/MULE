package com.byteme.Models;

import com.byteme.Schema.RandomEvent;
import com.byteme.Util.MockedRandomWrapper;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * MULE
 */
public class RandomEventGeneratorTest {
    MockedRandomWrapper mockRandom0 = new MockedRandomWrapper(0);
    MockedRandomWrapper mockRandom10 = new MockedRandomWrapper(10);
    MockedRandomWrapper mockRandom27 = new MockedRandomWrapper(27);
    MockedRandomWrapper mockRandom28 = new MockedRandomWrapper(28);
    MockedRandomWrapper mockRandom35 = new MockedRandomWrapper(35);
    MockedRandomWrapper mockRandom75 = new MockedRandomWrapper(75);
    MockedRandomWrapper mockRandom100 = new MockedRandomWrapper(100);

    @Test
    public void testGetEvent() throws Exception {
        // mockito used to mock out randomness

        // should be nothing
        RandomEventGenerator rng = new RandomEventGenerator(mockRandom100);
        assertEquals(RandomEvent.NOTHING, rng.getEvent(false));

        // should be nothing
        rng = new RandomEventGenerator(mockRandom28);
        assertEquals(RandomEvent.NOTHING, rng.getEvent(false));

        // should be nothing
        rng = new RandomEventGenerator(mockRandom35);
        assertEquals(RandomEvent.NOTHING, rng.getEvent(false));

        // should be nothing
        rng = new RandomEventGenerator(mockRandom75);
        assertEquals(RandomEvent.NOTHING, rng.getEvent(false));

        // should not be nothing
        RandomEventGenerator randomEventGen = new RandomEventGenerator(mockRandom0);
        assertNotEquals(randomEventGen.getEvent(false), RandomEvent.NOTHING);

        // should not be nothing
        randomEventGen = new RandomEventGenerator(mockRandom27);
        assertNotEquals(randomEventGen.getEvent(false), RandomEvent.NOTHING);

        // should not be nothing
        randomEventGen = new RandomEventGenerator(mockRandom10);
        assertNotEquals(randomEventGen.getEvent(false), RandomEvent.NOTHING);

        // testing last conditional
        randomEventGen = new RandomEventGenerator(mockRandom0);
        assertEquals(true, !randomEventGen.getEvent(true).isGood());
        assertNotEquals(RandomEvent.NOTHING, randomEventGen.getEvent(true));
    }
}