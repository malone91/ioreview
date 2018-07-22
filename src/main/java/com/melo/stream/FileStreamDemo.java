package com.melo.stream;

import java.io.*;

/**
 * 专门用于文件IO操作，操作对象为字节流，更适用于图片等二进制文件操作
 * @author 76009
 * @date 2018/7/22
 */
public class FileStreamDemo {

    public static void main(String[] args) {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        //定义源文件与目标文件
        File sourceFile = new File("E:/src.txt");
        File targetFile = new File("E:/target.txt");
        //实例化文件输入流与文件输出流
        try {
            inputStream = new FileInputStream(sourceFile);
            outputStream = new FileOutputStream(targetFile);
            //通过文件输入流读取源文件内容，并写入目标文件
            int byt;
            while ((byt = inputStream.read()) != -1) {
                outputStream.write(byt);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
