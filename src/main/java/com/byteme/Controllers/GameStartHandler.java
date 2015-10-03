package com.byteme.Controllers;

import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by rishav on 10/2/2015.
 */
public class GameStartHandler extends MapStateHandler {
    public GameStartHandler(BoardController boardController) {
        super(boardController);
    }

    @Override
    public void handlePass() {

    }

    @Override
    public void handleTownButtonClicked() {

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

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
