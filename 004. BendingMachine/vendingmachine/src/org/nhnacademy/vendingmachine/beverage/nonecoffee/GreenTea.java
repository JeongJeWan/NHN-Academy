package org.nhnacademy.vendingmachine.beverage.nonecoffee;

import org.nhnacademy.vendingmachine.beverage.BasicBeverage;
import org.nhnacademy.vendingmachine.beverage.type.Temperature;

public class GreenTea extends BasicBeverage {
    private final static String name= "녹차";
    private final static int price = 3000;


    protected GreenTea(String name, int price, Temperature temperature) {
        super(name, price, temperature);
        make();
    }


    public static GreenTea hotGreenTea() {
        return new GreenTea(name, price, Temperature.HOT);
    }

    public static GreenTea iceGreenTea() {
        return new GreenTea(name, price, Temperature.COOL);
    }

    public void make() {
        System.out.println("녹차 원료를 추출합니다.");
        System.out.println(super.getTemperature() + " " + getName()  + " 만듭니다!!");
    }
}
