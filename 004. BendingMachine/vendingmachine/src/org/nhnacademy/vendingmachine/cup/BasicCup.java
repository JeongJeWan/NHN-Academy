package org.nhnacademy.vendingmachine.cup;

import org.nhnacademy.vendingmachine.beverage.Beverage;
import org.nhnacademy.vendingmachine.cup.type.CupType;

public abstract class BasicCup {
    private Beverage beverage;
    public void putBeverage(Beverage beverage) {
        this.beverage = beverage;
    }
    public Beverage getBeverage() {
        return this.beverage;
    }

    abstract CupType cupType();
}
