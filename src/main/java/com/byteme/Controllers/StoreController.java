package com.byteme.Controllers;

import com.byteme.Models.*;
import com.byteme.Schema.MapControllerStates;
import com.byteme.Schema.Mule;
import com.byteme.Schema.MuleType;
import com.byteme.Schema.PlayerConfigParams;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

/**
 * Mule
 */
public class StoreController {

    private GameStartStore gameStartStore;
    private MapStateStore mapStateStore;
    private StoreStateStore storeStateStore;
    private PlaceMuleStore placeMuleStore;
    private static BoardController boardController;

    @FXML
    private Label foodQuantity;
    @FXML
    private Label foodPrice;
    @FXML
    private Label energyQuantity;
    @FXML
    private Label energyPrice;
    @FXML
    private Label smithoreQuantity;
    @FXML
    private Label smithorePrice;
    @FXML
    private Label crystiteQuantity;
    @FXML
    private Label crystitePrice;
    @FXML
    private Label muleQuantity;
    @FXML
    private Label mulePrice;
    @FXML
    private Label money;

    @FXML
    private Button foodButton;
    @FXML
    private Button energyButton;
    @FXML
    private Button smithoreButton;
    @FXML
    private Button crystiteButton;
    @FXML
    private Button muleButton;
    @FXML
    private Button changeState;

    @FXML
    private ChoiceBox muleType;

    //Reloads stores
    public StoreController() {
        reinit();
    }

    public void goToMap() {
        MasterController.getInstance().map();
    }

    public static void setBoardController(BoardController boardController1) {
        boardController = boardController1;
    }

    //Changes between buy and selling items
    public void changeState() {
        reinit();
        if (storeStateStore.getState()) {
            foodButton.setText("Sell Food");
            energyButton.setText("Sell Energy");
            smithoreButton.setText("Sell Smith Ore");
            crystiteButton.setText("Sell Crystite");
            muleButton.setDisable(true);
            changeState.setText("Change to Buy");
            storeStateStore.setState(false);

        } else {
            foodButton.setText("Buy Food");
            energyButton.setText("Buy Energy");
            smithoreButton.setText("Buy Smith Ore");
            crystiteButton.setText("Buy Crystite");
            muleButton.setDisable(false);
            changeState.setText("Change to Sell");
            storeStateStore.setState(true);
        }
        log("State changed!");
    }

