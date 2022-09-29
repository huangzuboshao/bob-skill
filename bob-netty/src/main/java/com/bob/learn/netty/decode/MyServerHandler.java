package com.bob.learn.netty.decode;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * @author Bob
 * @date 2022/9/28 14:35
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel socketChannel = (SocketChannel) ctx.channel();
        System.out.println("有一客户端【IP:" + socketChannel.remoteAddress().getHostString() + ",port: " + socketChannel.remoteAddress().getPort()
                + "】链接到本服务端【" + socketChannel.localAddress() + "】");
        ChannelHandler.channelGroup.add(socketChannel);
        String returnMsg = "通知客户端【IP:" + socketChannel.remoteAddress().getHostString() + ",port: " + socketChannel.remoteAddress().getPort()
                + "】链接建立成功:" + socketChannel.localAddress().getHostString() + "\n";
        ByteBuf buffer = Unpooled.buffer(returnMsg.getBytes(StandardCharsets.UTF_8).length);
        buffer.writeBytes(returnMsg.getBytes(StandardCharsets.UTF_8));
        ctx.writeAndFlush(buffer);
    }

    /**
     * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端【" + ctx.channel().remoteAddress().toString() + "】断开链接。\n");
        ChannelHandler.channelGroup.remove(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println(LocalDateTime.now() + "--接收到来自【" + channel.remoteAddress().getHostString() + "-" + channel.remoteAddress().getPort() + "】的消息：" + msg);
        String returnMsg = "服务端已收到来自【" + channel.remoteAddress().getHostString() + "-" + channel.remoteAddress().getPort() + "】的消息:" + msg + "\n";
        ByteBuf buffer = Unpooled.buffer(returnMsg.getBytes(StandardCharsets.UTF_8).length);
        buffer.writeBytes(returnMsg.getBytes(StandardCharsets.UTF_8));
        ChannelHandler.channelGroup.writeAndFlush(buffer);
    }

    /**
     * 抓住异常，当发生异常的时候，可以做一些相应的处理，比如打印日志、关闭链接
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常信息：\r\n" + cause.getMessage());
    }
}
