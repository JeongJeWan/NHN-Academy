package org.nanacademy.mart.test;

/*
    문제 1 [x]
    마트에서는 N개의 품목 매장이 있다. -> 마트가 N개의 Store를 가진다, 1(마트):N(품목 매장)
    생산자는 N개의 품목을 납품할 수 있다. -> 1(생산자):N(품목) or N(생산자):N(품목)
    마트에서는 생산자가 품목을 납품하기 전까지는 어떤 품목인지 알 수 없다. -> 마트로 전달되는 품목은 생산자가 결정한다. 마트는 품목 갯수만을 정한다.
    소비자는 품목별로 매장을 이용할 수 있다. 즉, 여러 사람이 각기 다른 품목을 구매할 경우 동시 구매가 가능하다. -> 1(소비자):1(품목)

    문제 2 [x]
    프로그램에서 사용되는 세마포어를 구현하여 적용하라.
 */
public class Mart extends Thread {
    private Store[] stores;
    private Seller seller;

    public Mart(int numberOfStores) {
        this.seller = new Seller(numberOfStores);
    }

    // 생산자로부터 받아온 품목 매장들을 반환한다.
    public Store[] getStores() {
        Store[] stores = seller.getStores();
        setStores(stores);
        return this.stores;
    }

    public void setStores(Store[] stores) {
        this.stores = stores;
    }

    @Override
    public void run() {
        super.run();
        seller.start();
    }
}
