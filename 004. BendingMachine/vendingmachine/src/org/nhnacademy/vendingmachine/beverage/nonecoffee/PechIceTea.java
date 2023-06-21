package org.nhnacademy.vendingmachine.beverage.nonecoffee;

import org.nhnacademy.vendingmachine.beverage.BasicBeverage;
import org.nhnacademy.vendingmachine.beverage.type.Temperature;

public class PechIceTea extends BasicBeverage {
    private final static String name= "복숭아 아이스티";
    private final static int price = 3500;
    protected PechIceTea(String name, int price, Temperature temperature) {
        super(name, price, temperature);
        make();
    }


    public static PechIceTea icePechIceTea() {
        return new PechIceTea(name, price, Temperature.HOT);
    }

    public void make() {
        System.out.println("복숭아를 추출합니다.");
        System.out.println( getName() + " 만듭니다!!" );
    }
}
