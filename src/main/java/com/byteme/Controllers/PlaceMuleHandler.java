package com.byteme.Controllers;

import com.byteme.Models.GameStartStore;
import com.byteme.Models.MULEStore;
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
import java.util.ResourceBundle;

/**
 * MULE
 */
public class PlaceMuleHandler extends MapStateHandler {
    private final GameStartStore st;
    private final PlaceMuleStore pm;
    private final MapStateStore m;

    public PlaceMuleHandler(BoardController boardController) {
        super(boardController);
        st = GameStartStore.getInstance();
        pm = MULEStore.getInstance().getPlaceMuleStore();
        m = MULEStore.getInstance().getMapStateStore();
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
        boolean found = false;
        for (int i = 0; i < p.getProperties().size(); i++) {
            Property a = p.getProperties().get(i);
            if (a.getRow() == row && a.getColumn() == column  ) {
                a.addMule(pm.getMule());
                tile.setCenter(new ImageView(new Image(getBoardController().getPossibleMaps().getTile(row, column).imagePath(true))));
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

    private void goToStore() {
        MasterController.getInstance().store();
        getBoardController().updateState(MapControllerStates.GAME_START);
    }

}
