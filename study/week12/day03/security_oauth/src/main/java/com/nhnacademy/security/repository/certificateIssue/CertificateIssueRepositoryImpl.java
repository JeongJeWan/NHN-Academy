package com.nhnacademy.security.repository.certificateIssue;

import com.nhnacademy.security.dto.certificateIssue.FamilyRelationAndNumber;
import com.nhnacademy.security.dto.certificateIssue.QFamilyRelationAndNumber;
import com.nhnacademy.security.entity.CertificateIssue;
import com.nhnacademy.security.entity.QFamilyRelationship;
import com.nhnacademy.security.entity.QResident;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CertificateIssueRepositoryImpl extends QuerydslRepositorySupport implements CertificateIssueRepositoryCustom {
    public CertificateIssueRepositoryImpl() {
        super(CertificateIssue.class);
    }

    @Override
    public List<FamilyRelationAndNumber> getRelationAndNumber(Long serialNumber) {

        QResident resident = QResident.resident;
        QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;

        return from(familyRelationship)
                .innerJoin(familyRelationship.baseResident, resident)
                .select(new QFamilyRelationAndNumber(
                    familyRelationship.pk.familyResidentSerialNumber, familyRelationship.familyRelationshipCode))
                .where(resident.residentSerialNumber.eq(serialNumber))
                .fetch();
    }
}
