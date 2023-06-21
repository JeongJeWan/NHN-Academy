package org.nhnacademy.vendingmachine.beverage.factory;

import org.nhnacademy.vendingmachine.beverage.Beverage;
import org.nhnacademy.vendingmachine.beverage.nonecoffee.Cocoa;

public class CocoaFactory implements BeverageFactory{
    @Override
    public Beverage makeHotBeverage() {
        return Cocoa.hotCocoa();
    }

    @Override
    public Beverage makeIceBeverage() {
        return Cocoa.iceCocoa();
    }
}
