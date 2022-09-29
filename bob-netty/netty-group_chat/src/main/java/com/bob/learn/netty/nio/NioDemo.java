package com.bob.learn.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 首先是先创建ServerSocketChannel 对象，和真正处理业务的线程池
 * <p>
 * 然后给刚刚创建的ServerSocketChannel 对象进行绑定一个对应的端口，然后设置为非阻塞
 * <p>
 * 然后创建Selector对象并打开，然后把这Selector对象注册到ServerSocketChannel 中，并设置好监听的事件，监听 SelectionKey.OP_ACCEPT
 * <p>
 * 接着就是Selector对象进行死循环监听每一个Channel通道的事件，循环执行 Selector.select() 方法，轮询就绪的 Channel
 * <p>
 * 从Selector中获取所有的SelectorKey（这个就可以看成是不同的事件），如果SelectorKey是处于 OP_ACCEPT 状态，说明是新的客户端接入，调用 ServerSocketChannel.accept 接收新的客户端。
 * 然后对这个把这个接受的新客户端的Channel通道注册到ServerSocketChannel上，并且把之前的OP_ACCEPT 状态改为SelectionKey.OP_READ读取事件状态，并且设置为非阻塞的，
 * 然后把当前的这个SelectorKey给移除掉，说明这个事件完成了
 * <p>
 * 如果第5步的时候过来的事件不是OP_ACCEPT 状态，那就是OP_READ读取数据的事件状态，然后调用本文章的上面的那个读取数据的机制就可以了
 *
 * @author Bob
 * @date 2022/9/21 15:25
 */
public class NioDemo {

    static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        //listen(getSelector());
    }

    /**
     * 1、注册感兴趣事件步骤：
     * 创建ServerSocketChannel对象，并配置为非阻塞模式，
     * 接着绑定到具体端口，最后向Selector中注册事件，此处指定的参数是OP_ACCEPT，
     * 即指定我们要监听accept事件，也就是新的连接发生时所产生的事件，
     *
     * @return
     * @throws IOException
     */
    private static Selector getSelector() throws IOException {
        // 创建可选择通道，并配置为非阻塞模式
        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);

        // 绑定通道到指定端口
        ServerSocket socket = server.socket();
        InetSocketAddress addr = new InetSocketAddress(PORT);
        socket.bind(addr);

        // 创建Selector对象
        Selector selector = Selector.open();
        // 向selector中注册感兴趣事件，即监听accept事件
        server.register(selector, SelectionKey.OP_ACCEPT);
        return selector;
    }

    /**
     * 2、从Selector中获取感兴趣事件，开始监听，内部不断循环
     * 非阻塞IO内部循环模式基本遵循这种方式，首先调用select()阻塞，
     * 直到至少有一个事件发生，然后再使用selectedKeys()得到SelectionKey，
     * 再进行迭代循环，操作业务逻辑
     */
    private static void listen(Selector selector) {
        System.out.println("listen on：" + PORT);
        try {
            while (true) {
                // 该调用会阻塞，直到至少有一个事件发生
                int select = selector.select();
                if (select == 0) {
                    continue;
                }
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iter = keys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    process(key);
                    iter.remove();

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 3、根据不同的事件做对应的处理
     * 分别判断是接收请求、读数据还是写事件，分别作不同的处理。
     * 在java1.4之前的IO系统中，提供的都是面向流的IO系统，
     * 系统一次一个字节地处理，一个输入流产生一个字节数据，
     * 一个输出流消费一个字节数据，面向流的IO速度非常慢。
     * 而在java1.4中退出的NIO，这是面向块的IO系统，系统以块的方式处理数据，
     * 每一个操作在一步中产生或消费一个块数据，因此速度提升了不少
     *
     * @param key
     */
    private static void process(SelectionKey key) throws IOException {
        Selector selector = key.selector();

        // 接收请求，判断客户端是否已经连上来了
        if (key.isAcceptable()) {
            ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
            SocketChannel clientChannel = serverChannel.accept();
            System.out.println(String.format("接收请求(isAcceptable) - Connection from %s", clientChannel.getRemoteAddress()));
            clientChannel.configureBlocking(false);
            //设置为 OP_READ ，说明要读数据
            clientChannel.register(selector, SelectionKey.OP_READ);
        }

        // 读取信息
        else if (key.isReadable()) {
            SocketChannel clientChannel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int len = clientChannel.read(buffer);
            if (len > 0) {
                buffer.flip();
                String content = new String(buffer.array(), 0, len);
                System.out.println(String.format("读取信息(isReadable) - From %s : %s", clientChannel.getRemoteAddress(), content));
                SelectionKey selectionKey = clientChannel.register(selector, SelectionKey.OP_WRITE);
                selectionKey.attach(content);
            } else {
                clientChannel.close();
            }
            buffer.clear();
        }

        // 写事件
        else if (key.isWritable()) {
            SocketChannel clientChannel = (SocketChannel) key.channel();
            String content = (String) key.attachment();
            System.out.println(String.format("写事件(isWritable) - From %s : %s", clientChannel.getRemoteAddress(), content));
            ByteBuffer buffer = ByteBuffer.wrap(("输出内容：" + content).getBytes());
            if (buffer != null) {
                clientChannel.write(buffer);
            } else {
                clientChannel.close();
            }
        }

        // isConnectable
        else if (key.isConnectable()) {
            System.out.println("=== isConnectable ===");
        }

        // isValid
        else if (key.isValid()) {

        }
    }
}
