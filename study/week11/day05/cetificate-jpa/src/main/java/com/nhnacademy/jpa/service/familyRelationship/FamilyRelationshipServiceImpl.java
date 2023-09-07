package com.nhnacademy.jpa.service.familyRelationship;

import com.nhnacademy.jpa.dto.familyRelationship.FamilyRelationshipCreateRequest;
import com.nhnacademy.jpa.dto.familyRelationship.FamilyRelationshipDto;
import com.nhnacademy.jpa.dto.resident.ResidentCreateRequest;
import com.nhnacademy.jpa.dto.resident.ResidentUpdateRequest;
import com.nhnacademy.jpa.entity.FamilyRelationship;
import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.repository.familyRelationship.FamilyRelationshipRepository;
import com.nhnacademy.jpa.repository.resident.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FamilyRelationshipServiceImpl implements FamilyRelationshipService{

    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final ResidentRepository residentRepository;

    @Override
    public FamilyRelationshipDto getFamilyRelationship(Long baseResidentSerialNumber, Long familyResidentSerialNumber) {
        return familyRelationshipRepository.getFamilyRelationship(baseResidentSerialNumber, familyResidentSerialNumber);
    }

    @Override
    public List<FamilyRelationshipDto> getFamilyRelationships(Long serialNumber) {
        return familyRelationshipRepository.getFamilyRelationships(serialNumber);
    }

    @Override
    public FamilyRelationshipDto createFamilyRelationship(Long baseResidentSerialNumber, FamilyRelationshipCreateRequest request) {
        FamilyRelationship familyRelationship = new FamilyRelationship();
        familyRelationship.setPk(new FamilyRelationship.Pk(request.getFamilyResidentSerialNumber(), baseResidentSerialNumber));
        familyRelationship.setFamilyRelationshipCode(request.getFamilyRelationshipCode());

        Optional<Resident> familyResident = residentRepository.findById(request.getFamilyResidentSerialNumber());
        Optional<Resident> baseResident = residentRepository.findById(baseResidentSerialNumber);

        familyRelationship.setFamilyResident(familyResident.get());
        familyRelationship.setBaseResident(baseResident.get());

        familyRelationshipRepository.save(familyRelationship);

        return familyRelationshipRepository.getFamilyRelationship(baseResidentSerialNumber, request.getFamilyResidentSerialNumber());
    }

    @Override
    public void updateFamilyRelationship(Long baseResidentSerialNumber, Long familyResidentSerialNumber, ResidentUpdateRequest request) {

        familyRelationshipRepository.updateFamilyRelationship(baseResidentSerialNumber, familyResidentSerialNumber, request.getDeathPlaceCode());
    }

    @Override
    public void deleteFamilyRealtionship(Long baseResidentSerialNumber, Long familyResidentSerialNumber) {
        familyRelationshipRepository.deleteFamilyRelationship(baseResidentSerialNumber, familyResidentSerialNumber);
    }
}
