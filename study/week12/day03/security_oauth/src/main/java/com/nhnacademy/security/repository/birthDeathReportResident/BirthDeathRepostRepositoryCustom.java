package com.nhnacademy.security.repository.birthDeathReportResident;

import com.nhnacademy.security.dto.birthDeathReportResident.BirthDeathReportResidentDto;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BirthDeathRepostRepositoryCustom {

    BirthDeathReportResidentDto getBirthDeathReportResident(Long serialNumber, String birthDeathTypeCode);
}
