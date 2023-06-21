package org.nhnacademy.vendingmachine.pay.factory;

import org.nhnacademy.vendingmachine.pay.OnlinePayment;
import org.nhnacademy.vendingmachine.pay.Payment;

public class OnlinePaymentFactory implements PaymentFactory{
    @Override
    public Payment createPayment() {
        System.out.println("온라인결제");
        return new OnlinePayment();
    }
}
