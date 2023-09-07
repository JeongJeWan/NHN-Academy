package com.nhnacademy.jpa.repository.householdCompositionResident;

import com.nhnacademy.jpa.dto.householdCompositionResident.HouseholdCompositionResidentDto;
import com.nhnacademy.jpa.dto.householdCompositionResident.QHouseholdCompositionResidentDto;
import com.nhnacademy.jpa.entity.HouseholdCompositionResident;
import com.nhnacademy.jpa.entity.QHouseholdCompositionResident;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class HouseholdCompositionResidentRepositoryImpl extends QuerydslRepositorySupport implements HouseholdCompositionResidentRepositoryCustom{
    public HouseholdCompositionResidentRepositoryImpl() {
        super(HouseholdCompositionResident.class);
    }

    @Override
    public HouseholdCompositionResidentDto getHouseholdCompositionResidentRepositoryby(Long residentSerialNumber) {
        QHouseholdCompositionResident householdCompositionResident = QHouseholdCompositionResident.householdCompositionResident;

        return from(householdCompositionResident)
                .select(new QHouseholdCompositionResidentDto(
                        householdCompositionResident.household.householdSerialNumber,
                        householdCompositionResident.resident.residentSerialNumber,
                        householdCompositionResident.reportDate,
                        householdCompositionResident.householdRelationshipCode,
                        householdCompositionResident.householdCompositionChangeReasonCode))
                .where(householdCompositionResident.resident.residentSerialNumber.eq(residentSerialNumber))
                .fetchOne();
    }
}
