package com.melo.socket;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Callable;

/**
 *
 * @author 76009
 * @date 2018/7/22
 */
public class BioEchoServerHandler implements Runnable {

    private Socket socket;
    public BioEchoServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        //通过socket对象的getInputStream与getOutputStream方法获得与客户端通信的输入流与输出流
        try {
            reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            while (true) {
                //获取客户端连接
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                //返回给客户端的数据
                writer.write(line +"\n");
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
