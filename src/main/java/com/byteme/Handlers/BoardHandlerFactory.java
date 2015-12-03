package com.byteme.Handlers;

import com.byteme.Controllers.*;
import com.byteme.Schema.MapControllerStates;

/**
 * MULE
 */
public class BoardHandlerFactory {
    /**
     * landPurchaseHandler of type MapStateHandler.
     */
    private final MapStateHandler landPurchaseHandler;
    /**
     * gameStartHandler of type MapStateHandler.
     */
    private final MapStateHandler gameStartHandler;
    /**
     * landGrantHandler of type MapStateHandler.
     */
    private final MapStateHandler landGrantHandler;
    /**
     * emptyHandler of type MapStateHandler.
     */
    private final MapStateHandler emptyHandler;
    /**
     * turnOverHandler of type MapStateHandler.
     */
    private final MapStateHandler turnOverHandler;
    /**
     * placeMuleHandler of type MapStateHandler.
     */
    private final MapStateHandler placeMuleHandler;

    public BoardHandlerFactory(BoardController boardController) {
        landPurchaseHandler = new LandPurchaseHandler(boardController);
        gameStartHandler = new GameStartHandler(boardController);
        landGrantHandler = new LandGrantHandler(boardController);
        emptyHandler = new EmptyHandler(boardController);
        turnOverHandler = new TurnOverHandler(boardController);
        placeMuleHandler = new PlaceMuleHandler(boardController);
    }

    /**
     * Factory method that returns correct handler given current state
     * @param state is the current state
     * @return correct instance of handler
     */
    public MapStateHandler getHandler(MapControllerStates state) {
        if (state
                == MapControllerStates.LAND_GRANT) {
            return landGrantHandler;
        } else if (state
                == MapControllerStates.LAND_PURCHASE) {
            return landPurchaseHandler;
        } else if (state
                == MapControllerStates.GAME_START) {
            return gameStartHandler;
        } else if (state
                == MapControllerStates.TURN_OVER) {
            return turnOverHandler;
        } else if (state
                == MapControllerStates.PLACE_MULE) {
            return placeMuleHandler;
        } else {
            return emptyHandler;
        }
    }

    /**
     *
     * @return instance of MapStateHandler.
     */
    public final MapStateHandler getLandPurchaseHandler() {
        return landPurchaseHandler;
    }

    /**
     *
     * @return instance of MapStateHandler.
     */
    public final MapStateHandler getGameStartHandler() {
        return gameStartHandler;
    }

    /**
     *
     * @return instance of MapStateHandler.
     */
    public final MapStateHandler getLandGrantHandler() {
        return landGrantHandler;
    }

    /**
     *
     * @return instance of MapStateHandler.
     */
    public final MapStateHandler getEmptyHandler() {
        return emptyHandler;
    }

    /**
     *
     * @return instance of MapStateHandler.
     */
    public final MapStateHandler getTurnOverHandler() {
        return turnOverHandler;
    }
}
