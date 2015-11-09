package com.byteme.Controllers;

//import com.byteme.Models.*;
import com.byteme.Models.MULEStore;
import com.byteme.Models.GameStartStore;
import com.byteme.Models.MapStateStore;
import com.byteme.Models.StoreStateStore;
import com.byteme.Models.PlaceMuleStore;
import com.byteme.Schema.MapControllerStates;
import com.byteme.Schema.Mule;
import com.byteme.Schema.MuleType;
import com.byteme.Schema.PlayerConfigParams;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

/**
 * Mule.
 */
public class StoreController {
    /**
     * gameStartStore of type GameStartStore.
     */
    private GameStartStore gameStartStore;
    /**
     * mapStateStore of type MapStateStore.
     */
    private MapStateStore mapStateStore;
    /**
     * storeStateStore of type StoreStateStore.
     */
    private StoreStateStore storeStateStore;
    /**
     * placeMuleStore of type PlaceMuleStore.
     */
    private PlaceMuleStore placeMuleStore;
    /**
     * boardController of type BoardController.
     */
    private static BoardController boardController;

    /**
     * foodQuantity instance of Label.
     */
    @FXML
    private Label foodQuantity;
    /**
     * foodPrice instance of Label.
     */
    @FXML
    private Label foodPrice;
    /**
     * energyQuantity instance of Label.
     */
    @FXML
    private Label energyQuantity;
    /**
     * energyPrice instance of Label.
     */
    @FXML
    private Label energyPrice;
    /**
     * smithoreQuantity instance of Label.
     */
    @FXML
    private Label smithoreQuantity;
    /**
     * smithorePrice instance of Label.
     */
    @FXML
    private Label smithorePrice;
    /**
     * crystiteQuantity instance of Label.
     */
    @FXML
    private Label crystiteQuantity;
    /**
     * crystitePrice instance of Label.
     */
    @FXML
    private Label crystitePrice;
    /**
     * muleQuantity instance of Label.
     */
    @FXML
    private Label muleQuantity;
    /**
     * mulePrice instance of Label.
     */
    @FXML
    private Label mulePrice;
    /**
     * money instance of Label.
     */
    @FXML
    private Label money;
    /**
     * foodButton instance of Button.
     */
    @FXML
    private Button foodButton;
    /**
     * foodButton instance of Button.
     */
    @FXML
    private Button energyButton;
    /**
     * smithoreButton instance of Button.
     */
    @FXML
    private Button smithoreButton;
    /**
     * crystiteButton instance of Button.
     */
    @FXML
    private Button crystiteButton;
    /**
     * muleButton instance of Button.
     */
    @FXML
    private Button muleButton;
    /**
     * changeState instance of Button.
     */
    @FXML
    private Button changeState;
    /**
     * muleType instance of ChoiceBox.
     */
    @FXML
    private ChoiceBox muleType;

    /**
     *
     */
    //Reloads stores
    public StoreController() {
        reinit();
    }

    /**
     *
     */
    public final void goToMap() {
        MasterController.getInstance().map();
    }

    /**
     *
     * @param boardController1 sets the boardController.
     */
    public static void setBoardController(final
            BoardController boardController1) {
        boardController = boardController1;
    }

