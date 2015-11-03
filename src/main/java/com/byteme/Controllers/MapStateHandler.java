package com.byteme.Controllers;

import com.byteme.Util.CanTick;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

/**
 * MULE
 */
//Every handler inherits this, the template for all handlers
public abstract class MapStateHandler implements CanTick, Initializable {

    private BoardController boardController;

    /**
     *
     * @param boardController
     */
    public MapStateHandler(BoardController boardController) {
        this.boardController = boardController;
    }

    /**
     *
     * @return
     */
    public BoardController getBoardController() {
        return boardController;
    }

    /**
     *
     * @param boardController
     */
    public void setBoardController(BoardController boardController) {
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
    public abstract void tileChosen(MouseEvent event);

    /**
     *
     */
    public abstract void stateChanged();

    /**
     *
     * @param log
     */
    protected void log(String log) {
        System.out.println(log);
    }
}