package com.byteme.Controllers;

import com.byteme.Models.*;
import com.byteme.Schema.*;
//import com.byteme.Models.GameStartStore;
//import com.byteme.Models.MULEStore;
//import com.byteme.Models.MapStateStore;
//import com.byteme.Schema.PlayerConfigParams;
////import com.byteme.Models.ConfigRepository;
//import com.byteme.Schema.Mule;

import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * MULE.
 */
public class GameStartHandler extends MapStateHandler {
    //Stores for variables
    /**
     * PENERGY, Energy value for P of type int.
     */
    private static final int PENERGY = 3;

    /**
     * RFOOD, Food value for R of type int.
     */
    private static final int RFOOD = 4;

    /**
     * PCRYSTITE, Crystite value for P of type int.
     */
    private static final int PCRYSTITE = 5;

    /**
     * M1CRYSTITE, Crystite value for M1 of type int.
     */
    private static final int M1CRYSTITE = 5;

    /**
     * M2SMITHORE, Smithore value for M2 of type int.
     */
    private static final int M2SMITHORE = 3;

    /**
     * M3SMITHORE, Smithore value for M3 of type int.
     */
    private static final int M3SMITHORE = 4;

    /**
     * M2CRYSTITE, Crystite value for M2 of type int.
     */
    private static final int M2CRYSTITE = 5;

    /**
     * M3CRYSTITE, Crystite value for M3 of type int.
     */
    private static final int M3CRYSTITE = 5;

    /**
     * instance of GameStartStore.
     * Swapped static and final positions.
     */
    private final GameStartStore gameStartStore = GameStartStore
        .getInstance();
    /**
     * instance of MapStateStore.
     */
    private final MapStateStore mapStateStore = MULEStore
        .getInstance().getMapStateStore();
    /**
     * instance of configRepository.
     * Swapped final and static for checkstyle.
     */
    private final ConfigRepository configRepository
        = MULEStore.getInstance().getConfigRepository();
    /**
     * instance of RandomEventGenerator.
     */
    private static final RandomEventGenerator EVTGEN
        = new RandomEventGenerator();

    /**
     *
     * @param boardController of type BoardController.
     */
    public GameStartHandler(final BoardController boardController) {
        super(boardController);
    }

    //When pass is failed, go to pub

    /**
     *
     */
    @Override
    public final void handlePass() {
        MasterController.getInstance().getBoardController()
            .updateState(MapControllerStates.TURN_OVER, true);
        MasterController.getInstance().pubScene();
    }

    //Go to town

    /**
     *
     */
    @Override
    public final void handleTownButtonClicked() {
        MasterController.getInstance().town();
    }

    //Does nothing, just there because it is inherited

    /**
     *
     * @param event of type MouseEvent.
     */
    @Override
    public void tileChosen(final MouseEvent event) {

    }

    //Renders all text labels

    /**
     *
     */
    @Override
    public final void stateChanged() {
        getBoardController().getPhaseLabel().setText("Game Start");
        getBoardController().setPlayer(mapStateStore.getPlayerAt(gameStartStore
            .getCurrentPlayer()));
        getBoardController().renderMoney(mapStateStore
            .getPlayerAt(gameStartStore.getCurrentPlayer()).getMoney());
        getBoardController().renderRound(mapStateStore.getCurrentRound());
        getBoardController().renderTimer(mapStateStore
            .getPlayerAt(gameStartStore.getCurrentPlayer()).getTimeLeft());
        log("Score: " + mapStateStore.getPlayerAt(gameStartStore
            .getCurrentPlayer()).calcScore());
    }

    /**
     *
     * @param money of type int.
     */
    private void setMoney(final int money) {
        getBoardController().renderMoney(money);
    }

    /**
     *
     * @param round of type int.
     */
    private void setRound(final int round) {
        getBoardController().renderRound(round);
    }

    /**
     *
     * @param timer of type int.
     */
    private void setTimer(final int timer) {
        getBoardController().renderTimer(timer);
    }

    //Every second, counts down

    /**
     *
     */
    @Override
    public final void tick() {
        PlayerConfigParams p = mapStateStore.getPlayerAt(gameStartStore
            .getCurrentPlayer());
        if (p.getTimeLeft() > 0) {
            getBoardController().renderTimer(p.getTimeLeft());
            p.setTimeLeft(p.getTimeLeft() - 1);
        } else {
            getBoardController()
                .updateState(MapControllerStates.TURN_OVER, true);
            MasterController.getInstance().pubScene();
        }
    }

