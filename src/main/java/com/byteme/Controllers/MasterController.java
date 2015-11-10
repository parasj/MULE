package com.byteme.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * MULE.
 */
public class MasterController {

    /**
     * gameStartStore of type GameStartStore.
     */
    private static final MasterController INSTANCE = new MasterController();//Change this back.
    /**
     * startGame of type Scene.
     */
    private Scene startGame;
    /**
     * loadGame of type Scene.
     */
    private Scene loadGame;
    /**
     * gameConfig of type Scene.
     */
    private Scene gameConfig;
    /**
     * playerConfig of type Scene.
     */
    private Scene playerConfig;
    /**
     * map of type Scene.
     */
    private Scene map;
    /**
     * town of type Scene.
     */
    private Scene town;
    /**
     * temp of type Scene.
     */
    private Scene temp;
    /**
     * pubScene of type Scene.
     */
    private Scene pubScene;
    /**
     * storeScene of type Scene.
     */
    private Scene storeScene;
    /**
     * theStage of type Stage.
     */
    private Stage theStage;
    /**
     * currStage of type String.
     */
    private String currStage;

    /**
     * boardController of type BoardController.
     */
    private BoardController boardController;
    /**
     * pubController of type PubController.
     */
    private PubController pubController;
    /**
     * storeController of type StoreController.
     */
    private StoreController storeController;

    /**
     *
     */
    ///Creates scenes
    public MasterController() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass()
                .getResource("/fxml/StartGame.fxml"));
            startGame = new Scene(root);
            root = FXMLLoader.load(getClass()
                .getResource("/fxml/LoadGame.fxml"));
            loadGame = new Scene(root);
            root = FXMLLoader.load(getClass()
                .getResource("/fxml/GameConfig.fxml"));
            gameConfig = new Scene(root);
            root = FXMLLoader.load(getClass()
                .getResource("/fxml/PlayerConfig.fxml"));
            playerConfig = new Scene(root);
            root = FXMLLoader.load(getClass().getResource("/fxml/Town.fxml"));
            town = new Scene(root);
            root = FXMLLoader.load(getClass()
                .getResource("/fxml/placeholder.fxml"));
            temp = new Scene(root);

            FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/fxml/Pub.fxml"));
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
     * @return instance of MasterController.
     */
    public static MasterController getInstance() {
        return INSTANCE;
    }

    /**
     *
     * @param stage of type Stage.
     */
    //Sets stages
    public final void setStage(final Stage stage) {
        theStage = stage;
    }

    /**
     *
     */
    public final void startGame() {
//        currStage = "Start Game";
        theStage.setScene(startGame);
    }

    /**
     *
     */
    public final void loadGame() {
//        currStage = "Load Game";
        theStage.setScene(loadGame);
    }

    /**
     *
     */
    public final void gameConfig() {
//        currStage = "Game Config";
        theStage.setScene(gameConfig);
    }

    /**
     *
     */
    public final void playerConfig() {
//        currStage = "Player Config";
        theStage.setScene(playerConfig);
    }

    /**
     *
     */
    public final void map() {
//        currStage = "Map";
        theStage.setScene(map);
    }

    /**
     *
     */
    public final void town() {
//        currStage = "Town";
        theStage.setScene(town);
    }

    /**
     *
     */
    public final void temp() {
//        currStage = "Temp";
        theStage.setScene(temp);
    }

    /**
     *
     */
    public final void pubScene() {
//        currStage = "Pub";
        theStage.setScene(pubScene);
        pubController.rerender();
    }

    /**
     *
     */
    public final void store() {
//        currStage = "Store";
        theStage.setScene(storeScene);
        storeController.reRender();
    }

    /**
     *
     * @return currStage of type String.
     */
    public final String getCurrStage() {
        return currStage;
    }

    /**
     *  Changed Param name because of checkstyle.
     * @param boardController1 of type BoardController.
     */
    public final void
        setBoardController(final BoardController boardController1) {
        if (boardController1 == null) {
            throw new IllegalArgumentException("Board Controller is null!");
        }
        this.boardController = boardController1;
    }

    /**
     *
     * @return instance of BoardController.
     */
    public final BoardController getBoardController() {
        return boardController;
    }

    /**
     *
     */
    //Creates map from template
    public final void createMap() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/fxml/Map.fxml"));
            map = new Scene(loader.load());
            setBoardController(loader.getController());
            pubController.setBoardController(boardController);
            StoreController.setBoardController(boardController);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
