package com.darren.demo.netty.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : darren
 * @date : 2021/11/5
 */
public class Server {

    private static int PORT = 8379;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("服务器端启动了....");
            //进行阻塞
            Socket socket = serverSocket.accept();
            //启动一个线程来处理客户端请求
            ExecutorService service = Executors.newFixedThreadPool(20);
            service.execute(new ServerHandler(socket));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            serverSocket = null;
        }
    }

}
