package com.nhnacademy.jpa.service.birthDeathReportResident;

import com.nhnacademy.jpa.dto.birthDeathReportResident.*;
import org.springframework.transaction.annotation.Transactional;

public interface BirthDeathReportResidentService {

    @Transactional
    BirthDeathReportResidentDto createBirthReportResident(Long reportResidentSerialNumber, BirthReportCreateRequest request);

    @Transactional
    BirthDeathReportResidentDto updateBirthReportResident(Long reportResidentSerialNumber, Long residentSerialNumber, BirthReportUpdateRequest request);

    @Transactional
    void deleteBirthDeathReportResident(Long reportResidentSerialNumber, Long residentSerialNumber);

    @Transactional
    BirthDeathReportResidentDto createDeathReportResident(Long reportResidentSerialNumber, DeathReportCreateRequest request);

    @Transactional
    BirthDeathReportResidentDto updateDeathReportResident(Long reportResidentSerialNumber, Long residentSerialNumber, DeathReportUpdateRequest request);
}
