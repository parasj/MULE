package com.byteme.Controllers;

import com.byteme.Models.*;
import com.byteme.Schema.*;
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
    private final static GameStartStore gameStartStore = GameStartStore.getInstance();
    private final static MapStateStore mapStateStore = MULEStore.getInstance().getMapStateStore();
    private final static ConfigRepository configRepository = MULEStore.getInstance().getConfigRepository();
    private final static RandomEventGenerator evtGen = new RandomEventGenerator();

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
    public void nextPlayer() {
        if (gameStartStore.getCurrentPlayer() == configRepository.getTotalPlayers() - 1) {
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
                                    player.setFood(player.getFood() + 4);
                                    break;
                                case ENERGY:
                                    player.setEnergy(player.getEnergy() + 2);
                                    break;
                                case SMITHORE:
                                    break;
                                case CRYSTITE:
                                    break;
                            }
                            break;
                        case P:
                            switch (mule.getType()) {
                                case FOOD:
                                    player.setFood(player.getFood() + 2);
                                    break;
                                case ENERGY:
                                    player.setEnergy(player.getEnergy() + 3);
                                    break;
                                case SMITHORE:
                                    player.setSmithore(player.getSmithore() + 1);
                                    break;
                                case CRYSTITE:
                                    player.setCrystite(player.getCrystite() + rand.nextInt(5));
                                    break;
                            }
                            break;
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
                                    player.setCrystite(player.getCrystite() + rand.nextInt(5));
                                    break;
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
                                    player.setSmithore(player.getSmithore() + 3);
                                    break;
                                case CRYSTITE:
                                    player.setCrystite(player.getCrystite() + rand.nextInt(5));
                                    break;
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
                                    player.setSmithore(player.getSmithore() + 4);
                                    break;
                                case CRYSTITE:
                                    player.setCrystite(player.getCrystite() + rand.nextInt(5));
                                    break;
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
