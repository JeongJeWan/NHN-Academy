package com.nhnacademy.jpa.repository.householdMovementAddress;

import com.nhnacademy.jpa.config.RootConfig;
import com.nhnacademy.jpa.config.WebConfig;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
public class HouseholdMovementAddressRepositoryTest {

    @Autowired
    private HouseholdMovementAddressRepository householdMovementAddressRepository;

    @Test
    void getHouseholdMovementAddressesBy() {
        Long householdSerialNumber = 1L;

        List<HouseholdMovementAddress> householdMovementAddresses = householdMovementAddressRepository.getHouseholdMovementAddressesBy(householdSerialNumber);

        householdMovementAddresses.stream().forEach(System.out::println);

        assertThat(householdMovementAddresses).hasSize(3);
    }

    @Test
    void updateHouseholdMovementAddress() {
        Long householdSerialNumber = 1L;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = format.parse("20130305");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        householdMovementAddressRepository.updateHouseholdMovementAddress(householdSerialNumber, date, "경기도 성남시 분당구 대왕판교로 645번길-2", "N");

        List<HouseholdMovementAddress> householdMovementAddresses = householdMovementAddressRepository.getHouseholdMovementAddressesBy(householdSerialNumber);
        householdMovementAddresses.stream().forEach(System.out::println);

        assertThat(householdMovementAddresses.get(0).getHouseMovementAddress()).isEqualTo("경기도 성남시 분당구 대왕판교로 645번길-2");
    }

    @Test
    void deleteHouseholdMovementAddress() {
        Long householdSerialNumber = 1L;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = format.parse("20130305");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        householdMovementAddressRepository.deleteHouseholdMovementAddress(householdSerialNumber, date);

        List<HouseholdMovementAddress> householdMovementAddresses = householdMovementAddressRepository.getHouseholdMovementAddressesBy(householdSerialNumber);
        householdMovementAddresses.stream().forEach(System.out::println);

        assertThat(householdMovementAddresses).hasSize(2);
        assertThat(householdMovementAddresses.get(0).getHouseMovementAddress()).isNotEqualTo("경기도 성남시 분당구 대왕판교로 645번길");
    }
}