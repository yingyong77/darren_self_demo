package com.netty.netty.protocol;

import com.netty.netty.message.LoginRequestMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 不怕粘包 因为消息编码器会按照规定去解码
 * 怕半包，因为可能最后读到的内容会变少 导致反序列化会报错
 * 所以补充LTC 帧解码器
 *
 * @author : darren
 * @date : 2022/6/2
 */
@Slf4j
public class TestMessageCodec {
    public static void main(String[] args) throws Exception {
        EmbeddedChannel channel = new EmbeddedChannel(
                //若发现数据不完整 则不会向下传递(移动) 会记录上一次没有处理完成的数据
                new LengthFieldBasedFrameDecoder(1024, 12, 4, 0, 0),
                new LoggingHandler(LogLevel.DEBUG), new MessageCodec());
        //写入一个对象 出栈
        LoginRequestMessage message = new LoginRequestMessage("zhangsan", "123");
        //出栈-encode
        //channel.writeOutbound(message);
        //入栈-decode
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        new MessageCodec().encode(null, message, buf);

        log.info(String.valueOf(buf.getClass()));
        //模拟半包 采用切片的方式
        //0拷贝  物理上是同一块内存
        ByteBuf buf1 = buf.slice(0, 100);
        ByteBuf buf2 = buf.slice(100, buf.readableBytes() - 100);
        buf.retain();
        channel.writeInbound(buf1); //会将引用计数-1
        channel.writeInbound(buf2);


    }

}
