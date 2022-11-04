import com.bob.learn.netty.codec.MyUDPChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * netty 服务端
 *
 * @author Bob
 * @date 2022/9/28 14:24
 */
public class UDPTest {
    public static void main(String[] args) {
        new UDPTest().bind(8888);
    }


    /**
     * 绑定端口启动服务
     *
     * @param port 端口
     */
    private void bind(int port) {
        //配置服务端NIO线程组
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(parentGroup)
                    //非阻塞模式
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)    //广播
                    .option(ChannelOption.SO_RCVBUF, 2048 * 1024)// 设置UDP读缓冲区为2M
                    .option(ChannelOption.SO_SNDBUF, 1024 * 1024)// 设置UDP写缓冲区为1M
                    .handler(new MyUDPChannelInitializer())
            ;
            ChannelFuture future = bootstrap.bind(port).sync();
            System.out.println("netty UDP服务端已启动...");
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //优雅的关闭释放内存
            parentGroup.shutdownGracefully();
        }
    }
}
