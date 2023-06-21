package org.nhnacademy.vendingmachine.pay.factory;

import org.nhnacademy.vendingmachine.pay.CardPayment;
import org.nhnacademy.vendingmachine.pay.Payment;

public class CardPaymentFactory implements PaymentFactory {
    @Override
    public Payment createPayment() {
        System.out.println("카드결제");
        return new CardPayment();
    }
}
