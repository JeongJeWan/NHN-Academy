package com.nhnacademy.security.repository.birthDeathReportResident;

import com.nhnacademy.security.dto.birthDeathReportResident.BirthDeathReportResidentDto;
import com.nhnacademy.security.dto.birthDeathReportResident.QBirthDeathReportResidentDto;
import com.nhnacademy.security.entity.BirthDeathReportResident;
import com.nhnacademy.security.entity.QBirthDeathReportResident;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class BirthDeathRepostRepositoryImpl extends QuerydslRepositorySupport implements BirthDeathRepostRepositoryCustom {
    public BirthDeathRepostRepositoryImpl() {
        super(BirthDeathReportResident.class);
    }

    @Override
    public BirthDeathReportResidentDto getBirthDeathReportResident(Long serialNumber, String birthDeathTypeCode) {
        QBirthDeathReportResident birthDeathReportResident = QBirthDeathReportResident.birthDeathReportResident;

        return from(birthDeathReportResident)
                .select(new QBirthDeathReportResidentDto(
                        birthDeathReportResident.pk.residentSerialNumber,
                        birthDeathReportResident.pk.birthDeathTypeCode,
                        birthDeathReportResident.reportResident.residentSerialNumber,
                        birthDeathReportResident.birthDeathReportDate,
                        birthDeathReportResident.birthReportQualificationsCode,
                        birthDeathReportResident.deathReportQualificationsCode,
                        birthDeathReportResident.emailAddress,
                        birthDeathReportResident.phoneNumber))
                .where(birthDeathReportResident.pk.residentSerialNumber.eq(serialNumber))
                .where(birthDeathReportResident.pk.birthDeathTypeCode.eq(birthDeathTypeCode))
                .fetchOne();
    }
}
