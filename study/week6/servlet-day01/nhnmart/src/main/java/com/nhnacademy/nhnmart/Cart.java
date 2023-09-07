package com.nhnacademy.nhnmart;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final ArrayList<Item> items = new ArrayList<>();

    public void add(Item item) {
        items.add(item);
    }

    public ArrayList<Item> getItemList() {
        return items;
    }

    public static class Item {
        private final String name;
        private final int price;
        private final int amount;

        public Item(String name, int amount, int price) {
            this.name = name;
            this.amount = amount;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public int getAmount() {
            return amount;
        }

        public int getPrice(){
            return price;
        }
    }

}