    /**
     *
     */
    //Changes between buy and selling items
    public final void changeState() {
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

    /**
     *
     */
    //Changes all the labels
    public final void reRender() {
        reinit();
        PlayerConfigParams player = mapStateStore.getPlayerAt(gameStartStore
            .getCurrentPlayer());
        if (player == null) {
            throw new IllegalArgumentException("Player is null!");
        }
        foodQuantity.setText("Quantity: " + storeStateStore.getFoodQuantity());
        foodPrice.setText("Price: " + storeStateStore.getFoodPrice());
        energyQuantity.setText("Quantity: " + storeStateStore
            .getEnergyQuantity());
        energyPrice.setText("Price: " + storeStateStore.getEnergyPrice());
        smithoreQuantity.setText("Quantity: " + storeStateStore
            .getSmithoreQuantity());
        smithorePrice.setText("Price: " + storeStateStore.getSmithorePrice());
        crystiteQuantity.setText("Quantity: " + storeStateStore
            .getCrystiteQuantity());
        crystitePrice.setText("Price: " + storeStateStore.getCrystitePrice());
        muleQuantity.setText("Quantity: " + storeStateStore.getMuleQuantity());
        mulePrice.setText("Price: " + storeStateStore.getMulePrice());
        money.setText("" + player.getMoney());
        logPlayer();
        muleType.getSelectionModel().selectFirst();
    }

    /**
     *
     */
    //Buys/ sells food
    public final void tradeFood() {
        reinit();
        PlayerConfigParams player = mapStateStore.getPlayerAt(gameStartStore
            .getCurrentPlayer());
        if (player == null) {
            throw new IllegalArgumentException("Player is null!");
        }
        if (storeStateStore.getState()) {
            if (player.getMoney() >= storeStateStore.getFoodPrice()
                    && storeStateStore.getFoodQuantity() > 0) {
                player.payMoney(storeStateStore.getFoodPrice());
                storeStateStore.setFoodQuantity(storeStateStore
                    .getFoodQuantity() - 1);
                player.addFood();
                reRender();
            } else {
                log("Cannot buy food");
            }
        } else {
            if (player.getFood() > 0) {
                player.payMoney(-1 * storeStateStore.getFoodPrice());
                storeStateStore.setFoodQuantity(storeStateStore
                    .getFoodQuantity() + 1);
                player.subFood();
                reRender();
            } else {
                log("Cannot sell food");
            }
        }
    }

    /**
     *
     */
    public final void tradeEnergy() {
        reinit();
        PlayerConfigParams player = mapStateStore.getPlayerAt(gameStartStore
            .getCurrentPlayer());
        if (player == null) {
            throw new IllegalArgumentException("Player is null!");
        }
        if (storeStateStore.getState()) {
            if (player.getMoney() >= storeStateStore.getEnergyPrice()
                    && storeStateStore.getEnergyQuantity() > 0) {
                player.payMoney(storeStateStore.getEnergyPrice());
                storeStateStore.setEnergyQuantity(storeStateStore
                    .getEnergyQuantity() - 1);
                player.addEnergy();
                reRender();
            } else {
                log("Cannot buy energy");
            }
        } else {
            if (player.getEnergy() > 0) {
                player.payMoney(-1 * storeStateStore.getEnergyPrice());
                storeStateStore.setEnergyQuantity(storeStateStore
                    .getEnergyQuantity() + 1);
                player.subEnergy();
                reRender();
            } else {
                log("Cannot sell energy");
            }
        }
    }

    /**
     *
     */
    public final void tradeSmithore() {
        reinit();
        PlayerConfigParams player = mapStateStore.getPlayerAt(gameStartStore
            .getCurrentPlayer());
        if (player == null) {
            throw new IllegalArgumentException("Player is null!");
        }
        if (storeStateStore.getState()) {
            if (player.getMoney() >= storeStateStore.getSmithorePrice()
                    && storeStateStore.getSmithoreQuantity() > 0) {
                player.payMoney(storeStateStore.getSmithorePrice());
                storeStateStore.setSmithoreQuantity(storeStateStore
                    .getSmithoreQuantity() - 1);
                player.addSmithore();
                reRender();
            } else {
                log("Cannot buy smithore");
            }
        } else {
            if (player.getSmithore() > 0) {
                player.payMoney(-1 * storeStateStore.getSmithorePrice());
                storeStateStore.setSmithoreQuantity(storeStateStore
                    .getSmithoreQuantity() + 1);
                player.subSmithore();
                reRender();
            } else {
                log("Cannot sell smithore");
            }
        }
    }

    /**
     *
     */
    public final void tradeCrystite() {
        reinit();
        PlayerConfigParams player = mapStateStore
            .getPlayerAt(gameStartStore.getCurrentPlayer());
        if (player == null) {
            throw new IllegalArgumentException("Player is null!");
        }
        if (storeStateStore.getState()) {
            if (player.getMoney() >= storeStateStore.getCrystitePrice()
                && storeStateStore.getCrystiteQuantity() > 0) {
                player.payMoney(storeStateStore.getCrystitePrice());
                storeStateStore
                .setCrystiteQuantity(storeStateStore.getCrystiteQuantity() - 1);
                player.addCrystite();
                reRender();
            } else {
                log("Cannot buy crystite");
            }
        } else {
            if (player.getCrystite() > 0) {
                player.payMoney(-1 * storeStateStore.getCrystitePrice());
                storeStateStore.
                setCrystiteQuantity(storeStateStore.getCrystiteQuantity() + 1);
                player.subCrystite();
                reRender();
            } else {
                log("Cannot sell crystite");
            }
        }
    }

    /**
     *
     */
    //Buys mule and places it, does not sell
    public final void tradeMule() {
        reinit();
        PlayerConfigParams player = mapStateStore.
                                    getPlayerAt(
                                    gameStartStore.getCurrentPlayer());
        if (player == null) {
            throw new IllegalArgumentException("Player is null!");
        }
        int muleCost = storeStateStore.getMulePrice()
                                       + storeStateStore.getMuleTypeCost(
                                       (String) muleType.getValue());
        //log("" + muleCost);
        if (storeStateStore.getState()) {
            if (player.getMoney() >= muleCost
                && storeStateStore.getMuleQuantity() > 0) {
                placeMuleStore.
                setMule(new Mule(getType((String) muleType.getValue())));
                boardController.updateState(
                                MapControllerStates.PLACE_MULE, true);
                goToMap();
                player.payMoney(muleCost);
                storeStateStore
                .setMuleQuantity(storeStateStore.getMuleQuantity() - 1);
                reRender();
            } else {
                log("Cannot buy Mule");
            }
        } else {
            log("Cannot sell Mule");
        }
    }

    /**
     *
     * @param string The string to be logged.
     */
    public final void log(final String string) {
        System.out.println(string);
    }

    /**
     *
     */
    public final void logPlayer() {
        PlayerConfigParams player = mapStateStore
                                    .getPlayerAt(
                                    gameStartStore.getCurrentPlayer());
        if (player == null) {
            throw new IllegalArgumentException("Player is null");
        }
        log("Player: " + player.getName());
        log("Food: " + player.getFood());
        log("Energy: " + player.getEnergy());
        log("Smithore: " + player.getSmithore());
        log("Crystite: " + player.getCrystite());
        log("==================================================");
    }

    /**
     *
     * @param string The type of MULE
     * @return The corresponding MuleType for the String
     */
    //Changes string to MuleType
    public final MuleType getType(final String string) {
        if (string.equals("Food")) {
            return MuleType.FOOD;
        } else if (string.equals("Energy")) {
            return MuleType.ENERGY;
        } else if (string.equals("Smithore")) {
            return MuleType.SMITHORE;
        } else if (string.equals("Crystite")) {
            return MuleType.CRYSTITE;
        } else {
            throw new IllegalStateException("Mule must have state!");
        }
    }

    /**
     *
     */
    //Loads the stores
    public final void reinit() {
        gameStartStore = GameStartStore.getInstance();
        mapStateStore = MULEStore.getInstance().getMapStateStore();
        storeStateStore = MULEStore.getInstance().getStoreStateStore();
        placeMuleStore = MULEStore.getInstance().getPlaceMuleStore();
    }

}
