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
    }

    @Override
    public void tick() {
        PlayerConfigParams p = m.getPlayerAt(m.getCurrentPlayer());
        if (p.getTimeLeft() > 0) {
            p.setTimeLeft(p.getTimeLeft() - 1);
            getBoardController().getTimerLabel().setText("" + p.getTimeLeft());
        } else {
            getBoardController().updateState(MapControllerStates.TURN_OVER);
            MasterController.getInstance().pubScene();
            if (m.getCurrentPlayer() < m.getNumPlayers() - 1) {
                m.setCurrentPlayer(m.getCurrentPlayer() + 1);
                m.getPlayerAt(m.getCurrentPlayer()).getTimeLeft();
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
