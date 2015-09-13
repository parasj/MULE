package com.byteme;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;

import java.io.IOException;

/**
 * Created by Siddarth on 9/13/2015.
 */
public class MapController {

    private Stage stage;
    private int numPlayers;
    private static int currentPlayer = 1;
    @FXML
    private Label playerLabel;

    public void tileChosen() {
        currentPlayer = (currentPlayer + 1 == numPlayers) ? numPlayers : (currentPlayer + 1) % numPlayers;
        playerLabel.setText("Player " + currentPlayer);
    }

    public void goToTown() throws IOException {
        setNewScene("/fxml/Town.fxml");
    }

    public void openTemp() throws IOException {
        setNewScene("/fxml/placeholder.fxml");
    }

    /*
        These methods abstract features needed by the Controller class.
        Please do not modify them.
     */

    /**
     * Changes the scene of the current stage to the one specified
     * @param fxmlFile A string containing the location of the new fxml file
     * @throws IOException fxml file load failed
     */
    public void setNewScene(String fxmlFile) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        MainController controller = loader.getController();
        controller.setStage(stage);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Sets this controller instance's stage
     * @param stage The stage to be set to the controller
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setNumPlayers(int num) { this.numPlayers = num; }
}
