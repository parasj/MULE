package com.byteme.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import javafx.scene.text.Text;

import java.io.IOException;
//import java.util.Timer;
//import java.util.TimerTask;
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
    public Scene pubScene;
    private Stage theStage;
    private String currStage;
    //Creates a MapController Object. This will contain data specific to the map.
    private static MapController mapInstance = MapController.getInstance();

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
            root = FXMLLoader.load(getClass().getResource("/fxml/Pub.fxml"));
            pubScene = new Scene(root);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MasterController getInstance() { return instance;}

    public void setStage(Stage stage) {
        theStage = stage;
    }

    public void startGame() {
        currStage = "Start Game";
        theStage.setScene(startGame);
    }

    public void loadGame() {
        currStage = "Load Game";
        theStage.setScene(loadGame);
    }

    public void gameConfig() {
        currStage = "Game Config";
        theStage.setScene(gameConfig);
    }

    public void playerConfig() {
        currStage = "Player Config";
        theStage.setScene(playerConfig);
    }

    public void map() {
        currStage = "Map";
        theStage.setScene(map);
        mapInstance.rerender();
    }

    public static MapController getMapInstance() {
        return mapInstance;
    }

    public void setMapInstance(MapController mapInstance) {
        this.mapInstance = mapInstance;
    }

    public void town() {
        currStage = "Town";
        theStage.setScene(town);
    }

    public void temp() {
        currStage = "Temp";
        theStage.setScene(temp);
    }

    public void pubScene() {
        currStage = "Pub";
        theStage.setScene(pubScene);
    }

    public void createMap() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Map.fxml"));
            map = new Scene(root);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public String getCurrStage() {
        return currStage;
    }

   /* public void countDownTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        label.update();
                        javafxcomponent.doSomething();
                    }
                });
            }
        }, 5000, 1000);
    }*/
}