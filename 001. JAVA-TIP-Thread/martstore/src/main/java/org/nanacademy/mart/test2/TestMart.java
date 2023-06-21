package org.nanacademy.mart.test2;


public class TestMart {
    public static void main(String[] args) {
        Store store = new Store();
        Seller seller = new Seller(store);
        Buyer[] buyers = new Buyer[10]; // 손님 10명 만들어서 시작

        for ( int i = 0; i < buyers.length; i++) {
            buyers[i] = new Buyer("손님" + i, store);
            buyers[i].start();
        }

        seller.start();
    }
}
