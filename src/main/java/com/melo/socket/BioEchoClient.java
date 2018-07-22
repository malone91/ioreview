package com.melo.socket;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author 76009
 * @date 2018/7/22
 */
public class BioEchoClient {

    public static void main(String[] args) {
        int port = 8082;
        String serverIP = "127.0.0.1";
        Socket socket = null;
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            //创建Socket对象，并连接到远程主机
            socket = new Socket(serverIP, port);
            //建立连接后从socket得到输入流与输出流，可以使用这两个流与服务器之间相互发送数据
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //向服务器发送数据
            writer.write("hello block IO.\n");
            writer.flush();
            //获取服务端返回的数据
            String echo = reader.readLine();
            System.out.println("echo: " + echo);
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
