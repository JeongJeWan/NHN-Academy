package com.nhnacademy.jpa.service.certificateIssue;

import com.nhnacademy.jpa.dto.certificateIssue.FamilyRelationAndNumber;
import com.nhnacademy.jpa.dto.resident.ResidentDto;
import com.nhnacademy.jpa.entity.*;
import com.nhnacademy.jpa.repository.birthDeathReportResident.BirthDeathRepostRepository;
import com.nhnacademy.jpa.repository.certificateIssue.CertificateIssueRepository;
import com.nhnacademy.jpa.repository.familyRelationship.FamilyRelationshipRepository;
import com.nhnacademy.jpa.repository.household.HouseholdRepository;
import com.nhnacademy.jpa.repository.householdCompositionResident.HouseholdCompositionResidentRepository;
import com.nhnacademy.jpa.repository.householdMovementAddress.HouseholdMovementAddressRepository;
import com.nhnacademy.jpa.repository.resident.ResidentRepository;
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
        return certificateIssueRepository.getResidentRegisrationCertificate(serialNumber);
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
        return householdRepository.getHouseholdBy(serialNumber);
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

        FamilyRelationship fatherRelationship = familyRelationshipRepository.getFatherRelationshipBy(serialNumber);

        return fatherRelationship.getFamilyResident();
    }

    @Override
    public Resident getMotherByBirthCertificate(Long serialNumber) {

        FamilyRelationship motherRelationship = familyRelationshipRepository.getMotherRelationshipBy(serialNumber);

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
