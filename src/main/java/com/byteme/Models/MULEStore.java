package com.byteme.Models;

import java.io.*;

/**
 * MULE
 */
public class MULEStore {
    private static final MULEStore instance = new MULEStore();

    public static MULEStore getInstance() {
        return instance;
    }

    private ConfigRepository configRepository;
    private GameStartStore gameStartStore;
    private LandGrantStore landGrantStore;
    private LandPurchaseStore landPurchaseStore;
    private MapStateStore mapStateStore;
    private PlaceMuleStore placeMuleStore;
    private StoreStateStore storeStateStore;

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

    private MULEStore() {
    }

    public void bootstrap() {
        //load();
        if (configRepository == null) configRepository = new ConfigRepository();
        if (gameStartStore == null) gameStartStore = new GameStartStore(configRepository);
        if (landGrantStore == null) landGrantStore = new LandGrantStore(configRepository);
        if (landPurchaseStore == null) landPurchaseStore = new LandPurchaseStore(configRepository);
        if (mapStateStore == null) mapStateStore = new MapStateStore(configRepository);
        if (placeMuleStore == null) placeMuleStore = new PlaceMuleStore();
        if (storeStateStore == null) storeStateStore = new StoreStateStore();
    }

    public void load() {
        System.out.println("LOADING GAME FROM DISK!");
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

    private void saveToDisk(String string, Serializable instance) {
        try {
            FileOutputStream fileOut = new FileOutputStream(string);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(instance);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data to %s", string);
        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }

    private Object loadFromDisk(String string) {
        long timeStart = System.nanoTime();
        Object obj = null;
        try {
            FileInputStream fileIn = new FileInputStream(string);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            obj = in.readObject();
            in.close();
            fileIn.close();
        } catch(IOException i) {
            System.out.println("No save found for " + string);
            return null;
        } catch(ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }
        System.out.printf("Loading %s took %4d milliseconds%n", string, (System.nanoTime() - timeStart) / 1000000L);
        return obj;
    }

    public void reinit() {
        configRepository.reinit();
        gameStartStore.reinit();
        landGrantStore.reinit();
        landPurchaseStore.reinit();
        mapStateStore.reinit();
        placeMuleStore.reinit();
        storeStateStore.reinit();
    }
}
