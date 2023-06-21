package org.nhnacademy.vendingmachine.pay;

public class OnlinePayment implements Payment {
    private int price;
    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public boolean pay(int price) {
        this.price = price;
        return true;
    }
}
