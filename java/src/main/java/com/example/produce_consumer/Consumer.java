package com.example.produce_consumer;

/**
 * Created by colin on 15-11-25.
 * 负责消费Storage的资源
 */
public class Consumer extends Thread {
    private int num;
    private Storage storage;
    public Consumer(Storage storage,int num) {
        this.storage = storage;
        this.num = num;
    }

    @Override
    public void run() {
        consume(num);
    }

    private void consume(int num) {
        storage.consume(num);
    }
}
