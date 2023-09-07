package com.nhnacademy.springframework.repository;



public class WaterBill {

    private String city;
    private String sector;
    private int unitPrice;
    private int billTotal;

    public WaterBill(String city, String sector, int unitPrice) {
        this.city = city;
        this.sector = sector;
        this.unitPrice = unitPrice;
    }

    public int getUnitPrice() {
        return unitPrice;
    }
    public void setBillTotal(int billTotal) {
        this.billTotal = billTotal;
    }

    @Override
    public String toString() {
        return "WaterBill{" +
                "city='" + city + '\'' +
                ", sector='" + sector + '\'' +
                ", unitPrice=" + unitPrice +
                ", billTotal=" + billTotal +
                '}';
    }
}

