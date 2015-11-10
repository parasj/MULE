package com.byteme.Controllers;
import com.byteme.Models.GameStartStore;
import com.byteme.Models.MULEStore;
import com.byteme.Models.MapStateStore;
import com.byteme.Models.ConfigRepository;
import com.byteme.Schema.MapControllerStates;
import com.byteme.Schema.PlayerConfigParams;
import com.byteme.Util.RandomWrapper;
import com.byteme.Util.TestableRandomWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


/**
 * MULE
 */
public class PubController {
<<<<<<< HEAD
    private TestableRandomWrapper random;
=======

>>>>>>> 245801b5ea65d4000aa1eb3f8548eb9a4637af64
    /**
     * FIRSTNUM of type int.
     * Made these variables this way because of checkstyle.
     */
    public static final int FIRSTNUM = 37;
    /**
     * SECONDNUM of type int.
     */
    public static final int SECONDNUM = 200;
    /**
     * THIRDNUM of type int.
     */
    public static final int THIRDNUM = 25;
    /**
     * FOURTHNUM of type int.
     */
    public static final int FOURTHNUM = 150;
    /**
     * FIFTHNUM of type int.
     */
    public static final int FIFTHNUM = 12;
    /**
     * SIXTHNUM of type int.
     */
    public static final int SIXTHNUM = 100;
    /**
     * SEVENTHNUM of type int.
     */
    public static final int SEVENTHNUM = 50;
    /**
     * MONEYCOMP of type int.
     */
    public static final int MONEYCOMP = 250;
    /**
     * gameStartStore of type GameStartStore.
     */
    private GameStartStore gameStartStore;
    /**
     * mapStateStore of type MapStateStore.
     */
    private MapStateStore mapStateStore;
<<<<<<< HEAD
    public ConfigRepository configRepository;
    private static final int[] roundBonusArr = {50, 50, 50, 100, 100, 100, 100,
        150, 150, 150, 150, 200};
=======
    private static final int[] roundBonusArr = {50, 50, 50, 100, 100, 100, 100, 150, 150, 150, 150, 200};
    private TestableRandomWrapper random = new RandomWrapper();

    /**
     * configRepository of type ConfigRepository.
     */
    private ConfigRepository configRepository;
    /**
     * ROUNDBONUSARR of type int[].
     */
    private static final int[] ROUNDBONUSARR =
        {50, 50, 50, 100, 100, 100, 100, 150, 150, 150, 150, 200};
    /**
     * boardController instance of BoardController.
     */
>>>>>>> 245801b5ea65d4000aa1eb3f8548eb9a4637af64
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

    public PubController(TestableRandomWrapper random, GameStartStore gameStartStore, MapStateStore mapStateStore, ConfigRepository configRepository) {
        this.random = random;
        this.gameStartStore = gameStartStore;
        this.mapStateStore = mapStateStore;
        this.configRepository = configRepository;
        getMoney();
    }


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
<<<<<<< HEAD
        GameStartHandler gameStartHandler =
            (GameStartHandler) boardController.getGameStartHandler();
=======
        GameStartHandler gameStartHandler = (GameStartHandler) boardController
        .getGameStartHandler();
>>>>>>> 245801b5ea65d4000aa1eb3f8548eb9a4637af64
        gameStartHandler.nextPlayer();
        boardController.updateState(MapControllerStates.GAME_START, true);
    }

    /**
     * @param timeLeft of type int.
     * @return int. the time bonus given.
     */
    private int getTimeBonus(final int timeLeft) {

        if (timeLeft >= FIRSTNUM) {
            return SECONDNUM;
        } else if (timeLeft >= THIRDNUM) {
            return FOURTHNUM;
        } else if (timeLeft >= FIFTHNUM) {
            return SIXTHNUM;
        } else if (timeLeft > 0) {
            return SEVENTHNUM;
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
<<<<<<< HEAD
    protected void getMoney() {
=======
    private void getMoney() {
>>>>>>> 245801b5ea65d4000aa1eb3f8548eb9a4637af64
        PlayerConfigParams currentPlayer = mapStateStore
            .getPlayerAt(gameStartStore.getCurrentPlayer());
        int timeLeft = currentPlayer.getTimeLeft();
        int roundBonus = roundBonusArr[mapStateStore.getCurrentRound() - 1];
        int timeBonus = random.getInt(getTimeBonus(timeLeft) + 1);
        int moneyBonus = roundBonus * timeBonus;
        if (moneyBonus > MONEYCOMP) {
            moneyBonus = MONEYCOMP;
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
            playerLabel.setText(String
                .format("Player %d %s", gameStartStore
                    .getCurrentPlayer() + 1, mapStateStore
                        .getPlayerAt(gameStartStore.getCurrentPlayer())
                            .getName()));
<<<<<<< HEAD
=======
            playerLabel.setText(String.format("Player %d %s", gameStartStore.getCurrentPlayer() + 1, mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer()).getName()));
>>>>>>> 245801b5ea65d4000aa1eb3f8548eb9a4637af64
        }
        if (moneyLabel != null) {
            moneyLabel.setText("MONEY: " + mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer()).getMoney());
        }
    }

    /**
     *
<<<<<<< HEAD
     * @param boardController of type BoardController.
=======
     * Also I changed parameter names because of checkstyle.
     * @param boardController1 of type BoardController.
>>>>>>> 245801b5ea65d4000aa1eb3f8548eb9a4637af64
     */
    public final void setBoardController(
            final BoardController boardController1) {
        this.boardController = boardController1;
    }

    /**
     *
     */
    public final void reinit() {
        gameStartStore = GameStartStore.getInstance();
        mapStateStore = MULEStore.getInstance().getMapStateStore();
        configRepository = MULEStore.getInstance().getConfigRepository();
    }

    public PlayerConfigParams getPlayer() {
        return mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer());
    }
}
