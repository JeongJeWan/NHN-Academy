package com.nhnacademy.springframework.service;

import com.nhnacademy.springframework.repository.WaterBill;

import java.util.List;

public interface ConstantWaterUsageService {
    List<WaterBill> calculateBill(int waterUsage);
}
