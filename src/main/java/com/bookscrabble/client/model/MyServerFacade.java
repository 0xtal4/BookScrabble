package com.bookscrabble.client.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * this class is made to make communication between the main server and the host easier
 * @param server is our socket to the main server
 */
public class MyServerFacade {
    private Socket server;
    /**
     * this is the builder for the class,it creates the connection to the main server.
     *
     *
     * @param port the port of the server.
     * @param address the adress of the server. if passed empty set to localhost
     */
    public MyServerFacade(int port, String address) throws IOException {
        if (address == null){
            address = "localhost";
        }
        server = new Socket("localhost", port);
    }
    /**
     * the challenge function is used to check if given word is in the given books.
     *
     *
     * @param dictionaries the books that we want to search the word in.
     * @param query the the word that we want to search
     */
    public Boolean Challenge(String[] dictionaries, String query){
        String res;
        try{
            PrintWriter out=new PrintWriter(server.getOutputStream());
            Scanner in=new Scanner(server.getInputStream());
            String dics = String.join(",", dictionaries);
            out.println("c,"+dics+"," + query);
            out.flush();
             res=in.next();
            return Boolean.getBoolean(res);
        }catch (IOException e) {
            System.out.println("your code ran into an exception");
        }
        return false;
    }
    /**
     * the query function is used to check if given word is in the Bloom filter.
     *
     *
     * @param dictionaries the books that we want to search the word in.
     * @param query the the word that we want to search
     */
    public Boolean Query(String[] dictionaries, String query){
        try{
            PrintWriter out=new PrintWriter(server.getOutputStream());
            Scanner in=new Scanner(server.getInputStream());
            String dics = String.join(",", dictionaries);
            out.println("q,"+dics+"," + query);
            out.flush();
            String res=in.next();
            return Boolean.getBoolean(res);
        }catch (IOException e) {
            System.out.println("your code ran into an exception");
        }
        return false;

    }
}