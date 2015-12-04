package com.byteme.Controllers;
import com.byteme.Handlers.GameStartHandler;
import com.byteme.Models.GameStartStore;
import com.byteme.Models.MULEStore;
import com.byteme.Models.MapStateStore;
import com.byteme.Schema.MapControllerStates;
import com.byteme.Schema.PlayerConfigParams;
import com.byteme.Util.RandomWrapper;
import com.byteme.Util.TestableRandomWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * MULE
 */
public class PubController implements Controller {
    /**
     * FIRST_NUM of type int.
     * Made these variables this way because of checkstyle.
     */
    private static final int FIRST_NUM = 37;
    /**
     * SECOND_NUM of type int.
     */
    private static final int SECOND_NUM = 200;
    /**
     * THIRD_NUM of type int.
     */
    private static final int THIRD_NUM = 25;
    /**
     * FOURTH_NUM of type int.
     */
    private static final int FOURTH_NUM = 150;
    /**
     * FIFTH_NUM of type int.
     */
    private static final int FIFTH_NUM = 12;
    /**
     * SIXTH_NUM of type int.
     */
    private static final int SIXTH_NUM = 100;
    /**
     * SEVENTH_NUM of type int.
     */
    private static final int SEVENTH_NUM = 50;
    /**
     * MONEY_COMP of type int.
     */
    private static final int MONEY_COMP = 250;
    /**
     * gameStartStore of type GameStartStore.
     */
    private GameStartStore gameStartStore;
    /**
     * mapStateStore of type MapStateStore.
     */
    private MapStateStore mapStateStore;
    private TestableRandomWrapper random = new RandomWrapper();

    /**
     * ROUND_BONUS_ARR of type int[].
     */
    private static final int[] ROUND_BONUS_ARR = {50,
        50, 50, 100, 100, 100, 100, 150, 150, 150, 150, 200};
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
     * The Constructor.
     * @param random The random.
     * @param gameStartStore The gamestart.
     * @param mapStateStore The mapstate.
     */
    public PubController(TestableRandomWrapper random,
                         GameStartStore gameStartStore,
                         MapStateStore mapStateStore) {
        this.random = random;
        this.gameStartStore = gameStartStore;
        this.mapStateStore = mapStateStore;
        getMoney();
    }


    /**
     * Loads the stores.
     */
    public PubController() {
        reinitialize();
    }

    /**
     * Goes back to the map.
     */
    public final void goToMap() {
        MasterController.getInstance().map();
        GameStartHandler gameStartHandler =
            (GameStartHandler) boardController.getBoardHandlerFactory().getGameStartHandler();
        gameStartHandler.nextPlayer();
        boardController.updateState(MapControllerStates.GAME_START, true);
    }

    /**
     * @param timeLeft of type int.
     * @return int. the time bonus given.
     */
    private int getTimeBonus(final int timeLeft) {

        if (timeLeft >= FIRST_NUM) {
            return SECOND_NUM;
        } else if (timeLeft >= THIRD_NUM) {
            return FOURTH_NUM;
        } else if (timeLeft >= FIFTH_NUM) {
            return SIXTH_NUM;
        } else if (timeLeft > 0) {
            return SEVENTH_NUM;
        } else if (timeLeft == 0) {
            return 0;
        } else {
            throw new IllegalArgumentException("Time cannot be negative!");
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
        int roundBonus = ROUND_BONUS_ARR[mapStateStore.getCurrentRound() - 1];
        int timeBonus = random.getInt(getTimeBonus(timeLeft) + 1);
        int moneyBonus = roundBonus * timeBonus;
        if (moneyBonus > MONEY_COMP) {
            moneyBonus = MONEY_COMP;
        }
        System.out.println("You Earned: " + moneyBonus);
        currentPlayer.payMoney(-1 * moneyBonus);
    }

    /**
     *
     */
    //Recreates labels
    public final void render() {
        reinitialize();
        getMoney();
        if (playerLabel != null) {
            playerLabel.setText(String
                .format("Player %d %s", gameStartStore
                    .getCurrentPlayer() + 1, mapStateStore
                        .getPlayerAt(gameStartStore.getCurrentPlayer())
                            .getName()));
            playerLabel.setText(String.format("Player %d %s",
                    gameStartStore.getCurrentPlayer() + 1,
                    mapStateStore.getPlayerAt(
                            gameStartStore.getCurrentPlayer()).getName()));
        }
        if (moneyLabel != null) {
            moneyLabel.setText("MONEY: "
                    + mapStateStore.getPlayerAt(
                    gameStartStore.getCurrentPlayer()).getMoney());
        }
    }

    /**
     *
     * Also I changed parameter names because of checkstyle.
     * @param boardController1 of type BoardController.
     */
    public final void setBoardController(
            final BoardController boardController1) {
        this.boardController = boardController1;
    }

    /**
     * Re-initializes the game start store, map state store,
     * and configuration repository.
     */
    private void reinitialize() {
        gameStartStore = GameStartStore.getInstance();
        mapStateStore = MULEStore.getInstance().getMapStateStore();
    }

    /**
     * Gets the player.
     * @return The player.
     */
    public PlayerConfigParams getPlayer() {
        return mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer());
    }
}
