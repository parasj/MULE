package com.byteme.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
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

    /**
     *
     */
    ///Creates scenes
    public MasterController() {
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

    /**
     *
     * @return
     */
    public static MasterController getInstance() { return instance;}

    /**
     *
     * @param stage
     */
    //Sets stages
    public void setStage(Stage stage) {
        theStage = stage;
    }

    /**
     *
     */
    public void startGame() {
//        currStage = "Start Game";
        theStage.setScene(startGame);
    }

    /**
     *
     */
    public void loadGame() {
//        currStage = "Load Game";
        theStage.setScene(loadGame);
    }

    /**
     *
     */
    public void gameConfig() {
//        currStage = "Game Config";
        theStage.setScene(gameConfig);
    }

    /**
     *
     */
    public void playerConfig() {
//        currStage = "Player Config";
        theStage.setScene(playerConfig);
    }

    /**
     *
     */
    public void map() {
//        currStage = "Map";
        theStage.setScene(map);
    }

    /**
     *
     */
    public void town() {
//        currStage = "Town";
        theStage.setScene(town);
    }

    /**
     *
     */
    public void temp() {
//        currStage = "Temp";
        theStage.setScene(temp);
    }

    /**
     *
     */
    public void pubScene() {
//        currStage = "Pub";
        theStage.setScene(pubScene);
        pubController.rerender();
    }

    /**
     *
     */
    public void store() {
//        currStage = "Store";
        theStage.setScene(storeScene);
        storeController.reRender();
    }

    /**
     *
     * @return
     */
    public String getCurrStage() {
        return currStage;
    }

    /**
     *
     * @param boardController
     */
    public void setBoardController(BoardController boardController) {
        if (boardController == null) {
            throw new IllegalArgumentException("Board Controller is null!");
        }
        this.boardController = boardController;
    }

    /**
     *
     * @return
     */
    public BoardController getBoardController() {
        return boardController;
    }

    /**
     *
     */
    //Creates map from template
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
<<<<<<< Updated upstream
=======

    public BoardController getBoardController() {
        return boardController;
    }


>>>>>>> Stashed changes
}