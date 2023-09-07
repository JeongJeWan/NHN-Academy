package com.nhnacademy.security.repository.householdCompositionResident;

import com.nhnacademy.security.dto.householdCompositionResident.HouseholdCompositionResidentDto;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface HouseholdCompositionResidentRepositoryCustom {
    HouseholdCompositionResidentDto getHouseholdCompositionResidentRepositoryby(Long residentSerialNumber);
}
