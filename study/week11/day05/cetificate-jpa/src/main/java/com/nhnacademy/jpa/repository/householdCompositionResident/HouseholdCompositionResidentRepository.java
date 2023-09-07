package com.nhnacademy.jpa.repository.householdCompositionResident;

import com.nhnacademy.jpa.entity.HouseholdCompositionResident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HouseholdCompositionResidentRepository extends HouseholdCompositionResidentRepositoryCustom, JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.Pk> {

    @Query("delete from HouseholdCompositionResident h " +
            "where h.pk.householdSerialNumber = ?1")
    void deleteHouseholdCompositionResidentBy(Long householdSerialNumber);

    @Query(value = "select * from household_resident_serial_number " +
            "where household_resident_serial_number = ?1", nativeQuery = true)
    List<HouseholdCompositionResident> getHouseholdCompositionResidentsBy(Long householdSerialNumber);
}
