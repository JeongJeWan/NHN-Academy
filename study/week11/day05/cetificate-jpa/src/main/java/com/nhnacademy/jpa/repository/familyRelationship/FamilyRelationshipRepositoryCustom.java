package com.nhnacademy.jpa.repository.familyRelationship;

import com.nhnacademy.jpa.dto.familyRelationship.FamilyRelationshipDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface FamilyRelationshipRepositoryCustom {

    List<FamilyRelationshipDto> getFamilyRelationships(Long baseResidentSerialNumber);

    FamilyRelationshipDto getFamilyRelationship(Long baseResidentSerialNumber, Long familyResidentSerialNumber);
}
