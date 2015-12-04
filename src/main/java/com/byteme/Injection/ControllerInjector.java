package com.byteme.Injection;

import com.byteme.Controllers.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * Created by rishavbose365 on 12/3/2015.
 */
public class ControllerInjector {
    private static ControllerInjector ourInstance = new ControllerInjector();

    public static ControllerInjector getInstance() {
        return ourInstance;
    }

    private BoardController boardController;

    private MasterController masterController;

    private ConfigurationController configurationController;

    private PubController pubController;

    private StoreController storeController;

    private TownController townController;

    private ControllerInjector() {
        boardController = new BoardController();

        Scene startGame;
        Scene loadGame;
        Scene gameConfig;
        Scene playerConfig;
        Scene town;
        Scene temp;
        Scene pubScene;
        Scene storeScene;

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
            masterController = new MasterController(startGame, loadGame, gameConfig,
                    playerConfig, town, temp,
                    pubScene, storeScene, boardController, pubController, storeController);
        } catch (IOException e) {
            e.printStackTrace();
        }

        configurationController = new ConfigurationController();


        townController = new TownController();
    }

    public BoardController getBoardController() {
        return boardController;
    }

    public MasterController getMasterController() {
        return masterController;
    }

    public ConfigurationController getConfigurationController() {
        return configurationController;
    }

    public PubController getPubController() {
        return pubController;
    }

    public StoreController getStoreController() {
        return storeController;
    }

    public TownController getTownController() {
        return townController;
    }
}
