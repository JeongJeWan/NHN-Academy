package org.nanacademy.mart.test2;

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
                int cThings = ThreadLocalRandom.current().nextInt(0 , store.things.length);
                // 연습 8-3
                store.enter();  // 소비자 매장 입장 (알아서 기다림)
                store.buy(cThings);    // 물건 구매
                store.exit();   // 퇴장
                Thread.sleep(ThreadLocalRandom.current().nextLong(1000, 10000));    // 1~10초 간격 랜덤
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
