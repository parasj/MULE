package com.byteme.Handlers;

import com.byteme.Controllers.BoardController;
import com.byteme.Handlers.MapStateHandler;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * MULE.
 */
//Placeholder for now
public class SelectionOverHandler extends MapStateHandler {
    /**
     *
     * @param boardController of the type BoardController.
     */
    public SelectionOverHandler(final BoardController boardController) {
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
    public final void initialize(final URL location,
        final ResourceBundle resources) {
    }
}
