package com.nulljs.learn_netty.server.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;

public class EchoTimeHandler implements Runnable {

    private Socket socket;

    public EchoTimeHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ){
            String body = null;
            while ((body = in.readLine()) != null && body.length() != 0) {
                System.out.println("server receives: " + body);
                out.println("server sends: " + LocalDateTime.now());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
