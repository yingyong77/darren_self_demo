package com.netty.netty.utils;

import io.netty.buffer.ByteBuf;
import io.netty.util.internal.StringUtil;

import static io.netty.buffer.ByteBufUtil.appendPrettyHexDump;

/**
 * @author : darren
 * @date : 2022/3/24
 */
public class ByteBufUtil {
    public static void log(ByteBuf buf) {
        int length = buf.readableBytes();
        int rows = length / 16 + (length % 15 == 0 ? 0 : 1) + 4;
        StringBuilder sb = new StringBuilder(rows * 80 * 2)
                .append("read index").append(buf.readerIndex())
                .append("write index").append(buf.writerIndex())
                .append("capacity").append(buf.capacity())
                .append(StringUtil.NEWLINE);
        appendPrettyHexDump(sb, buf);
        System.out.println(sb.toString());

    }
}
