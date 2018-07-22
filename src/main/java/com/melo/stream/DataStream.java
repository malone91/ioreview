package com.melo.stream;

import java.io.*;

/**
 * 能够对Java原生类型直接写入再按写入顺序打印出Java原生类型数据直接读取，常用于网络数据传输过程中的写入与读取
 * @author 76009
 * @date 2018/7/22
 */
public class DataStream {

    public static void main(String[] args) {
        String fileName = "E:/src.txt";
        FileOutputStream outputStream = null;
        DataOutputStream dataOutputStream = null;
        try {
            //将Java原生类型数据通过DataOutputStream写入文件
            outputStream = new FileOutputStream(fileName);
            dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeInt(2018);
            dataOutputStream.writeUTF("melo");
            dataOutputStream.writeBoolean(false);

            //使用DataInputStream从文件中按照写入顺序读取Java原生类型数据
            FileInputStream inputStream = new FileInputStream(fileName);
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            System.out.println(dataInputStream.readInt());
            System.out.println(dataInputStream.readUTF());
            System.out.println(dataInputStream.readBoolean());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
