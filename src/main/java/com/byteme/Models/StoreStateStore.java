package com.byteme.Models;

/**
 * Created by rishavbose365 on 10/7/2015.
 */
public class StoreStateStore {
    private static final StoreStateStore ourInstance = new StoreStateStore();

    public static StoreStateStore getInstance() {
        return ourInstance;
    }

    private boolean state = true; //true = buy false = sell
    //TODO get quantities based on difficulty
    private int foodQuantity = 16;
    private int foodPrice = 30;
    private int energyQuantity = 16;
    private int energyPrice = 25;
    private int smithoreQuantity = 0;
    private int smithorePrice = 50;
    private int crystiteQuantity = 0;
    private int crystitePrice = 100;
    private int muleQuantity = 25;
    private int mulePrice = 100;

    private static final int foodMuleCost = 25;
    private static final int energyMuleCost = 50;
    private static final int smithoreMuleCost = 75;
    private static final int crystiteMuleCost = 25;

    private StoreStateStore() {}

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isState() {
        return state;
    }

    public int getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getEnergyQuantity() {
        return energyQuantity;
    }

    public void setEnergyQuantity(int energyQuantity) {
        this.energyQuantity = energyQuantity;
    }

    public int getEnergyPrice() {
        return energyPrice;
    }

    public void setEnergyPrice(int energyPrice) {
        this.energyPrice = energyPrice;
    }

    public int getSmithoreQuantity() {
        return smithoreQuantity;
    }

    public void setSmithoreQuantity(int smithoreQuantity) {
        this.smithoreQuantity = smithoreQuantity;
    }

    public int getSmithorePrice() {
        return smithorePrice;
    }

    public void setSmithorePrice(int smithorePrice) {
        this.smithorePrice = smithorePrice;
    }

    public int getCrystiteQuantity() {
        return crystiteQuantity;
    }

    public void setCrystiteQuantity(int crystiteQuantity) {
        this.crystiteQuantity = crystiteQuantity;
    }

    public int getCrystitePrice() {
        return crystitePrice;
    }

    public void setCrystitePrice(int crystitePrice) {
        this.crystitePrice = crystitePrice;
    }

    public int getMuleQuantity() {
        return muleQuantity;
    }

    public void setMuleQuantity(int muleQuantity) {
        this.muleQuantity = muleQuantity;
    }

    public int getMuleTypePrice(String type) {
        if (type.equals("Food")) return mulePrice + foodMuleCost;
        else if (type.equals("Energy")) return mulePrice + energyMuleCost;
        else if (type.equals("Smithore")) return mulePrice + smithoreMuleCost;
        else if (type.equals("Crystite")) return mulePrice + crystiteMuleCost;
        else {
            System.out.println("There was an error!");
            return 0;
        }
    }

    public int getMulePrice() {
        return this.mulePrice;
    }

    public void setMulePrice(int mulePrice) {
        this.mulePrice = mulePrice;
    }

    public int getMuleTypeCost(String s) {
        if (s.equals("Food")) {
            return foodMuleCost;
        } else if (s.equals("Energy")) {
            return energyMuleCost;
        } else if (s.equals("Smithore")) {
            return smithoreMuleCost;
        } else if (s.equals("Crystite")) {
            return crystiteMuleCost;
        } else {
            return 0;
        }
    }
}
