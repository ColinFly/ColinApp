package com.example.multi_thread;

/**
 * Created by colin on 15-12-14.
 */
public class HeroThread extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始了战斗");
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"骁勇善战");
        }
        System.out.println(Thread.currentThread().getName()+"结束了战斗");
    }
}
