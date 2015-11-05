package com.byteme.Controllers;

import static org.junit.Assert.*;

import com.byteme.Main;
import com.byteme.Schema.Difficulty;
import com.byteme.Schema.MapControllerStates;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.junit.Test;

/**
 * Created by Daniel on 11/4/2015.
 */
public class ConfigurationControllerTest extends Main {

    @Test
    public void FlapperTest() {
        ConfigurationController c = new ConfigurationController();
        assertEquals(c.chooseMoneyAmount("Flapper"), 1600);
    }
    @Test
    public void FLAPPERTest() {
        ConfigurationController c = new ConfigurationController();
        assertEquals(c.chooseMoneyAmount("FLAPPER"), 1600);
    }
    @Test
    public void flapperTest() {
        ConfigurationController c = new ConfigurationController();
        assertEquals(c.chooseMoneyAmount("flapper"), 1600);
    }
    @Test
    public void HumanTest() {
        ConfigurationController c = new ConfigurationController();
        assertEquals(c.chooseMoneyAmount("Human"), 600);
    }
    @Test
    public void HUMANTest() {
        ConfigurationController c = new ConfigurationController();
        assertEquals(c.chooseMoneyAmount("HUMAN"), 600);
    }
    @Test
    public void humanTest() {
        ConfigurationController c = new ConfigurationController();
        assertEquals(c.chooseMoneyAmount("human"), 600);
    }
    @Test
    public void DefaultTest() {
        ConfigurationController c = new ConfigurationController();
        assertEquals(c.chooseMoneyAmount("Default"), 1000);
    }
    @Test
    public void DEFAULTTest() {
        ConfigurationController c = new ConfigurationController();
        assertEquals(c.chooseMoneyAmount("DEFAULT"), 1000);
    }
    @Test
    public void defaultTest() {
        ConfigurationController c = new ConfigurationController();
        assertEquals(c.chooseMoneyAmount("default"), 1000);
    }
}