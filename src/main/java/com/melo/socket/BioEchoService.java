package com.melo.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author 76009
 * @date 2018/7/22
 */
public class BioEchoService {

    //服务端业务处理逻辑线程池
    private static final ExecutorService excutor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        int port = 8082;
        ServerSocket serverSocket = null;
        try {
            //绑定一个特定的端口创建ServerSocket对象
            serverSocket = new ServerSocket(port);
            Socket socket = null;
            while (true) {
                //使用ServerSocket的accept方法监听这个方法的请求连接
                socket = serverSocket.accept();
                excutor.submit(new BioEchoServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
