package com.nhnacademy.springframework.service;

import com.nhnacademy.springframework.repository.WaterBill;
import com.nhnacademy.springframework.repository.WaterBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultConstantWaterUsageService implements ConstantWaterUsageService{

    List<WaterBill> waterBillList = new ArrayList<>();
    private final WaterBillRepository waterBillRepository;

    @Autowired
    public DefaultConstantWaterUsageService(WaterBillRepository waterBillRepository) {
        this.waterBillRepository = waterBillRepository;
    }

    @Override
    public List<WaterBill> calculateBill(int waterUsage) {
        waterBillList = waterBillRepository.findWaterBill(waterUsage);
        return waterBillList;
    }
}
