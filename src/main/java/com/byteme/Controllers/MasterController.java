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
 * MULE
 */
public class MasterController {

    private static final MasterController instance = new MasterController();
    private Scene startGame;
    private Scene loadGame;
    private Scene gameConfig;
    private Scene playerConfig;
    private Scene map;
    private Scene town;
    private Scene temp;
    private Scene pubScene;
    private Scene storeScene;
    private Stage theStage;
    private String currStage;

    private BoardController boardController;
    private PubController pubController;
    private StoreController storeController;


    private MasterController() {
        Parent root;
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

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Pub.fxml"));
            pubScene = new Scene(loader.load());
            pubController = loader.getController();

            loader = new FXMLLoader(getClass().getResource("/fxml/Store.fxml"));
            storeScene = new Scene(loader.load());
            storeController = loader.getController();
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
        pubController.rerender();
    }

    public void store() {
        currStage = "Store";
        theStage.setScene(storeScene);
        storeController.reRender();
    }

    public String getCurrStage() {
        return currStage;
    }

    private void setBoardController(BoardController mc) {
        boardController = mc;
    }

    public void createMap() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Map.fxml"));
            map = new Scene(loader.load());
            setBoardController(loader.getController());
            pubController.setBoardController(boardController);
            storeController.setBoardController(boardController);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BoardController getBoardController() {
        return boardController;
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