package org.nhnacademy.vendingmachine.beverage;

import org.nhnacademy.vendingmachine.beverage.type.Temperature;

public abstract class BasicBeverage implements Beverage{
    private final String name;
    private final int price;
    private final Temperature temperature;

    protected BasicBeverage(String name, int price, Temperature temperature) {
        this.name = name;
        this.price = price;
        this.temperature = temperature;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public Temperature getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "BasicBeverage{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", temperature=" + temperature +
                '}';
    }
}
