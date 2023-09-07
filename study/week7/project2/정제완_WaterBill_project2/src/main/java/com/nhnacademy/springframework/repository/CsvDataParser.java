package com.nhnacademy.springframework.repository;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CsvDataParser implements DataParser{

    List<WaterBill> waterBillList = new ArrayList<>();

    @Override
    public List<WaterBill> parser(String fileName) {

        File csvfile = new File(fileName);

        try {
            CsvMapper csvMapper = new CsvMapper();
            CsvSchema schema = CsvSchema.emptySchema().withHeader();
            MappingIterator<Map<String, String>> rows = csvMapper.readerFor(Map.class)
                    .with(schema)
                    .readValues(csvfile);

            while (rows.hasNext()) {
                Map<String, String> row = rows.next();
                WaterBill waterBill = new WaterBill(row.get("지자체명"), row.get("업종"), Integer.parseInt(row.get("구간금액(원)")));
                waterBillList.add(waterBill);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return waterBillList;
    }
}
