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
    public static MULEStore getInstance() {
        return ourInstance;
    }

    public GameStartStore getGameStartStore() {
        return gameStartStore;
    }

    private GameStartStore gameStartStore;

    private MULEStore() {
        gameStartStore = new GameStartStore();
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
