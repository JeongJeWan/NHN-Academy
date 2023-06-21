package org.nhnacademy.vendingmachine.pay.factory;

import org.nhnacademy.vendingmachine.pay.CashPayment;
import org.nhnacademy.vendingmachine.pay.Payment;

public class CashPaymentFactory implements PaymentFactory {


    @Override
    public Payment createPayment() {
        System.out.println("현금결제");
        return new CashPayment();
    }
}
