package com.bob.learn.netty.aio.client;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Future;

/**
 * @author bob
 */
public class AioClient {

    public static void main(String[] args) throws Exception {
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
        Future<Void> future = socketChannel.connect(new InetSocketAddress("192.168.2.1", 9999));
        future.get();
        socketChannel.read(ByteBuffer.allocate(1024), null, new  AioClientHandler(socketChannel, StandardCharsets.UTF_8));
        Thread.sleep(100000);
    }

}
