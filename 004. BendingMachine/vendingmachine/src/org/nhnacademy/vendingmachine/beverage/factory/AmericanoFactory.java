package org.nhnacademy.vendingmachine.beverage.factory;

import org.nhnacademy.vendingmachine.beverage.Beverage;
import org.nhnacademy.vendingmachine.beverage.coffee.Americano;

public class AmericanoFactory implements BeverageFactory{
    @Override
    public Beverage makeHotBeverage() {
        return Americano.hotAmericano();
    }

    @Override
    public Beverage makeIceBeverage() {
        return Americano.iceAmericano();
    }
}
