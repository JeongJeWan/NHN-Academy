package org.nanacademy.mart;

import java.util.Scanner;

class StoreTest {
    public static void main(String[] args) {

        System.out.print("품목 메장 개수 입력 : ");
        Store[] stores = new Store[new Scanner(System.in).nextInt()];
        Buyer[] buyers = new Buyer[5];

        for(int i=0; i < stores.length; i++) {
            stores[i] = new Store((i+1) + "th STORE");
            Seller seller = new Seller(stores[i]);
            seller.start();
            for(int j=0; j < buyers.length; j++) {
                buyers[j] = new Buyer("손님"+(j+1), stores[j]);
                buyers[j].start();
            }
        }

    }
}