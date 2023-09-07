package com.nhnacademy.jpa.service.familyRelationship;

import com.nhnacademy.jpa.dto.familyRelationship.RelationshipDto;
import com.nhnacademy.jpa.dto.familyRelationship.RelationshipRegisterRequest;
import com.nhnacademy.jpa.dto.familyRelationship.RelationshipUpdateRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FamilyRelationshipService {

    RelationshipDto getRelationship(Long baseResidentNumber, Long familyResidentNumber);

    List<RelationshipDto> getRelationships(Long serialNumber);

    RelationshipDto registerRelationshipAndGet(Long baseResidentSerialNumber, RelationshipRegisterRequest request);

    void modifyRelationship(Long baseResidentNumber, Long familyResidentNumber, RelationshipUpdateRequest request);

    void deleteRelationship(Long baseResidentNumber, Long familyResidentNumber);
}
