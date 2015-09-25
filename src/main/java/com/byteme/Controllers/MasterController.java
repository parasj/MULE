package com.byteme.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Siddarth Senthilkumar on 9/24/15.
 */
public class MasterController {

    private static MasterController instance = new MasterController();
    private Scene startGame;
    private Scene loadGame;
    private Scene gameConfig;
    private Scene playerConfig;
    private Scene map;
    private Scene town;
    private Scene temp;
    private Stage theStage;
    private int numPlayers;

    private MasterController() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/StartGame.fxml"));
            startGame = new Scene(root);
            root = FXMLLoader.load(getClass().getResource("/fxml/LoadGame.fxml"));
            loadGame = new Scene(root);
            root = FXMLLoader.load(getClass().getResource("/fxml/GameConfig.fxml"));
            gameConfig = new Scene(root);
            root = FXMLLoader.load(getClass().getResource("/fxml/PlayerConfig.fxml"));
            playerConfig = new Scene(root);
            root = FXMLLoader.load(getClass().getResource("/fxml/Town.fxml"));
            town = new Scene(root);
            root = FXMLLoader.load(getClass().getResource("/fxml/placeholder.fxml"));
            temp = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MasterController getInstance() { return instance;}

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public void setStage(Stage stage) {
        theStage = stage;
    }

    public void startGame() {
        theStage.setScene(startGame);
    }

    public void loadGame() {
        theStage.setScene(loadGame);
    }

    public void gameConfig() {
        theStage.setScene(gameConfig);
    }

    public void playerConfig() {
        theStage.setScene(playerConfig);
    }

    public void map() {
        theStage.setScene(map);
    }

    public void town() {
        theStage.setScene(town);
    }

    public void temp() {
        theStage.setScene(temp);
    }

    public void createMap() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Map.fxml"));
            map = new Scene(root);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}