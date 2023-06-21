package org.nanacademy.mart.test2;

import java.util.concurrent.ThreadLocalRandom;

public class Seller extends Thread {
    Store store;

    public Seller(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            try {
                // 연습 8-2
                store.sell();    //매장에 물건이 비워지지 않도록 채워둠
                Thread.sleep(ThreadLocalRandom.current().nextLong(100, 1000));
            } catch ( InterruptedException e ) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
