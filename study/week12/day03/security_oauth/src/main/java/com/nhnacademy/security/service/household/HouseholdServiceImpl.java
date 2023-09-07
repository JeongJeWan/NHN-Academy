package com.nhnacademy.security.service.household;

import com.nhnacademy.security.dto.familyRelationship.RelationshipDto;
import com.nhnacademy.security.dto.household.HouseholdCreateRequest;
import com.nhnacademy.security.dto.household.HouseholdDto;
import com.nhnacademy.security.entity.Household;
import com.nhnacademy.security.entity.HouseholdCompositionResident;
import com.nhnacademy.security.entity.Resident;
import com.nhnacademy.security.repository.familyRelationship.FamilyRelationshipRepository;
import com.nhnacademy.security.repository.household.HouseholdRepository;
import com.nhnacademy.security.repository.householdCompositionResident.HouseholdCompositionResidentRepository;
import com.nhnacademy.security.repository.resident.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class HouseholdServiceImpl implements HouseholdService {

    private final HouseholdRepository householdRepository;
    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;
    private final ResidentRepository residentRepository;
    private final FamilyRelationshipRepository familyRelationshipRepository;

    public HouseholdServiceImpl(HouseholdRepository householdRepository,
                                HouseholdCompositionResidentRepository householdCompositionResidentRepository,
                                ResidentRepository residentRepository,
                                FamilyRelationshipRepository familyRelationshipRepository) {
        this.householdRepository = householdRepository;
        this.householdCompositionResidentRepository = householdCompositionResidentRepository;
        this.residentRepository = residentRepository;
        this.familyRelationshipRepository = familyRelationshipRepository;
    }

    @Override
    @Transactional
    public HouseholdDto registerHousehold(HouseholdCreateRequest request) {
        Household household = new Household();
        household.setHouseholdSerialNumber(request.getHouseholdSerialNumber());

        Optional<Resident> resident = residentRepository.findById(request.getHouseholdResidentSerialNumber());
        household.setResident(resident.get());

        household.setHouseholdCompositionDate(request.getHouseholdCompositionDate());
        household.setHouseholdCompositionReasonCode(request.getHouseholdCompositionReasonCode());
        household.setCurrentHouseMovementAddress(request.getCurrentHouseMovementAddress());

        householdRepository.save(household);

        HouseholdCompositionResident householdCompositionResident = new HouseholdCompositionResident();
        householdCompositionResident.setPk(new HouseholdCompositionResident.Pk(
                request.getHouseholdSerialNumber(), request.getHouseholdResidentSerialNumber()));

        householdCompositionResident.setReportDate(request.getHouseholdCompositionDate());

        RelationshipDto relationship = familyRelationshipRepository.getRelationship(request.getHouseholdSerialNumber(), request.getHouseholdResidentSerialNumber());
        householdCompositionResident.setHouseholdRelationshipCode(relationship.getFamilyRelationshipCode());
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
