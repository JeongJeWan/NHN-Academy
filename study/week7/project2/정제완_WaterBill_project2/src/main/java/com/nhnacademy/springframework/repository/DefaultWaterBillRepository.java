package com.nhnacademy.springframework.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DefaultWaterBillRepository implements WaterBillRepository{

    List<WaterBill> waterBillList = new ArrayList<>();
    private final CsvJsonDataParser csvJsonDataParser;

    @Autowired
    public DefaultWaterBillRepository(CsvJsonDataParser csvJsonDataParser) {
        this.csvJsonDataParser = csvJsonDataParser;
    }


    @Override
    public List<WaterBill> fileLoad(String fileName) {
        waterBillList = csvJsonDataParser.parser(fileName);
        return waterBillList;
    }

    @Override
    public List<WaterBill> findWaterBill(int waterUsage) {

        for(WaterBill waterBill : waterBillList) {
            waterBill.setBillTotal(waterUsage * waterBill.getUnitPrice());
        }

        return waterBillList.stream()
                .sorted(Comparator.comparingInt(WaterBill::getUnitPrice))
                .limit(5)
                .collect(Collectors.toList());
    }
}
