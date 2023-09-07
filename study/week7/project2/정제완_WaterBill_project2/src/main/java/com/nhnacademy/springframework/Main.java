package com.nhnacademy.springframework;

import com.nhnacademy.springframework.config.WaterBillConfig;
import com.nhnacademy.springframework.report.DefaultResultReport;
import com.nhnacademy.springframework.repository.DefaultWaterBillRepository;
import com.nhnacademy.springframework.repository.WaterBill;
import com.nhnacademy.springframework.service.DefaultConstantWaterUsageService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WaterBillConfig.class);
            Scanner scanner = new Scanner(System.in)) {

            DefaultWaterBillRepository waterBillRepository = context.getBean("defaultWaterBillRepository", DefaultWaterBillRepository.class);

            Resource resource = new ClassPathResource("/data/Tariff_20220331.json");
            waterBillRepository.fileLoad(String.valueOf(resource.getFile()));

            DefaultConstantWaterUsageService constantWaterUsageService = context.getBean("defaultConstantWaterUsageService", DefaultConstantWaterUsageService.class);
            DefaultResultReport resultReport = context.getBean("defaultResultReport", DefaultResultReport.class);

            List<WaterBill> waterBillList;
            while (!Thread.interrupted()) {
                System.out.print("물 사용량: ");
                int waterUsage = scanner.nextInt();
                waterBillList = constantWaterUsageService.calculateBill(waterUsage);
                resultReport.report(waterBillList);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
