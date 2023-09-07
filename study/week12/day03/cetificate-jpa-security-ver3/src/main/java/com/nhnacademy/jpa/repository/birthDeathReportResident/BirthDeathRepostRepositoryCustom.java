package com.nhnacademy.jpa.repository.birthDeathReportResident;

import com.nhnacademy.jpa.dto.birthDeathReportResident.BirthDeathReportResidentDto;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BirthDeathRepostRepositoryCustom {

    BirthDeathReportResidentDto getBirthDeathReportResident(Long serialNumber, String birthDeathTypeCode);
}
