package com.man.multithread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    private ServerSocket serverSocket;


    public SocketServer(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("服务端启动成功，端口："+port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void start () {
        //防止端口监听阻塞主线程
        new Thread(() -> {
            while (true) {
                try {
                    Socket client = serverSocket.accept();
                    new ClientHandler(client).start();
                } catch (IOException e) {
                    System.out.println("服务端异常");
                }
            }
        }).start();
    }


    public static void main(String[] args) {
        SocketServer socketServer = new SocketServer(8888);
        socketServer.start();
    }


}

