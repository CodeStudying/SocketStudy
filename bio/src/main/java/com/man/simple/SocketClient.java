package com.man.simple;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端要做的事情：
 Step 1：创建Socket对象，指明需要链接的服务器的地址和端号
 Step 2：链接建立后，通过输出流向服务器发送请求信息
 Step 3：通过输出流获取服务器响应的信息
 Step 4：关闭相关资源
 */
public class SocketClient {

    public static void main(String[] args) {
        //1.创建客户端Socket，指定服务器地址和端口
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 8888);
            //2.获取输出流，向服务器端发送信息
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            //建立键盘输入：
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("请输入发送消息内容：");
                bw.write(scanner.nextLine()+"\n");
                bw.newLine();
                bw.flush();
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println(socket.getInetAddress().getLocalHost()+":"+socket.getPort()+">>>>"+br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.shutdownOutput();//关闭输出流
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
