package com.byteme.Controllers;

import com.byteme.Models.ConfigRepository;
import com.byteme.Models.MapStateStore;
import com.byteme.Schema.MapControllerStates;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by rishav on 10/2/2015.
 */
public class LandGrantHandler extends MapStateHandler {
    private MapStateStore s = MapStateStore.getInstance();

    public LandGrantHandler(BoardController boardController) {
        super(boardController);
    }

    public void handlePass() {
        getBoardController().getAlertsLabel().setVisible(false);
        s.setPassCounter(s.getPassCounter() + 1);
        if (s.getPassCounter() >= s.getNumPlayers()) {
            s.setPassCounter(0);
            s.setCurrentState(MapControllerStates.GAME_START);
            getBoardController().getPhaseLabel().setText("Selection phase is over!");
            changePlayer(1);
            s.setTimeLeft(pubController.calcTimeLeft(null));
            getBoardController().updateState(MapControllerStates.LAND_PURCHASE);
        }
//        Not used in Land Grant Stage
//        } else {
//            if (s.getCurrentState() == MapControllerStates.GAME_START || s.getCurrentState() == MapControllerStates.SELECTION_OVER)
//                pubController.goToMap();
//            else {
//                s.getInstance().setTimeLeft(0);
//                changePlayer();
//                MasterController.getInstance().map();
//            }
//        }
    }

    @Override
    public void handleTileChosen() {

    }

    @Override
    public void handleTownButtonClicked() {
        log("Cannot go to town during land grant phase!");
    }

    @Override
    public void tileChosen(MouseEvent event) {

        getBoardController().getAlertsLabel().setVisible(false);
        BorderPane tile = (BorderPane) event.getSource();
        if (!owned(tile)) {
            // Change tile background color to player color
            setColorTile(tile);

            //TODO: Save which tile was clicked by which player (currentPlayer is a static variable of this class)
            System.out.println("Player " + s.getCurrentPlayer() + ": " + map.getRowIndex(tile) + ", " + map.getColumnIndex(tile));

            if (s.getCurrentPlayer() >= s.getNumPlayers()) {
                incRound();
                rerenderPlayerText();
            }

            // Land Grant is only 2 turns per player.
            if (s.getCurrentRound() >= 2) {
                phaseLabel.setText("Property Selection");
                s.setCurrentState(MapControllerStates.LAND_PURCHASE);
                rerenderPlayerText();
                s.setCurrentRound(0);
            }

            // Reset counters and change currentPlayer
            s.setPassCounter(0);
            s.setPurchaseOpportunities(0);
            changePlayer();
        } else {
            // Property is owned, just display warning
            ownedMessage();
        }
    }

    @Override
    public void stateChanged() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void tick() {

    }

    /**
     * Updates the player label to next player's name.
     * Increments currentPlayer.
     */
    public void changePlayer() {
        if (s.getCurrentPlayer() + 1 == s.getNumPlayers()) {
            changePlayer(s.getNumPlayers());
        } else {
            changePlayer((s.getCurrentPlayer() + 1) % s.getNumPlayers());
        }
    }

    /**
     * Updates the player label to next player's name.
     * Increments currentPlayer.
     * @param playerNumber The number of the player to be set
     */
    public void changePlayer(int playerNumber) {
        s.setCurrentPlayer(playerNumber);
        //rerenderPlayerText();
    }
}
