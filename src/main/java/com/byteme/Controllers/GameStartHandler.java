package com.byteme.Controllers;

import com.byteme.Models.GameStartStore;
import com.byteme.Models.MapStateStore;
import com.byteme.Schema.MapControllerStates;
import com.byteme.Schema.PlayerConfigParams;
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
        getBoardController().renderMoney(0);
        getBoardController().renderRound(0);
        getBoardController().renderTimer(0);
    }

    private void setMoney(int m) {
        getBoardController().renderMoney(-2);
    }

    private void setRound(int r) {
        getBoardController().renderRound(-2);
    }

    private void setTimer(int t) {
        getBoardController().renderTimer(-2);
    }

    @Override
    public void tick() {
        PlayerConfigParams p = m.getPlayerAt(m.getCurrentPlayer());
        if (p.getTimeLeft() > 0) {
            p.setTimeLeft(p.getTimeLeft() - 1);
            getBoardController().renderTimer(p.getTimeLeft());
        } else {
            getBoardController().updateState(MapControllerStates.TURN_OVER);
            MasterController.getInstance().pubScene();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
