package com.nhnacademy.jpa.service.householdMovementAddress;

import com.nhnacademy.jpa.dto.householdMovementAddress.HouseholdMovementAddressCreateRequest;
import com.nhnacademy.jpa.dto.householdMovementAddress.HouseholdMovementAddressDto;
import com.nhnacademy.jpa.dto.householdMovementAddress.HouseholdMovementAddressUpdateRequest;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

public interface HouseholdMovementAddressService {

    HouseholdMovementAddressDto createHouseholdMovementAddress(Long householdSerialNumber, HouseholdMovementAddressCreateRequest request);

    HouseholdMovementAddressDto updateHouseholdMovementAddress(Long householdSerialNumber, String houseMovementReportDate, HouseholdMovementAddressUpdateRequest request) throws ParseException ;

    void deleteHouseholdMovementAddress(Long householdSerialNumber, String houseMovementReportDate) throws ParseException ;
}
