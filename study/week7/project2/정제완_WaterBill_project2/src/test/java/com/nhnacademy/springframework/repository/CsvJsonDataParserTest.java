package com.nhnacademy.springframework.repository;

import com.nhnacademy.springframework.config.WaterBillConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvJsonDataParserTest {
    private CsvJsonDataParser dataParser;
    private Resource resource;

    @BeforeEach
    void setup() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WaterBillConfig.class);
        dataParser = context.getBean("csvJsonDataParser", CsvJsonDataParser.class);
        resource = new ClassPathResource("/data/Tariff_20220331.json");
    }

    @Test
    void parse() {

        List<WaterBill> waterBillList;

        try {
            waterBillList = dataParser.parser(String.valueOf(resource.getFile()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(waterBillList);


        assertEquals(690, waterBillList.get(0).getUnitPrice());
        assertEquals(1090, waterBillList.get(1).getUnitPrice());
        assertEquals(1530, waterBillList.get(2).getUnitPrice());
        assertEquals(1410, waterBillList.get(3).getUnitPrice());
        assertEquals(1480, waterBillList.get(4).getUnitPrice());
    }

}