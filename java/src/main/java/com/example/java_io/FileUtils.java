package com.example.java_io;

import java.io.File;
import java.io.IOError;
import java.io.IOException;

/**
 * Created by colin on 16-1-15.
 * 列出File类的常用操作
 * 1.过滤,遍历等操作
 */
public class FileUtils {
    /**
     * 列出指定目录下(包括子目录)的所有文件
     */
    public static void listDirectory(File dir) throws IOException {
        if (!dir.exists()) {
            throw new IllegalArgumentException("目录:"+dir+"不存在");
        }
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(dir+"不是目录");
        }
       /* String[] fileName=dir.list();//返回当前目录下的子目录及文件
        for (String str : fileName) {
            System.out.println(dir+"/"+str);
        }*/
        //
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    //递归(对返回的File对象做递归操作)
                    listDirectory(file);
                } else {
                    System.out.println(file);
                }
            }
        }
    }
}
