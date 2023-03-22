package com.bob.learn.netty.spring.config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.Arrays;


/**
 * @author bob
 * @date 2022/10/11
 */
@Component
public class NettyServer {

    private Logger log = LoggerFactory.getLogger(NettyServer.class);

    /**
     * 配置服务端NIO线程组
     */
    //NioEventLoopGroup extends MultiThreadEventLoopGroup Math.max(1, SystemPropertyUtil.getInt("io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
    private final EventLoopGroup childGroup = new NioEventLoopGroup();
    private final EventLoopGroup parentGroup = new NioEventLoopGroup();

    private Channel channel;

    public ChannelFuture bind(InetSocketAddress address) {
        ChannelFuture channelFuture = null;
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup, childGroup)
                    //非阻塞模式
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    ;//.childHandler(new MyChannelInitializer());
            channelFuture = b.bind(address).syncUninterruptibly();
            channel = channelFuture.channel();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (null != channelFuture && channelFuture.isSuccess()) {
            } else {
            }
        }
        return channelFuture;
    }

    public static void main(String[] args) {
        String[] arr1 = new String[4];
        System.out.println(arr1[1]);
        arr1[0] = "a";
        arr1[1] = "b";
        arr1[2] = "c";
        arr1[3] = "d";
        System.out.println(Arrays.toString(arr1));
        //String[] arr2 = new String[];
          //      System.arraycopy(arr1,0,);
        //arr2[0] = "aa";

        System.out.println(Arrays.toString(arr1));
        //System.out.println(Arrays.toString(arr2));

    }
    public Channel getChannel() {
        return channel;
    }
}
