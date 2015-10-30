package com.byteme.Controllers;

import com.byteme.Models.MULEStore;
import com.byteme.Models.MapStateStore;
import com.byteme.Util.CanTick;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

/**
 * MULE
 */
public abstract class MapStateHandler implements CanTick, Initializable {

    private BoardController boardController;
    private MapStateStore s = MULEStore.getInstance().getMapStateStore();

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


//    Implement in each state store
//    /**
//     * Updates the player label to next player's name.
//     * Increments currentPlayer.
//     */
//    public void changePlayer() {
//        if (s.getCurrentPlayer() + 1 == s.getNumPlayers()) {
//            changePlayer(s.getNumPlayers());
//        } else {
//            changePlayer((s.getCurrentPlayer() + 1) % s.getNumPlayers());
//        }
//    }
//
//    /**
//     * Updates the player label to next player's name.
//     * Increments currentPlayer.
//     * @param playerNumber The number of the player to be set
//     */
//    public void changePlayer(int playerNumber) {
//        s.setCurrentPlayer(playerNumber);
//        //rerenderPlayerText();
//    }

}
