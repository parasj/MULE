package com.byteme.Models;

import com.byteme.Schema.MapTile;
import com.byteme.Schema.MapType;

import java.util.Random;

/**
 * MULE
 */
public class MapBoard {
    private final int height;
    private final int width;
    private MapTile[][] board;

    private static final MapTile[][] STANDARD_MAP = {
        {MapTile.P,    MapTile.P,  MapTile.M1, MapTile.P,  MapTile.R,
         MapTile.P,  MapTile.M3, MapTile.P,  MapTile.P},
        {MapTile.P,    MapTile.M1, MapTile.P,  MapTile.P,  MapTile.R,
         MapTile.P,  MapTile.P,  MapTile.P,  MapTile.M3},
        {MapTile.M3,   MapTile.P,  MapTile.P,  MapTile.P,  MapTile.Town,
         MapTile.P,  MapTile.P,  MapTile.P,  MapTile.M1},
        {MapTile.P,    MapTile.M2, MapTile.P,  MapTile.P,  MapTile.R,
         MapTile.P,  MapTile.M2, MapTile.P,  MapTile.P},
        {MapTile.P,    MapTile.P,  MapTile.M2, MapTile.P,  MapTile.R,
         MapTile.P,  MapTile.P,  MapTile.P,  MapTile.M2}};

    /**
     * Creates a new MapBoard.
     */
    public MapBoard() {
        this(5, 9, MapType.STANDARD);
    }

    /**
     * Gets the width.
     * @return The width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height.
     * @return The height
     */
    public int getHeight() {
        return height;
    }


    /**
     * Creates a MapBoard.
     * @param height The height
     * @param width The width
     * @param type The MapType
     */
    public MapBoard(int height, int width, MapType type) {
        this.height = height;
        this.width = width;

        if (type == MapType.RANDOM) {
            board = new MapTile[height][9];
            randomBoard();
        } else if (type == MapType.STANDARD) {
            board = STANDARD_MAP;

        }
    }

    /**
     * Gets the map board.
     * @return The board
     */
    public MapTile[][] getBoard() {
        return board.clone();
    }

    /**
     * Gets the tile at board[height][width].
     * @param height The height.
     * @param width The width.
     * @return The tile
     */
    public MapTile getTile(int height, int width) {
        return board[height][width];
    }

    /**
     * Sets the tile at height, width.
     * @param height The height
     * @param width The width
     * @param newTile The new tile to be set
     */
    public void setTile(int height, int width, MapTile newTile) {
        this.board[height][width] = newTile;
    }

    public void randomBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (j == board[i].length / 2) {
                    board[i][j] = MapTile.R;
                } else {
                    board[i][j] = getRandom();
                }
            }
        }
    }

    public MapTile getRandom() {
        Random rand = new Random();
        int num = rand.nextInt(4);
        if (num == 0) {
            return MapTile.P;
        } else if (num == 1) {
            return MapTile.M1;
        } else if (num == 2) {
            return MapTile.M2;
        } else if (num == 3) {
            return MapTile.M3;
        } else {
            throw new IllegalArgumentException("Wrong random!");
        }
    }
}
