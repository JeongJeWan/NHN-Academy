package org.nhnacademy.vendingmachine.machine;

import org.nhnacademy.vendingmachine.beverage.Beverage;
import org.nhnacademy.vendingmachine.beverage.factory.BeverageFactory;
import org.nhnacademy.vendingmachine.beverage.type.Temperature;
import org.nhnacademy.vendingmachine.cup.BasicCup;
import org.nhnacademy.vendingmachine.cup.PlasticCup;
import org.nhnacademy.vendingmachine.pay.CashPayment;
import org.nhnacademy.vendingmachine.pay.Payment;
import org.nhnacademy.vendingmachine.pay.factory.PaymentFactory;

public abstract class BasicMachine {
    private Payment payment;
    private BeverageFactory beverageFactory;
    
    protected BasicMachine(PaymentFactory paymentFactory) {
        this.payment = paymentFactory.createPayment();
    }

    public void setBeverageFactory(BeverageFactory beverageFactory) {
        this.beverageFactory = beverageFactory;
    }

    private boolean pay() {
        payment.pay(27000);
        if(payment.getPrice() < 1000) {
            System.out.println("결제실패!");
            return  false;
        }
        if(payment instanceof CashPayment) {
            CashPayment.changeCash(payment.getPrice() - 1500);
        }
        System.out.println("결제성공!");
        return true;
    }

    private Beverage makeBeverage(Temperature temperature) {
        Beverage beverage = null;
        if(temperature == Temperature.HOT) {
            beverage = beverageFactory.makeHotBeverage();
        } else {
            beverage = beverageFactory.makeIceBeverage();
        }
        return beverage;
    }

    abstract BasicCup takeCup();
    abstract void putIce(BasicCup basicCup);
    abstract void beveragePutCup(BasicCup basicCup, Beverage beverage);

    public final void machineMovement(Temperature temperature) {
        if(pay()) {
            BasicCup cup = takeCup();
            if(cup instanceof PlasticCup) {
                putIce(cup);
            }
            Beverage beverage = makeBeverage(temperature);
            beveragePutCup(cup, beverage);
        }
        System.out.println("종료합니다.");
    }
}
