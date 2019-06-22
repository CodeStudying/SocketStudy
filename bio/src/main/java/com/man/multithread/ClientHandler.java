package com.man.multithread;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientHandler {

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        System.out.println("新客户端接入");
        new Thread(() -> {
            try {
                InputStream inputStream = socket.getInputStream();
                while (true) {
                    byte[] data = new byte[1024];
                    int len;
                    while ((len = inputStream.read(data)) != -1) {
                        String message = new String(data, 0, len);
                        System.out.println("客户端传来消息: " + message);
                        socket.getOutputStream().write(data);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
