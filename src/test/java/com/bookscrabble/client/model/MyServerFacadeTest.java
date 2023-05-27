package com.bookscrabble.client.model;

import java.io.Console;
import java.io.IOException;

public class MyServerFacadeTest {
    public static void main(String[] args) throws IOException {
        MyServerFacade f = new MyServerFacade(9999, "localhost");
        if (f.Challenge(new String[]{"test1"}, "word1")){
            System.out.println("test 1 passed");
        }else {
            System.out.println("test 1 failed");
        }
        if (f.Challenge(new String[]{"test2"}, "word2")){
            System.out.println("test 2 passed");
        }else{
            System.out.println("test 2 failed");

        }
        if (f.Query(new String[]{"test1"}, "word1")){
            System.out.println("test 1 passed");
        }else {
            System.out.println("test 1 failed");
        }
        if (f.Query(new String[]{"test2"}, "word2")){
            System.out.println("test 2 passed");
        }else{
            System.out.println("test 2 failed");

        }
    }

}
