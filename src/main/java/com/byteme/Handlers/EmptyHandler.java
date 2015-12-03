package com.byteme.Handlers;

import com.byteme.Controllers.BoardController;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * MULE.
 */
public class EmptyHandler extends MapStateHandler {
    /**
     * An empty handler object.
     * @param boardController of type BoardController.
     */
    public EmptyHandler(final BoardController boardController) {
        super(boardController);
    }
}
