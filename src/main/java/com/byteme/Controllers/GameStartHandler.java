package com.byteme.Controllers;

import com.byteme.Models.GameStartStore;
import com.byteme.Models.MapStateStore;
import com.byteme.Schema.MapControllerStates;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by rishav on 10/2/2015.
 */
public class GameStartHandler extends MapStateHandler {
    private GameStartStore s = GameStartStore.getInstance();
    private final static MapStateStore m = MapStateStore.getInstance();

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
        renderMoney(-1);
        renderRound(-1);
        renderTimer(-1);
    }

    private void renderMoney(int m) {
        getBoardController().getMoneyLabel().setText(String.format("Money: %6d", m);
    }

    private void renderRound(int r) {
        getBoardController().getRoundLabel().setText(String.format("Round: %6d", r);
    }

    private void renderTimer(int t) {
        getBoardController().getTimerLabel().setText(String.format("Timer: %6d", t);
    }

    private void setMoney(int m) {
        renderMoney(-2);
    }

    private void setRound(int r) {
        renderRound(-2);
    }

    private void setTimer(int t) {
        renderTimer(-2);
    }

    @Override
    public void tick() {
        if (m.getTimeLeft() > 0) {
            m.setTimeLeft(m.getTimeLeft() - 1);
        } else {
            getBoardController().updateState(MapControllerStates.TURN_OVER);
            MasterController.getInstance().pubScene();
            if (m.getCurrentPlayer() < m.getNumPlayers() - 1) {
                m.setCurrentPlayer(m.getCurrentPlayer() + 1);
            } else {
                m.setCurrentRound(m.getCurrentRound() + 1);
                m.setCurrentPlayer(0);
                m.sortPlayers();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
