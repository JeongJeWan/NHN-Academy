package org.nhnacademy.vendingmachine.beverage.coffee;

import org.nhnacademy.vendingmachine.beverage.BasicBeverage;
import org.nhnacademy.vendingmachine.beverage.type.Temperature;

public class CafeLatte extends BasicBeverage {
    private final static String name= "카페라떼";
    private final static int price = 4000;
    protected CafeLatte(String name, int price, Temperature temperature) {
        super(name, price, temperature);
        make();
    }


    public static CafeLatte hotCafeLatte() {
        return new CafeLatte(name, price, Temperature.HOT);
    }
    public static CafeLatte iceCafeLatte() {
        return new CafeLatte(name, price, Temperature.COOL);
    }

    public void make() {
        System.out.println("원두를 추출합니다.");
        System.out.println("우유를 넣습니다.");
        System.out.println(super.getTemperature() + " " + getName()  + " 만듭니다!!");
    }
}
