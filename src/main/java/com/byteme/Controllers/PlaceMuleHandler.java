package com.byteme.Controllers;

import com.byteme.Models.ConfigRepository;
import com.byteme.Models.GameStartStore;
import com.byteme.Models.MapStateStore;
import com.byteme.Models.PlaceMuleStore;
import com.byteme.Schema.MapControllerStates;
import com.byteme.Schema.PlayerConfigParams;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by rishavbose365 on 10/7/2015.
 */
public class PlaceMuleHandler extends MapStateHandler {
    private final static GameStartStore st = GameStartStore.getInstance();
    private final static PlaceMuleStore pm = PlaceMuleStore.getInstance();
    private final static MapStateStore m = MapStateStore.getInstance();
    private final static ConfigRepository r = ConfigRepository.getInstance();

    public PlaceMuleHandler(BoardController boardController) {
        super(boardController);
    }

    @Override
    public void handlePass() {
        log("Cannot pass now!");
    }

    @Override
    public void handleTownButtonClicked() {
    }

    @Override
    public void tileChosen(MouseEvent event) {
        //TODO handle when user chooses a tile to place mule on
        MasterController.getInstance().store();
        getBoardController().updateState(MapControllerStates.GAME_START);
    }

    @Override
    public void stateChanged() {
        getBoardController().getPhaseLabel().setText("Place Mule");
        getBoardController().setPlayer(m.getPlayerAt(st.getCurrentPlayer()));
        getBoardController().renderMoney(m.getPlayerAt(st.getCurrentPlayer()).getMoney());
        getBoardController().renderRound(m.getCurrentRound());
        getBoardController().renderTimer(m.getPlayerAt(st.getCurrentPlayer()).getTimeLeft());
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
            //TODO handle if time runs out and you still have to place mule
            getBoardController().updateState(MapControllerStates.TURN_OVER);
            MasterController.getInstance().pubScene();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
