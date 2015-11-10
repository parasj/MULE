package com.byteme.Schema;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
/**
 * Created by Daniel on 11/8/2015.
 */
public class PlayerConfigParamsTest  {
    @Test
    public void getsetTest() {
        ArrayList<Property> property = new ArrayList<>();
        PlayerConfigParams p = new PlayerConfigParams("dan", Race.FLAPPER, "green", 100, property, 1);
        assertEquals(p.getOrder(), 1);
        p.setOrder(2);
        assertEquals(p.getOrder(), 2);
        assertEquals(p.getName(), "dan");
        assertEquals(p.getRace(), Race.FLAPPER);
        assertEquals(p.getColor(), "green");
        assertEquals(p.getMoney(), 100);
        p.setMoney(200);
        assertEquals(p.getMoney(), 200);
        p.setMoney(-1);
        assertEquals(p.getMoney(), 0);
    }
    @Test(expected = Exception.class)
    public void paymakeTest() {
        ArrayList<Property> property = new ArrayList<>();
        PlayerConfigParams p = new PlayerConfigParams("dan", Race.FLAPPER, "green", 100, property, 1);
        p.setMoney(200);
        assertEquals(p.getMoney(), 200);
        p.payMoney(100);
        assertEquals(p.getMoney(), 100);
        p.makeMoney(100);
        assertEquals(p.getMoney(), 200);
        p.payMoney(400);
    }
    @Test
    public void resourceTest() {
        ArrayList<Property> property = new ArrayList<>();
        PlayerConfigParams p = new PlayerConfigParams("dan", Race.FLAPPER, "green", 100, property, 1);
        p.setFood(100);
        assertEquals(p.getFood(), 100);
        p.addFood();
        assertEquals(p.getFood(), 101);
        p.subFood();;
        assertEquals(p.getFood(), 100);
        p.setFood(-1);
        assertEquals(p.getFood(), 0);
        p.setEnergy(100);
        assertEquals(p.getEnergy(), 100);
        p.addEnergy();
        assertEquals(p.getEnergy(), 101);
        p.subEnergy();
        assertEquals(p.getEnergy(), 100);
        p.setEnergy(-1);
        assertEquals(p.getEnergy(), 0);
        p.setSmithore(100);
        assertEquals(p.getSmithore(), 100);
        p.addSmithore();
        assertEquals(p.getSmithore(), 101);
        p.subSmithore();
        assertEquals(p.getSmithore(), 100);
        p.setSmithore(-1);
        assertEquals(p.getSmithore(), 0);
        p.setCrystite(100);
        assertEquals(p.getCrystite(), 100);
        p.addCrystite();
        assertEquals(p.getCrystite(), 101);
        p.subCrystite();
        assertEquals(p.getCrystite(), 100);
        p.setCrystite(-1);
        assertEquals(p.getCrystite(), 0);
        assertEquals(p.calcScore(), 100);
    }
}