package com.bookscrabble.client.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * this class is made to make communication between the main server and the host easier
 */
public class MyServerFacade {

    //socket to the main server

    int Port;
    String Address;
    /**
     * this is the builder for the class,it creates the connection to the main server.
     *
     * @param port the port of the server.
     * @param address the adress of the server. if passed empty set to localhost
     */
    public MyServerFacade(int port, String address) throws IOException {
        if (address == null){
            Address = "localhost";
        }
        Port = port;
    }

    /**
     * the challenge function is used to check if given word is in the given books.
     *
     * @param dictionaries the books that we want to search the word in.
     * @param query the word that we want to search
     */
    public Boolean Challenge(String[] dictionaries, String query){
        try{
            boolean answer = false;
            Socket server = new Socket(Address,Port);
            PrintWriter out=new PrintWriter(server.getOutputStream());
            Scanner in=new Scanner(server.getInputStream());
            String dics = String.join(",", dictionaries);
            System.out.println("sending challenge " + query);
            out.println("C,"+dics+"," + query);
            out.flush();
            if (in.hasNext()) {
                String res = in.next();
                System.out.println("challenge "+ query +" res: " + res);
                if (res.equals("true"))
                    answer = true;
                in.close();
                out.close();
                server.close();
                return answer;
            }
        }catch (IOException e) {
            System.out.println("your code ran into an exception");
        }
        return false;
    }

    /**
     * the query function is used to check if given word is in the Bloom filter.
     *
     * @param dictionaries the books that we want to search the word in.
     * @param query the the word that we want to search
     */
    public Boolean Query(String[] dictionaries, String query) throws IOException {
        boolean answer = false;
        Socket server = new Socket(Address,Port);
        PrintWriter out = new PrintWriter(server.getOutputStream());
        Scanner in = new Scanner(server.getInputStream());
        String dics = String.join(",", dictionaries);
        System.out.println("sending query "+ query);
        out.println("Q," + dics + "," + query);
        out.flush();
        if (in.hasNext()) {
            String res = in.next();
            System.out.println("Query "+ query +" res: " + res);

            if (res.equals("true"))
                answer = true;
            in.close();
            out.close();
            server.close();
            return answer;
        }
    return false;
    }
}