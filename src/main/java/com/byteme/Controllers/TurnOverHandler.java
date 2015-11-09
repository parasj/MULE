package com.byteme.Controllers;

import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The Mule Game's TurnOverHandler class.
 */

// Empty, because we do nothing when turn is over,
// everything done when game starts, is just a placeholder
public class TurnOverHandler extends MapStateHandler {
    /**
     *
     * @param boardController The BoardController
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