    //Changes all the labels
    public void reRender() {
        reinit();
        PlayerConfigParams p = mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer());
        foodQuantity.setText("Quantity: " + storeStateStore.getFoodQuantity());
        foodPrice.setText("Price: " + storeStateStore.getFoodPrice());
        energyQuantity.setText("Quantity: " + storeStateStore.getEnergyQuantity());
        energyPrice.setText("Price: " + storeStateStore.getEnergyPrice());
        smithoreQuantity.setText("Quantity: " + storeStateStore.getSmithoreQuantity());
        smithorePrice.setText("Price: " + storeStateStore.getSmithorePrice());
        crystiteQuantity.setText("Quantity: " + storeStateStore.getCrystiteQuantity());
        crystitePrice.setText("Price: " + storeStateStore.getCrystitePrice());
        muleQuantity.setText("Quantity: " + storeStateStore.getMuleQuantity());
        mulePrice.setText("Price: " + storeStateStore.getMulePrice());
        money.setText("" + p.getMoney());
        logPlayer();
        muleType.getSelectionModel().selectFirst();
    }

    //Buys/ sells food
    public void tradeFood() {
        reinit();
        PlayerConfigParams p = mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer());
        if (storeStateStore.getState()) {
            if (p.getMoney() >= storeStateStore.getFoodPrice() && storeStateStore.getFoodQuantity() > 0) {
                p.payMoney(storeStateStore.getFoodPrice());
                storeStateStore.setFoodQuantity(storeStateStore.getFoodQuantity() - 1);
                p.addFood();
                reRender();
            } else {
                log("Cannot buy food");
            }
        } else {
            if (p.getFood() > 0) {
                p.payMoney(-1 * storeStateStore.getFoodPrice());
                storeStateStore.setFoodQuantity(storeStateStore.getFoodQuantity() + 1);
                p.subFood();
                reRender();
            } else {
                log("Cannot sell food");
            }
        }
    }

    public void tradeEnergy() {
        reinit();
        PlayerConfigParams p = mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer());
        if (storeStateStore.getState()) {
            if (p.getMoney() >= storeStateStore.getEnergyPrice() && storeStateStore.getEnergyQuantity() > 0) {
                p.payMoney(storeStateStore.getEnergyPrice());
                storeStateStore.setEnergyQuantity(storeStateStore.getEnergyQuantity() - 1);
                p.addEnergy();
                reRender();
            } else {
                log("Cannot buy energy");
            }
        } else {
            if (p.getEnergy() > 0) {
                p.payMoney(-1 * storeStateStore.getEnergyPrice());
                storeStateStore.setEnergyQuantity(storeStateStore.getEnergyQuantity() + 1);
                p.subEnergy();
                reRender();
            } else {
                log("Cannot sell energy");
            }
        }
    }

    public void tradeSmithore() {
        reinit();
        PlayerConfigParams p = mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer());
        if (storeStateStore.getState()) {
            if (p.getMoney() >= storeStateStore.getSmithorePrice() && storeStateStore.getSmithoreQuantity() > 0) {
                p.payMoney(storeStateStore.getSmithorePrice());
                storeStateStore.setSmithoreQuantity(storeStateStore.getSmithoreQuantity() - 1);
                p.addSmithore();
                reRender();
            } else {
                log("Cannot buy smithore");
            }
        } else {
            if (p.getSmithore() > 0) {
                p.payMoney(-1 * storeStateStore.getSmithorePrice());
                storeStateStore.setSmithoreQuantity(storeStateStore.getSmithoreQuantity() + 1);
                p.subSmithore();
                reRender();
            } else {
                log("Cannot sell smithore");
            }
        }
    }

    public void tradeCrystite() {
        reinit();
        PlayerConfigParams p = mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer());
        if (storeStateStore.getState()) {
            if (p.getMoney() >= storeStateStore.getCrystitePrice() && storeStateStore.getCrystiteQuantity() > 0) {
                p.payMoney(storeStateStore.getCrystitePrice());
                storeStateStore.setCrystiteQuantity(storeStateStore.getCrystiteQuantity() - 1);
                p.addCrystite();
                reRender();
            } else {
                log("Cannot buy crystite");
            }
        } else {
            if (p.getCrystite() > 0) {
                p.payMoney(-1 * storeStateStore.getCrystitePrice());
                storeStateStore.setCrystiteQuantity(storeStateStore.getCrystiteQuantity() + 1);
                p.subCrystite();
                reRender();
            } else {
                log("Cannot sell crystite");
            }
        }
    }

    //Buys mule and places it, does not sell
    public void tradeMule() {
        reinit();
        PlayerConfigParams p = mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer());
        int muleCost = storeStateStore.getMulePrice() + storeStateStore.getMuleTypeCost((String) muleType.getValue());
        //log("" + muleCost);
        if (storeStateStore.getState()) {
            if (p.getMoney() >= muleCost && storeStateStore.getMuleQuantity() > 0) {
                placeMuleStore.setMule(new Mule(getType((String) muleType.getValue())));
                boardController.updateState(MapControllerStates.PLACE_MULE, true);
                goToMap();
                p.payMoney(muleCost);
                storeStateStore.setMuleQuantity(storeStateStore.getMuleQuantity() - 1);
                reRender();
            } else {
                log("Cannot buy Mule");
            }
        } else {
            log("Cannot sell Mule");
        }
    }

    public void log(String string) {
        System.out.println(string);
    }

    public void logPlayer() {
        PlayerConfigParams p = mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer());
        log("Player: " + p.getName());
        log("Food: " + p.getFood());
        log("Energy: " + p.getEnergy());
        log("Smithore: " + p.getSmithore());
        log("Crystite: " + p.getCrystite());
        log("==================================================");
    }

    //Changes string to MuleType
    public MuleType getType(String s) {
        if (s.equals("Food")) {
            return MuleType.FOOD;
        } else if (s.equals("Energy")) {
            return MuleType.ENERGY;
        } else if (s.equals("Smithore")) {
            return MuleType.SMITHORE;
        } else if (s.equals("Crystite")) {
            return MuleType.CRYSTITE;
        } else {
            return null;
        }
    }

    //Loads the stores
    public void reinit() {
        gameStartStore = GameStartStore.getInstance();
        mapStateStore = MULEStore.getInstance().getMapStateStore();
        storeStateStore = MULEStore.getInstance().getStoreStateStore();
        placeMuleStore = MULEStore.getInstance().getPlaceMuleStore();
    }

}
