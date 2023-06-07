package com.bookscrabble.client.model;

import java.io.*;
import java.net.Socket;

/**
 * The Guest class represents a guest player in the Book Scrabble game.
 * It handles the communication with the game server and provides methods
 * for connecting, placing words, and performing challenges.
 */
public class Guest {
    private static Socket serverConnection;
    private static Writer writer;
    private static BufferedReader reader;

    private int points;

    /**
     * Constructs a new Guest object.
     */
    public Guest() {
        serverConnection = null;
        points = 0;
        writer = null;
        reader = null;
    }

    /**
     * Connects to the game server at the specified host and port.
     *
     * @param host the host name or IP address of the server
     * @param port the port number of the server
     * @throws Exception if there was an error when trying to connect to the server
     */
    public void connect(String host, int port) throws Exception {
        serverConnection = new Socket(host, port);
        writer = new OutputStreamWriter(serverConnection.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(serverConnection.getInputStream()));
    }

    /**
     * Tries to place a word on the game board.
     *
     * @param word the word to be placed
     * @throws Exception if there was an error in the connection, the word is invalid,
     *                   or the dictionary check fails
     */
    public void tryPlaceWord(Word word) throws Exception {
        if (serverConnection.isConnected()) {
            writer.write("W," + word.toString());
            writer.flush();

            String result = reader.readLine();
            if (result.equals("boardIllegal"))
                throw new Exception("boardIllegal");
            if (result.equals("dictionaryIllegal"))
                throw new Exception("dictionaryIllegal");
            if (result.equals("error"))
                throw new Exception("connectionError");
            if (result.contains("success")) {
                String[] s = result.split(",");
                int points = Integer.parseInt(s[1]);
                this.points += points;
            }
        } else {
            throw new Exception("connectionError");
        }
    }

    /**
     * Performs a challenge against a word on the game board.
     *
     * @param word the word to challenge
     * @return true if the challenge is successful, false otherwise
     * @throws Exception if there was an error in the connection
     */
    public boolean challenge(Word word) throws Exception {
        if (serverConnection.isConnected()) {
            writer.write("C," + word.toString());
            writer.flush();
            String result = reader.readLine();
            if (result.equals("true"))
                return true;
            else
                return false;
        } else {
            throw new Exception("connectionError");
        }
    }

    /**
     * Gets the current points of the guest player.
     *
     * @return the points of the guest player
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the points of the guest player.
     *
     * @param points the points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }
}