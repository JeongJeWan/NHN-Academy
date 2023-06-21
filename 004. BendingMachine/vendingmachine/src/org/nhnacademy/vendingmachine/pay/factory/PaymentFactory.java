package org.nhnacademy.vendingmachine.pay.factory;

import org.nhnacademy.vendingmachine.pay.Payment;

public interface PaymentFactory {
    Payment createPayment();
}
