package com.example.java_io;

import java.io.UnsupportedEncodingException;

/**
 * utf-8
 * Created by colin on 16-1-15.
 * utf-8下中文3字节,gbk下中文2字节,英文都是一个字节
 * 1byte~8bit~需要用2个16进制表示～1个byte
 * 1int～4byte
 * 思考时从大大小的分解，例如:1个int~4个byte~8个16进制
 * 要取多少位就与上多少个0xff
 */
public class EncodeDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "慕课ABC";
        byte[] bytes1 = str.getBytes();
        for (byte b : bytes1) {
            //字节转int且以16进制的方式显示
//            System.out.println(b );
//            System.out.println(b & 0xff);
            System.out.print(Integer.toHexString(b & 0xff) + "  ");//这样就干掉了前面三个字节(都是0)
            //慕
//            ffffffe6（4字节）
//            ffffff85（4字节）
//            ffffff95（4字节）
            //课
//            ffffffe8（4字节）
//            ffffffaf（4字节）
//            ffffffbe（4字节）

//            41
//            42
//            43
        }

        System.out.println();
        byte[] bytes2 = str.getBytes("gbk");
        for (byte b : bytes2) {
            System.out.print(Integer.toHexString(b & 0xff) + "  ");
        }


        System.out.println();
        //java是双字节编码utf-16be
        //无论中文还是英文都是两个字节
        byte[] bytes3 = str.getBytes("utf-16be");
        for (byte b : bytes3) {
            System.out.print(Integer.toHexString(b & 0xff) + "  ");
        }

        System.out.println();
        //byte转字符串
        System.out.println(new String(bytes3));//默认是utf-8
        System.out.println(new String(bytes3));//默认是utf-8
        System.out.println(new String(bytes3,"utf-16be"));

        //中文机器上创建文本文件将默认为ANSI编码
    }
}
