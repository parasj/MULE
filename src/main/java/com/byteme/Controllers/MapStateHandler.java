package com.byteme.Controllers;

import com.byteme.Util.CanTick;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

/**
 * MULE.
 */
//Every handler inherits this, the template for all handlers
public abstract class MapStateHandler implements CanTick, Initializable {
    /**
     * boardController of type BoardControl.
     */
    private BoardController boardController;

    /**
     *
     * @param boardController
     */
    public MapStateHandler(final BoardController boardController) {
        this.boardController = boardController;
    }

    /**
     *
     * @return boardController of type BoardController.
     */
    public final BoardController getBoardController() {
        return boardController;
    }

    /**
     *
     * @param boardController of type BoardController.
     */
    public final void setBoardController(final BoardController boardController) {
        this.boardController = boardController;
    }

    /**
     *
     */
    public abstract void handlePass();

    /**
     *
     */
    public abstract void handleTownButtonClicked();

    /**
     *
     * @param event
     */
    public abstract void tileChosen(final MouseEvent event);

    /**
     *
     */
    public abstract void stateChanged();

    /**
     *
     * @param log of type String.
     */
    protected final void log(final String log) {
        System.out.println(log);
    }
}