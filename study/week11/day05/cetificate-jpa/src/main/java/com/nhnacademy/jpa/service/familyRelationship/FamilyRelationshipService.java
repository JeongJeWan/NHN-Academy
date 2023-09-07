package com.nhnacademy.jpa.service.familyRelationship;

import com.nhnacademy.jpa.dto.familyRelationship.FamilyRelationshipCreateRequest;
import com.nhnacademy.jpa.dto.familyRelationship.FamilyRelationshipDto;
import com.nhnacademy.jpa.dto.resident.ResidentCreateRequest;
import com.nhnacademy.jpa.dto.resident.ResidentUpdateRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FamilyRelationshipService {

    @Transactional(readOnly = true)
    FamilyRelationshipDto getFamilyRelationship(Long baseResidentSerialNumber, Long familyResidentSerialNumber);

    List<FamilyRelationshipDto> getFamilyRelationships(Long serialNumber);

    @Transactional
    FamilyRelationshipDto createFamilyRelationship(Long baseResidentSerialNumber, FamilyRelationshipCreateRequest request);

    @Transactional
    void updateFamilyRelationship(Long baseResidentSerialNumber, Long familyResidentSerialNumber, ResidentUpdateRequest request);

    @Transactional
    void deleteFamilyRealtionship(Long baseResidentSerialNumber, Long familyResidentSerialNumber);
}
