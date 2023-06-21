package org.nhnacademy.vendingmachine.beverage.factory;

import org.nhnacademy.vendingmachine.beverage.Beverage;
import org.nhnacademy.vendingmachine.beverage.coffee.Mocaccino;

public class MocaccinoFactory implements BeverageFactory{
    @Override
    public Beverage makeHotBeverage() {
        return Mocaccino.hotMocaccio();
    }

    @Override
    public Beverage makeIceBeverage() {
        return Mocaccino.iceMocaccino();
    }
}
