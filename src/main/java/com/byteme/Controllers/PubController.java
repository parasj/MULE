package com.byteme.Controllers;
import com.byteme.Models.MapStateStore;
import com.byteme.Models.ConfigRepository;
import com.byteme.Schema.MapControllerStates;
import com.byteme.Schema.PlayerConfigParams;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.util.Random;

/**
 * Created by Daniel on 9/30/2015.
 */
public class PubController {


    @FXML
    private Label playerLabel;
    @FXML
    private Label moneyLabel;

    private static ConfigRepository configRepository = ConfigRepository.getInstance();
    private int[] roundBonusArr = {50, 50, 50, 100, 100, 100, 100, 150, 150, 150, 150, 200};

    private MapController mapController;

    public PubController() {

    }

    public void setMapController(MapController mapController) {
        this.mapController = mapController;
    }

    public void goToMap() {
        //TODO Update the Label in Map.
        //getMoney();
        //TODO Increment Round Properly
        //Change phase Mapstore 3
        mapController.incRound();
        MapStateStore.getInstance().setTimeLeft(calcTimeLeft(null));
        mapController.changePlayer();
        MapStateStore.getInstance().setCurrentState(MapControllerStates.GAME_START);
        MasterController.getInstance().map();
    }

    public int calcTimeLeft(PlayerConfigParams player) {
        //TODO calculate the time left
        return 10;
    }
    private int getTimeBonus(int timeLeft) {
        if (timeLeft >= 37) {
            return 200;
        } else if (timeLeft >= 25) {
            return 150;
        } else if (timeLeft >= 12) {
            return 100;
        } else {
            return 0;
        }
    }

    private void getMoney() {
        int currentPlayerIndex = MapStateStore.getInstance().getCurrentPlayer();
        PlayerConfigParams currentPlayer = configRepository.getPlayerConfig(currentPlayerIndex);
        int timeLeft = MapStateStore.getInstance().getTimeLeft();
        int roundBonus = roundBonusArr[MapStateStore.getInstance().getCurrentRound() - 1];
        Random rand = new Random();
        int timeBonus = rand.nextInt(getTimeBonus(timeLeft) + 1);
        int moneyBonus = roundBonus * timeBonus;
        if (moneyBonus > 250) {
            moneyBonus = 250;
        }
        System.out.println("You Earned: " + moneyBonus);
        currentPlayer.payMoney(-1 * moneyBonus);
    }

    public void rerender() {
        getMoney();
        if (playerLabel != null) playerLabel.setText(String.format("Player %d %s", MapStateStore.getInstance().getCurrentPlayer(), ConfigRepository.getInstance().getPlayerConfig(MapStateStore.getInstance().getCurrentPlayer()).getName()));
        if (moneyLabel != null) moneyLabel.setText("MONEY: " + ConfigRepository.getInstance().getPlayerConfig(MapStateStore.getInstance().getCurrentPlayer()).getMoney());
    }
}