    /**
     *
     * @param location of type URL.
     * @param resources of type ResourceBundle.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

    }

    /**
     *
     */
    //Increments player
    public final void nextPlayer() {
        if (gameStartStore.getCurrentPlayer() == configRepository
                .getTotalPlayers() - 1) {
            mapStateStore.setCurrentRound(mapStateStore.getCurrentRound() + 1);
            calculateProduction();
            gameStartStore.setCurrentPlayer(1);
            mapStateStore.sortPlayers();
        }
        gameStartStore.incCurrentPlayer();
        mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer())
            .calcTimeLeft();
        log("Player changed to " + gameStartStore.getCurrentPlayer());
        calculateRandomEvents();
    }

    /**
     *
     */
    //Checks random events at beginning of turn
    public final void calculateRandomEvents() {
        PlayerConfigParams p = mapStateStore.getPlayerAt(gameStartStore
            .getCurrentPlayer());
        RandomEvent evt = EVTGEN.getEvent(lastPlace(p));
        p.setFood(evt.calcFood(p.getFood()));
        p.setEnergy(evt.calcEnergy(p.getEnergy()));
        p.setMoney(evt.calcMoney(p.getMoney(), mapStateStore
            .getCurrentRound()));
        p.setSmithore(evt.calcOre(p.getSmithore()));

        String evtName = evt.toString();
        log("Random Event: " + evtName);

        if (!evt.equals(RandomEvent.NOTHING)) {
            Alert.AlertType alertType = Alert.AlertType.INFORMATION;
            String header = "Good fortune, " + mapStateStore
                .getPlayerAt(gameStartStore.getCurrentPlayer()).getName() + "!";
            if (evt.isBad()) {
                alertType = Alert.AlertType.WARNING;
                header = "Better luck next time, " + mapStateStore
                .getPlayerAt(gameStartStore.getCurrentPlayer()).getName() + "!";
            }

            Alert alert = new Alert(alertType);
            alert.setTitle("Random Event");
            alert.setHeaderText(header);
            alert.setContentText(evtName);

            alert.showAndWait();
        }
    }

    /**
     *
     * @param p of type PlayerConfigParams.
     * @return true or false.
     */
    //Returns whoever is in last place, used for random events
    private boolean lastPlace(final PlayerConfigParams p) {
        int pScore = p.calcScore();
        int minPScore = Integer.MIN_VALUE;
        for (PlayerConfigParams pl : mapStateStore.getPlayers()) {
            minPScore = Math.min(minPScore, pl.calcScore());
        }
        return pScore <= minPScore;
    }

    /**
     *
     */
    //Calcs how much mules make
    private void calculateProduction() {
        Random rand = new Random();
        for (PlayerConfigParams player: mapStateStore.getPlayers()) {
            for (Property prop : player.getProperties()) {
                Mule mule = prop.getMule();
                if (mule != null && player.getEnergy() > 0) {
                    log("Property is: " + prop.getMaptile());
                    switch (prop.getMaptile()) {
                        case R:
                            switch (mule.getType()) {
                                case FOOD:
                                    player.setFood(player.getFood() + RFOOD);
                                    break;
                                case ENERGY:
                                    player.setEnergy(player.getEnergy() + 2);
                                    break;
                                case SMITHORE:
                                    break;
                                case CRYSTITE:
                                    break;
                                    default:
                                    throw new IllegalArgumentException("Type "
                                        + "must be specified");
                            }
                            break;
                        case P:
                            switch (mule.getType()) {
                                case FOOD:
                                    player.setFood(player.getFood() + 2);
                                    break;
                                case ENERGY:
                                    player.setEnergy(player.getEnergy()
                                        + PENERGY);
                                    break;
                                case SMITHORE:
                                    player.setSmithore(player.getSmithore()
                                        + 1);
                                    break;
                                case CRYSTITE:
                                    player.setCrystite(player.getCrystite()
                                        + rand.nextInt(PCRYSTITE));
                                    break;
                            }
                            break;
                            default:
                                    throw new IllegalArgumentException("Type "
                                        + "must be specified");
                        case M1:
                            switch (mule.getType()) {
                                case FOOD:
                                    player.setFood(player.getFood() + 1);
                                    break;
                                case ENERGY:
                                    player.setEnergy(player.getEnergy() + 1);
                                    break;
                                case SMITHORE:
                                    player.setSmithore(player.getSmithore()
                                        + 2);
                                    break;
                                case CRYSTITE:
                                    player.setCrystite(player.getCrystite()
                                        + rand.nextInt(M1CRYSTITE));
                                    break;
                                default:
                                    throw new IllegalArgumentException("Type "
                                        + "must be specified");
                            }
                            break;
                        case M2:
                            switch (mule.getType()) {
                                case FOOD:
                                    player.setFood(player.getFood() + 1);
                                    break;
                                case ENERGY:
                                    player.setEnergy(player.getEnergy() + 1);
                                    break;
                                case SMITHORE:
                                    player.setSmithore(player.getSmithore()
                                        + M2SMITHORE);
                                    break;
                                case CRYSTITE:
                                    player.setCrystite(player.getCrystite()
                                        + rand.nextInt(M2CRYSTITE));
                                    break;
                                default:
                                    throw new IllegalArgumentException("Type "
                                        + "must be specified");
                            }
                            break;
                        case M3:
                            switch (mule.getType()) {
                                case FOOD:
                                    player.setFood(player.getFood() + 1);
                                    break;
                                case ENERGY:
                                    player.setEnergy(player.getEnergy() + 1);
                                    break;
                                case SMITHORE:
                                    player.setSmithore(player.getSmithore()
                                        + M3SMITHORE);
                                    break;
                                case CRYSTITE:
                                    player.setCrystite(player.getCrystite()
                                        + rand.nextInt(M3CRYSTITE));
                                    break;
                                default:
                                    throw new IllegalArgumentException("Type "
                                        + "must be specified");
                            }
                            break;
                    }
                    player.subEnergy();
                }
            }
            log("==================================================");
            log("Your resources are:\n");
            log(player.getResources());
        }
    }
}
