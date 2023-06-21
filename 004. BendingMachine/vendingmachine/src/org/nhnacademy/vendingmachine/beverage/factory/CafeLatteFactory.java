package org.nhnacademy.vendingmachine.beverage.factory;

import org.nhnacademy.vendingmachine.beverage.Beverage;
import org.nhnacademy.vendingmachine.beverage.coffee.CafeLatte;

public class CafeLatteFactory implements BeverageFactory{
    @Override
    public Beverage makeHotBeverage() {
        return CafeLatte.hotCafeLatte();
    }

    @Override
    public Beverage makeIceBeverage() {
        return CafeLatte.iceCafeLatte();
    }
}
