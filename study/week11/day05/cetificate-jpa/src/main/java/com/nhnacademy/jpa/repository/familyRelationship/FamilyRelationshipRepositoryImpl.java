package com.nhnacademy.jpa.repository.familyRelationship;

import com.nhnacademy.jpa.dto.familyRelationship.FamilyRelationshipDto;
import com.nhnacademy.jpa.dto.familyRelationship.QFamilyRelationshipDto;
import com.nhnacademy.jpa.entity.FamilyRelationship;
import com.nhnacademy.jpa.entity.QFamilyRelationship;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class FamilyRelationshipRepositoryImpl extends QuerydslRepositorySupport implements FamilyRelationshipRepositoryCustom {
    public FamilyRelationshipRepositoryImpl() {
        super(FamilyRelationship.class);
    }

    @Override
    public List<FamilyRelationshipDto> getFamilyRelationships(Long baseResidentSerialNumber) {
        QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;

        return from(familyRelationship)
                .select(new QFamilyRelationshipDto(familyRelationship.pk.familyResidentSerialNumber,
                            familyRelationship.pk.baseResidentSerialNumber,
                            familyRelationship.familyRelationshipCode))
                .where(familyRelationship.pk.baseResidentSerialNumber.eq(baseResidentSerialNumber))
                .fetch();
    }

    @Override
    public FamilyRelationshipDto getFamilyRelationship(Long baseResidentSerialNumber, Long familyResidentSerialNumber) {
        QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;

        return from(familyRelationship)
                .select(new QFamilyRelationshipDto(familyRelationship.pk.familyResidentSerialNumber,
                        familyRelationship.pk.baseResidentSerialNumber,
                        familyRelationship.familyRelationshipCode))
                .where(familyRelationship.pk.baseResidentSerialNumber.eq(baseResidentSerialNumber))
                .where(familyRelationship.pk.familyResidentSerialNumber.eq(familyResidentSerialNumber))
                .fetchOne();
    }
}
