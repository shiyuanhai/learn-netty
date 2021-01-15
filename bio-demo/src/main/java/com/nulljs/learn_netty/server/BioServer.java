package com.nulljs.learn_netty.server;

import com.nulljs.learn_netty.server.handler.EchoTimeHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioServer {

    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT);) {
            System.out.println("server started at: " + PORT);
            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();
                new Thread(new EchoTimeHandler(socket)).start();
                // TODO: thread pool
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
