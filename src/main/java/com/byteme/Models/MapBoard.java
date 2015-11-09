package com.byteme.Models;

import com.byteme.Schema.MapTile;
import com.byteme.Schema.MapType;

/**
 * MULE
 */
public class MapBoard {
    private final int height;
    private final int width;
    private MapTile[][] board;

    private static final MapTile[][] standardMap =
            {{MapTile.P,    MapTile.P,  MapTile.M1, MapTile.P,  MapTile.R,      MapTile.P,  MapTile.M3, MapTile.P,  MapTile.P},
             {MapTile.P,    MapTile.M1, MapTile.P,  MapTile.P,  MapTile.R,      MapTile.P,  MapTile.P,  MapTile.P,  MapTile.M3},
             {MapTile.M3,   MapTile.P,  MapTile.P,  MapTile.P,  MapTile.Town,   MapTile.P,  MapTile.P,  MapTile.P,  MapTile.M1},
             {MapTile.P,    MapTile.M2, MapTile.P,  MapTile.P,  MapTile.R,      MapTile.P,  MapTile.M2, MapTile.P,  MapTile.P},
             {MapTile.P,    MapTile.P,  MapTile.M2, MapTile.P,  MapTile.R,      MapTile.P,  MapTile.P,  MapTile.P,  MapTile.M2}};

//    public MapBoard() {
//        this(5, 9, MapType.STANDARD);
//    }

    /**
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @param height
     * @param width
     * @param type
     */
    public MapBoard(int height, int width, MapType type) {
        this.height = height;
        this.width = width;

        if (type == MapType.RANDOM) {
            board = new MapTile[height][9];
        } else if (type == MapType.STANDARD) {
            board = standardMap;

        }
    }

    /**
     * Gets the map board
     * @return board
     */
    public MapTile[][] getBoard() {
        return board.clone();
    }

    /**
     *
     * @param height
     * @param width
     * @return
     */
    public MapTile getTile(int height, int width) {
        return board[height][width];
    }

    /**
     *
     * @param height
     * @param width
     * @param newTile
     */
    public void setTile(int height, int width, MapTile newTile) {
        this.board[height][width] = newTile;
    }
}
