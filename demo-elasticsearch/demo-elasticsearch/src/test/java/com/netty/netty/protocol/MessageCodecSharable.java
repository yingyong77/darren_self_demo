package com.netty.netty.protocol;

import com.netty.netty.config.Config;
import com.netty.netty.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.MessageToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 必须和{@link LengthFieldBasedFrameDecoder} 一起使用 确保收到的消息是完整的
 * 上一个handler是粘包半包处理器 所以不会有线程安全问题
 * <p>
 * Inbound---入栈
 * OutBound---出栈
 * 是否保存了状态决定是否要用sharable
 *
 * @author : darren
 * @date : 2022/6/2
 */
@Slf4j
@ChannelHandler.Sharable
public class MessageCodecSharable extends MessageToMessageCodec<ByteBuf, Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> outList) throws Exception {

        ByteBuf out = ctx.alloc().buffer();
        //4字节的魔数
        out.writeBytes(new byte[]{1, 2, 3, 4});
        //字节的版本
        out.writeByte(1);
        //1字节的序列化方式  0 jdk 1 json
        Serializer.Algorithm serializerAlgorithm = Config.getSerializerAlgorithm();
        out.writeByte(serializerAlgorithm.ordinal());
        //写入1字节的指令类型
        out.writeByte(msg.getMessageType());
        //4个字节的指令请求序号
        out.writeInt(msg.getSequenceId());
        //补充字节  凑满2的n次方
        out.writeByte(0xff);
        //长度-采用jdk的序列化方式
        //字节数组
        byte[] byteArray = serializerAlgorithm.serialize(msg);
        //长度 4字节
        out.writeInt(byteArray.length);
        //内容
        out.writeBytes(byteArray);

        outList.add(out);
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
        //找到序列化算法
        //确定消息类型 通过messageType
        Class<? extends Message> messageClass = Message.getMessageClass(messageType);
        Message message = Serializer.Algorithm.values()[serializerType].deSerialize(messageClass, bytes);
        // }
        log.debug("{},{},{},{},{},{}", magicNumber, version, serializerType, messageType, sequenceId, length);
        log.debug("{}", message);
        //给下一个handler用
        out.add(message);
    }
}
