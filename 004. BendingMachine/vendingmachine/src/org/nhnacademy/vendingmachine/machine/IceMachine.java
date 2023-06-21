package org.nhnacademy.vendingmachine.machine;

import org.nhnacademy.vendingmachine.beverage.Beverage;
import org.nhnacademy.vendingmachine.cup.BasicCup;
import org.nhnacademy.vendingmachine.cup.PlasticCup;
import org.nhnacademy.vendingmachine.pay.factory.PaymentFactory;

public class IceMachine extends BasicMachine{
    public IceMachine(PaymentFactory paymentFactory) {
        super(paymentFactory);
    }

    @Override
    BasicCup takeCup() {
        System.out.println("플라스틱 컵을 받습니다.");
        return new PlasticCup();
    }

    @Override
    void putIce(BasicCup basicCup) {
        System.out.println("컵에 얼음을 받습니다.");
    }

    @Override
    void beveragePutCup(BasicCup basicCup, Beverage beverage) {
        basicCup.putBeverage(beverage);
        System.out.println("음료를 받습니다.");
    }
}
