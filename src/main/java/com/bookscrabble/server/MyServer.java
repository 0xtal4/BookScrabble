package com.bookscrabble.server;
import com.bookscrabble.server.ClientHandler;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.Executor;

public class MyServer {
    private int port;
    private ClientHandler ch;
    private volatile boolean stop;
    private Executor threadPool;

    public MyServer(int Port, ClientHandler handler){
        this.port = Port;
        this.ch = handler;
        this.stop = false;
    }
    public void start(){
    new Thread(() -> {
        try {
            runServer();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }).start();
    }
    public void close(){
        this.stop = true;
    }

    /*private void runServer()throws Exception {
        ServerSocket server=new ServerSocket(port);
        server.setSoTimeout(1000);
        while(!stop){
            try{
                Socket aClient=server.accept(); // blocking call
                try {
                    ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
                    aClient.getInputStream().close();
                    aClient.getOutputStream().close();
                    aClient.close();
                } catch (IOException e) {}
            }catch(SocketTimeoutException e) {}
        }
        server.close();
    }*/
    private void runServer() throws Exception {
        ServerSocket server = new ServerSocket(this.port);
        server.setSoTimeout(1000);

        while (!this.stop) {
            try {
                Socket aClient = server.accept();
                this.threadPool.execute(() -> handleClient(aClient));
            } catch (SocketTimeoutException ignored) {
            }
        }

        server.close();
    }
    private void handleClient(Socket clientSocket) {
        try {
            this.ch.handleClient(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.getInputStream().close();
            clientSocket.getOutputStream().close();
            clientSocket.close();
        } catch (IOException e) {
            // Handle or log the exception accordingly
        }
    }
}
