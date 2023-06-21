package org.nhnacademy.vendingmachine.machine;

import org.nhnacademy.vendingmachine.beverage.Beverage;
import org.nhnacademy.vendingmachine.cup.BasicCup;
import org.nhnacademy.vendingmachine.cup.PaperCup;
import org.nhnacademy.vendingmachine.pay.factory.PaymentFactory;

public class HotMachine extends BasicMachine{
    public HotMachine(PaymentFactory paymentFactory) {
        super(paymentFactory);
    }

    @Override
    BasicCup takeCup() {
        System.out.println("종이컵을 받습니다.");
        return new PaperCup();
    }

    @Override
    void putIce(BasicCup basicCup) {

    }

    @Override
    void beveragePutCup(BasicCup basicCup, Beverage beverage) {
        basicCup.putBeverage(beverage);
        System.out.println("음료를 받습니다.");
    }
}
