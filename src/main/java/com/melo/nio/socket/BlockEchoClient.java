package com.melo.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 *
 * @author 76009
 * @date 2018/7/22
 */
public class BlockEchoClient {

    public static void main(String[] args) {
        ByteBuffer helloBuffer = ByteBuffer.wrap("你好".getBytes());
        CharBuffer charBuffer;
        Charset charset = Charset.defaultCharset();
        CharsetDecoder decoder = charset.newDecoder();
        //创建客户端socketChannel
        try {
            SocketChannel socketChannel = SocketChannel.open();
            if (socketChannel.isOpen()) {
                socketChannel.configureBlocking(true);
                socketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 128 * 1024);
                socketChannel.setOption(StandardSocketOptions.SO_SNDBUF, 128 * 1024);
                socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
                socketChannel.setOption(StandardSocketOptions.SO_LINGER, 5);
                //连接服务端
                socketChannel.connect(new InetSocketAddress("127.0.0.1", 8087));
                if (socketChannel.isConnected()) {
                    //向服务端发送数据
                    socketChannel.write(helloBuffer);
                    //创建接收服务端返回数据ByteBuffer
                    ByteBuffer resBuffer = ByteBuffer.allocateDirect(1024);
                    while (socketChannel.read(resBuffer) != -1) {
                        System.out.println(socketChannel.read(resBuffer));
                        resBuffer.flip();
                        charBuffer = decoder.decode(resBuffer);
                        System.out.println(charBuffer.toString());
                        if (resBuffer.hasRemaining()) {
                            resBuffer.compact();
                        } else {
                            resBuffer.clear();
                        }
                    }
                }
                socketChannel.close();
                System.out.println("关闭连接");
            } else {
                throw new RuntimeException("connection cannot be established");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
