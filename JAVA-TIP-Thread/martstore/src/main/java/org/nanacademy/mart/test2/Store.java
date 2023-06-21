package org.nanacademy.mart.test2;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Store {
    Semaphore tickets;
    int[] things = new int[5];
    List buyList = new ArrayList<Integer>();

    public Store() {
        tickets = new Semaphore(5);

    }

    public void enter() {
        try {
            tickets.acquire();
            System.out.println(Thread.currentThread().getName() + " 입장");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void exit() {
        System.out.println(Thread.currentThread().getName() + "퇴장");
        tickets.release();
    }

    public synchronized void buy(int nThings) throws InterruptedException {
        while ( cThings() == 0 || things[nThings] <= 0 || Buying(nThings)) {
            try {
                System.out.println(Thread.currentThread().getName() + " 구매 대기");
                wait();     // 살게 없으면 기다림
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            buyList.add(nThings);

            System.out.println(Thread.currentThread().getName() + " " + nThings + "번째 품목 구매 완료");
            --things[nThings];

            for(int i=0; i < things.length; i ++){
                System.out.println((i + 1) + "남은 품목 개수 : " +things[i]);
            }

            if(buyList.indexOf(nThings)!= -1){
                buyList.remove(buyList.indexOf(nThings));
            }
            Thread.sleep(100);
        } catch (InterruptedException ignore) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void sell() {
        while ( cThings() >= 10 * things.length ) {       // 최대 10개 물건만 전시 가능
            try {
                System.out.println("납품 대기 중");
                wait();     // 10개를 넘었으면 더 납품 못 함 -> 납품 대기
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for(int i = 0; i < ThreadLocalRandom.current().nextInt(1 , things.length); i++){
            ++things[ThreadLocalRandom.current().nextInt(0 , things.length)];
        }

        for(int i=0; i < things.length; i ++){
            System.out.println((i + 1) + "납품한 품목 개수 : " + things[i]);
        }
        System.out.println("납품 완료");
        notifyAll();
    }

    public boolean Buying(int itemNumber){
        return buyList.contains(itemNumber);
    }

    public int cThings(){
        int count = 0;

        for(int thing : things){
            count += thing;
        }
        return count;
    }
}
