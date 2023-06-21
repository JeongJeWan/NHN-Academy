package org.nhnacademy.vendingmachine.beverage.coffee;

import org.nhnacademy.vendingmachine.beverage.BasicBeverage;
import org.nhnacademy.vendingmachine.beverage.type.Temperature;

public class Americano extends BasicBeverage {
    private final static String name= "아메리카노";
    private final static int price = 1500;
    protected Americano(String name, int price, Temperature temperature) {
        super(name, price, temperature);
        make();
    }


    public static Americano hotAmericano() {

        return new Americano(name, price, Temperature.HOT);
    }
    public static Americano iceAmericano() {

        return new Americano(name, price, Temperature.COOL);
    }

    public void make() {
        System.out.println("원두를 추출합니다.");
        System.out.println(super.getTemperature() + " " + getName()  + " 만듭니다!!");
    }
}
