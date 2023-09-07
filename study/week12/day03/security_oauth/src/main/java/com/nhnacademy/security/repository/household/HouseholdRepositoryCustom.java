package com.nhnacademy.security.repository.household;

import com.nhnacademy.security.dto.household.HouseholdDto;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface HouseholdRepositoryCustom {
    HouseholdDto getHousehold(Long householdSerialNumber);
}
