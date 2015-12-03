package com.byteme.Handlers;

import com.byteme.Controllers.BoardController;
import com.byteme.Handlers.MapStateHandler;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The Mule Game's TurnOverHandler class.
 */

// Empty, because we do nothing when turn is over,
// everything done when game starts, is just a placeholder
public class TurnOverHandler extends MapStateHandler {
    /**
     * Constructor
     * @param boardController The BoardController
     */
    public TurnOverHandler(final BoardController boardController) {
        super(boardController);
    }
}
