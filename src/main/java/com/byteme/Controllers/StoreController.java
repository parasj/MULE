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

    private final static GameStartStore st = GameStartStore.getInstance();
    private final static MapStateStore m = MapStateStore.getInstance();
    private StoreStateStore s = StoreStateStore.getInstance();
    private PlaceMuleStore pm = PlaceMuleStore.getInstance();
    private RemoveHorseStore rh = RemoveHorseStore.getInstance();
    private BoardController boardController;

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


    public void goToMap() {
        MasterController.getInstance().map();
    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }

    public void changeState() {
        if (s.getState()) {
            foodButton.setText("Sell Food");
            energyButton.setText("Sell Energy");
            smithoreButton.setText("Sell Smith Ore");
            crystiteButton.setText("Sell Crystite");
            muleButton.setText("Sell Mule");
            changeState.setText("Change to Buy");
            s.setState(false);

        } else {
            foodButton.setText("Buy Food");
            energyButton.setText("Buy Energy");
            smithoreButton.setText("Buy Smith Ore");
            crystiteButton.setText("Buy Crystite");
            muleButton.setText("Buy Mule");
            changeState.setText("Change to Sell");
            s.setState(true);
        }
        log("State changed!");
    }

    public void reRender() {
        PlayerConfigParams p = m.getPlayerAt(st.getCurrentPlayer());
        foodQuantity.setText("Quantity: " + s.getFoodQuantity());
        foodPrice.setText("Price: " + s.getFoodPrice());
        energyQuantity.setText("Quantity: " + s.getEnergyQuantity());
        energyPrice.setText("Price: " + s.getEnergyPrice());
        smithoreQuantity.setText("Quantity: " + s.getSmithoreQuantity());
        smithorePrice.setText("Price: " + s.getSmithorePrice());
        crystiteQuantity.setText("Quantity: " + s.getCrystiteQuantity());
        crystitePrice.setText("Price: " + s.getCrystitePrice());
        muleQuantity.setText("Quantity: " + s.getMuleQuantity());
        mulePrice.setText("Price: " + s.getMulePrice());
        money.setText("" + p.getMoney());
        logPlayer();
        muleType.getSelectionModel().selectFirst();
    }

    public void tradeFood() {
        PlayerConfigParams p = m.getPlayerAt(st.getCurrentPlayer());
        if (s.getState()) {
            if (p.getMoney() >= s.getFoodPrice() && s.getFoodQuantity() > 0) {
                p.payMoney(s.getFoodPrice());
                s.setFoodQuantity(s.getFoodQuantity() - 1);
                p.addFood();
                reRender();
            } else {
                log("Cannot buy food");
            }
        } else {
            if (p.getFood() > 0) {
                p.payMoney(-1 * s.getFoodPrice());
                s.setFoodQuantity(s.getFoodQuantity() + 1);
                p.subFood();
                reRender();
            } else {
                log("Cannot sell food");
            }
        }
    }

    public void tradeEnergy() {
        PlayerConfigParams p = m.getPlayerAt(st.getCurrentPlayer());
        if (s.getState()) {
            if (p.getMoney() >= s.getEnergyPrice() && s.getEnergyQuantity() > 0) {
                p.payMoney(s.getEnergyPrice());
                s.setEnergyQuantity(s.getEnergyQuantity() - 1);
                p.addEnergy();
                reRender();
            } else {
                log("Cannot buy energy");
            }
        } else {
            if (p.getEnergy() > 0) {
                p.payMoney(-1 * s.getEnergyPrice());
                s.setEnergyQuantity(s.getEnergyQuantity() + 1);
                p.subEnergy();
                reRender();
            } else {
                log("Cannot sell energy");
            }
        }
    }

    public void tradeSmithore() {
        PlayerConfigParams p = m.getPlayerAt(st.getCurrentPlayer());
        if (s.getState()) {
            if (p.getMoney() >= s.getSmithorePrice() && s.getSmithoreQuantity() > 0) {
                p.payMoney(s.getSmithorePrice());
                s.setSmithoreQuantity(s.getSmithoreQuantity() - 1);
                p.addSmithore();
                reRender();
            } else {
                log("Cannot buy smithore");
            }
        } else {
            if (p.getSmithore() > 0) {
                p.payMoney(-1 * s.getSmithorePrice());
                s.setSmithoreQuantity(s.getSmithoreQuantity() + 1);
                p.subSmithore();
                reRender();
            } else {
                log("Cannot sell smithore");
            }
        }
    }

    public void tradeCrystite() {
        PlayerConfigParams p = m.getPlayerAt(st.getCurrentPlayer());
        if (s.getState()) {
            if (p.getMoney() >= s.getCrystitePrice() && s.getCrystiteQuantity() > 0) {
                p.payMoney(s.getCrystitePrice());
                s.setCrystiteQuantity(s.getCrystiteQuantity() - 1);
                p.addCrystite();
                reRender();
            } else {
                log("Cannot buy crystite");
            }
        } else {
            if (p.getCrystite() > 0) {
                p.payMoney(-1 * s.getCrystitePrice());
                s.setCrystiteQuantity(s.getCrystiteQuantity() + 1);
                p.subCrystite();
                reRender();
            } else {
                log("Cannot sell crystite");
            }
        }
    }

    public void tradeMule() {
        PlayerConfigParams p = m.getPlayerAt(st.getCurrentPlayer());
        if (s.getState()) {
            if (p.getMoney() >= s.getMulePrice() && s.getMuleQuantity() > 0) {
                pm.setMule(new Mule(getType((String) muleType.getValue())));
                boardController.updateState(MapControllerStates.PLACE_MULE);
                goToMap();
                //DOESNT WAIT UNTIL SELECTED
                p.payMoney(s.getMulePrice() + s.getMuleTypeCost((String) muleType.getValue()));
                s.setMuleQuantity(s.getMuleQuantity() - 1);
                if (pm.isEmpty()) {
                    p.addMule();
                }
                reRender();
            } else {
                log("Cannot buy Mule");
            }
        } else {
            if (p.getMuleCount() > 0) {
                rh.setMule(new Mule(getType((String) muleType.getValue())));
                boardController.updateState(MapControllerStates.REMOVE_MULE);
                goToMap();
                pm.setMule(null);
                //Doesn't wait
                p.payMoney(-1 * (s.getMulePrice() + s.getMuleTypeCost((String) muleType.getValue())));
                s.setMuleQuantity(s.getMuleQuantity() + 1);
                p.subMule();
                reRender();
            } else {
                log("Cannot sell Mule");
            }
        }
    }

    public void log(String string) {
        System.out.println(string);
    }

    public void logPlayer() {
        PlayerConfigParams p = m.getPlayerAt(st.getCurrentPlayer());
        log("Player: " + p.getName());
        log("Food: " + p.getFood());
        log("Energy: " + p.getEnergy());
        log("Smithore: " + p.getSmithore());
        log("Crystite: " + p.getCrystite());
        log("==================================================");
    }

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

}
