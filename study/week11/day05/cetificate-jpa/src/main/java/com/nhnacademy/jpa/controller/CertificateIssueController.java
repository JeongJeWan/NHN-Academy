package com.nhnacademy.jpa.controller;

import com.nhnacademy.jpa.dto.certificateIssue.FamilyComposition;
import com.nhnacademy.jpa.dto.certificateIssue.FamilyRelationAndNumber;
import com.nhnacademy.jpa.dto.certificateIssue.ResidentRegistrationAddress;
import com.nhnacademy.jpa.dto.certificateIssue.ResidentRegistrationInfo;
import com.nhnacademy.jpa.dto.resident.ResidentDto;
import com.nhnacademy.jpa.entity.*;
import com.nhnacademy.jpa.service.certificateIssue.CertificateIssueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class CertificateIssueController {

    private final CertificateIssueService certificateIssueService;

    public CertificateIssueController(CertificateIssueService certificateIssueService) {
        this.certificateIssueService = certificateIssueService;
    }

    @GetMapping("/family/certificate/{serialNumber}")
    public String familyCertificate(@PathVariable("serialNumber") Long serialNumber, Model model) {
        CertificateIssue certificateIssue = certificateIssueService.getFamilyRelationshipCertificate(serialNumber);
        if(Objects.isNull(certificateIssue)) {
            return "error";
        }

        ResidentDto resident = certificateIssueService.getResidentDto(serialNumber);
        List<FamilyRelationAndNumber> relationAndNumbers = certificateIssueService.getRelationAndNumber(serialNumber);

        List<FamilyComposition> contents = new ArrayList<>();
        contents.add(new FamilyComposition("본인", resident.getName(), resident.getBirthDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")),
                resident.getResidentRegistrationNumber(), resident.getGenderCode()));

        for (FamilyRelationAndNumber number : relationAndNumbers) {
            ResidentDto residentDto = certificateIssueService.getResidentDto(number.getFamilyResidentSerialNumber());
            contents.add(new FamilyComposition(number.getFamilyRelationshipCode(), residentDto.getName(), residentDto.getBirthDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")),
                    residentDto.getResidentRegistrationNumber(), residentDto.getGenderCode()));
        }

        Date reportDate = certificateIssue.getCertificateIssueDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일");

        model.addAttribute("certificate", certificateIssue);
        model.addAttribute("reportDate", format.format(reportDate));
        model.addAttribute("address", resident.getRegistrationBaseAddress());
        model.addAttribute("contents", contents);

        return "certification/familyRelationCertification";
    }

    @GetMapping("/residentRegistration/certificate/{serialNumber}")
    public String residentRegistration(@PathVariable("serialNumber") Long serialNumber, Model model) {
        CertificateIssue certificateIssue = certificateIssueService.getResidentRegistration(serialNumber);
        if(Objects.isNull(certificateIssue)) {
            return "error";
        }

        Household household = certificateIssueService.getHousehold(serialNumber);

        List<HouseholdMovementAddress> householdAddresses = certificateIssueService.getHouseholdMovementAddress(household.getHouseholdSerialNumber());
        List<ResidentRegistrationAddress> residentAddresses = new ArrayList<>();

        for(HouseholdMovementAddress address : householdAddresses) {
            String yn = "";
            if(address.getLastAddressYn().equals("Y")) {
                yn = "현주소";
            } else {
                yn = "전주소";
            }
            Date date = address.getPk().getHouseMovementReportDate();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            residentAddresses.add(new ResidentRegistrationAddress(yn, address.getHouseMovementAddress(), format.format(date)));
        }

        List<ResidentRegistrationInfo> contents = new ArrayList<>();
        List<HouseholdCompositionResident> residents = certificateIssueService.getHouseholdCompositionResident(household.getHouseholdSerialNumber());

        for(HouseholdCompositionResident resident : residents) {
            Date date = resident.getReportDate();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            contents.add(new ResidentRegistrationInfo(
                    resident.getHouseholdRelationshipCode(),
                    resident.getResident().getName(),
                    resident.getResident().getResidentRegistrationNumber(),
                    format.format(date),
                    resident.getHouseholdCompositionChangeReasonCode()
            ));
        }

        Date reportDate = certificateIssue.getCertificateIssueDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일");

        model.addAttribute("certificates", certificateIssue);
        model.addAttribute("reportDate", format.format(reportDate));
        model.addAttribute("name", certificateIssue.getResident().getName());
        model.addAttribute("household", household);
        model.addAttribute("addresses", residentAddresses);
        model.addAttribute("contents", contents);

        return "certification/familyRelationCertification";
    }

    @GetMapping("/birth/certificate/{serialNumber}")
    public String birthReportCertificate(@PathVariable("serialNumber") Long serialNumber, Model model) {
        CertificateIssue certificateIssue = certificateIssueService.getBirthCertificate(serialNumber);
        if (Objects.isNull(certificateIssue)) {
            return "error";
        }

        Date reportDate = certificateIssue.getCertificateIssueDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일");

        Resident father = certificateIssueService.getFatherByBirthCertificate(serialNumber);
        Resident mother = certificateIssueService.getMotherByBirthCertificate(serialNumber);
        BirthDeathReportResident birthReportResident = certificateIssueService.getBirthReportResident(serialNumber);

        model.addAttribute("reportDate", format.format(reportDate));
        model.addAttribute("birthDate", certificateIssue.getResident().getBirthDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")));
        model.addAttribute("certificate", certificateIssue);
        model.addAttribute("father", father);
        model.addAttribute("mother", mother);
        model.addAttribute("birthReport", birthReportResident);

        return "issue/birth";
    }

    @GetMapping("/death/certificate/{serialNumber}")
    public String deathReportCertificate(@PathVariable("serialNumber") Long serialNumber, Model model) {
        CertificateIssue certificateIssue = certificateIssueService.getDeathCertificate(serialNumber);
        if (Objects.isNull(certificateIssue)) {
            return "error";
        }

        Date dateReport = certificateIssue.getCertificateIssueDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일");

        BirthDeathReportResident deathReportResident = certificateIssueService.getDeathReportResident(serialNumber);

        model.addAttribute("reportDate", format.format(dateReport));
        model.addAttribute("certificate", certificateIssue);
        model.addAttribute("deathDate", certificateIssue.getResident().getDeathDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")));
        model.addAttribute("deathReport", deathReportResident);

        return "issue/death";
    }
}
