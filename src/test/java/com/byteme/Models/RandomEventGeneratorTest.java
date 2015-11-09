package com.byteme.Models;

import com.byteme.Schema.RandomEvent;
import com.byteme.Util.MockedRandomWrapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * MULE
 */
public class RandomEventGeneratorTest {
    MockedRandomWrapper mockRandom0 = new MockedRandomWrapper(0);
    MockedRandomWrapper mockRandom100 = new MockedRandomWrapper(100);

    @Test
    public void testGetEvent() throws Exception {
        // should be nothing
        RandomEventGenerator rng = new RandomEventGenerator(mockRandom100);
        assertEquals(RandomEvent.NOTHING, rng.getEvent(false));

        // should not be nothing
        RandomEventGenerator randomEventGen = new RandomEventGenerator(mockRandom0);
        assertNotEquals(randomEventGen.getEvent(false), RandomEvent.NOTHING);

        // testing last conditional
        randomEventGen = new RandomEventGenerator(mockRandom0);
        assertEquals(true, randomEventGen.getEvent(true).isGood());
        assertNotEquals(RandomEvent.NOTHING, randomEventGen.getEvent(true));

        // testing last conditional
        randomEventGen = new RandomEventGenerator(mockRandom100);
        assertEquals(RandomEvent.NOTHING, randomEventGen.getEvent(false));
    }
}