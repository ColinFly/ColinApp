package com.example.produce_consumer;

/**
 * Created by colin on 15-11-25.
 * 负责生产Storage的资源
 */
public class Producer extends Thread{
    private int num;
    private Storage storage;


    public Producer(Storage storage, int num) {
        this.num = num;
        this.storage = storage;
    }

    @Override
    public void run() {
        produce(num);
    }

    private void produce(int num) {
        storage.produce(num);
    }
}
