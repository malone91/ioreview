package com.melo.nio;

import java.nio.CharBuffer;

/**
 *
 * @author 76009
 * @date 2018/7/22
 */
public class BufferDemo {

    public static void main(String[] args) {
        String content = "hello everyone, haha";
        CharBuffer buffer = CharBuffer.allocate(50);
        //将字符串内容写入buffer
        for (int i = 0; i < content.length(); i++) {
            buffer.put(content.charAt(i));
        }
        //反转Buffer，准备读取Buffer内容
        buffer.flip();
        //读取buffer中的数据
        while (buffer.hasRemaining()) {
            System.out.print(buffer.get());
        }
        //倒回读取之前，准备再次读取
        buffer.rewind();
        System.out.println();
        //读取buffer中的数据
        while (buffer.hasRemaining()) {
            System.out.print(buffer.get());
        }
        System.out.println();
        //清空buffer，复位position，buffer可以再次使用
        buffer.clear();
        buffer.put("你").put("j");
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.print(buffer.get());
        }
    }
}
