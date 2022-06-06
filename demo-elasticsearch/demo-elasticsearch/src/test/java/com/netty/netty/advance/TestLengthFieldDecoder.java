package com.netty.netty.advance;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * LTC解码器测试
 *
 * @author : darren
 * @date : 2022/5/31
 */
public class TestLengthFieldDecoder {

    public static void main(String[] args) {
        EmbeddedChannel channel = new EmbeddedChannel(new LengthFieldBasedFrameDecoder(1024, 0, 4, 1, 0),
                new LoggingHandler(LogLevel.DEBUG));
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        send(buf, "Hello, World");
        send(buf, "Hi!");
        channel.writeInbound(buf);
    }


    private static void send(ByteBuf buf, String s) {
        byte[] bytes = s.getBytes();
        int length = bytes.length;
        //先写长度
        buf.writeInt(length);
        //版本号
        buf.writeByte(1);
        //再写内容
        buf.writeBytes(bytes);
    }

}
