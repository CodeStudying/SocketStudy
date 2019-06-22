package com.man.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端要做的事情：
 Step 1：创建ServerSocket对象，绑定监听的端口
 Step 2：调用accept()方法监听客户端的请求
 Step 3：连接建立后，通过输入流读取客户端发送的请求信息
 Step 4：通过输出流向客户端发送响应信息
 Step 5：关闭相关资源
 */
public class SocketServer {

    private final static Logger logger = LoggerFactory.getLogger(SocketServer.class);


    public static void main(String[] args) throws IOException {

        //1.创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
        ServerSocket serverSocket = new ServerSocket(8888);

        //2.调用accept()等待客户端连接
        logger.info("~~~服务端已就绪，等待客户端接入~，");
        Socket socket = serverSocket.accept();


        //3.连接后获取输入流，读取客户端信息
        InputStream is = socket.getInputStream(); //获取输入流
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String info;
        while ((info = br.readLine()) != null) {//循环读取客户端的信息
            logger.info("客户端发送过来的信息：{}", info);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String str = "服务器收到消息："+info+"\n";
            bw.write(str);
            bw.flush();
        }

        socket.shutdownInput();//关闭输入流
        socket.close();
    }


}
