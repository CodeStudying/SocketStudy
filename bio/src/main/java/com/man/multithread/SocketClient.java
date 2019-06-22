package com.man.multithread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;

public class SocketClient {

    private final static Logger logger = LoggerFactory.getLogger(SocketClient.class);

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);

        new Thread(() -> {
            while (true) {
                try {
                    String message = "hello world";
                    logger.info("客户端发送数据: {}", message);
                    socket.getOutputStream().write(message.getBytes());
                } catch (Exception e) {
                    logger.info("写数据出错!");
                }
                sleep();
            }

        }).start();
    }

    private static void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
