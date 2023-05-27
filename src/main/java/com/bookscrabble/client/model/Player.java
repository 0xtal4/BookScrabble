package com.bookscrabble.client.model;

import java.util.ArrayList;

public class Player {
    ArrayList<Tile> givenTiles;
    int points;

    public Player()
    {
        givenTiles = new ArrayList<>();
        points = 0;
    }
}
