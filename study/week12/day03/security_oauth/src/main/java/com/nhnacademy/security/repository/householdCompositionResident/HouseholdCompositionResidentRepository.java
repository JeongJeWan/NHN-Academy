package com.nhnacademy.security.repository.householdCompositionResident;

import com.nhnacademy.security.entity.HouseholdCompositionResident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HouseholdCompositionResidentRepository extends HouseholdCompositionResidentRepositoryCustom, JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.Pk> {

    @Transactional
    @Modifying
    @Query("delete from HouseholdCompositionResident h " +
            "where h.pk.householdSerialNumber = ?1")
    void deleteHouseholdCompositionResidentBy(Long householdSerialNumber);

    @Query(value = "select * from household_composition_resident " +
            "where household_serial_number = ?1", nativeQuery = true)
    List<HouseholdCompositionResident> getHouseholdCompositionResidentsBy(Long householdSerialNumber);

    @Query(value = "select household_serial_number from household_composition_resident " +
            "where resident_serial_number = ?1", nativeQuery = true)
    Long getHouseholdSerialNumber(Long residentSerialNumber);
}
