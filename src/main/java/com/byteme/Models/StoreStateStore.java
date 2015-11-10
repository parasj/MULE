package com.byteme.Models;

import java.io.Serializable;

/**
 * MULE
 */
public class StoreStateStore implements Serializable {

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

    /**
     *
     */
    public StoreStateStore() {}

    /**
     *
     * @return
     */
    public boolean getState() {
        return state;
    }

    /**
     *
     * @param state
     */
    public void setState(boolean state) {
        this.state = state;
    }

    /**
     *
     * @return
     */
    public boolean isState() {
        return state;
    }

    /**
     *
     * @return
     */
    public int getFoodQuantity() {
        return foodQuantity;
    }

    /**
     *
     * @param foodQuantity
     */
    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    /**
     *
     * @return
     */
    public int getFoodPrice() {
        return foodPrice;
    }

    /**
     *
     * @param foodPrice
     */
    public void setFoodPrice(int foodPrice) {
        if (foodPrice < 0) {
            this.foodPrice = 0;
        } else {
            this.foodPrice = foodPrice;
        }
    }

    /**
     *
     * @return
     */
    public int getEnergyQuantity() {
        return energyQuantity;
    }

    /**
     *
     * @param energyQuantity
     */
    public void setEnergyQuantity(int energyQuantity) {
        if (energyQuantity < 0) {
            this.energyQuantity = 0;
        } else {
            this.energyQuantity = energyQuantity;
        }
    }

    /**
     *
     * @return
     */
    public int getEnergyPrice() {
        return energyPrice;
    }

    /**
     *
     * @param energyPrice
     */
    public void setEnergyPrice(int energyPrice) {
        if (energyPrice < 0) {
            this.energyPrice = 0;
        } else {
            this.energyPrice = energyPrice;
        }
    }

    /**
     *
     * @return
     */
    public int getSmithoreQuantity() {
        return smithoreQuantity;
    }

    /**
     *
     * @param smithoreQuantity
     */
    public void setSmithoreQuantity(int smithoreQuantity) {
        if (smithoreQuantity < 0) {
            this.smithoreQuantity = 0;
        } else {
            this.smithoreQuantity = smithoreQuantity;
        }
    }

    /**
     *
     * @return
     */
    public int getSmithorePrice() {
        return smithorePrice;
    }

    public void setSmithorePrice(int smithorePrice) {
        if (smithorePrice >= 0) {
            this.smithorePrice = smithorePrice;
        }
    }

    /**
     *
     * @return
     */
    public int getCrystiteQuantity() {
        return crystiteQuantity;
    }

    /**
     *
     * @param crystiteQuantity
     */
    public void setCrystiteQuantity(int crystiteQuantity) {
        if (crystiteQuantity < 0) {
            this.crystiteQuantity = 0;
        } else {
            this.crystiteQuantity = crystiteQuantity;
        }
    }

    /**
     *
     * @return
     */
    public int getCrystitePrice() {
        return crystitePrice;
    }

    /**
     *
     * @param crystitePrice
     */
    public void setCrystitePrice(int crystitePrice) {
        if (crystitePrice < 0) {
            this.crystitePrice = 0;
        } else {
            this.crystitePrice = crystitePrice;
        }
    }

    /**
     *
     * @return
     */
    public int getMuleQuantity() {
        return muleQuantity;
    }

    /**
     *
     * @param muleQuantity
     */
    public void setMuleQuantity(int muleQuantity) {
        if (muleQuantity < 0) {
            this.muleQuantity = 0;
        } else {
            this.muleQuantity = muleQuantity;
        }
    }

    /**
     *
     * @param type
     * @return
     */
    public int getMuleTypePrice(String type) {
        switch (type) {
            case "Food":
                return mulePrice + foodMuleCost;
            case "Energy":
                return mulePrice + energyMuleCost;
            case "Smithore":
                return mulePrice + smithoreMuleCost;
            case "Crystite":
                return mulePrice + crystiteMuleCost;
            default:
                System.out.println("There was an error!");
                return 0;
        }
    }

    /**
     *
     * @return
     */
    public int getMulePrice() {
        return this.mulePrice;
    }

    /**
     *
     * @param mulePrice
     */
    public void setMulePrice(int mulePrice) {
        if (mulePrice < 0) {
            this.mulePrice = 0;
        } else {
            this.mulePrice = mulePrice;
        }
    }

    /**
     *
     * @param string
     * @return
     */
    public int getMuleTypeCost(String string) {
        switch (string) {
            case "Food":
                return foodMuleCost;
            case "Energy":
                return energyMuleCost;
            case "Smithore":
                return smithoreMuleCost;
            case "Crystite":
                return crystiteMuleCost;
            default:
                return 0;
        }
    }
}
