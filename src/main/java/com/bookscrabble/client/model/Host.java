package com.bookscrabble.client.model;

import com.bookscrabble.server.MyServer;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
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
    private MyServerFacade facade;

    //for now this is hardcoded, eventually the
    private String[] dictionaries;

    public Board gameBoard;
    MyServer server;

    private Host()
    {
        gameBoard = Board.getBoard();
    }

    public void setDictionaries(String[] dics){
        this. dictionaries = Arrays.copyOfRange(dics, 0, dics.length-1);
    }

    /**
     * launch the Host server, connect to the Dictionary server through the facade and set the dictionaries
     * @param dictionaries array of the dictionaries names
     * @throws Exception if there was an error when trying to connect to server
     */
    public void startGame(String[] dictionaries) throws Exception
    {
        server = new MyServer(3000, new GuestHanlder());
        facade = new MyServerFacade(9999, "localhost");
        this.dictionaries = Arrays.copyOfRange(dictionaries, 0, dictionaries.length-1);
    }

    public Tile getTile()
    {
        return Tile.Bag.getBag().getRand();
    }

    /**
     * tries to insert a given word onto the board
     * @param word
     * @return "success,points(int)"/ "error"/ "boardIllegal" / "dictionaryIllegal"
     */
    public String tryInsertWord(Word word)
    {
        if(gameBoard.boardLegal(word))
        {
            try {
                //check if the word is dictionary legal or nat
                if(facade.Query(dictionaries, word.toString()))
                    return "success," + gameBoard.tryPlaceWord(word);
                else return "dictionaryIllegal";
            }catch (IOException e)
            {
                //an error has come up when trying to reach the server
                return "error";
            }
        }
        return "boardIllegal";
    }

    public boolean challenge(Word word)
    {
        return facade.Challenge(dictionaries, word.toLetters());
    }

    public static Host getHost()
    {
        if(host == null)
            host = new Host();
        return host;
    }

}