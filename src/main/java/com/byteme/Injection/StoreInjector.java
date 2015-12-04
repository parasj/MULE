package com.byteme.Injection;

import com.byteme.Models.*;
import com.byteme.Schema.GameConfigParams;
import com.byteme.Schema.PlayerConfigParams;

import java.util.Map;

/**
 * Created by rishav on 12/3/2015.
 */
public class StoreInjector {

//    private static StoreInjector ourInstance = new StoreInjector();
//
//    public static StoreInjector getInstance() {
//        return ourInstance;
//    }

    private ConfigRepository configRepository;

    private GameStartStore gameStartStore;

    private LandGrantStore landGrantStore;

    private LandPurchaseStore landPurchaseStore;

    private MapStateStore mapStateStore;

    private PlaceMuleStore placeMuleStore;

    private StoreStateStore storeStateStore;

    private StoreInjector(GameConfigParams gameConfigParams,
                          Map<Integer, PlayerConfigParams> playerConfigList) {

        configRepository = new ConfigRepository(gameConfigParams, playerConfigList);

        gameStartStore = new GameStartStore(configRepository);

        landGrantStore = new LandGrantStore(configRepository);

        landPurchaseStore = new LandPurchaseStore(configRepository);

        mapStateStore = new MapStateStore(configRepository);

        placeMuleStore = new PlaceMuleStore();

        storeStateStore = new StoreStateStore();

    }

    public ConfigRepository getConfigRepository() {
        return configRepository;
    }

    public GameStartStore getGameStartStore() {
        return gameStartStore;
    }

    public LandGrantStore getLandGrantStore() {
        return landGrantStore;
    }

    public LandPurchaseStore getLandPurchaseStore() {
        return landPurchaseStore;
    }

    public MapStateStore getMapStateStore() {
        return mapStateStore;
    }

    public PlaceMuleStore getPlaceMuleStore() {
        return placeMuleStore;
    }

    public StoreStateStore getStoreStateStore() {
        return storeStateStore;
    }
}
