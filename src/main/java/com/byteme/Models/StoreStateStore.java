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

    private static final int FOOD_MULE_COST = 25;
    private static final int ENERGY_MULE_COST = 50;
    private static final int SMITHORE_MULE_COST = 75;
    private static final int CRYSTITE_MULE_COST = 25;

    /**
     * Creates a StoreStateStore.
     */
    public StoreStateStore() { }

    /**
     * Gets the state.
     * @return The state
     */
    public boolean getState() {
        return state;
    }

    /**
     * Sets the state.
     * @param state The state
     */
    public void setState(boolean state) {
        this.state = state;
    }

    /**
     * Gets whether there is a state.
     * @return Is there a state.
     */
    public boolean isState() {
        return state;
    }

    /**
     * Gets the amount of food.
     * @return Amount of food
     */
    public int getFoodQuantity() {
        return foodQuantity;
    }

    /**
     * Sets the amount of food.
     * @param foodQuantity The amount of food
     */
    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    /**
     * Gets the food price.
     * @return The food price.
     */
    public int getFoodPrice() {
        return foodPrice;
    }

    /**
     * Sets the food price.
     * @param foodPrice The food price
     */
    public void setFoodPrice(int foodPrice) {
        if (foodPrice < 0) {
            this.foodPrice = 0;
        } else {
            this.foodPrice = foodPrice;
        }
    }

    /**
     * Gets the energy amount.
     * @return The amount of energy
     */
    public int getEnergyQuantity() {
        return energyQuantity;
    }

    /**
     * Sets the amount of energy.
     * @param energyQuantity The amount of energy
     */
    public void setEnergyQuantity(int energyQuantity) {
        if (energyQuantity < 0) {
            this.energyQuantity = 0;
        } else {
            this.energyQuantity = energyQuantity;
        }
    }

    /**
     * Gets the price of energy.
     * @return The price of energy
     */
    public int getEnergyPrice() {
        return energyPrice;
    }

    /**
     * Sets the energy price.
     * @param energyPrice The price of energy
     */
    public void setEnergyPrice(int energyPrice) {
        if (energyPrice < 0) {
            this.energyPrice = 0;
        } else {
            this.energyPrice = energyPrice;
        }
    }

    /**
     * Gets the amount of smith ore.
     * @return The amount of smith ore
     */
    public int getSmithoreQuantity() {
        return smithoreQuantity;
    }

    /**
     * Sets the amount of smith ore.
     * @param smithoreQuantity The amount of smith ore.
     */
    public void setSmithoreQuantity(int smithoreQuantity) {
        if (smithoreQuantity < 0) {
            this.smithoreQuantity = 0;
        } else {
            this.smithoreQuantity = smithoreQuantity;
        }
    }

    /**
     * Gets the smith ore price.
     * @return The price of smith ore.
     */
    public int getSmithorePrice() {
        return smithorePrice;
    }

    /**
     * Sets the price of smith ore.
     * @param smithorePrice The price of smith ore.
     */
    public void setSmithorePrice(int smithorePrice) {
        if (smithorePrice >= 0) {
            this.smithorePrice = smithorePrice;
        }
    }

    /**
     * Gets the amount of crystite.
     * @return The amount of crystite.
     */
    public int getCrystiteQuantity() {
        return crystiteQuantity;
    }

    /**
     * Sets the amount of crystite.
     * @param crystiteQuantity The amount of crystite.
     */
    public void setCrystiteQuantity(int crystiteQuantity) {
        if (crystiteQuantity < 0) {
            this.crystiteQuantity = 0;
        } else {
            this.crystiteQuantity = crystiteQuantity;
        }
    }

    /**
     * Gets the price of crystite.
     * @return The price of crystite.
     */
    public int getCrystitePrice() {
        return crystitePrice;
    }

    /**
     * Sets the price of the crystite.
     * @param crystitePrice The price of crystite
     */
    public void setCrystitePrice(int crystitePrice) {
        if (crystitePrice < 0) {
            this.crystitePrice = 0;
        } else {
            this.crystitePrice = crystitePrice;
        }
    }

    /**
     * Gets the amount of mules.
     * @return The amount of mules.
     */
    public int getMuleQuantity() {
        return muleQuantity;
    }

    /**
     * Sets the amount of mules.
     * @param muleQuantity The number of mules.
     */
    public void setMuleQuantity(int muleQuantity) {
        if (muleQuantity < 0) {
            this.muleQuantity = 0;
        } else {
            this.muleQuantity = muleQuantity;
        }
    }

    /**
     * Gets the price of the mule based on type.
     * @param type The type of mule.
     * @return The price of the mule
     */
    public int getMuleTypePrice(String type) {
        switch (type) {
        case "Food":
            return mulePrice + FOOD_MULE_COST;
        case "Energy":
            return mulePrice + ENERGY_MULE_COST;
        case "Smithore":
            return mulePrice + SMITHORE_MULE_COST;
        case "Crystite":
            return mulePrice + CRYSTITE_MULE_COST;
        default:
            System.out.println("There was an error!");
            return 0;
        }
    }

    /**
     * Gets the mule price.
     * @return The price of the mule.
     */
    public int getMulePrice() {
        return this.mulePrice;
    }

    /**
     * Sets the mule price.
     * @param mulePrice The price of the mule.
     */
    public void setMulePrice(int mulePrice) {
        if (mulePrice < 0) {
            this.mulePrice = 0;
        } else {
            this.mulePrice = mulePrice;
        }
    }

    /**
     * Gets the cost for each mule based on type.
     * @param string The type of mule
     * @return The cost
     */
    public int getMuleTypeCost(String string) {
        switch (string) {
        case "Food":
            return FOOD_MULE_COST;
        case "Energy":
            return ENERGY_MULE_COST;
        case "Smithore":
            return SMITHORE_MULE_COST;
        case "Crystite":
            return CRYSTITE_MULE_COST;
        default:
            return 0;
        }
    }
}
