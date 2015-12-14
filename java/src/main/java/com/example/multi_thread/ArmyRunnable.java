package com.example.multi_thread;

/**
 * Created by colin on 15-12-14.
 */
public class ArmyRunnable implements Runnable {

    //保证这个keepRunning的值永远是最新的，即线程的可见性
    public volatile boolean keepRunning=true;

    @Override
    public void run() {
        while (keepRunning) {
            //发动5连击
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"攻击了"+"["+i+"]");

                Thread.yield();//让出处理器时间，下次谁进攻还不一定
            }
        }
        System.out.println(Thread.currentThread().getName()+"结束了战斗");
    }
}
