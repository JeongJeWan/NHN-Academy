package com.nhnacademy.jpa.service.certificateIssue;

import com.nhnacademy.jpa.dto.certificateIssue.FamilyRelationAndNumber;
import com.nhnacademy.jpa.dto.resident.ResidentDto;
import com.nhnacademy.jpa.entity.*;

import java.util.List;

public interface CertificateIssueService {

    CertificateIssue getFamilyRelationshipCertificate(Long serialNumber);
    CertificateIssue getResidentRegistration(Long serialNumber);
    CertificateIssue getBirthCertificate(Long serialNumber);
    CertificateIssue getDeathCertificate(Long serialNumber);
    List<FamilyRelationAndNumber> getRelationAndNumber(Long serialNumber);
    Household getHousehold(Long serialNumber);
    List<HouseholdMovementAddress> getHouseholdMovementAddress(Long householdSerialNumber);
    List<HouseholdCompositionResident> getHouseholdCompositionResident(Long householdSerialNumber);
    Resident getFatherByBirthCertificate(Long serialNumber);
    Resident getMotherByBirthCertificate(Long serialNumber);
    BirthDeathReportResident getBirthReportResident(Long serialNumber);
    BirthDeathReportResident getDeathReportResident(Long serialNumber);
    ResidentDto getResidentDto(Long serialNumber);
}
