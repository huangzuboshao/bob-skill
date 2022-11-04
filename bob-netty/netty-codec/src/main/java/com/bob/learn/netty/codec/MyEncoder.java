package com.bob.learn.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 自定义编码器
 *
 * @author bob
 * @date 2022/10/9
 */
public class MyEncoder extends MessageToByteEncoder {

    /**
     * 编码开始
     */
    private final static int PREFIX = 0x02;
    /**
     * 编码结束
     */
    private final static int SUFFIX = 0x03;

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        String message = msg.toString();
        byte[] bytes = message.getBytes();
        byte[] toSend = new byte[bytes.length + 2];
        System.arraycopy(bytes, 0, toSend, 1, bytes.length);
        toSend[0] = PREFIX;
        toSend[toSend.length - 1] = SUFFIX;
        out.writeInt(toSend.length);
        out.writeBytes(toSend);
    }
}
