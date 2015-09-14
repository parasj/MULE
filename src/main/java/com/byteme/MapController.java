package com.byteme;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;

import java.io.IOException;

/**
 * Created by Siddarth on 9/13/2015.
 */
public class MapController implements Initializable {

    private Stage stage;
    private int numPlayers;
    private static int currentPlayer = 1;
    @FXML
    private Label playerLabel;

    @FXML
    private GridPane map;

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

        String P = "com/byteme/images/Plain.png";
        String M1 = "com/byteme/images/Mountain.png";
        String M2 = "com/byteme/images/Mountain.png";
        String M3 = "com/byteme/images/Mountain.png";
        String R = "com/byteme/images/River.png";
        String Town = "com/byteme/images/Town.png";

        String[][] standardMap = {{P,P,M1,P,R,P,M3,P,P},
                                    {P,M1,P,P,R,P,P,P,M3},
                                    {M3,P,P,P,Town,P,P,P,M1},
                                    {P,M2,P,P,R,P,M2,P,P},
                                    {P,P,M2,P,R,P,P,P,M2}};

        for (int i = 0; i < standardMap.length; i++) {
            for (int j = 0; j < standardMap[i].length; j++) {
                ImageView tile = new ImageView(standardMap[i][j]);
                map.add(tile, j, i);
            }
        }

        map.getChildren().get(23).setOnMouseClicked((MouseEvent e) -> goToTown());

    }

    public void tileChosen(MouseEvent event) {

        // Get the square being clicked
        ImageView tile = (ImageView) event.getSource();

        // Oddly enough, these give me null for the 0th row and 0th column tiles
        System.out.println("Which grid row? " + map.getRowIndex(tile));
        System.out.println("Which grid column? " + map.getColumnIndex(tile));

        System.out.println(tile.getImage());

        // Change the player label
        currentPlayer = (currentPlayer + 1 == numPlayers) ? numPlayers : (currentPlayer + 1) % numPlayers;
        playerLabel.setText("Player " + currentPlayer);
    }

    public void goToTown() {
        try {
            setNewScene("fxml/Town.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void openTemp() throws IOException {
        setNewScene("fxml/placeholder.fxml");
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
