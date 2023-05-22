package com.bookscrabble.client.model;

import java.io.Console;
import java.io.OutputStream;
import java.net.Socket;

public class Guest {

    private static Socket serverConnection;
    private static OutputStream out;

    public static void connect(String host, int port) throws Exception{
        serverConnection = new Socket(host, port);
        out = serverConnection.getOutputStream();
    }

    public static void tryPlaceWord(Word word)
    {
        if(serverConnection.isConnected())
        {

        }
        else{
            System.out.println("the connectin with the host was lost....");
        }
    }
}
