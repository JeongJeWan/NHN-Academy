package com.nhnacademy.springframework.repository;

import java.util.List;

public interface DataParser {
    List<WaterBill> parser(String fileName);
}
