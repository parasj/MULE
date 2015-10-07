package com.byteme.Controllers;

import com.byteme.Schema.MapControllerStates;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Mule
 */
public class StoreController {

    private BoardController boardController;
    //Paras, should this be in a store for the controller?
    private boolean state = true; //true = buy false = sell

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


    public void goToMap() {
        GameStartHandler gameStartHandler = (GameStartHandler) boardController.getGameStartHandler();
        gameStartHandler.nextPlayer();
        MasterController.getInstance().map();
        boardController.updateState(MapControllerStates.GAME_START);
    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }

    public void changeState() {
        if (state) {
            foodButton.setText("Sell Food");
            energyButton.setText("Sell Energy");
            smithoreButton.setText("Sell Smith Ore");
            crystiteButton.setText("Sell Crystite");
            muleButton.setText("Sell Mule");
            changeState.setText("Change to Buy");
            state = false;

        } else {
            foodButton.setText("Buy Food");
            energyButton.setText("Buy Energy");
            smithoreButton.setText("Buy Smith Ore");
            crystiteButton.setText("Buy Crystite");
            muleButton.setText("Buy Mule");
            changeState.setText("Change to Sell");
            state = true;
        }
    }

}
