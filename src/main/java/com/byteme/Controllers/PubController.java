package com.byteme.Controllers;

import com.byteme.Models.ConfigRepository;
import com.byteme.Schema.PlayerConfigParams;
import javafx.fxml.Initializable;

import java.util.Random;

/**
 * Created by Daniel on 9/30/2015.
 */
public class PubController {
    private static ConfigRepository configRepository = ConfigRepository.getInstance();
    private int[] roundBonusArr = {50, 50, 50, 100, 100, 100, 100, 150, 150, 150, 150, 200};
    public void goToMap() {
        //TODO Update the Label in Map.
        getMoney();
        //TODO Increment Round Properly
        MasterController.getMapInstance().incRound();
        MasterController.getMapInstance().setTimeLeft(calcTimeLeft(null));
        MasterController.getMapInstance().nextPlayer();
        MasterController.getInstance().map();
    }
    private int calcTimeLeft(PlayerConfigParams player) {
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
            return 50;
        }
    }

    private void getMoney() {
        int currentPlayerIndex = MasterController.getMapInstance().getCurrentPlayer();
        PlayerConfigParams currentPlayer = configRepository.getPlayerConfig(currentPlayerIndex);
        int timeLeft = MasterController.getMapInstance().getTimeLeft();
        int roundBonus = roundBonusArr[MasterController.getMapInstance().getCurrentRound() - 1];
        Random rand = new Random();
        int timeBonus = rand.nextInt(getTimeBonus(timeLeft) + 1);
        System.out.println(timeBonus);
        int moneyBonus = roundBonus * timeBonus;
        if (moneyBonus > 250) {
            moneyBonus = 250;
        }
        System.out.println(moneyBonus);
        currentPlayer.payMoney(-1 * moneyBonus);
    }

}
