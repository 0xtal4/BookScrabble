package com.bookscrabble.server;

public class MyServerMain {
    public static void main(String[] args) {

        MyServer s=new MyServer(9999, new BookScrabbleHandler());
        int c = Thread.activeCount();
        System.out.println("server running");
        s.start(); // runs in the background
    }
}