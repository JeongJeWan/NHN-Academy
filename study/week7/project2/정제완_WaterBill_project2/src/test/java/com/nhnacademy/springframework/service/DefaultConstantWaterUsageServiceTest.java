package com.nhnacademy.springframework.service;

import com.nhnacademy.springframework.config.WaterBillConfig;
import com.nhnacademy.springframework.repository.DefaultWaterBillRepository;
import com.nhnacademy.springframework.repository.WaterBill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefaultConstantWaterUsageServiceTest {

    private DefaultConstantWaterUsageService constantWaterUsageService;
    private DefaultWaterBillRepository waterBillRepository;
    private Resource resource;

    @BeforeEach
    void setup() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WaterBillConfig.class);
        constantWaterUsageService = context.getBean("defaultConstantWaterUsageService", DefaultConstantWaterUsageService.class);
        waterBillRepository = context.getBean("defaultWaterBillRepository", DefaultWaterBillRepository.class);
        resource = new ClassPathResource("/data/Tariff_20220331.json");
    }

    @Test
    void calculateBill() {

        List<WaterBill> waterBillList;

        try {
            waterBillRepository.fileLoad(String.valueOf(resource.getFile()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        waterBillList = constantWaterUsageService.calculateBill(1000);

        assertNotNull(waterBillList);

        assertEquals(220, waterBillList.get(0).getUnitPrice());
        assertEquals(230, waterBillList.get(1).getUnitPrice());
        assertEquals(300, waterBillList.get(2).getUnitPrice());
        assertEquals(330, waterBillList.get(3).getUnitPrice());
        assertEquals(345, waterBillList.get(4).getUnitPrice());

    }
}