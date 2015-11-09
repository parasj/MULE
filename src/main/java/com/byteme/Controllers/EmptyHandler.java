package com.byteme.Controllers;

import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * MULE.
 */
public class EmptyHandler extends MapStateHandler {
    /**
     *
     * @param boardController of type BoardController.
     */
    public EmptyHandler(final BoardController boardController) {
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
     * @param location
     * @param resources
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

    }
}
