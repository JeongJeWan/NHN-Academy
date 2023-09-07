package com.nhnacademy.jpa.repository.household;

import com.nhnacademy.jpa.dto.household.HouseholdDto;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface HouseholdRepositoryCustom {
    HouseholdDto getHousehold(Long householdSerialNumber);
}
