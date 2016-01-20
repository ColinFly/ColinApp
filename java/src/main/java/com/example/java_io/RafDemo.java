package com.example.java_io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by colin on 16-1-15.
 */
public class RafDemo {
    public static void main(String[] args) throws IOException {
        File demo=new File("demo");//默认会在此工程下的一级目录创建
        if (!demo.exists()) {
            demo.mkdir();
        }
        File file = new File(demo, "raf.dat");//demo作为父目录
        if (!file.exists()) {
            file.createNewFile();
        }

        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        //指针的位置
        System.out.println(raf.getFilePointer());
        raf.write('A');//一个char是两个字节,这里只写了一个字节(后8位)
        System.out.println(raf.getFilePointer());
        raf.write('B');
        System.out.println(raf.getFilePointer());

        int i = 0x7fffffff;
        //用write方法一次写一个字节
        raf.write(i >>> 24);//先写高8位
        raf.write(i >>> 16);
        raf.write(i >>> 8);
        raf.write(i);//依次写入
        System.out.println(raf.getFilePointer());
    }
}
