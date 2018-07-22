package com.melo.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用Channel的read与write方法实现文件的复制
 * @author 76009
 * @date 2018/7/22
 */
public class FileChannelDemo2 {

    public static void main(String[] args) throws IOException {
        //从输入流中获取文件通道
        FileInputStream inputStream = new FileInputStream("E:/src.txt");
        FileChannel channel = inputStream.getChannel();
        FileOutputStream outputStream = new FileOutputStream("E:/target.txt");
        FileChannel fileChannel = outputStream.getChannel();

        //从文件读取内容buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int byteRead = channel.read(buffer);
        while (byteRead != -1) {
            //翻转buffer，为下面的读取做准备
            buffer.flip();
            while (buffer.hasRemaining()) {
                fileChannel.write(buffer);
            }
            //复位buffer
            buffer.clear();
            byteRead = channel.read(buffer);
        }

        inputStream.close();
        channel.close();
        outputStream.close();
        fileChannel.close();
    }
}
