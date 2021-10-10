package com.darren.demo.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : darren
 * @date : 2021/10/10
 * Bio 模型
 */
public class SocketServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9000);
        while (true) {
            System.out.println("等待连接。。");
            //阻塞方法
            Socket clientSocket = serverSocket.accept();
            System.out.println("有客户端连接了。。");
            handler(clientSocket);
        }

    }

    private static void handler(Socket clientSocket) throws IOException {
        byte[] bytes = new byte[1024];
        System.out.println("准备read。。");
        //接收客户端的数据，阻塞方法，没有数据可读时就阻塞
        int read = clientSocket.getInputStream().read(bytes);
        if (read != -1) {
            System.out.println("接收到了客户端的数据:" + new String(bytes, 0, read));
        }

    }
}
