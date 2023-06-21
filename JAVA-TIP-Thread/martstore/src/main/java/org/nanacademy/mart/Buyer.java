package org.nanacademy.mart;


import java.util.concurrent.ThreadLocalRandom;

public class Buyer extends Thread{

    Store store;

    public Buyer(String name, Store store) {
        super(name);
        this.store = store;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            store.enter();
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 10000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            store.buy();

            store.exit();
        }
    }
}
