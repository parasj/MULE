package com.byteme.Controllers;
import com.byteme.Models.MapStateStore;
import com.byteme.Models.ConfigRepository;
import com.byteme.Schema.MapControllerStates;
import com.byteme.Schema.PlayerConfigParams;
import javafx.fxml.FXML;
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
    private final int[] roundBonusArr = {50, 50, 50, 100, 100, 100, 100, 150, 150, 150, 150, 200};
    private final MapStateStore s = MapStateStore.getInstance();
    public final ConfigRepository r = ConfigRepository.getInstance();

//    private MapController mapController;

    public PubController() {

    }

//    public void setMapController(MapController mapController) {
//        this.mapController = mapController;
//    }

    public void goToMap() {
        if (s.getCurrentPlayer() < r.getTotalPlayers() - 1) {
            s.getPlayerAt(s.getCurrentPlayer()).calcTimeLeft();
            s.setCurrentPlayer(s.getCurrentPlayer() + 1);
        } else {
            s.setCurrentRound(s.getCurrentRound() + 1);
            s.setCurrentPlayer(0);
            s.sortPlayers();
        }
        MapStateStore.getInstance().setCurrentState(MapControllerStates.GAME_START);
        MasterController.getInstance().map();
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
        PlayerConfigParams currentPlayer = s.getPlayerAt(s.getCurrentPlayer());
        int timeLeft = currentPlayer.getTimeLeft();
        int roundBonus = roundBonusArr[MapStateStore.getInstance().getCurrentRound()];
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
        if (playerLabel != null) {
            playerLabel.setText(String.format("Player %d %s", MapStateStore.getInstance().getCurrentPlayer(), s.getPlayerAt(s.getCurrentPlayer()).getName()));
        }
        if (moneyLabel != null) {
            moneyLabel.setText("MONEY: " + s.getPlayerAt(s.getCurrentPlayer()).getMoney());
        }
    }
}
