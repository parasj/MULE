package com.byteme.Models;

import com.byteme.Schema.Mule;

import java.io.*;

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

    public void load() {
        System.out.println("LOADING GAME TO DISK!");
        configRepository = (ConfigRepository) loadFromDisk("ConfigRepository.mule");
        gameStartStore = (GameStartStore) loadFromDisk("GameStartStore.mule");
        landGrantStore = (LandGrantStore) loadFromDisk("LandGrantStore.mule");
        landPurchaseStore = (LandPurchaseStore) loadFromDisk("LandPurchaseStore.mule");
        mapStateStore = (MapStateStore) loadFromDisk("MapStateStore.mule");
        placeMuleStore = (PlaceMuleStore) loadFromDisk("PlaceMuleStore.mule");
        storeStateStore = (StoreStateStore) loadFromDisk("StoreStateStore.mule");
    }

    public void save() {
        System.out.println("SAVING GAME TO DISK!");
        saveToDisk("ConfigRepository.mule", configRepository);
        saveToDisk("GameStartStore.mule", gameStartStore);
        saveToDisk("LandGrantStore.mule", landGrantStore);
        saveToDisk("LandPurchaseStore.mule", landPurchaseStore);
        saveToDisk("MapStateStore.mule", mapStateStore);
        saveToDisk("PlaceMuleStore.mule", placeMuleStore);
        saveToDisk("StoreStateStore.mule", storeStateStore);
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

    private Object loadFromDisk(String s) {
        Object obj = null;
        try {
            FileInputStream fileIn = new FileInputStream("/tmp/employee.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            obj = in.readObject();
            in.close();
            fileIn.close();
        } catch(IOException i) {
            i.printStackTrace();
            return null;
        } catch(ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return null;
        }
        return obj;
    }
}
