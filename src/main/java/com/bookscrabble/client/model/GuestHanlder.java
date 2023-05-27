package com.bookscrabble.client.model;

import com.bookscrabble.server.ClientHandler;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Responsible for handling the clients that connect to the host
 */

public class GuestHanlder implements ClientHandler {

    //the last word that was requested, this way we can avoid naughty hackers (;
    private Word lastWordRequest;
    /**
     * Handles the requests that are recieved by the players that connected to the given host in guest mode
     * @param inFromclient
     * @param outToClient
     */
    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient){
        String text = new Scanner(inFromclient).next();
        PrintWriter out=new PrintWriter(outToClient);
        String[] textArr = text.split(",");
        String request = textArr[0];
        int col = Integer.parseInt(textArr[1]);
        int row = Integer.parseInt(textArr[2]);
        boolean vertical = false;
        if(textArr[3].equals("T"))
            vertical = true;
        String givenWord = textArr[4];


        switch (request){
            //the guest requested to place a word on the board
            case "W":
                //construct the word:
                Word word = new Word(getTiles(givenWord), row, col, vertical);
                lastWordRequest = word;
                out.println(Host.getHost().tryInsertWord(word));
                break;

            case "C":
                out.println(Host.getHost().challange(lastWordRequest));
        }
        out.flush();
    }

    private Tile[] getTiles(String word)
    {
        char[] letters = word.toCharArray();
        Tile[] tiles = new Tile[letters.length];
        for(int i=0;i<letters.length; i++)
            tiles[i] = Tile.Bag.getBag().getTile(letters[i]);
        return tiles;
    }


    @Override
    public void close() {

    }
}
