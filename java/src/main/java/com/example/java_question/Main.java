package com.example.java_question;

/**
 * Created by colin on 15-12-30.
 */
public class Main {
    public static void main(String[] args) {

//        printFibonacci();
//        printArmstrong();
        printPrimeNumber();
    }

    /**
     * 质数，又称素数，是只能被1或者自己整除的自然数。
     * 比1大但不是素数的数我们称之为合数，1和0即非素数也非合数
     * 最小的素数是2，而最大的素数并不存在，这一点欧几里德已在其《几何原本》中证明。
     * 题目：判断101-200之间有多少个素数，并输出所有素数。\
     * 程序分析：判断素数的方法：用一个数分别去除2到sqrt(这个数)，如果能被整除， 则表明此数不是素数，反之是素数。
     */
    private static void printPrimeNumber() {
        int count = 0;
        for (int i = 101; i < 200; i += 2) {
            boolean b = false;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    b = false;
                    break;
                } else {
                    b = true;
                }
            }
            if (b) {
                count++;
                System.out.println(i);
            }
        }
        System.out.println("素数个数是: " + count);
    }

    /**
     * 古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，
     * 小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？
     */
    private static void printFibonacci() {
        System.out.println("第1个月的兔子对数:    1");
        System.out.println("第2个月的兔子对数:    1");
        int f1 = 1, f2 = 1, f, M = 24;
        for (int i = 3; i <= M; i++) {
            f = f2;//将f2的值存一下
            f2 = f1 + f2;
            f1 = f;//f1后面也是在按f2的规律再增长
            System.out.println("第" + i + "个月的兔子对数: " + f2);
        }
    }

    /**
     * 题目：打印出所有的 "水仙花数 "，所谓 "水仙花数 "是指一个三位数，其各位数字立方和等于该数本身。例如：153是一个 "水仙花
     * 数 "，因为153=1的三次方＋5的三次方＋3的三次方。
     */
    private static void printArmstrong() {
        int g, s, b;
        for (int i = 100; i <= 999; i++) {
            //除权后取余
            g = i % 10;//-----153%10=3
            s = (i / 10) % 10;//----15%10=15
            b = (i / 100) % 10;//----153/100=1

            if (i == (g * g * g + s * s * s + b * b * b)) {
                System.out.println(i);
            }

        }
    }


}
