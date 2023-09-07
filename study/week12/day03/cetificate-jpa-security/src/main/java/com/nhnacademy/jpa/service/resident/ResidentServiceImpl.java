package com.nhnacademy.jpa.service.resident;

import com.nhnacademy.jpa.dto.resident.ResidentAccountRegister;
import com.nhnacademy.jpa.dto.resident.ResidentDto;
import com.nhnacademy.jpa.dto.resident.ResidentCreateRequest;
import com.nhnacademy.jpa.dto.resident.ResidentUpdateRequest;
import com.nhnacademy.jpa.entity.HouseholdCompositionResident;
import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.exception.ResidentDeleteFailedException;
import com.nhnacademy.jpa.repository.householdCompositionResident.HouseholdCompositionResidentRepository;
import com.nhnacademy.jpa.repository.resident.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ResidentServiceImpl implements ResidentService{

    private final ResidentRepository residentRepository;
    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public int getResidentTotalPages(Pageable pageable) {
        return residentRepository.getAllBy(pageable).getTotalPages();
    }

    @Override
    public List<Resident> gerResidents(Pageable pageable) {
        return residentRepository.getAllBy(pageable).getContent();
    }

    @Override
    public ResidentDto getResident(Long residentSerialNumber) {
        return residentRepository.getResident(residentSerialNumber);
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

    @Transactional
    @Override
    public ResidentDto addAccount(Long serialNumber, ResidentAccountRegister residentAccountRegister) {
        residentRepository.addAccount(serialNumber,
                residentAccountRegister.getId(),
                passwordEncoder.encode(residentAccountRegister.getPassword()),
                residentAccountRegister.getEmail());

        return residentRepository.getResident(serialNumber);
    }

    @Override
    public Long getHouseholdSerialNumber(Long serialNumber) {
        return householdCompositionResidentRepository.getHouseholdSerialNumber(serialNumber);
    }

    @Override
    public List<HouseholdCompositionResident> getFamilies(Long householdSerialNumber) {
        return householdCompositionResidentRepository.getHouseholdCompositionResidentsBy(householdSerialNumber);
    }
}
