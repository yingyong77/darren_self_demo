package com.darren.demo.netty.aio;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

/**
 * @author : darren
 * @date : 2021/11/8
 */
public class Client implements Runnable {

    private AsynchronousSocketChannel channel;

    @SneakyThrows
    public Client() {
        channel = AsynchronousSocketChannel.open();
    }

    public void connect() {
        channel.connect(new InetSocketAddress("127.0.0.1", 8379));
    }

    public void write(String data) {
        try {
            channel.write(ByteBuffer.wrap(data.getBytes())).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void read() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            channel.read(buffer).get();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }


    public static void main(String[] args) {
        try {
            Client c1 = new Client();
            Client c2 = new Client();
            Client c3 = new Client();

            c1.connect();
            c2.connect();
            c3.connect();
            new Thread(c1).start();
            new Thread(c2).start();
            new Thread(c3).start();

            Thread.sleep(1000);
            c1.write("c1 aaa");
            c2.write("c2 bbbb");
            c3.write("c3 ccccc");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
