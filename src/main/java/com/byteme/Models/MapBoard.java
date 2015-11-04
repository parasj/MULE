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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public MapBoard(int height, int width, MapType type) {
        this.height = height;
        this.width = width;

        if (type == MapType.RANDOM) {
            board = new MapTile[height][9];
        } else if (type == MapType.STANDARD) {
            board = standardMap;

        }
    }

    public MapTile[][] getBoard() {
        return board;
    }

    public MapTile getTile(int h, int r) {
        return board[h][r];
    }

    public void setTile(int h, int r, MapTile newTile) {
        this.board[h][r] = newTile;
    }
}
