package com.melo.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 文件读写
 * @author 76009
 * @date 2018/7/22
 */
public class FileChannelDemo1 {

    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("E:/src.txt");
        //从输入流中获取源文件的通道
        FileChannel channel = inputStream.getChannel();
        FileOutputStream outputStream = new FileOutputStream("E:/target.txt");
        FileChannel outputStreamChannel = outputStream.getChannel();
        //使用transferTo API 将文件写入
        channel.transferTo(0, channel.size(), outputStreamChannel);

        inputStream.close();
        channel.close();
        outputStream.close();
        outputStreamChannel.close();
    }

}
