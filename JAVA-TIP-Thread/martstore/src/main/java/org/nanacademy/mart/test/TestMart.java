package org.nanacademy.mart.test;


public class TestMart {
    public static void main(String[] args) throws InterruptedException {
        Mart mart = new Mart(5);
        Buyer[] buyers = new Buyer[40];
    
        mart.start();
        Store[] stores = mart.getStores();
        Thread.sleep(1000); // Store 생성전에 접근하는 것을 막기 위해 텀을 줌
        for (int i = 0; i < buyers.length; i++) {
            Store store = stores[i % stores.length];
            buyers[i] = new Buyer("손님 " + i, store);
            buyers[i].start();
        }
    }
}
