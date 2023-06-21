package org.nanacademy.mart;


import java.util.concurrent.ThreadLocalRandom;

public class Seller extends Thread{
    Store store;

    public Seller(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            try {
                store.sell();
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            }catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
