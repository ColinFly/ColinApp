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

        int i = 0x7fffffff;//int最大整数
        System.out.println(i);
        int i2 = 0b01111111111111111111111111111111;
        System.out.println(i2);
        //byte取值范围 -128~127
//        1、byte为一字节8位，最高位是符号位，即最大值是01111111，因正数的补码是其本身，即此正数为01111111
//                十进制表示形式为127
//        2、最大正数是01111111，那么最小负是10000000(最大的负数是11111111，即-1)
        byte byte1 = (byte) 11111111;
        System.out.println(byte1);
    }
}
