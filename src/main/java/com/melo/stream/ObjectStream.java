package com.melo.stream;

import java.io.*;

/**
 * 常用于Java对象的序列化与反序列化或者网络数据的写入与读取
 * @author 76009
 * @date 2018/7/22
 */
public class ObjectStream {

    public static void main(String[] args) {
        User user = new User();
        user.setEmail("haha");
        user.setName("melo");
        //将User序列化到文件
        try {
            FileOutputStream outputStream = new FileOutputStream("E:/user.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(user);
            objectOutputStream.close();
            outputStream.close();

            //从文件中读取
            FileInputStream inputStream = new FileInputStream("E:/user.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            User object = (User) objectInputStream.readObject();
            System.out.println(object.getEmail());
            System.out.println(object.getName());

            objectInputStream.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
