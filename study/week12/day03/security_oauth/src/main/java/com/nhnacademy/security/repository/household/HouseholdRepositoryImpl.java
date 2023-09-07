package com.nhnacademy.security.repository.household;

import com.nhnacademy.security.dto.household.HouseholdDto;
import com.nhnacademy.security.dto.household.QHouseholdDto;
import com.nhnacademy.security.entity.Household;
import com.nhnacademy.security.entity.QHousehold;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class HouseholdRepositoryImpl extends QuerydslRepositorySupport implements HouseholdRepositoryCustom {

    public HouseholdRepositoryImpl() {
        super(Household.class);
    }

    @Override
    public HouseholdDto getHousehold(Long householdSerialNumber) {
        QHousehold household = QHousehold.household;

        return from(household)
                .select(new QHouseholdDto(
                        household.householdSerialNumber,
                        household.resident.residentSerialNumber,
                        household.householdCompositionDate,
                        household.householdCompositionReasonCode,
                        household.currentHouseMovementAddress))
                .where(household.householdSerialNumber.eq(householdSerialNumber))
                .fetchOne();
    }
}
