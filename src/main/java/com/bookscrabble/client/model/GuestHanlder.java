package com.bookscrabble.client.model;

import com.bookscrabble.server.ClientHandler;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * Responsible for handling the clients that connect to the host
 */

public class GuestHanlder implements ClientHandler {

    /**
     * Handles the requests that are recieved by the players that connected to the given host in guest mode
     * @param inFromclient
     * @param outToClient
     */
    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient){
    }


    @Override
    public void close() {

    }
}
