package com.nhnacademy.springframework.report;

import com.nhnacademy.springframework.repository.WaterBill;

import java.util.List;

public interface ResultReport {
    void report(List<WaterBill> waterBillDatas);
}
