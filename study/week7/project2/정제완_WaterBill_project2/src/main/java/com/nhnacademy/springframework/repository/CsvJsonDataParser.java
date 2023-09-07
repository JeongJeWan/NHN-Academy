package com.nhnacademy.springframework.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CsvJsonDataParser implements DataParser {

    private static final String CSV_EXTENSION = "csv";
    private static final String JSON_EXTENSION = "json";

    private final List<WaterBill> waterBillList = new ArrayList<>();

    @Override
    public List<WaterBill> parser(String fileName) {

        String fileExtension = getFileExtension(fileName);

        System.out.println("파일 확장자: " + fileExtension);

        File file = new File(fileName);

        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> data;
        try {
            if (fileExtension.equalsIgnoreCase(CSV_EXTENSION)) {
                CsvMapper csvMapper = new CsvMapper();
                CsvSchema schema = CsvSchema.emptySchema().withHeader();
                MappingIterator<Map<String, String>> rows = csvMapper.readerFor(Map.class)
                        .with(schema)
                        .readValues(file);

                while (rows.hasNext()) {
                    Map<String, String> row = rows.next();
                    WaterBill waterBill = new WaterBill(row.get("지자체명"), row.get("업종"), Integer.parseInt(row.get("구간금액(원)")));
                    waterBillList.add(waterBill);
                }
            } else if (fileExtension.equalsIgnoreCase(JSON_EXTENSION)) {
                data = mapper.readValue(file, new TypeReference<>() {});
                for (Map<String, Object> row : data) {
                    String city = (String) row.get("지자체명");
                    String sector = (String) row.get("업종");
                    int unitPrice = (int) row.get("구간금액(원)");
                    WaterBill waterBill = new WaterBill(city, sector, unitPrice);
                    waterBillList.add(waterBill);
                }
            } else {
                throw new RuntimeException("Unsupported file extension: " + fileExtension);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return waterBillList;
    }

    private String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf('.');
        return (index == -1) ? "" : fileName.substring(index + 1);
    }
}
