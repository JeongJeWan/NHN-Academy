package com.nhnacademy.security.service.certificateIssue;

import com.nhnacademy.security.dto.certificateIssue.FamilyRelationAndNumber;
import com.nhnacademy.security.dto.resident.ResidentDto;
import com.nhnacademy.security.entity.*;

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
