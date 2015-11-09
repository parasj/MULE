package com.byteme.Controllers;

import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * MULE.
 */
//Empty, because we do nothing when turn is over,
public class TurnOverHandler extends MapStateHandler {
    /**
     *
     * @param boardController of type BoardController.
     */
    public TurnOverHandler(final BoardController boardController) {
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
     * @param event of type MouseEvent.
     */
    @Override
    public void tileChosen(final MouseEvent event) {

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
     * @param location of type URL.
     * @param resources of type ResourceBundle.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
    }
}
