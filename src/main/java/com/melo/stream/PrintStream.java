package com.melo.stream;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 常用于日志输出组件的实现，无需抛出IO异常的场景
 * @author 76009
 * @date 2018/7/22
 */
public class PrintStream {

    public static void main(String[] args) {
        File file = new File("E:/src.txt");
        java.io.PrintStream printStream = null;
        try {
            printStream = new java.io.PrintStream(file);
            //将内容写入文件
            printStream.print("你好啊haa");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (printStream != null) {
                printStream.close();
            }
        }
    }
}
