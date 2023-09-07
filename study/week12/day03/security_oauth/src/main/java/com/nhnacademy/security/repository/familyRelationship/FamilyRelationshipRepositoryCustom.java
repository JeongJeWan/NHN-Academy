package com.nhnacademy.security.repository.familyRelationship;

import com.nhnacademy.security.dto.familyRelationship.RelationshipDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface FamilyRelationshipRepositoryCustom {
    List<RelationshipDto> getFamilyRelationships(Long baseResidentSerialNumber);

    RelationshipDto getRelationship(Long baseResidentSerialNumber, Long familyResidentSerialNumber);
}
