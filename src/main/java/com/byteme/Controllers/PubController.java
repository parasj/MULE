package com.byteme.Controllers;
import com.byteme.Models.GameStartStore;
import com.byteme.Models.MULEStore;
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
    private final GameStartStore g;
    private final MapStateStore s;
    public final ConfigRepository r;
    private static final int[] roundBonusArr = {50, 50, 50, 100, 100, 100, 100, 150, 150, 150, 150, 200};

    private BoardController boardController;

    @FXML
    private Label playerLabel;
    @FXML
    private Label moneyLabel;

    public PubController() {
        g = GameStartStore.getInstance();
        s = MULEStore.getInstance().getMapStateStore();
        r = MULEStore.getInstance().getConfigRepository();
    }


    public void goToMap() {
        MasterController.getInstance().map();
        GameStartHandler gameStartHandler = (GameStartHandler) boardController.getGameStartHandler();
        gameStartHandler.nextPlayer();
        boardController.updateState(MapControllerStates.GAME_START);
    }

    private int getTimeBonus(int timeLeft) {
        if (timeLeft >= 37) return 200;
        else if (timeLeft >= 25) return 150;
        else if (timeLeft >= 12) return 100;
        else if (timeLeft > 0) return 50;
        else return 0;
    }

    private void getMoney() {
        PlayerConfigParams currentPlayer = s.getPlayerAt(g.getCurrentPlayer());
        int timeLeft = currentPlayer.getTimeLeft();
        int roundBonus = roundBonusArr[s.getCurrentRound() - 1];
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
            playerLabel.setText(String.format("Player %d %s", g.getCurrentPlayer() + 1, s.getPlayerAt(g.getCurrentPlayer()).getName()));
        }
        if (moneyLabel != null) {
            moneyLabel.setText("MONEY: " + s.getPlayerAt(g.getCurrentPlayer()).getMoney());
        }
    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }
}
