package com.byteme.Controllers;

import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * MULE
 */
//Empty, because we do nothing when turn is over, everything done when game starts, is just a placeholder
public class TurnOverHandler extends MapStateHandler{
    /**
     *
     * @param boardController
     */
    public TurnOverHandler(BoardController boardController) {
        super(boardController);
    }

    /**
     *
     */
    @Override
    public void handlePass() {

    }

    /**
     *
     */
    @Override
    public void handleTownButtonClicked() {

    }

    /**
     *
     * @param event
     */
    @Override
    public void tileChosen(MouseEvent event) {

    }

    /**
     *
     */
    @Override
    public void stateChanged() {

    }

    /**
     *
     */
    @Override
    public void tick() {

    }

    /**
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
