package com.bookscrabble.client.model;

public class HostTest {
    public static void main(String[] args) throws Exception {
        //create a host
        Host myHost = Host.getHost();
        //start the game
        myHost.startGame(new String[]{"test1", "test2"});

    }
}
