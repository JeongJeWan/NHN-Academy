package com.nhnacademy.security.service.familyRelationship;

import com.nhnacademy.security.dto.familyRelationship.RelationshipDto;
import com.nhnacademy.security.dto.familyRelationship.RelationshipRegisterRequest;
import com.nhnacademy.security.dto.familyRelationship.RelationshipUpdateRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FamilyRelationshipService {

    @Transactional(readOnly = true)
    RelationshipDto getRelationship(Long baseResidentNumber, Long familyResidentNumber);

    List<RelationshipDto> getRelationships(Long serialNumber);

    @Transactional
    RelationshipDto registerRelationshipAndGet(Long baseResidentSerialNumber, RelationshipRegisterRequest request);

    @Transactional
    void modifyRelationship(Long baseResidentNumber, Long familyResidentNumber, RelationshipUpdateRequest request);

    @Transactional
    void deleteRelationship(Long baseResidentNumber, Long familyResidentNumber);
}
