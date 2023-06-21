package org.nhnacademy.vendingmachine.pay;

public interface Payment {
    int getPrice();
    boolean pay(int price);
}
