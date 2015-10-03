package com.byteme.Controllers;

import com.byteme.Util.CanTick;
import javafx.scene.input.MouseEvent;

/**
 * Created by rishav on 10/2/2015.
 */
public abstract class MapStateHandler implements CanTick {

    private BoardController boardController;
    public abstract void handlePass();
    public abstract void handleTileChosen();
    public abstract void handleTownButtonClicked();

    public MapStateHandler(BoardController boardController) {
        this.boardController = boardController;
    }

    public BoardController getBoardController() {
        return boardController;
    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }

    public abstract void tileChosen(MouseEvent event);

    public abstract void stateChanged();
}
