package com.melo.stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * ByteArrayOutputStream 是OutputStream的子类
 * 其中子类还包括：ByteArrayOutputStream、FilterOutputStream、FileOutputStream、PipedOutputStream、ObjectOutputStream
 * InputStream同理
 * ByteArrayOutputStream不用关闭流
 * 将数据写入字节数组，随着数据的写入能够自动扩容
 *
 * 应用场景：
 *      使用ByteArrayOutputStream/ByteArrayInputStream可以将字符串或字节数组转换成输入/输出流。
 *      这种能力在很多场景下都是很有用处的，譬如，某个第三方API使用字节流对外输出数据，这个时候可以使用ByteArrayInputStream
 *      对象将获得的字节流暂时保存到内存，不必保存到磁盘。
 * @author 76009
 * @date 2018/7/22
 */
public class ByteArrayStreamDemo {

    public static void main(String[] args) {
        //将字符串转为子节点数组
        String content = "hello java I/O 卡尔";
        byte[] bytes = content.getBytes(Charset.forName("UTF-8"));
        //将字节数组转为字节输入流
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        //将字节输入流写入到字节输出流
        byte[] byteArray = new byte[1024];
        int size = 0;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            while ( (size = inputStream.read(byteArray)) != -1) {
                outputStream.write(byteArray, 0, size);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(outputStream.toString("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
