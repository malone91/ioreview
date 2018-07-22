package com.melo.stream;

import java.io.*;

/**
 * 使用BufferedStream提高IO性能与操作效率。
 * 使用了装饰器模式，在fileStream基础上增加了字节流缓存能力，使得对字节数组的读取写入能够批量进行。
 * @author 76009
 * @date 2018/7/22
 */
public class BufferedStream {

    public static void main(String[] args) {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        //定义源文件与目标文件
        File sourceFile = new File("E:/src.txt");
        File targetFile = new File("E:/target.txt");
        //实例化文件输入流与输出流
        try {
            inputStream = new FileInputStream(sourceFile);
            bufferedInputStream = new BufferedInputStream(inputStream);

            outputStream = new FileOutputStream(targetFile);
            bufferedOutputStream = new BufferedOutputStream(outputStream);

            //通过缓冲输入流读取源文件内容，并写入缓冲输出流，最终写入文件
            byte[] buff = new byte[1024];
            int byt;
            while ((byt = bufferedInputStream.read(buff, 0, buff.length)) != -1) {
                bufferedOutputStream.write(buff, 0, byt);
            }
            bufferedOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
