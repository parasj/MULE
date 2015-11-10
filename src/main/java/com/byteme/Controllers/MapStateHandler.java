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
     * boardController of type BoardController.
     */
    private BoardController boardController;

    /**
     * Changed Parameter names because of checkstyle.
     * @param boardController1 of type BoardController.
     */
    MapStateHandler(final BoardController boardController1) {
        this.boardController = boardController1;
    }

    /**
     *
     * @return boardController of type BoardController.
     */
    final BoardController getBoardController() {
        return boardController;
    }

    /**
     *
     * @param boardController2 of type BoardController.
     */
    public final void
            setBoardController(final BoardController boardController2) {
        this.boardController = boardController2;
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
     * @param event of type MouseEvent.
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
    final void log(final String log) {
        System.out.println(log);
    }
}
