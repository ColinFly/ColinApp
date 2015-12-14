package com.example.multi_thread;

/**
 * Created by colin on 15-12-14.
 * 测试线程
 */
public class Actor extends Thread {

    @Override
    public void run() {
        System.out.println(getName()+"是一个演员");
        int count = 0;
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println(getName()+"演出"+(++count)+"次");
            if (count == 100) {
                keepRunning = false;
            }
            if (count % 10 == 0) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Actor().start();
    }
}
