package com.bob.learn.netty.codec;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author bob
 * @date 2022/10/9
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //自定义编码器
        ch.pipeline().addLast(new MyEncoder());
        //自定义解码器
        ch.pipeline().addLast(new MyDecoder());
        //自定义的接收数据实现方法
        ch.pipeline().addLast(new MyServerHandler());
    }
}
