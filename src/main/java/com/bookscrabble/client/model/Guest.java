package com.bookscrabble.client.model;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Guest {
    private static Socket serverConnection;
    private static Writer writer;
    private static BufferedReader reader;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    private int points;

    public Guest() {
        serverConnection = new Socket();
        points=0;
        writer = null;
        reader = null;
    }

    public void connect(String host, int port) throws Exception {
        serverConnection = new Socket(host, port);
        writer = new OutputStreamWriter(serverConnection.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(serverConnection.getInputStream()));
    }

    public void tryPlaceWord(Word word) throws Exception {
        if (serverConnection.isConnected()) {
            writer.write("W,"+word.toString());
            writer.flush();

            String result = reader.readLine();
            if(result.equals("boardIllegal"))
                throw new Exception("boardIllegal");
            if(result.equals("dictionaryIllegal"))
                throw new Exception("dictionaryIllegal");
            if(result.equals("error"))
                throw new Exception("connectionError");
            if(result.contains("success")){
                String[] s = result.split(",");
                int points = Integer.parseInt(s[1]);
                this.points+=points;
            }
        } else
            throw new Exception("connectionError");
    }
    public boolean challenge(Word word) throws Exception {
        if (serverConnection.isConnected()) {
            writer.write("C,"+word.toString());
            writer.flush();
            String result = reader.readLine();
            if(result.equals("true"))
                return true;
            else
                return false;

        } else
            throw new Exception("connectionError");
    }
}