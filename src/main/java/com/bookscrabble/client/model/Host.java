package com.bookscrabble.client.model;

import java.util.HashMap;

/**
 * This class handless all the host's functionallity
 * this includes:
 * 1. receiving connections from up to 3 guests
 * 3. run the game logic (i.e. board, bag and tiles logic)
 * 2. connecting the game server where words are checked for there legality
 */

public class Host {

    static Host host = null;
    private HashMap<Integer, Player> players;
    MyServer server;

    private Host()
    {
        server = new MyServer(3000, new GuestHanlder());
    }

    public static void startGame()
    {

    }

    public static Host get()
    {
        if(host == null)
            host = new Host();
        return host;
    }

}