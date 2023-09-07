package com.nhnacademy.jpa.service.household;

import com.nhnacademy.jpa.dto.familyRelationship.FamilyRelationshipDto;
import com.nhnacademy.jpa.dto.household.HouseholdCreateRequest;
import com.nhnacademy.jpa.dto.household.HouseholdDto;
import com.nhnacademy.jpa.entity.Household;
import com.nhnacademy.jpa.entity.HouseholdCompositionResident;
import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.repository.familyRelationship.FamilyRelationshipRepository;
import com.nhnacademy.jpa.repository.household.HouseholdRepository;
import com.nhnacademy.jpa.repository.householdCompositionResident.HouseholdCompositionResidentRepository;
import com.nhnacademy.jpa.repository.resident.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HouseholdServiceImpl implements HouseholdService{

    private final HouseholdRepository householdRepository;
    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;
    private final ResidentRepository residentRepository;
    private final FamilyRelationshipRepository familyRelationshipRepository;

    @Override
    @Transactional
    public HouseholdDto createHousehold(HouseholdCreateRequest request) {
        Household household = new Household();
        household.setHouseholdSerialNumber(request.getHouseholdSerialNumber());

        Optional<Resident> resident = residentRepository.findById(household.getHouseholdSerialNumber());
        household.setHouseholdResident(resident.get());

        household.setHouseholdCompositionDate(request.getHouseholdCompositionDate());
        household.setHouseholdCompositionReasonCode(request.getHouseholdCompositionReasonCode());
        household.setCurrentHouseMovementAddress(request.getCurrentHouseMovementAddress());

        householdRepository.save(household);

        HouseholdCompositionResident householdCompositionResident = new HouseholdCompositionResident();
        householdCompositionResident.setPk(new HouseholdCompositionResident.Pk(
                request.getHouseholdSerialNumber(), request.getHouseholdResidentSerialNumber()
        ));

        householdCompositionResident.setReportDate(request.getHouseholdCompositionDate());

        FamilyRelationshipDto familyRelationshipDto = familyRelationshipRepository.getFamilyRelationship(request.getHouseholdSerialNumber(), request.getHouseholdResidentSerialNumber());
        householdCompositionResident.setHouseholdRelationshipCode(familyRelationshipDto.getFamilyRelationshipCode());
        householdCompositionResident.setHouseholdCompositionChangeReasonCode(request.getHouseholdCompositionReasonCode());
        householdCompositionResident.setHousehold(household);
        householdCompositionResident.setResident(resident.get());

        householdCompositionResidentRepository.save(householdCompositionResident);

        return householdRepository.getHousehold(request.getHouseholdSerialNumber());
    }

    @Override
    public void deleteHousehold(Long householdSerialNumber) {
        householdRepository.deleteById(householdSerialNumber);
        householdCompositionResidentRepository.deleteHouseholdCompositionResidentBy(householdSerialNumber);
    }
}
