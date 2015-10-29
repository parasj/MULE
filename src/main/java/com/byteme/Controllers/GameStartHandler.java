package com.byteme.Controllers;

import com.byteme.Models.ConfigRepository;
import com.byteme.Models.GameStartStore;
import com.byteme.Models.MapStateStore;
import com.byteme.Models.RandomEventGenerator;
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
    private final static GameStartStore st = GameStartStore.getInstance();
    private final static MapStateStore m = MapStateStore.getInstance();
    private final static ConfigRepository r = ConfigRepository.getInstance();
    private final static RandomEventGenerator evtGen = new RandomEventGenerator();

    public GameStartHandler(BoardController boardController) {
        super(boardController);
    }

    @Override
    public void handlePass() {
        MasterController.getInstance().getBoardController().updateState(MapControllerStates.TURN_OVER);
        MasterController.getInstance().pubScene();
    }

    @Override
    public void handleTownButtonClicked() {
        MasterController.getInstance().town();
    }

    @Override
    public void tileChosen(MouseEvent event) {

    }

    @Override
    public void stateChanged() {
        getBoardController().getPhaseLabel().setText("Game Start");
        getBoardController().setPlayer(m.getPlayerAt(st.getCurrentPlayer()));
        getBoardController().renderMoney(m.getPlayerAt(st.getCurrentPlayer()).getMoney());
        getBoardController().renderRound(m.getCurrentRound());
        getBoardController().renderTimer(m.getPlayerAt(st.getCurrentPlayer()).getTimeLeft());
        log("Score: " + m.getPlayerAt(st.getCurrentPlayer()).calcScore());
    }

    private void setMoney(int m) {
        getBoardController().renderMoney(m);
    }

    private void setRound(int r) {
        getBoardController().renderRound(r);
    }

    private void setTimer(int t) {
        getBoardController().renderTimer(t);
    }

    @Override
    public void tick() {
        PlayerConfigParams p = m.getPlayerAt(st.getCurrentPlayer());
        if (p.getTimeLeft() > 0) {
            getBoardController().renderTimer(p.getTimeLeft());
            p.setTimeLeft(p.getTimeLeft() - 1);
        } else {
            getBoardController().updateState(MapControllerStates.TURN_OVER);
            MasterController.getInstance().pubScene();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void nextPlayer() {
        if (st.getCurrentPlayer() == r.getTotalPlayers() - 1) {
            m.setCurrentRound(m.getCurrentRound() + 1);
            calculateProduction();
            st.setCurrentPlayer(1);
            m.sortPlayers();
        }
        st.incCurrentPlayer();
        m.getPlayerAt(st.getCurrentPlayer()).calcTimeLeft();
        log("Player changed to " + st.getCurrentPlayer());
        calculateRandomEvents();
    }

    public void calculateRandomEvents() {
        PlayerConfigParams p = m.getPlayerAt(st.getCurrentPlayer());
        RandomEvent evt = evtGen.getEvent(lastPlace(p));
        p.setFood(evt.calcFood(p.getFood()));
        p.setEnergy(evt.calcEnergy(p.getEnergy()));
        p.setMoney(evt.calcMoney(p.getMoney(), m.getCurrentRound()));
        p.setSmithore(evt.calcOre(p.getSmithore()));

        String evtName = evt.toString();
        log("Random Event: " + evtName);

        if (!evt.equals(RandomEvent.NOTHING)) {
            Alert.AlertType alertType = Alert.AlertType.INFORMATION;
            String header = "Good fortune, " + m.getPlayerAt(st.getCurrentPlayer()).getName() + "!";
            if (!evt.isGood()) {
                alertType = Alert.AlertType.WARNING;
                header = "Better luck next time, " + m.getPlayerAt(st.getCurrentPlayer()).getName() + "!";
            }

            Alert alert = new Alert(alertType);
            alert.setTitle("Random Event");
            alert.setHeaderText(header);
            alert.setContentText(evtName);

            alert.showAndWait();
        }
    }

    private boolean lastPlace(PlayerConfigParams p) {
        int pScore = p.calcScore();
        int minPScore = Integer.MIN_VALUE;
        for (PlayerConfigParams pl : m.getPlayers())
            minPScore = Math.min(minPScore, pl.calcScore());
        return pScore <= minPScore;
    }

    private void calculateProduction() {
        Random rand = new Random();
        for (PlayerConfigParams player: m.getPlayers()) {
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
