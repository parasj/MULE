package com.byteme.Schema;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Created by Daniel on 11/8/2015.
 */
public class MuleTest  {
    @Test
    public void foodMuleTest() {
        Mule mule = new Mule(MuleType.FOOD);
        assertEquals(mule.getType(), MuleType.FOOD);
        assertEquals(mule.toString(), "Mule{" +
                "type=" + "FOOD" +
                '}');
    }
    @Test
    public void energyMuleTest() {
        Mule mule = new Mule(MuleType.ENERGY);
        assertEquals(mule.getType(), MuleType.ENERGY);
        assertEquals(mule.toString(), "Mule{" +
                "type=" + "ENERGY" +
                '}');
    }
    @Test
    public void smithoreMuleTest() {
        Mule mule = new Mule(MuleType.SMITHORE);
        assertEquals(mule.getType(), MuleType.SMITHORE);
        assertEquals(mule.toString(), "Mule{" +
                "type=" + "SMITHORE" +
                '}');
    }
    @Test
    public void crystiteMuleTest() {
        Mule mule = new Mule(MuleType.CRYSTITE);
        assertEquals(mule.getType(), MuleType.CRYSTITE);
        assertEquals(mule.toString(), "Mule{" +
                "type=" + "CRYSTITE" +
                '}');
    }
    @Test
    public void setTypeTest() {
        Mule mule = new Mule(MuleType.CRYSTITE);
        mule.setType(MuleType.FOOD);
        assertEquals(mule.getType(), MuleType.FOOD);
        assertEquals(mule.toString(), "Mule{" +
                "type=" + "FOOD" +
                '}');
        mule.setType(MuleType.ENERGY);
        assertEquals(mule.getType(), MuleType.ENERGY);
        assertEquals(mule.toString(), "Mule{" +
                "type=" + "ENERGY" +
                '}');
        mule.setType(MuleType.SMITHORE);
        assertEquals(mule.getType(), MuleType.SMITHORE);
        assertEquals(mule.toString(), "Mule{" +
                "type=" + "SMITHORE" +
                '}');
        mule.setType(MuleType.CRYSTITE);
        assertEquals(mule.getType(), MuleType.CRYSTITE);
        assertEquals(mule.toString(), "Mule{" +
                "type=" + "CRYSTITE" +
                '}');
    }
    @Test(expected = Exception.class)
    public void nullSetTypeTest() {
        Mule mule = new Mule(MuleType.CRYSTITE);
        mule.setType(null);
    }
}