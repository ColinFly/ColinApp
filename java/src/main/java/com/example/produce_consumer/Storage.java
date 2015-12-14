package com.example.produce_consumer;

import java.util.LinkedList;

/**
 * Created by colin on 15-11-25.
 */
public class Storage {
    private final int MAX = 100;//仓储最大值
    private LinkedList<Object> list = new LinkedList<>();//模拟仓储

    public void produce(int num) {
        synchronized (list) {//锁住仓储
            while (list.size() + num > MAX) {
                System.out.println("【要生产的产品数量】:" + num + "\t【库存量】:"
                        + list.size() + "\t暂时不能执行生产任务!");
                try {
                    list.wait();//放弃锁，使自己处于等等状态，让其他线程执行。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < num; i++) {
                list.add(new Object());
            }
            System.out.println("【已经生产产品数】:" + num + "\t【现仓储量为】:" + list.size());
            list.notifyAll();//向其他等待的线程发出可执行的通知，同时放弃锁，使自己处于等待状态。
        }
    }

    public void consume(int num) {
        synchronized (list) {
            while (list.size() < num) {
                System.out.println("【要消费的产品数量】:" + num + "\t【库存量】:"
                        + list.size() + "\t暂时不能执行消费任务!");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i=0;i<num;i++) {
                list.remove();
            }
            System.out.println("【已经消费产品数】:" + num + "\t【现仓储量为】:" + list.size());

            list.notifyAll();
        }
    }


}
