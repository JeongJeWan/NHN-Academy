package com.nhnacademy.jpa.service.householdMovementAddress;

import com.nhnacademy.jpa.config.RootConfig;
import com.nhnacademy.jpa.config.WebConfig;
import com.nhnacademy.jpa.dto.household.HouseholdCreateRequest;
import com.nhnacademy.jpa.dto.householdMovementAddress.HouseholdMovementAddressCreateRequest;
import com.nhnacademy.jpa.dto.householdMovementAddress.HouseholdMovementAddressDto;
import com.nhnacademy.jpa.dto.householdMovementAddress.HouseholdMovementAddressUpdateRequest;
import com.nhnacademy.jpa.entity.HouseholdMovementAddress;
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
public class HouseholdMovementAddressServiceTest {

    @Autowired
    HouseholdMovementAddressService householdMovementAddressService;

    @Test
    void createHouseholdMovementAddress() {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = format.parse("20230305");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        HouseholdMovementAddressCreateRequest request = new HouseholdMovementAddressCreateRequest();
        request.setHouseMovementAddress("경상남도 거제시 사등면");
        request.setLastAddressYn("N");
        request.setHouseMovementReportDate(date);

        HouseholdMovementAddressDto householdMovementAddress =  householdMovementAddressService.createHouseholdMovementAddress(1L, request);

        System.out.println(householdMovementAddress);

        assertThat(householdMovementAddress.getHouseMovementAddress()).isEqualTo("경상남도 거제시 사등면");
    }

    @Test
    void updateHouseholdMovementAddress() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = format.parse("20230305");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        HouseholdMovementAddressCreateRequest requestCreate = new HouseholdMovementAddressCreateRequest();
        requestCreate.setHouseMovementAddress("경상남도 거제시 사등면");
        requestCreate.setLastAddressYn("N");
        requestCreate.setHouseMovementReportDate(date);

        householdMovementAddressService.createHouseholdMovementAddress(1L, requestCreate);

        HouseholdMovementAddressDto householdMovementAddress = null;

        HouseholdMovementAddressUpdateRequest request = new HouseholdMovementAddressUpdateRequest();

        request.setHouseMovementAddress("광주광역시 동구");
        request.setLastAddressYn("N");
        try {
            householdMovementAddress = householdMovementAddressService.updateHouseholdMovementAddress(1L,"20230305", request);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        System.out.println(householdMovementAddress);

        assertThat(householdMovementAddress.getHouseMovementAddress()).isEqualTo("광주광역시 동구");
    }

    @Test
    void deleteHouseholdMovementAddress() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = format.parse("20230305");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        HouseholdMovementAddressCreateRequest requestCreate = new HouseholdMovementAddressCreateRequest();
        requestCreate.setHouseMovementAddress("경상남도 거제시 사등면");
        requestCreate.setLastAddressYn("N");
        requestCreate.setHouseMovementReportDate(date);


        HouseholdMovementAddressDto householdMovementAddress = householdMovementAddressService.createHouseholdMovementAddress(1L, requestCreate);

        try {
            householdMovementAddressService.deleteHouseholdMovementAddress(1L, "20230305");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}