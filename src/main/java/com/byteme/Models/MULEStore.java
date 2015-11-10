package com.byteme.Models;

import java.io.*;

/**
 * MULE
 */
public class MULEStore {
    private static final MULEStore instance = new MULEStore();

    private ConfigRepository configRepository;
    private GameStartStore gameStartStore;
    private LandGrantStore landGrantStore;
    private LandPurchaseStore landPurchaseStore;
    private MapStateStore mapStateStore;
    private PlaceMuleStore placeMuleStore;
    private StoreStateStore storeStateStore;

    /**
     * Gets the instance of MULEStore.
     * @return The instance of MULEStore
     */
    public static MULEStore getInstance() {
        return instance;
    }

    /**
     * Gets the Config Repository.
     * @return The Config Repository
     */
    public ConfigRepository getConfigRepository() {
        return configRepository;
    }

    /**
     * Gets the Game Start Store.
     * @return The Game Start Store
     */
    public GameStartStore getGameStartStore() {
        return gameStartStore;
    }

    /**
     * Gets the Land Grant Store.
     * @return The Land Grant Store
     */
    public LandGrantStore getLandGrantStore() {
        return landGrantStore;
    }

    /**
     * Gets the Land Purchase Store.
     * @return The Land Purchase Store
     */
    public LandPurchaseStore getLandPurchaseStore() {
        return landPurchaseStore;
    }

    /**
     * Gets the Map State Store.
     * @return The Map State Store
     */
    public MapStateStore getMapStateStore() {
        return mapStateStore;
    }

    /**
     * Gets the Place Mule Store.
     * @return The Place Mule Store
     */
    public PlaceMuleStore getPlaceMuleStore() {
        return placeMuleStore;
    }

    /**
     * Gets the Store State Store.
     * @return The Store State Store
     */
    public StoreStateStore getStoreStateStore() {
        return storeStateStore;
    }

    /**
     * Creates a MULEStore.
     */
    private MULEStore() {
    }

    /**
     * Creates new repositories if not found.
     */
    public void bootstrap() {
        if (configRepository == null) {
            configRepository = new ConfigRepository();
        }
        if (gameStartStore == null) {
            gameStartStore = new GameStartStore(configRepository);
        }
        if (landGrantStore == null) {
            landGrantStore = new LandGrantStore(configRepository);
        }
        if (landPurchaseStore == null) {
            landPurchaseStore = new LandPurchaseStore(configRepository);
        }
        if (mapStateStore == null) {
            mapStateStore = new MapStateStore(configRepository);
        }
        if (placeMuleStore == null) {
            placeMuleStore = new PlaceMuleStore();
        }
        if (storeStateStore == null) {
            storeStateStore = new StoreStateStore();
        }
    }

    /**
     * Loads the game from disk.
     */
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

    /**
     * Saves the game to disk.
     */
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

    /**
     * Saves the game.
     * @param string Output file name.
     * @param instance instance of the game.
     */
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

    /**
     * Loads the game from disk.
     * @param string The string
     * @return The object loaded
     */
    private Object loadFromDisk(String string) {
        long timeStart = System.nanoTime();
        Object obj;
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

    /**
     * Re-initializes the land grant store, land purchase store,
     * and the map state store.
     */
    public void reinitialize() {
        landGrantStore.reinitialize();
        landPurchaseStore.reinitialize();
        mapStateStore.reinitialize();
    }
}
