package com.nhnacademy.jpa.service.resident;

import com.nhnacademy.jpa.dto.resident.ResidentAccountRegister;
import com.nhnacademy.jpa.dto.resident.ResidentDto;
import com.nhnacademy.jpa.dto.resident.ResidentCreateRequest;
import com.nhnacademy.jpa.dto.resident.ResidentUpdateRequest;
import com.nhnacademy.jpa.entity.HouseholdCompositionResident;
import com.nhnacademy.jpa.entity.Resident;
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
