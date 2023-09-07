package com.nhnacademy.jpa.repository.householdMovementAddress;

import com.nhnacademy.jpa.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface HouseholdMovementAddressRepository extends HouseholdMovementAddressRepositoryCustom, JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.Pk> {

    @Query(value = "select * from household_movement_address " +
            "where household_serial_number = ?1 " +
            "order by house_movement_report_date desc", nativeQuery = true)
    List<HouseholdMovementAddress> getHouseholdMovementAddressesBy(Long householdSerialNumber);

    @Transactional
    @Modifying
    @Query("update HouseholdMovementAddress h " +
            "set h.houseMovementAddress = ?3, h.lastAddressYn = ?4 " +
            "where h.pk.householdSerialNumber = ?1 and h.pk.houseMovementReportDate = ?2")
    void updateHouseholdMovementAddress(Long householdSerialNumber, Date houseMovementReportDate, String houseMovementAddress, String lastAddressYn);

    @Transactional
    @Modifying
    @Query("delete HouseholdMovementAddress h " +
            "where h.pk.householdSerialNumber = ?1 and h.pk.houseMovementReportDate = ?2")
    void deleteHouseholdMovementAddress(Long householdSerialNumber, Date houseMovementReportDate);
}
