package com.example.produce_consumer;

/**
 * Created by colin on 15-11-25.
 */
public class ProduceConsumer {
    public static void main(String[] args) {
        Storage storage = new Storage();

        new Producer(storage, 10).start();
        new Producer(storage, 20).start();
        new Producer(storage, 30).start();
        new Producer(storage, 60).start();

        new Consumer(storage, 30).start();
        new Consumer(storage, 50).start();


    }
}
