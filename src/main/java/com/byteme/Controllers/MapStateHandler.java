package com.byteme.Controllers;

import com.byteme.Util.CanTick;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

/**
 * MULE
 */
//Every handler inherits this
public abstract class MapStateHandler implements CanTick, Initializable {

    private BoardController boardController;

    public MapStateHandler(BoardController boardController) {
        this.boardController = boardController;
    }

    public BoardController getBoardController() {
        return boardController;
    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }

    public abstract void handlePass();

    public abstract void handleTownButtonClicked();

    public abstract void tileChosen(MouseEvent event);

    public abstract void stateChanged();

    protected void log(String log) {
        System.out.println(log);
    }
}