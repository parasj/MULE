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
    MockedRandomWrapper mockRandom100 = new MockedRandomWrapper(0);

    @Test
    public void testGetEvent() throws Exception {
        // mockito used to mock out randomness

        // should be nothing
        RandomEventGenerator randomEventGenerator = new RandomEventGenerator(mockRandom100);
        assertEquals(randomEventGenerator.getEvent(false), RandomEvent.NOTHING);

        // should not be nothing
        randomEventGenerator = new RandomEventGenerator(mockRandom0);
        assertNotEquals(randomEventGenerator.getEvent(false), RandomEvent.NOTHING);

        // testing last conditional
        RandomEventGenerator mockRandomEventGenerator = mock(RandomEventGenerator.class);
        when(mockRandomEventGenerator.getRandomEvent(false)).thenReturn(RandomEvent.GT_ALUMNI_PACKAGE);
        assertEquals(randomEventGenerator.getEvent(true), RandomEvent.GT_ALUMNI_PACKAGE);

        when(mockRandomEventGenerator.getRandomEvent(true)).thenReturn(RandomEvent.MUSEUM_COMPUTER);
        assertEquals(randomEventGenerator.getEvent(false), RandomEvent.MUSEUM_COMPUTER);
    }
}