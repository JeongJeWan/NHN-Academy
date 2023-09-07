package com.nhnacademy.jpa.controller.restController;

import com.nhnacademy.jpa.dto.birthDeathReportResident.*;
import com.nhnacademy.jpa.service.birthDeathReportResident.BirthDeathReportResidentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/residents/{serialNumber}", produces = "application/json;")
public class BirthDeathReportResidentRestController {

    private final BirthDeathReportResidentService birthDeathReportResidentService;


    public BirthDeathReportResidentRestController(BirthDeathReportResidentService birthDeathReportResidentService) {
        this.birthDeathReportResidentService = birthDeathReportResidentService;
    }

    @PostMapping("/birth")
    public BirthDeathReportResidentDto createBirthReport(@PathVariable("serialNumber") Long reportResidentSerialNumber, @RequestBody BirthReportCreateRequest request) {
        return birthDeathReportResidentService.createBirthReportResident(reportResidentSerialNumber, request);
    }

    @PutMapping("/birth/{targetSerialNumber}")
    public BirthDeathReportResidentDto updateBirthReport(@PathVariable("serialNumber") Long reportResidentSerialNumber, @PathVariable("targetSerialNumber") Long residentSerialNumber, @RequestBody BirthReportUpdateRequest request){
        return birthDeathReportResidentService.updateBirthReportResident(reportResidentSerialNumber, residentSerialNumber, request);
    }

    @DeleteMapping("/birth/{targetSerialNumber}")
    public void deleteBirthReport(@PathVariable("serialNumber") Long reportResidentSerialNumber, @PathVariable("targetSerialNumber") Long residentSerialNumber) {
        birthDeathReportResidentService.deleteBirthDeathReportResident(reportResidentSerialNumber, residentSerialNumber);
    }

    @PostMapping("/death")
    public BirthDeathReportResidentDto createDeathReport(@PathVariable("serialNumber") Long reportResidentSerialNumber, @RequestBody DeathReportCreateRequest request) {
        return birthDeathReportResidentService.createDeathReportResident(reportResidentSerialNumber, request);
    }

    @PutMapping("/death/{targetSerialNumber}")
    public BirthDeathReportResidentDto updateDeathReport(@PathVariable("serialNumber") Long reportResidentSerialNumber, @PathVariable("targetSerialNumber") Long residentSerialNumber, @RequestBody DeathReportUpdateRequest request) {
        return birthDeathReportResidentService.updateDeathReportResident(reportResidentSerialNumber, residentSerialNumber, request);
    }

    @DeleteMapping("/death/{targetSerialNumber}")
    public void deleteDeathReport(@PathVariable("serialNumber") Long reportResidentSerialNumber, @PathVariable("targetSerialNumber") Long residentSerialNumber) {
        birthDeathReportResidentService.deleteBirthDeathReportResident(reportResidentSerialNumber, residentSerialNumber);
    }
 }
