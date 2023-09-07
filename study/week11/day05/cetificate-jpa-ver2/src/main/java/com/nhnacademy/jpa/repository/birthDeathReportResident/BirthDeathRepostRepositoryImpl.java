package com.nhnacademy.jpa.repository.birthDeathReportResident;

import com.nhnacademy.jpa.dto.birthDeathReportResident.BirthDeathReportResidentDto;
import com.nhnacademy.jpa.dto.birthDeathReportResident.QBirthDeathReportResidentDto;
import com.nhnacademy.jpa.entity.BirthDeathReportResident;
import com.nhnacademy.jpa.entity.QBirthDeathReportResident;
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
