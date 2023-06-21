package org.nanacademy.mart.test;

import java.util.concurrent.ThreadLocalRandom;

public class Seller extends Thread {
    private Store[] stores; // 공유 자원

    public Seller(int numberOfProducts) {
        this.stores = new Store[numberOfProducts];
    }

    // 판매자가 품목을 결정한다.
    private synchronized void createStores() {
        for (int i = 0; i < stores.length; i++) {
            stores[i] = new Store(String.valueOf((char) (65 + i)));
        }
    }

    public Store[] getStores() {
        return stores;
    }

    @Override
    public void run() {
        createStores();
        while (!Thread.interrupted()) {
            try {
                // 판매자는 모든 매장에 랜덤으로 부여된 갯수만큼 품목을 전달한다.
                for (Store store : stores) {
                    int numberOfNewProducts = ThreadLocalRandom.current().nextInt(5);
                    for (int i = 0; i < numberOfNewProducts; i++) {
                        store.sell();
                    }
                }
                Thread.sleep(ThreadLocalRandom.current().nextLong(1_000, 10_000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}