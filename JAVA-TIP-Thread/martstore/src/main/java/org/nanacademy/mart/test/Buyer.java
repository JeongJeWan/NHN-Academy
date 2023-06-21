package org.nanacademy.mart.test;

import java.util.concurrent.ThreadLocalRandom;

public class Buyer extends Thread {
    Store store;

    public Buyer(String name, Store store) {
        super(name);
        this.store = store;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                store.enter();
                store.buy();
                store.exit();
                Thread.sleep(ThreadLocalRandom.current().nextLong(100, 500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
