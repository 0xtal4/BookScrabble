package com.bookscrabble.client.model;

import com.bookscrabble.server.MainTrain;
import com.bookscrabble.server.MyServer;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import java.io.IOException;

public class HostTest {
    public static void main(String[] args){
        //start the server:
        MyServer s=new MyServer(9999, new MainTrain.ClientHandler1());
        s.start();
        String[] dictionaries = {"Frank Herbert - Dune.txt", "mobydick.txt"};
        //create a host
        Host myHost = Host.getHost();
        //start the game
        try {
            myHost.startGame(dictionaries);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("failed to start the server");
        }
    }
}
