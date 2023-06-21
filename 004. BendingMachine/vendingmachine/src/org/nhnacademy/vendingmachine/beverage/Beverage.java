package org.nhnacademy.vendingmachine.beverage;

import org.nhnacademy.vendingmachine.beverage.type.Temperature;

public interface Beverage {

    String getName();
    int getPrice();
    Temperature getTemperature();

}
