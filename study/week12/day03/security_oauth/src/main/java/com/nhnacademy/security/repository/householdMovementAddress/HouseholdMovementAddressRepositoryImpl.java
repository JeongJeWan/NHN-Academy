package com.nhnacademy.security.repository.householdMovementAddress;

import com.nhnacademy.security.dto.householdMovementAddress.HouseholdMovementAddressDto;
import com.nhnacademy.security.dto.householdMovementAddress.QHouseholdMovementAddressDto;
import com.nhnacademy.security.entity.HouseholdMovementAddress;
import com.nhnacademy.security.entity.QHouseholdMovementAddress;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Date;

public class HouseholdMovementAddressRepositoryImpl extends QuerydslRepositorySupport implements HouseholdMovementAddressRepositoryCustom {
    public HouseholdMovementAddressRepositoryImpl() {
        super(HouseholdMovementAddress.class);
    }

    @Override
    public HouseholdMovementAddressDto getHouseholdMovementAddress(Long householdSerialNumber, Date houseMovementReportDate) {

        QHouseholdMovementAddress householdMovementAddress = QHouseholdMovementAddress.householdMovementAddress;

        String date = houseMovementReportDate.getYear() + "-" + houseMovementReportDate.getMonth() + "-" + houseMovementReportDate.getDate();

        return from(householdMovementAddress)
                .select(new QHouseholdMovementAddressDto(
                        householdMovementAddress.pk.householdSerialNumber,
                        householdMovementAddress.pk.houseMovementReportDate,
                        householdMovementAddress.houseMovementAddress,
                        householdMovementAddress.lastAddressYn))
                .where(householdMovementAddress.pk.householdSerialNumber.eq(householdSerialNumber))
                .where(householdMovementAddress.pk.houseMovementReportDate.eq(houseMovementReportDate))
                .fetchOne();
    }
}
