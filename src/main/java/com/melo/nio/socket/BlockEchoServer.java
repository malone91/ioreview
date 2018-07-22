package com.melo.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author 76009
 * @date 2018/7/22
 */
public class BlockEchoServer {

    //执行服务端业务逻辑线程池
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        //新建ServerSocketChannel
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //如果创建成功
            if (serverSocketChannel.isOpen()) {
                //设置为阻塞模式
                serverSocketChannel.configureBlocking(true);
                //设置网络传输参数
                serverSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4 * 1024);
                serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
                //绑定服务端Channel端口与本地IP
                serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 8087));
                while (true) {
                    //等待客户端连接的请求
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //提交给线程池处理
                    executor.submit(new EchoHandler(socketChannel));
                }
            } else {
                throw new RuntimeException("server socket chanel cannot be opened");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
