package com.byteme;

import com.byteme.Controllers.BoardController;
import com.byteme.Models.MULEStore;

public class InjectionClass {
    private BoardController boardController;

    public MULEStore getMULEStore() {
        return MULEStore.getInstance();
    }

    public BoardController getBoardController() {
        return boardController;
    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }
}
