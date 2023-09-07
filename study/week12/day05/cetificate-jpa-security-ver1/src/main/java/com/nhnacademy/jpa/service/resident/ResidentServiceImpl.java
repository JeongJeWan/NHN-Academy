package com.nhnacademy.jpa.service.resident;

import com.nhnacademy.jpa.dto.resident.ResidentDto;
import com.nhnacademy.jpa.dto.resident.ResidentCreateRequest;
import com.nhnacademy.jpa.dto.resident.ResidentUpdateRequest;
import com.nhnacademy.jpa.entity.HouseholdCompositionResident;
import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.exception.ResidentDeleteFailedException;
import com.nhnacademy.jpa.repository.householdCompositionResident.HouseholdCompositionResidentRepository;
import com.nhnacademy.jpa.repository.resident.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ResidentServiceImpl implements ResidentService{

    private final ResidentRepository residentRepository;
    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResidentDto getResident(Long residentSerialNumber) {
        return residentRepository.getResident(residentSerialNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Resident> getResidentList(Pageable pageable) {
        return residentRepository.getAllBy(pageable);
    }

    @Override
    public Resident createResident(ResidentCreateRequest residentCreateRequest) {
        Resident resident = new Resident();
        resident.setResidentSerialNumber(residentCreateRequest.getResidentSerialNumber());
        resident.setName(residentCreateRequest.getName());
        resident.setResidentRegistrationNumber(residentCreateRequest.getResidentRegistrationNumber());
        resident.setGenderCode(residentCreateRequest.getGenderCode());
        resident.setBirthDate(residentCreateRequest.getBirthDate());
        resident.setBirthPlaceCode(residentCreateRequest.getBirthPlaceCode());
        resident.setRegistrationBaseAddress(residentCreateRequest.getRegistrationBaseAddress());
        resident.setDeathDate(residentCreateRequest.getDeathDate());
        resident.setDeathPlaceCode(residentCreateRequest.getDeathPlaceCode());
        resident.setDeathPlaceAddress(residentCreateRequest.getDeathPlaceAddress());
        resident.setId(residentCreateRequest.getId());
        resident.setPassword(passwordEncoder.encode(residentCreateRequest.getPassword()));
        resident.setEmail(residentCreateRequest.getEmail());

        return residentRepository.save(resident);
    }

    @Override
    public ResidentDto updateResident(Long residentSerialNumber, ResidentUpdateRequest residentUpdateRequest) {
        residentRepository.updateResident(residentSerialNumber, residentUpdateRequest.getName(),
                residentUpdateRequest.getDeathDate(), residentUpdateRequest.getDeathPlaceCode(),
                residentUpdateRequest.getDeathPlaceAddress());
        return residentRepository.getResident(residentSerialNumber);
    }

    @Override
    public void deleteResident(Long residentSerialNumber) {
        Long count = residentRepository.countFamilyNumberBySerialNumber(residentSerialNumber);
        if(count > 1) {
            throw new ResidentDeleteFailedException();
        }


        residentRepository.deleteByResidentSerialNumber(residentSerialNumber);
    }

    @Override
    public Long getHouseholdSerialNumber(Long serialNumber) {
        return householdCompositionResidentRepository.getHouseholdSerialNumber(serialNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HouseholdCompositionResident> getFamilies(Long householdSerialNumber) {
        return householdCompositionResidentRepository.getHouseholdCompositionResidentsBy(householdSerialNumber);
    }
}
