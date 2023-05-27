package com.bookscrabble.client.model;


public class Word {
    private Tile[] tiles;
    private int row;
    private int col;
    private boolean vertical;

    public Word(Tile[] tiles, int row, int col, boolean vertical) {
        this.tiles = tiles;
        this.row = row;
        this.col = col;
        this.vertical = vertical;
    }
    public Tile[] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }

    public int getRow() {
        return row;
    }

    public boolean equals(Object object) {
        Word word = (Word) object;
        return row == word.row && col == word.col && vertical == word.vertical && java.util.Arrays.equals(tiles, word.tiles);
    }
    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isVertical() {
        return vertical;
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }
    public int getLength(){
        return this.tiles.length;
    }

    /**
     * turns the word's data into a string in the following format:
     * row(int),col(int),vertical("T"/"F"),word
     * @return
     */
    public String toString(){
        StringBuilder stringWord= new StringBuilder();
        stringWord.append(row);
        stringWord.append(",");
        stringWord.append(col);
        stringWord.append(",");
        if(vertical)
            stringWord.append("T");
        else stringWord.append("F");
        stringWord.append(",");
        for (Tile tile : tiles) {
            stringWord.append(tile.letter);
        }
        return stringWord.toString();
    }

    public String toLetters(){
        StringBuilder stringWord= new StringBuilder();
        for (Tile tile : tiles) {
            stringWord.append(tile.letter);
        }
        return stringWord.toString();
    }
}
