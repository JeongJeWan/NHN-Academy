package com.nhnacademy.jpa.service.birthDeathReportResident;

import com.nhnacademy.jpa.dto.birthDeathReportResident.*;
import com.nhnacademy.jpa.dto.familyRelationship.RelationshipDto;
import com.nhnacademy.jpa.entity.BirthDeathReportResident;
import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.repository.birthDeathReportResident.BirthDeathRepostRepository;
import com.nhnacademy.jpa.repository.familyRelationship.FamilyRelationshipRepository;
import com.nhnacademy.jpa.repository.resident.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BirthDeathReportResidentServiceImpl implements BirthDeathReportResidentService{

    private final BirthDeathRepostRepository birthDeathRepostRepository;
    private final ResidentRepository residentRepository;
    private final FamilyRelationshipRepository familyRelationshipRepository;

    @Override
    public BirthDeathReportResidentDto createBirthReportResident(Long reportResidentSerialNumber, BirthReportCreateRequest request) {
        BirthDeathReportResident birthDeathReportResident = new BirthDeathReportResident();
        birthDeathReportResident.setPk(new BirthDeathReportResident.Pk(
                request.getResidentSerialNumber(), request.getBirthDeathTypeCode()
        ));

        Optional<Resident> reportResident = residentRepository.findById(reportResidentSerialNumber);
        birthDeathReportResident.setReportResident(reportResident.get());

        RelationshipDto familyRelationshipDto = familyRelationshipRepository.getRelationship(request.getResidentSerialNumber(), reportResidentSerialNumber);
        birthDeathReportResident.setBirthReportQualificationsCode(familyRelationshipDto.getFamilyRelationshipCode());

        birthDeathReportResident.setDeathReportQualificationsCode(request.getDeathReportQualificationsCode());
        birthDeathReportResident.setBirthDeathReportDate(request.getBirthDeathReportDate());
        birthDeathReportResident.setEmailAddress(request.getEmailAddress());
        birthDeathReportResident.setPhoneNumber(request.getPhoneNumber());

        Optional<Resident> resident = residentRepository.findById(request.getResidentSerialNumber());
        birthDeathReportResident.setResident(resident.get());

        birthDeathRepostRepository.saveAndFlush(birthDeathReportResident);

        return birthDeathRepostRepository.getBirthDeathReportResident(request.getResidentSerialNumber(), request.getBirthDeathTypeCode());
    }

    @Override
    public BirthDeathReportResidentDto updateBirthReportResident(Long reportResidentSerialNumber, Long residentSerialNumber, BirthReportUpdateRequest request) {
        birthDeathRepostRepository.updateBirthDeathReportResident(reportResidentSerialNumber, residentSerialNumber, request.getBirthDeathReportDate(), request.getEmailAddress(), request.getPhoneNumber());

        return birthDeathRepostRepository.getBirthDeathReportResident(residentSerialNumber, "출생");
    }

    @Override
    public void deleteBirthDeathReportResident(Long reportResidentSerialNumber, Long residentSerialNumber) {
        birthDeathRepostRepository.deleteBirthDeathReportResident(reportResidentSerialNumber, residentSerialNumber);
    }

    @Override
    public BirthDeathReportResidentDto createDeathReportResident(Long reportResidentSerialNumber, DeathReportCreateRequest request) {
        BirthDeathReportResident birthDeathReportResident = new BirthDeathReportResident();
        birthDeathReportResident.setPk(new BirthDeathReportResident.Pk(
                request.getResidentSerialNumber(), request.getBirthDeathTypeCode()
        ));

        Optional<Resident> reportResident = residentRepository.findById(reportResidentSerialNumber);
        birthDeathReportResident.setReportResident(reportResident.get());

        RelationshipDto relationshipDto = familyRelationshipRepository.getRelationship(request.getResidentSerialNumber(), reportResidentSerialNumber);
        birthDeathReportResident.setDeathReportQualificationsCode(relationshipDto.getFamilyRelationshipCode());

        birthDeathReportResident.setBirthDeathReportDate(request.getBirthDeathReportDate());
        birthDeathReportResident.setEmailAddress(request.getEmailAddress());
        birthDeathReportResident.setPhoneNumber(request.getPhoneNumber());

        Optional<Resident> resident = residentRepository.findById(request.getResidentSerialNumber());
        birthDeathReportResident.setResident(resident.get());

        birthDeathRepostRepository.save(birthDeathReportResident);

        return birthDeathRepostRepository.getBirthDeathReportResident(request.getResidentSerialNumber(), request.getBirthDeathTypeCode());
    }

    @Override
    public BirthDeathReportResidentDto updateDeathReportResident(Long reportResidentSerialNumber, Long residentSerialNumber, DeathReportUpdateRequest request) {
        birthDeathRepostRepository.updateBirthDeathReportResident(reportResidentSerialNumber, residentSerialNumber, request.getBirthDeathReportDate(), request.getEmailAddress(), request.getPhoneNumber());

        return birthDeathRepostRepository.getBirthDeathReportResident(residentSerialNumber, "사망");
    }
}
