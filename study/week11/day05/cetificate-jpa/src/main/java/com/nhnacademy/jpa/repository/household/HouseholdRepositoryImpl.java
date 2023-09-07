package com.nhnacademy.jpa.repository.household;

import com.nhnacademy.jpa.dto.household.HouseholdDto;
import com.nhnacademy.jpa.dto.household.QHouseholdDto;
import com.nhnacademy.jpa.entity.Household;
import com.nhnacademy.jpa.entity.QHousehold;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class HouseholdRepositoryImpl extends QuerydslRepositorySupport implements HouseholdRepositoryCustom{
    public HouseholdRepositoryImpl() {
        super(Household.class);
    }

    @Override
    public HouseholdDto getHousehold(Long householdSerialNumber) {
        QHousehold household = QHousehold.household;

        return from(household)
                .select(new QHouseholdDto(
                        household.householdSerialNumber,
                        household.householdResident.residentSerialNumber,
                        household.householdCompositionDate,
                        household.householdCompositionReasonCode,
                        household.currentHouseMovementAddress))
                .where(household.householdSerialNumber.eq(householdSerialNumber))
                .fetchOne();
    }
}
