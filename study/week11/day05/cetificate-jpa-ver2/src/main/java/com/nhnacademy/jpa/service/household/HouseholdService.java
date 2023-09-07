package com.nhnacademy.jpa.service.household;

import com.nhnacademy.jpa.dto.household.HouseholdCreateRequest;
import com.nhnacademy.jpa.dto.household.HouseholdDto;
import org.springframework.transaction.annotation.Transactional;

public interface HouseholdService {
    @Transactional
    HouseholdDto registerHousehold(HouseholdCreateRequest request);

    @Transactional
    void deleteHousehold(Long householdSerialNumber);
}
