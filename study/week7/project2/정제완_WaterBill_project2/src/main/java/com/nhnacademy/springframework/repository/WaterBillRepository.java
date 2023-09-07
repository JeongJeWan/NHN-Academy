package com.nhnacademy.springframework.repository;


import java.util.List;

public interface WaterBillRepository {

    List<WaterBill> fileLoad(String fileName);
    List<WaterBill> findWaterBill(int waterUsage);
}
