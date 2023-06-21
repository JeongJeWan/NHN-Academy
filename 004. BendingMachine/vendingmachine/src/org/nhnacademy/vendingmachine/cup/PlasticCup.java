package org.nhnacademy.vendingmachine.cup;

import org.nhnacademy.vendingmachine.cup.type.CupType;

public class PlasticCup extends BasicCup{
    @Override
    public CupType cupType() {
        return CupType.플라스틱컵;
    }
}
