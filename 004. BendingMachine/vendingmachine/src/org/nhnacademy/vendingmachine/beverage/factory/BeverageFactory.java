package org.nhnacademy.vendingmachine.beverage.factory;

import org.nhnacademy.vendingmachine.beverage.Beverage;

public interface BeverageFactory {
    Beverage makeHotBeverage();
    Beverage makeIceBeverage();
}
