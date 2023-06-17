package com.bookscrabble.client.model;

import com.bookscrabble.server.ClientHandler;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Responsible for handling the clients that connect to the host
 */

public class GuestHanlder implements ClientHandler {

    //the last word that was requested, this way we can avoid naughty hackers (;
    private Word lastWordRequest;
    private HashMap<Character, Tile> givenTiles;
    /**
     * Handles the requests that are received by the players that connected to the given host in guest mode
     * @param inFromclient
     * @param outToClient
     */
    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient){
        String text = new Scanner(inFromclient).next();
        PrintWriter out=new PrintWriter(outToClient);
        String[] textArr = text.split(",");
        String request = textArr[0];



        switch (request){
            //the guest requested to place a word on the board
            case "W":
                int col = Integer.parseInt(textArr[1]);
                int row = Integer.parseInt(textArr[2]);
                boolean vertical = false;
                if(textArr[3].equals("T"))
                    vertical = true;
                String givenWord = textArr[4];
                //construct the word:
                Tile[] given = getGivenTiles(request);
                if(given == null)
                {
                    out.println("hacker");
                    break;
                }
                Word word = new Word(getGivenTiles(givenWord), row, col, vertical);
                lastWordRequest = word;
                out.println(Host.getHost().tryInsertWord(word));
                break;

            case "C":
                out.println(Host.getHost().challenge(lastWordRequest));
                break;
                //the guest wants to take another tile
            case "T":
                Tile newTile = Host.getHost().getTile();
                givenTiles.put(newTile.letter, newTile);
                out.println(newTile.toMessage());
        }
        out.flush();
    }

    public Tile[] getGivenTiles(String word)
    {
        ArrayList<Tile> tiles = new ArrayList<>();

        for (char c: word.toCharArray()) {
            if(givenTiles.containsKey(c))
                tiles.add(givenTiles.get(c));
            else return null;
        }
        return tiles.toArray(new Tile[0]);
    }


    @Override
    public void close() {

    }
}
