package com.nhnacademy.jpa.service.birthDeathReportResident;

import com.nhnacademy.jpa.dto.birthDeathReportResident.*;
import org.springframework.transaction.annotation.Transactional;

public interface BirthDeathReportResidentService {

    BirthDeathReportResidentDto createBirthReportResident(Long reportResidentSerialNumber, BirthReportCreateRequest request);

    BirthDeathReportResidentDto updateBirthReportResident(Long reportResidentSerialNumber, Long residentSerialNumber, BirthReportUpdateRequest request);

    void deleteBirthDeathReportResident(Long reportResidentSerialNumber, Long residentSerialNumber);

    BirthDeathReportResidentDto createDeathReportResident(Long reportResidentSerialNumber, DeathReportCreateRequest request);

    BirthDeathReportResidentDto updateDeathReportResident(Long reportResidentSerialNumber, Long residentSerialNumber, DeathReportUpdateRequest request);
}
