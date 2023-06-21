package org.nhnacademy.vendingmachine.beverage.factory;

import org.nhnacademy.vendingmachine.beverage.Beverage;
import org.nhnacademy.vendingmachine.beverage.nonecoffee.PechIceTea;

public class PechIceTeaFactory implements BeverageFactory{
    @Override
    public Beverage makeHotBeverage() {
        return null;
    }

    @Override
    public Beverage makeIceBeverage() {
        return PechIceTea.icePechIceTea();
    }
}
