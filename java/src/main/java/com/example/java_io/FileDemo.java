package com.example.java_io;

import java.io.File;
import java.io.IOException;

/**
 * Created by colin on 16-1-15.
 * 了解File的Api
 */
public class FileDemo {
    public static void main(String[] args) throws IOException {
        File file=new File(".app/mooc");//window下是\个分隔符所以需要加一个\作为转义字符  eg:E:\javaio\-->E:\\javaio\\..
        System.out.println(file.exists());
        if (!file.exists()) {
            file.mkdir();//mkdirs()多级目录的创建
        }
        File file2 = new File("./app/mooc/test.txt");
        if (!file2.exists()) {
            file2.createNewFile();
        }
        System.out.println(file2);//相当于file.toString～./app/mooc/test.txt
        System.out.println(file2.getAbsolutePath());
        System.out.println(file2.getAbsoluteFile());
        System.out.println(file2.getParent());
        System.out.println(file2.getName());
        System.out.println(file2.canWrite());
    }
}
