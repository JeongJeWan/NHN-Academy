package com.nhnacademy.springframework.report;

import com.nhnacademy.springframework.repository.WaterBill;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class DefaultResultReport implements ResultReport{

    @Override
    public void report(List<WaterBill> waterBillDatas) {
        for(WaterBill waterBill : waterBillDatas) {
            System.out.println(waterBill);
        }
    }
}