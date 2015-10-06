package com.byteme.Controllers;
import com.byteme.Models.GameStartStore;
import com.byteme.Models.MapStateStore;
import com.byteme.Models.ConfigRepository;
import com.byteme.Schema.MapControllerStates;
import com.byteme.Schema.PlayerConfigParams;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Random;

/**
 * MULE
 */
public class PubController {


    @FXML
    private Label playerLabel;
    @FXML
    private Label moneyLabel;

    private GameStartStore g = GameStartStore.getInstance();
    private int[] roundBonusArr = {50, 50, 50, 100, 100, 100, 100, 150, 150, 150, 150, 200};
    private MapStateStore s = MapStateStore.getInstance();
    public ConfigRepository r = ConfigRepository.getInstance();

//    private MapController mapController;

    public PubController() {

    }

//    public void setMapController(MapController mapController) {
//        this.mapController = mapController;
//    }

    public void goToMap() {
        if (g.getCurrentPlayer() < r.getTotalPlayers() - 1) {
            g.incCurrentPlayer();
            s.getPlayerAt(g.getCurrentPlayer()).calcTimeLeft();
        } else {
            s.setCurrentRound(s.getCurrentRound() + 1);
            g.setCurrentPlayer(1);
            s.sortPlayers();
        }
        MasterController.getInstance().map();
        MapStateStore.getInstance().setCurrentState(MapControllerStates.GAME_START);
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
        PlayerConfigParams currentPlayer = s.getPlayerAt(g.getCurrentPlayer());
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
