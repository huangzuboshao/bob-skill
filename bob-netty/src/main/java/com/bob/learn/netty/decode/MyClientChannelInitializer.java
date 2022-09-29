package com.bob.learn.netty.decode;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.StandardCharsets;


/**
 * @author Bob
 * @date 2022/9/28 14:32
 */
public class MyClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //1.基于换行符
        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        //2.基于指定字符串【换行符，这样功能等同于LineBasedFrameDecoder】
        //socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, false, Delimiters.lineDelimiter()));
        //socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, false, Unpooled.buffer().writeBytes(" ".getBytes())));
        //解码转String
        socketChannel.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
        // 管道中接收数据实现方法
        socketChannel.pipeline().addLast(new MyServerHandler());
    }
}
