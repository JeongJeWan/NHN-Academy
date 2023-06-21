package org.nhnacademy.vendingmachine.beverage.nonecoffee;

import org.nhnacademy.vendingmachine.beverage.BasicBeverage;
import org.nhnacademy.vendingmachine.beverage.type.Temperature;

public class Cocoa extends BasicBeverage {
    private final static String name= "초코";
    private final static int price = 3000;


    protected Cocoa(String name, int price, Temperature temperature) {
        super(name, price, temperature);
        make();
    }


    public static Cocoa hotCocoa() {
        return new Cocoa(name, price, Temperature.HOT);
    }

    public static Cocoa iceCocoa() {
        return new Cocoa(name, price, Temperature.COOL);
    }

    public void make() {
        System.out.println("초코 원료를 추출합니다.");
        System.out.println(super.getTemperature() + " " + getName()  + " 만듭니다!!");
    }
}
