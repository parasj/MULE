package com.byteme.Controllers;

import com.byteme.Models.ConfigRepository;
import com.byteme.Models.GameStartStore;
import com.byteme.Models.MapStateStore;
import com.byteme.Schema.MapControllerStates;
import com.byteme.Schema.PlayerConfigParams;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * MULE
 */
public class GameStartHandler extends MapStateHandler {
    private GameStartStore s = GameStartStore.getInstance();
    private final static MapStateStore m = MapStateStore.getInstance();
    public ConfigRepository r = ConfigRepository.getInstance();

    public GameStartHandler(BoardController boardController) {
        super(boardController);
    }

    @Override
    public void handlePass() {

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
        getBoardController().renderMoney(0);
        getBoardController().renderRound(1);
        getBoardController().renderTimer(0);
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
        PlayerConfigParams p = m.getPlayerAt(s.getCurrentPlayer());
        if (p.getTimeLeft() > 0) {
            getBoardController().renderTimer(p.getTimeLeft());
            p.setTimeLeft(p.getTimeLeft() - 1);
            System.out.println("Tick");
        } else {
            getBoardController().updateState(MapControllerStates.TURN_OVER);
            MasterController.getInstance().pubScene();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void nextPlayer() {
        if (s.getCurrentPlayer() < r.getTotalPlayers() - 1) {
            s.incCurrentPlayer();
            m.getPlayerAt(s.getCurrentPlayer()).calcTimeLeft();
        } else {
            m.setCurrentRound(m.getCurrentRound() + 1);
            s.setCurrentPlayer(1);
            m.sortPlayers();
        }
    }
}
