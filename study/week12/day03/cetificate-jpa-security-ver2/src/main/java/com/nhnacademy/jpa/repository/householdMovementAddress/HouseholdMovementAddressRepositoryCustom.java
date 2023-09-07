package com.nhnacademy.jpa.repository.householdMovementAddress;

import com.nhnacademy.jpa.dto.householdMovementAddress.HouseholdMovementAddressDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Date;

@NoRepositoryBean
public interface HouseholdMovementAddressRepositoryCustom {

    HouseholdMovementAddressDto getHouseholdMovementAddress(Long householdSerialNumber, Date houseMovementReportDate);
}
