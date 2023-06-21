package org.nhnacademy.vendingmachine.pay;


public class CashPayment implements Payment{

    private int price;
    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public boolean pay(int price) {
        this.price = price;
        return true;
    }

    public static void changeCash(int changeMoney) {
        int[] money = {1, 10, 100, 500, 1000, 5000};
        int bandingMachine = 0;
        int idx = money.length - 1;

        System.out.print("거스름돈 입니다!! 받으세요~!");
        while(changeMoney > 0) {
            bandingMachine = changeMoney/money[idx];
            if(bandingMachine > 0){
                System.out.print("\n" + money[idx]+"원: "+bandingMachine+" 개");
                changeMoney %= money[idx];
            }
            idx--;
        }
        System.out.println();
    }
}
