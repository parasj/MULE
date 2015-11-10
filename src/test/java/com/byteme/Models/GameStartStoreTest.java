package com.byteme.Models;

import com.byteme.Main;
import com.byteme.Schema.Difficulty;
import com.byteme.Schema.GameConfigParams;
import com.byteme.Schema.MapType;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * MULE
 */
public class GameStartStoreTest extends Main{
    @Test
    public void testSetCurrentPlayer() throws Exception {
        // Main.launch();
        //ImageView horse = ("#horse");
        GameStartStore.getInstance().setCurrentPlayer(1);
        assertEquals(GameStartStore.getInstance().getCurrentPlayer(), 1);
        GameStartStore.getInstance().setCurrentPlayer(0);
        assertEquals(GameStartStore.getInstance().getCurrentPlayer(), 0);
//        GameStartStore.getInstance().incCurrentPlayer();
//        GameConfigParams g = new GameConfigParams(Difficulty.STANDARD, MapType.STANDARD, 3);
//        assertEquals(GameStartStore.getInstance().getCurrentPlayer(), 1);
    }
//    @Test
//    public void incCurrentPlayerTest() throws Exception {
//        Main.launch();
//
//    }
}