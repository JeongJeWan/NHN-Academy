package com.nhnacademy.jpa.service.household;

import com.nhnacademy.jpa.config.RootConfig;
import com.nhnacademy.jpa.config.WebConfig;
import com.nhnacademy.jpa.dto.household.HouseholdCreateRequest;
import com.nhnacademy.jpa.dto.household.HouseholdDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class HouseholdServiceTest {

    @Autowired
    HouseholdService householdService;

    @Test
    void registerHousehold() {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = format.parse("20230305");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        HouseholdCreateRequest request = new HouseholdCreateRequest();
        request.setHouseholdSerialNumber(2L);
        request.setHouseholdResidentSerialNumber(3L);
        request.setHouseholdCompositionDate(date);
        request.setHouseholdCompositionReasonCode("전입");
        request.setCurrentHouseMovementAddress("광주광역시 서석동 조선대학교");

        HouseholdDto householdDto = householdService.registerHousehold(request);

        System.out.println(householdDto);

        assertThat(householdDto.getCurrentHouseMovementAddress()).isEqualTo("광주광역시 서석동 조선대학교");
    }

    @Test
    void deleteHousehold() {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = format.parse("20230305");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        HouseholdCreateRequest request = new HouseholdCreateRequest();
        request.setHouseholdSerialNumber(2L);
        request.setHouseholdResidentSerialNumber(3L);
        request.setHouseholdCompositionDate(date);
        request.setHouseholdCompositionReasonCode("전입");
        request.setCurrentHouseMovementAddress("광주광역시 서석동 조선대학교");

        HouseholdDto householdDto = householdService.registerHousehold(request);

        householdService.deleteHousehold(2L);

    }
}