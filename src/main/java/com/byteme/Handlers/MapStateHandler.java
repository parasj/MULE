package com.byteme.Handlers;

import com.byteme.Controllers.BoardController;
import com.byteme.Util.CanTick;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

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
    public final void setBoardController(final
        BoardController boardController2) {
        this.boardController = boardController2;
    }

    /**
     *
     */
    public void handlePass() {

    }

    /**
     *
     */
    public void handleTownButtonClicked() {

    }

    /**
     *
     * @param event of type MouseEvent.
     */
    public void tileChosen(final MouseEvent event) {

    }

    /**
     *
     */
    public void stateChanged() {

    }

    /**
     *
     * @param location of type URL.
     * @param resources of type ResourceBundle.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

    }

    @Override
    public void tick() {

    }

    /**
     *
     * @param log of type String.
     */
    final void log(final String log) {
        System.out.println(log);
    }
}
