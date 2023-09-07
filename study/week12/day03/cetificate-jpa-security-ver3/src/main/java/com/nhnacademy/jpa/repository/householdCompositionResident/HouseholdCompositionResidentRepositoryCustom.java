package com.nhnacademy.jpa.repository.householdCompositionResident;

import com.nhnacademy.jpa.dto.householdCompositionResident.HouseholdCompositionResidentDto;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface HouseholdCompositionResidentRepositoryCustom {
    HouseholdCompositionResidentDto getHouseholdCompositionResidentRepositoryby(Long residentSerialNumber);
}
