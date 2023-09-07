package com.nhnacademy.security.repository.householdMovementAddress;

import com.nhnacademy.security.dto.householdMovementAddress.HouseholdMovementAddressDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Date;

@NoRepositoryBean
public interface HouseholdMovementAddressRepositoryCustom {

    HouseholdMovementAddressDto getHouseholdMovementAddress(Long householdSerialNumber, Date houseMovementReportDate);
}
