package com.byteme.Models;

import com.byteme.Schema.Mule;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * MULE
 */
public class MULEStore {
    private static MULEStore ourInstance = new MULEStore();
    private ConfigRepository configRepository;
    private GameStartStore gameStartStore;
    private LandGrantStore landGrantStore;
    private LandPurchaseStore landPurchaseStore;
    private MapStateStore mapStateStore;
    private PlaceMuleStore placeMuleStore;
    private StoreStateStore storeStateStore;

    public static MULEStore getInstance() {
        return ourInstance;
    }

    public ConfigRepository getConfigRepository() {
        return configRepository;
    }

    public GameStartStore getGameStartStore() {
        return gameStartStore;
    }

    public LandGrantStore getLandGrntStore() {
        return landGrantStore;
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

    private MULEStore() {
        configRepository = new ConfigRepository();
        gameStartStore = new GameStartStore();
        landGrantStore = new LandGrantStore();
        landPurchaseStore = new LandPurchaseStore();
        mapStateStore = new MapStateStore();
        placeMuleStore = new PlaceMuleStore();
        storeStateStore = new StoreStateStore();
    }

    public void load() {}

    public void save() {
        System.out.println("SAVING GAME TO DISK!");
        saveToDisk("GameStartStore.mule", gameStartStore);
    }

    private void saveToDisk(String s, Serializable inst) {
        try {
            FileOutputStream fileOut = new FileOutputStream(s);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(inst);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data to %s", s);
        } catch(IOException i) {
            i.printStackTrace();
        }
    }
}
