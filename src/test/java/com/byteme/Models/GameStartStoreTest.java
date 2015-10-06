package com.byteme.Models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * MULE
 */
public class GameStartStoreTest {
    @Test
    public void testSetCurrentPlayer() throws Exception {
        GameStartStore.getInstance().setCurrentPlayer(1);
        assertEquals(GameStartStore.getInstance().getCurrentPlayer(), 1);
        GameStartStore.getInstance().setCurrentPlayer(0);
        assertEquals(GameStartStore.getInstance().getCurrentPlayer(), 0);
    }
}