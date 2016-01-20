package com.example.java_io;

/**
 * java中的二进制
 * Created by colin on 16-1-16.
 * 概念：java中用补码表示二进制数，补码的最高位是符号位，最高位为“0”表示正数，最高位为“1”表示负数。
 * <p/>
 * 例如：
 * +21，其二进制表示形式是00010101，则其补码同样为00010101
 * -21，按照概念其绝对值为00010101，各位取反为11101010，再加1为11101011，即-21的二进制表示形式为11101011
 * <p/>
 * <p/>
 * 注意:当字长为8位时，(-128)没有相对应的原码和反码
 * -128的机器码是多少，原码110000000，反码101111111，补码110000000，截取低8位即10000000，表示的是一个负数。)
 * <p/>
 * byte是一个字节保存的，有8个位，即8个0、1。
 * 8位的第一个位是符号位，
 * 也就是说0000 0001代表的是数字1
 * 1000 0000代表的就是-1
 * 所以正数最大位0111 1111，也就是数字127
 * 负数最大为1111 1111，也就是数字-128
 * <p/>
 * 1、反码：
 * 一个数如果是正，则它的反码与原码相同；
 * 一个数如果是负，则符号位为1，其余各位是对原码取反；!!!!!!!!!!(负数的反码是在其原码的基础上, 符号位不变，其余各个位取反.)
 * <p/>
 * 原码----(取反加1)--->补码
 * 补码----(减1取反)--->补码(10000000减1得01111111)
 * <p/>
 * &0xff是只取低八位
 * <p/>
 * [+1] = [00000001]原 = [00000001]反 = [00000001]补
 * [-1] = [10000001]原 = [11111110]反 = [11111111]补
 *
 * 为什么计算机的二进制要用补码来表示:因为计算机只有加法器
 * 所以补码的设计目的是:
 * ⑴使符号位能与有效值部分一起参加运算,从而简化运算规则.
 * ⑵使减法运算转换为加法运算,进一步简化计算机中运算器的线路设计
 */


public class Binary {
    public static void main(String[] args) {
        int i = 0x7fffffff;//16进制表示int最大整数
        System.out.println(i);
        int i2 = 0b01111111111111111111111111111111;//二进制表示int最大整数
        System.out.println(i2);

        byte byte1 = 127;
        System.out.println(Integer.toBinaryString(byte1));//正数的补码是其本身
        byte byte2 = -128;//机器码是110000000(9位哦！)
        System.out.println(Integer.toBinaryString(byte2));
        System.out.println(Integer.toBinaryString(byte2 & 0xff));
    }
}
