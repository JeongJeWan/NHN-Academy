package com.nhnacademy.security.service.resident;

import com.nhnacademy.security.dto.resident.ResidentAccountRegister;
import com.nhnacademy.security.dto.resident.ResidentCreateRequest;
import com.nhnacademy.security.dto.resident.ResidentDto;
import com.nhnacademy.security.dto.resident.ResidentUpdateRequest;
import com.nhnacademy.security.entity.HouseholdCompositionResident;
import com.nhnacademy.security.entity.Resident;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ResidentService {
    int getResidentTotalPages(Pageable pageable);
    List<Resident> gerResidents(Pageable pageable);
    @Transactional(readOnly = true)
    ResidentDto getResident(Long residentSerialNumber);
    @Transactional
    Resident createResident(ResidentCreateRequest residentCreateRequest);
    @Transactional
    ResidentDto updateResident(Long residentSerialNumber, ResidentUpdateRequest residentUpdateRequest);
    @Transactional
    void deleteResident(Long residentSerialNumber);

    ResidentDto addAccount(Long serialNumber, ResidentAccountRegister residentAccountRegister);
    Long getHouseholdSerialNumber(Long serialNumber);
    List<HouseholdCompositionResident> getFamilies(Long householdSerialNumber);
}
