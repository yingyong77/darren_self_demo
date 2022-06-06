package com.netty.netty.protocol;

import com.netty.netty.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * 可以是可共享的 因为前一个处理器 {@link io.netty.handler.codec.LengthFieldBasedFrameDecoder} 已经处理完成 到了当前处理器 不会存在没有处理完成的数据
 * 自定义消息编解码器
 *
 * @author : darren
 * @date : 2022/6/1
 */
@Slf4j
@ChannelHandler.Sharable
public class MessageCodec extends ByteToMessageCodec<Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        //4字节的魔数
        out.writeBytes(new byte[]{1, 2, 3, 4});
        //字节的版本
        out.writeByte(1);
        //1字节的序列化方式  0 jdk 1 json
        out.writeByte(0);
        //写入1字节的指令类型
        out.writeByte(msg.getMessageType());
        //4个字节的指令请求序号
        out.writeInt(msg.getSequenceId());
        //补充字节  凑满2的n次方
        out.writeByte(0xff);
        //长度-采用jdk的序列化方式
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(msg);
        //字节数组
        byte[] byteArray = bos.toByteArray();
        //长度 4字节
        out.writeInt(byteArray.length);
        //内容
        out.writeBytes(byteArray);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //4字节的魔数值
        int magicNumber = in.readInt();
        //1自己的版本
        byte version = in.readByte();
        //序列化方式
        byte serializerType = in.readByte();
        //指令类型
        byte messageType = in.readByte();
        //4字节请求序列号
        int sequenceId = in.readInt();
        //无意义补充字节
        in.readByte();
        //内容的长度
        int length = in.readInt();
        byte[] bytes = new byte[length];
        //将内容读进bytes中
        in.readBytes(bytes, 0, length);
        //反序列化方式是jdk
        //if (serializerType == 0) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(bis);
        //转换成message
        Message message = (Message) objectInputStream.readObject();
        // }
        log.debug("{},{},{},{},{},{}", magicNumber, version, serializerType, messageType, sequenceId, length);
        log.debug("{}", message);
        //给下一个handler用
        out.add(message);
    }
}
