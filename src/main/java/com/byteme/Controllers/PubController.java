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
 * MULE.
 */
public class PubController {
    /**
     * gameStartStore of type GameStartStore.
     */
    private GameStartStore gameStartStore;
    /**
     * mapStateStore of type MapStateStore.
     */
    private MapStateStore mapStateStore;
    /**
     * configRepository of type ConfigRepository.
     */
    private ConfigRepository configRepository;
    /**
     * roundBonusArr of type int[].
     */
    private static final int[] roundBonusArr =
        {50, 50, 50, 100, 100, 100, 100, 150, 150, 150, 150, 200};
    /**
     * boardController instance of BoardController.
     */
    private BoardController boardController;
    /**
     * playerLabel instance of Label.
     */
    @FXML
    private Label playerLabel;
    /**
     * moneyLabel instance of Label.
     */
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
    public final void goToMap() {
        MasterController.getInstance().map();
        GameStartHandler gameStartHandler = (GameStartHandler) boardController
        .getGameStartHandler();
        gameStartHandler.nextPlayer();
        boardController.updateState(MapControllerStates.GAME_START, true);
    }

    /**
     * @param timeLeft of type int.
     * @return int. the time bonus given.
     */
    private int getTimeBonus(final int timeLeft) {
        if (timeLeft >= 37) {
            return 200;
        } else if (timeLeft >= 25) {
            return 150;
        } else if (timeLeft >= 12) {
            return 100;
        } else if (timeLeft > 0) {
            return 50;
        } else {
            return 0;
        }
    }

    /**
     *
     */
    //Gets how much money to pay player
    private void getMoney() {
        PlayerConfigParams currentPlayer = mapStateStore
            .getPlayerAt(gameStartStore.getCurrentPlayer());
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
    public final void rerender() {
        reinit();
        getMoney();
        if (playerLabel != null) {
            playerLabel.setText(String.format("Player %d %s", gameStartStore
                .getCurrentPlayer() + 1, mapStateStore
                .getPlayerAt(gameStartStore
                    .getCurrentPlayer()).getName()));
        }
        if (moneyLabel != null) {
            moneyLabel.setText("MONEY: " + mapStateStore
                .getPlayerAt(gameStartStore.getCurrentPlayer()).getMoney());
        }
    }

    /**
     *
     * @param boardController of type BoardController.
     */
    public final void setBoardController(
            final BoardController boardController) {
        this.boardController = boardController;
    }

    /**
     *
     */
    //Loads stores
    public final void reinit() {
        gameStartStore = GameStartStore.getInstance();
        mapStateStore = MULEStore.getInstance().getMapStateStore();
        configRepository = MULEStore.getInstance().getConfigRepository();
    }
}
