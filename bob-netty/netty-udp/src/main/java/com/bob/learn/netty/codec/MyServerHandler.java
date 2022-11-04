package com.bob.learn.netty.codec;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author bob
 * @date 2022/10/9
 */
public class MyServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
        String msg = packet.content().toString(StandardCharsets.UTF_8);
        System.out.println("UDP 服务端已收到：【" + msg + "】");

        // 由于数据报的数据是以字符数组传的形式存储的，所以传转数据
        String json = "服务端已收到,通知客户端";
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        DatagramPacket data = new DatagramPacket(Unpooled.copiedBuffer(bytes), packet.sender());
        //向客户端发送消息
        ctx.writeAndFlush(data);
    }
}
