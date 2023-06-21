package org.nanacademy.mart;


/**
 * 동기화 관련 문제
 */
public class Store {

    String storeName;
    Semaphore tickets;  //  매장에 들어올 수 있는 티켓
    int boxCount;

    public Store(String storeName) {
        this.storeName = storeName;
        tickets = new Semaphore(5); //  티켓은 5개 밖에 없음
        boxCount = 0;
        System.out.println(storeName + " 생성!!!");
    }

    public void enter(){
        try {
            tickets.acquire();
            System.out.println(storeName + "  :  " + Thread.currentThread().getName() + " 입장");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        tickets.acquire();  //  티켓 받기, 못받으면 외부로 처리 ㅏ게 한다
    }

    public void exit(){

        try {
            tickets.release();
            System.out.println(storeName + "  :  " + Thread.currentThread().getName() + " 퇴장");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void buy() {    //  매장에서 물건 구매는 동시에 1명만 가능

        while(boxCount == 0) {
            try {
                System.out.println(storeName + "  :  " + Thread.currentThread().getName() + " -> 구매 대기");
                wait();
                Thread.sleep(3000);
            } catch (InterruptedException ignore) {

            }
        }

        --boxCount;
        System.out.println(storeName + "  :  " + Thread.currentThread().getName() + "가 구매 완료. 재고 : " + boxCount);

        notify();
    }

    public synchronized void sell() {   //  매장은 물건을 납품 받아 판매, 이때 물건은 최대 10개

        while (boxCount >= 10) {
            try {
                System.out.println(storeName + "  :  납품 대기 중입니다.");
                wait();
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }

        ++boxCount;
        System.out.println(storeName + "  :  납품 완료. 재고 : " + boxCount);

        notify();
    }
}
