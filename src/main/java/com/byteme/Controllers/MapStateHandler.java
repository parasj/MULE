package com.byteme.Controllers;

/**
 * Created by rishav on 10/2/2015.
 */
public abstract class MapStateHandler {

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
}
