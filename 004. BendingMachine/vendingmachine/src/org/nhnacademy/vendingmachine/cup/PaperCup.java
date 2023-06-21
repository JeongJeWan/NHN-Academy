package org.nhnacademy.vendingmachine.cup;

import org.nhnacademy.vendingmachine.cup.type.CupType;

public class PaperCup extends BasicCup{
    @Override
    public CupType cupType() {
        return CupType.종이컵;
    }
}
