package com.nhnacademy.jpa.service.resident;

import com.nhnacademy.jpa.dto.resident.ResidentDto;
import com.nhnacademy.jpa.dto.resident.ResidentCreateRequest;
import com.nhnacademy.jpa.dto.resident.ResidentUpdateRequest;
import com.nhnacademy.jpa.entity.HouseholdCompositionResident;
import com.nhnacademy.jpa.entity.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ResidentService {
    Page<Resident> getResidentList(Pageable pageable);
    ResidentDto getResident(Long residentSerialNumber);
    Resident createResident(ResidentCreateRequest residentCreateRequest);
    ResidentDto updateResident(Long residentSerialNumber, ResidentUpdateRequest residentUpdateRequest);
    void deleteResident(Long residentSerialNumber);
    Long getHouseholdSerialNumber(Long serialNumber);
    List<HouseholdCompositionResident> getFamilies(Long householdSerialNumber);
}
