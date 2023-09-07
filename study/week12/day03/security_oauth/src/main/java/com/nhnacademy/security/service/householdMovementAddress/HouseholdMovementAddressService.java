package com.nhnacademy.security.service.householdMovementAddress;

import com.nhnacademy.security.dto.householdMovementAddress.HouseholdMovementAddressCreateRequest;
import com.nhnacademy.security.dto.householdMovementAddress.HouseholdMovementAddressDto;
import com.nhnacademy.security.dto.householdMovementAddress.HouseholdMovementAddressUpdateRequest;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

public interface HouseholdMovementAddressService {

    @Transactional
    HouseholdMovementAddressDto createHouseholdMovementAddress(Long householdSerialNumber, HouseholdMovementAddressCreateRequest request);

    @Transactional
    HouseholdMovementAddressDto updateHouseholdMovementAddress(Long householdSerialNumber, String houseMovementReportDate, HouseholdMovementAddressUpdateRequest request) throws ParseException ;

    @Transactional
    void deleteHouseholdMovementAddress(Long householdSerialNumber, String houseMovementReportDate) throws ParseException ;
}
