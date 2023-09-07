package com.nhnacademy.security.service.household;

import com.nhnacademy.security.dto.household.HouseholdCreateRequest;
import com.nhnacademy.security.dto.household.HouseholdDto;
import org.springframework.transaction.annotation.Transactional;

public interface HouseholdService {
    @Transactional
    HouseholdDto registerHousehold(HouseholdCreateRequest request);

    @Transactional
    void deleteHousehold(Long householdSerialNumber);
}
