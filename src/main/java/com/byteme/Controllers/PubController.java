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
    private GameStartStore gameStartStore;
    private MapStateStore mapStateStore;
    public ConfigRepository configRepository;
    private static final int[] roundBonusArr = {50, 50, 50, 100, 100, 100, 100, 150, 150, 150, 150, 200};

    private BoardController boardController;

    @FXML
    private Label playerLabel;
    @FXML
    private Label moneyLabel;

    /**
     *
     */
    //Loads stores
    public PubController() {
        reinit();
    }

    /**
     *
     */
    public void goToMap() {
        MasterController.getInstance().map();
        GameStartHandler gameStartHandler = (GameStartHandler) boardController.getGameStartHandler();
        gameStartHandler.nextPlayer();
        boardController.updateState(MapControllerStates.GAME_START, true);
    }

    /**
     *
     * @param timeLeft
     * @return
     */
    public int getTimeBonus(int timeLeft) {
        if (timeLeft >= 37) return 200;
        else if (timeLeft >= 25) return 150;
        else if (timeLeft >= 12) return 100;
        else if (timeLeft > 0) return 50;
        else if (timeLeft == 0) return 0;
        else throw new IllegalArgumentException("Time cannot be negative!");
    }

    /**
     *
     */
    //Gets how much money to pay player
    private void getMoney() {
        PlayerConfigParams currentPlayer = mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer());
        int timeLeft = currentPlayer.getTimeLeft();
        int roundBonus = roundBonusArr[mapStateStore.getCurrentRound() - 1];
        Random rand = new Random();
        int timeBonus = rand.nextInt(getTimeBonus(timeLeft) + 1);
        int moneyBonus = roundBonus * timeBonus;
        if (moneyBonus > 250) {
            moneyBonus = 250;
        }
        System.out.println("You Earned: " + moneyBonus);
        currentPlayer.payMoney(-1 * moneyBonus);
    }

    /**
     *
     */
    //Recreates labels
    public void rerender() {
        reinit();
        getMoney();
        if (playerLabel != null) {
            playerLabel.setText(String.format("Player %d %s", gameStartStore.getCurrentPlayer() + 1, mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer()).getName()));
        }
        if (moneyLabel != null) {
            moneyLabel.setText("MONEY: " + mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer()).getMoney());
        }
    }

    /**
     *
     * @param boardController
     */
    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }

    /**
     *
     */
    //Loads stores
    public void reinit() {
        gameStartStore = GameStartStore.getInstance();
        mapStateStore = MULEStore.getInstance().getMapStateStore();
        configRepository = MULEStore.getInstance().getConfigRepository();
    }
}
