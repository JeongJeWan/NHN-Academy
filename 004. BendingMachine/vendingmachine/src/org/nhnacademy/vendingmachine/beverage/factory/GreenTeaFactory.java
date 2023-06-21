package org.nhnacademy.vendingmachine.beverage.factory;

import org.nhnacademy.vendingmachine.beverage.Beverage;
import org.nhnacademy.vendingmachine.beverage.nonecoffee.GreenTea;

public class GreenTeaFactory implements BeverageFactory{

    @Override
    public Beverage makeHotBeverage() {
        return GreenTea.hotGreenTea();
    }

    @Override
    public Beverage makeIceBeverage() {
        return GreenTea.iceGreenTea();
    }

}
