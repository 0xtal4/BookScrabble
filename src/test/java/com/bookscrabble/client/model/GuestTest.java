package com.bookscrabble.client.model;

import com.bookscrabble.server.MainTrain;
import com.bookscrabble.server.MyServer;

public class GuestTest {
    public static void main(String[] args) {
        try {
        Guest g =new Guest();
        String[] dictionaries = {"Frank Herbert - Dune.txt", "mobydick.txt"};
        //create a host
        Host myHost = Host.getHost();
        Tile t1 = new Tile('c',9);
        Tile t2 = new Tile('h',3);
        Tile[] tiles = new Tile[2];
        tiles[0] = t1;
        tiles[1] = t2;
        myHost.startGame(dictionaries);
        System.out.println("myhost started");
        g.connect("localhost",3000);
        Word w1 = new Word(tiles,-1,0,false);
        Word w2 = new Word(tiles,1,0,false);
        try {
            g.tryPlaceWord(w1);
            System.out.println("test 1 failed");
        }catch(Exception e){
            }
        try {
            g.tryPlaceWord(w2);
            System.out.println("test 2 failed");
        }catch (Exception e){}
        System.out.println("done");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("failed to start the server");
        }
    }
}
