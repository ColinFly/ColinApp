package com.example.java_io;

/**
 * Created by colin on 16-1-16.
 */
public class ByteUtilsTest {
    public static void main(String[] args) {
        short s = 122;
        int i = 122;
        long l = 1222222;
        char c = 'a';
        float f = 122.22f;
        double d = 122.22;
        String string = "我是好孩子";

        System.out.println(s);
        System.out.println(i);
        System.out.println(l);
        System.out.println(c);
        System.out.println(f);
        System.out.println(d);
        System.out.println(string);

        System.out.println("**************");

        System.out.println(ByteUtil.getShort(ByteUtil.getBytes(s)));
        System.out.println(ByteUtil.getInt(ByteUtil.getBytes(i)));
        System.out.println(ByteUtil.getLong(ByteUtil.getBytes(l)));
        System.out.println(ByteUtil.getChar(ByteUtil.getBytes(c)));
        System.out.println(ByteUtil.getFloat(ByteUtil.getBytes(f)));
        System.out.println(ByteUtil.getDouble(ByteUtil.getBytes(d)));
        System.out.println(ByteUtil.getString(ByteUtil.getBytes(string)));
    }
}
