package com.example.multi_thread;

/**
 * Created by colin on 15-12-14.
 */
public class StageThread extends Thread {
    @Override
    public void run() {
        ArmyRunnable armyA=new ArmyRunnable();
        ArmyRunnable armyB=new ArmyRunnable();

        //使用Runnable接口创建线程
        Thread threadA=new Thread(armyA,"军队A");
        Thread threadB = new Thread(armyB, "军队B");

        //启动线程
        threadA.start();
        threadB.start();

        try {
            //过一段时间再停止战斗
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("半路杀出个程咬金！！！！！");
        Thread mrCheng = new HeroThread();
        mrCheng.setName("程咬金");

        armyA.keepRunning = false;
        armyB.keepRunning = false;
//        threadA.stop();
//        threadB.stop();//军队战争还没结束，就被强制stop了，是暴力的
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mrCheng.start();
        try {
            mrCheng.join();//保证其执行完毕！！
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("战争结束，请各自离场！");
//
//        try {
//            threadB.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) {
        new StageThread().start();
    }
}
