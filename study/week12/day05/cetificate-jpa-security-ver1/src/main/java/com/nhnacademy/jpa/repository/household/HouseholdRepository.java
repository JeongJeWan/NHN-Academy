package com.nhnacademy.jpa.repository.household;

import com.nhnacademy.jpa.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HouseholdRepository extends HouseholdRepositoryCustom, JpaRepository<Household, Long> {

    @Query(value = "select * from household " +
            "where household_resident_serial_number = ?1", nativeQuery = true)
    Household getHouseholdBySerialNumber(Long serialNumber);
}
