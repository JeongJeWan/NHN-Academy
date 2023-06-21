package org.nanacademy.mart.test;

import java.util.concurrent.ThreadLocalRandom;

public class Store {
    private String name;
    int numberOfProducts;
    MySemaphore capacity;
    MySemaphore canBuy;

    public Store(String name) {
        numberOfProducts = 0;
        capacity = new MySemaphore(5); // 최대 대기인원
        canBuy = new MySemaphore(1); // 한번에 계산할 수 있는 인원
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void enter() throws InterruptedException {
        try {
            capacity.acquire();
            System.out.println("*Buyer Log : " + Thread.currentThread().getName() + " 입장 -> " + getName() + " Store");
        } catch (InterruptedException | IllegalMonitorStateException e) {
            throw e;
        }
    }

    public void exit() throws IllegalMonitorStateException {
        capacity.release();
        System.out.println("*Buyer Log : " + getName() + " Store -> " + Thread.currentThread().getName() + " 퇴장");
    }

    public synchronized void buy() {
        try {
            while (numberOfProducts == 0) {
                System.out.println("*Buyer Log : " + getName() + " Store : " + Thread.currentThread().getName() + " 구매 대기...");
                wait(); // 납품되기전까지 wait
            }
            // 한 매장당 한명씩 계산
            canBuy.acquire();
            --numberOfProducts;
            Thread.sleep(ThreadLocalRandom.current().nextLong(1_000, 10_000));
            System.out.println("*Buyer Log : " + getName() + " Store : " + Thread.currentThread().getName() + " 결제 완료!");
            notify(); // 납품대기중인 생산자 awake
            canBuy.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void sell() {
        while (numberOfProducts >= 10) {
            try {
                System.out.println("----Seller  Log : " + getName() + " Store : " + "납품 대기...----");
                wait(); // 구매하기전까지 wait
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("----Seller Log : " + getName() + " Store : " + "납품 완료! (현재 수량 : " + ++numberOfProducts + ")----");
        notify(); // 구매대기중인 소비자 awake
    }
}
