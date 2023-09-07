package com.nhnacademy.security.service.certificateIssue;

import com.nhnacademy.security.dto.certificateIssue.FamilyRelationAndNumber;
import com.nhnacademy.security.dto.resident.ResidentDto;
import com.nhnacademy.security.entity.*;
import com.nhnacademy.security.repository.birthDeathReportResident.BirthDeathRepostRepository;
import com.nhnacademy.security.repository.certificateIssue.CertificateIssueRepository;
import com.nhnacademy.security.repository.familyRelationship.FamilyRelationshipRepository;
import com.nhnacademy.security.repository.household.HouseholdRepository;
import com.nhnacademy.security.repository.householdCompositionResident.HouseholdCompositionResidentRepository;
import com.nhnacademy.security.repository.householdMovementAddress.HouseholdMovementAddressRepository;
import com.nhnacademy.security.repository.resident.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CertificateIssueServiceImpl implements CertificateIssueService{

    private final CertificateIssueRepository certificateIssueRepository;
    private final HouseholdRepository householdRepository;
    private final HouseholdMovementAddressRepository householdMovementAddressRepository;
    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;
    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final BirthDeathRepostRepository birthDeathRepostRepository;
    private final ResidentRepository residentRepository;

    @Override
    public CertificateIssue getFamilyRelationshipCertificate(Long serialNumber) {
        return certificateIssueRepository.getFamilyCertificate(serialNumber);
    }

    @Override
    public CertificateIssue getResidentRegistration(Long serialNumber) {
        return certificateIssueRepository.getResidentRegistrationCertificate(serialNumber);
    }

    @Override
    public CertificateIssue getBirthCertificate(Long serialNumber) {
        return certificateIssueRepository.getBirthCertificate(serialNumber);
    }

    @Override
    public CertificateIssue getDeathCertificate(Long serialNumber) {
        return certificateIssueRepository.getDeathCertificate(serialNumber);
    }

    @Override
    public List<FamilyRelationAndNumber> getRelationAndNumber(Long serialNumber) {
        return certificateIssueRepository.getRelationAndNumber(serialNumber);
    }

    @Override
    public Household getHousehold(Long serialNumber) {
        return householdRepository.getHouseholdBySerialNumber(serialNumber);
    }

    @Override
    public List<HouseholdMovementAddress> getHouseholdMovementAddress(Long householdSerialNumber) {
        return householdMovementAddressRepository.getHouseholdMovementAddressesBy(householdSerialNumber);
    }

    @Override
    public List<HouseholdCompositionResident> getHouseholdCompositionResident(Long householdSerialNumber) {
        return householdCompositionResidentRepository.getHouseholdCompositionResidentsBy(householdSerialNumber);
    }

    @Override
    public Resident getFatherByBirthCertificate(Long serialNumber) {

        FamilyRelationship fatherRelationship = familyRelationshipRepository.getFatherRelationship(serialNumber);

        return fatherRelationship.getFamilyResident();
    }

    @Override
    public Resident getMotherByBirthCertificate(Long serialNumber) {

        FamilyRelationship motherRelationship = familyRelationshipRepository.getMotherRelationship(serialNumber);

        return motherRelationship.getFamilyResident();
    }

    @Override
    public BirthDeathReportResident getBirthReportResident(Long serialNumber) {
        return birthDeathRepostRepository.getBirthReportResidentBy(serialNumber);
    }

    @Override
    public BirthDeathReportResident getDeathReportResident(Long serialNumber) {
        return birthDeathRepostRepository.getDeathReportResidentBy(serialNumber);
    }

    @Override
    public ResidentDto getResidentDto(Long serialNumber) {
        return residentRepository.getResident(serialNumber);
    }
}
