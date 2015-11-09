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
 * MULE
 */
public class GameStartHandler extends MapStateHandler {
    //Stores for variables
<<<<<<< HEAD
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
    private GameStartStore gameStartStore = GameStartStore
        .getInstance();
    /**
     * instance of MapStateStore.
     */
    private MapStateStore mapStateStore = MULEStore
        .getInstance().getMapStateStore();
    /**
     * instance of configRepository.
     * Swapped final and static for checkstyle.
     */
    private ConfigRepository configRepository
        = MULEStore.getInstance().getConfigRepository();
    /**
     * instance of RandomEventGenerator.
     */
    private static final RandomEventGenerator EVTGEN
        = new RandomEventGenerator();
=======
    private final static GameStartStore gameStartStore = GameStartStore.getInstance();
    private final static MapStateStore mapStateStore = MULEStore.getInstance().getMapStateStore();
    private final static ConfigRepository configRepository = MULEStore.getInstance().getConfigRepository();
    private final static RandomEventGenerator evtGen = new RandomEventGenerator();
>>>>>>> origin/master

    /**
     *
     * @param boardController
     */
    public GameStartHandler(BoardController boardController) {
        super(boardController);
    }

    //When pass is failed, go to pub

    /**
     *
     */
    @Override
    public void handlePass() {
        MasterController.getInstance().getBoardController().updateState(MapControllerStates.TURN_OVER, true);
        MasterController.getInstance().pubScene();
    }

    //Go to town

    /**
     *
     */
    @Override
    public void handleTownButtonClicked() {
        MasterController.getInstance().town();
    }

    //Does nothing, just there because it is inherited

    /**
     *
     * @param event
     */
    @Override
    public void tileChosen(MouseEvent event) {

    }

    //Renders all text labels

    /**
     *
     */
    @Override
    public void stateChanged() {
        getBoardController().getPhaseLabel().setText("Game Start");
        getBoardController().setPlayer(mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer()));
        getBoardController().renderMoney(mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer()).getMoney());
        getBoardController().renderRound(mapStateStore.getCurrentRound());
        getBoardController().renderTimer(mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer()).getTimeLeft());
        log("Score: " + mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer()).calcScore());
    }

    /**
     *
     * @param money
     */
    private void setMoney(int money) {
        getBoardController().renderMoney(money);
    }

    /**
     *
     * @param round
     */
    private void setRound(int round) {
        getBoardController().renderRound(round);
    }

    /**
     *
     * @param timer
     */
    private void setTimer(int timer) {
        getBoardController().renderTimer(timer);
    }

    //Every second, counts down

    /**
     *
     */
    @Override
    public void tick() {
        PlayerConfigParams p = mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer());
        if (p.getTimeLeft() > 0) {
            getBoardController().renderTimer(p.getTimeLeft());
            p.setTimeLeft(p.getTimeLeft() - 1);
        } else {
            getBoardController().updateState(MapControllerStates.TURN_OVER, true);
            MasterController.getInstance().pubScene();
        }
    }

    /**
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     *
     */
    //Increments player
<<<<<<< HEAD
    public final void nextPlayer() {
        if (gameStartStore.getCurrentPlayer() == configRepository
                .getTotalPlayers() - 1) {
=======
    public void nextPlayer() {
        if (gameStartStore.getCurrentPlayer() == configRepository.getTotalPlayers() - 1) {
>>>>>>> origin/master
            mapStateStore.setCurrentRound(mapStateStore.getCurrentRound() + 1);
            calculateProduction();
            gameStartStore.setCurrentPlayer(1);
            mapStateStore.sortPlayers();
        }
        gameStartStore.incCurrentPlayer();
        mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer()).calcTimeLeft();
        log("Player changed to " + gameStartStore.getCurrentPlayer());
        calculateRandomEvents();
    }

    /**
     *
     */
    //Checks random events at beginning of turn
    public void calculateRandomEvents() {
        PlayerConfigParams p = mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer());
        RandomEvent evt = evtGen.getEvent(lastPlace(p));
        p.setFood(evt.calcFood(p.getFood()));
        p.setEnergy(evt.calcEnergy(p.getEnergy()));
        p.setMoney(evt.calcMoney(p.getMoney(), mapStateStore.getCurrentRound()));
        p.setSmithore(evt.calcOre(p.getSmithore()));

        String evtName = evt.toString();
        log("Random Event: " + evtName);

        if (!evt.equals(RandomEvent.NOTHING)) {
            Alert.AlertType alertType = Alert.AlertType.INFORMATION;
            String header = "Good fortune, " + mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer()).getName() + "!";
            if (!evt.isGood()) {
                alertType = Alert.AlertType.WARNING;
                header = "Better luck next time, " + mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer()).getName() + "!";
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
     * @param p
     * @return
     */
    //Returns whoever is in last place, used for random events
    private boolean lastPlace(PlayerConfigParams p) {
        int pScore = p.calcScore();
        int minPScore = Integer.MIN_VALUE;
        for (PlayerConfigParams pl : mapStateStore.getPlayers())
            minPScore = Math.min(minPScore, pl.calcScore());
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
<<<<<<< HEAD
                                    player.setFood(player.getFood() + RFOOD);
=======
                                    player.setFood(player.getFood() + 4);
>>>>>>> origin/master
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
<<<<<<< HEAD
                                    player.setEnergy(player.getEnergy()
                                        + PENERGY);
=======
                                    player.setEnergy(player.getEnergy() + 3);
>>>>>>> origin/master
                                    break;
                                case SMITHORE:
                                    player.setSmithore(player.getSmithore() + 1);
                                    break;
                                case CRYSTITE:
<<<<<<< HEAD
                                    player.setCrystite(player.getCrystite()
                                        + rand.nextInt(PCRYSTITE));
=======
                                    player.setCrystite(player.getCrystite() + rand.nextInt(5));
>>>>>>> origin/master
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
                                    player.setSmithore(player.getSmithore() + 2);
                                    break;
                                case CRYSTITE:
<<<<<<< HEAD
                                    player.setCrystite(player.getCrystite()
                                        + rand.nextInt(M1CRYSTITE));
=======
                                    player.setCrystite(player.getCrystite() + rand.nextInt(5));
>>>>>>> origin/master
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
<<<<<<< HEAD
                                    player.setSmithore(player.getSmithore()
                                        + M2SMITHORE);
                                    break;
                                case CRYSTITE:
                                    player.setCrystite(player.getCrystite()
                                        + rand.nextInt(M2CRYSTITE));
=======
                                    player.setSmithore(player.getSmithore() + 3);
                                    break;
                                case CRYSTITE:
                                    player.setCrystite(player.getCrystite() + rand.nextInt(5));
>>>>>>> origin/master
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
<<<<<<< HEAD
                                    player.setSmithore(player.getSmithore()
                                        + M3SMITHORE);
                                    break;
                                case CRYSTITE:
                                    player.setCrystite(player.getCrystite()
                                        + rand.nextInt(M3CRYSTITE));
=======
                                    player.setSmithore(player.getSmithore() + 4);
                                    break;
                                case CRYSTITE:
                                    player.setCrystite(player.getCrystite() + rand.nextInt(5));
>>>>>>> origin/master
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
