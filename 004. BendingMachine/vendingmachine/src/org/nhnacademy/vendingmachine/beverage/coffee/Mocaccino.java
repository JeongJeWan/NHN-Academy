package org.nhnacademy.vendingmachine.beverage.coffee;

import org.nhnacademy.vendingmachine.beverage.BasicBeverage;
import org.nhnacademy.vendingmachine.beverage.type.Temperature;

public class Mocaccino extends BasicBeverage {
    private final static String name= "모카치노";
    private final static int price = 3500;
    protected Mocaccino(String name, int price, Temperature temperature) {
        super(name, price, temperature);
        make();
    }


    public static Mocaccino hotMocaccio() {

        return new Mocaccino(name, price, Temperature.HOT);
    }
    public static Mocaccino iceMocaccino() {
        return new Mocaccino(name, price, Temperature.COOL);
    }

    public void make() {
        System.out.println("원두를 추출합니다.");
        System.out.println("모카를 추가합니다.");
        System.out.println(super.getTemperature() + " " + getName()  + " 만듭니다!!");
    }
}
