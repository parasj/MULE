package com.byteme.Controllers;

import com.byteme.Models.ConfigRepository;
import com.byteme.Models.MapBoard;
import com.byteme.Models.MapStateStore;
import com.byteme.Schema.MapControllerStates;
import com.byteme.Util.CanTick;
import com.byteme.Util.GlobalTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by parasjain on 10/2/15.
 */
public class BoardController implements Initializable, CanTick {
    private final static ConfigRepository configRepository = ConfigRepository.getInstance();
    private final static MapStateStore s = MapStateStore.getInstance();

    private final MapBoard possibleMaps = new MapBoard();
    private final GlobalTimer timer = GlobalTimer.getInstance();

    private MapControllerStates state;
    private MapStateController childController;

    /*** FXML UI items ***/
    @FXML
    private Label playerLabel;
    @FXML
    private Label moneyLabel;
    @FXML
    private Label phaseLabel;
    @FXML
    private Label alertsLabel;
    @FXML
    private Label roundLabel;
    @FXML
    private GridPane map;
    @FXML
    private Label timerLabel;

    // implementations of MapStateHandler
    // ...
    // TODO - fill this in



    public BoardController() {
        updateState(MapControllerStates.GAME_START);
        timer.setTickHandler(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    /**** Timer ****/
    @Override
    public void tick() {
        log("Tick");
        childController.tick();
    }

    /**** Data Binding ****/
    public void updateState(MapControllerStates newState) {
        state = newState;
        // TODO - switch controller as needed
        // if (state = MapControllerStates.GAME_START)
    }


    /**** UI Events ****/
    public void passButtonClicked() {
        log("Pass button clicked");
        childController.handlePass();
    }

    public void tileChosen() {
        log("Tile chosen");
        childController.handlePass(); // todo change this

    }


    /**** UI Elements ****/
    public Label getPlayerLabel() {
        return playerLabel;
    }

    public Label getMoneyLabel() {
        return moneyLabel;
    }

    public Label getPhaseLabel() {
        return phaseLabel;
    }

    public Label getAlertsLabel() {
        return alertsLabel;
    }

    public Label getRoundLabel() {
        return roundLabel;
    }

    public GridPane getMap() {
        return map;
    }

    public Label getTimerLabel() {
        return timerLabel;
    }


    /**** Util functions ****/
    private void log(String s) {
        System.out.println(s);
    }
}
