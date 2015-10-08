package com.byteme.Controllers;

import com.byteme.Models.ConfigRepository;
import com.byteme.Models.GameStartStore;
import com.byteme.Models.MapStateStore;
import com.byteme.Models.PlaceMuleStore;
import com.byteme.Schema.MapControllerStates;
import com.byteme.Schema.PlayerConfigParams;
import com.byteme.Schema.Property;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * MULE
 */
public class PlaceMuleHandler extends MapStateHandler {
    private final static GameStartStore st = GameStartStore.getInstance();
    private final static PlaceMuleStore pm = PlaceMuleStore.getInstance();
    private final static MapStateStore m = MapStateStore.getInstance();

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
        BorderPane tile = (BorderPane) event.getSource();
        PlayerConfigParams p = m.getPlayerAt(st.getCurrentPlayer());
        int row = GridPane.getRowIndex(tile);
        int column = GridPane.getColumnIndex(tile);
            //TODO Add horse properly
            //tile.setCenter(horse);

        boolean found = false;
        for (int i = 0; i < p.getProperties().size(); i++) {
            Property a = p.getProperties().get(i);
            if (a.getOwner().equals(p) && a.getRow() == row && a.getColumn() == column) {
                log("You can place a mule here!");
                a.addMule(pm.getMule());
                log("Placed MULE: " + pm.getMule().toString());
                //MasterController.getInstance().getBoardController().propertyUpdated(properties.get(i));
                found = true;
                break;
            }
        }
        if (!found) {
            log("********************************************");
            log("* Cannot place M.U.L.E here! M.U.L.E lost! *");
            log("********************************************");
        }
        goToStore();
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
            getBoardController().updateState(MapControllerStates.TURN_OVER);
            MasterController.getInstance().pubScene();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void goToStore() {
        MasterController.getInstance().store();
        getBoardController().updateState(MapControllerStates.GAME_START);
    }

}
