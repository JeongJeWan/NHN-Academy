package org.nhnacademy.vendingmachine;

import org.nhnacademy.vendingmachine.beverage.factory.AmericanoFactory;
import org.nhnacademy.vendingmachine.beverage.factory.CafeLatteFactory;
import org.nhnacademy.vendingmachine.beverage.factory.GreenTeaFactory;
import org.nhnacademy.vendingmachine.beverage.type.Temperature;
import org.nhnacademy.vendingmachine.machine.HotMachine;
import org.nhnacademy.vendingmachine.machine.IceMachine;
import org.nhnacademy.vendingmachine.pay.factory.CardPaymentFactory;
import org.nhnacademy.vendingmachine.pay.factory.CashPaymentFactory;
import org.nhnacademy.vendingmachine.pay.factory.OnlinePaymentFactory;
import org.nhnacademy.vendingmachine.pay.factory.PaymentFactory;


public class Main {
    public static void main(String[] args) {

        AmericanoFactory americanoFactory = new AmericanoFactory();
        CafeLatteFactory cafeLatteFactory = new CafeLatteFactory();
        PaymentFactory paymentFactory = new CardPaymentFactory();

        System.out.println("========= 따듯한 아메리카노 =========");
        HotMachine hotAmeMachine = new HotMachine(paymentFactory);
        hotAmeMachine.setBeverageFactory(americanoFactory);
        hotAmeMachine.machineMovement(Temperature.HOT);

        System.out.println("========= 따듯한 카페라떼 =========");
        HotMachine hotLatMachine = new HotMachine(paymentFactory);
        hotLatMachine.setBeverageFactory(cafeLatteFactory);
        hotLatMachine.machineMovement(Temperature.HOT);

        paymentFactory = new CashPaymentFactory();
        System.out.println("========= 시원한 아메리카노 =========");
        paymentFactory = new CashPaymentFactory();
        IceMachine iceAmeMachine = new IceMachine(paymentFactory);
        iceAmeMachine.setBeverageFactory(americanoFactory);
        iceAmeMachine.machineMovement(Temperature.COOL);

        /*
         * 녹차 추가
         * fatory 패키지에서 GreenFoactory 를 추가
         * nonecoffee에 Greentea를 추가 하면
         * 녹차 생성 끝
         */

        GreenTeaFactory greenTeaFactory = new GreenTeaFactory();
        paymentFactory = new OnlinePaymentFactory();
        System.out.println("========= 시원한 녹차 =========");
        HotMachine hotGreMacnine = new HotMachine(paymentFactory);
        hotGreMacnine.setBeverageFactory(greenTeaFactory);
        hotGreMacnine.machineMovement(Temperature.COOL);

    }
}
