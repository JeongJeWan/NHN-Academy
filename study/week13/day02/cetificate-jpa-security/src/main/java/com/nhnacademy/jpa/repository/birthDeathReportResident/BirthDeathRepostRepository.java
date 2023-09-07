package com.nhnacademy.jpa.repository.birthDeathReportResident;

import com.nhnacademy.jpa.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface BirthDeathRepostRepository extends BirthDeathRepostRepositoryCustom, JpaRepository<BirthDeathReportResident, BirthDeathReportResident.Pk> {

    @Query(value = "select * from birth_death_report_resident " +
            "where resident_serial_number = ?1 and birth_death_type_code = '출생'", nativeQuery = true)
    BirthDeathReportResident getBirthReportResidentBy(Long serialNumber);

    @Query(value = "select * from birth_death_report_resident " +
            "where resident_serial_number =?1 and  birth_death_type_code = '사망'", nativeQuery = true)
    BirthDeathReportResident getDeathReportResidentBy(Long serialNumber);

    @Transactional
    @Modifying
    @Query("update BirthDeathReportResident b " +
            "set b.birthDeathReportDate = ?3, b.emailAddress = ?4, b.phoneNumber = ?5 " +
            "where b.pk.residentSerialNumber = ?2 and b.reportResident.residentSerialNumber = ?1")
    void updateBirthDeathReportResident(Long reportResidentSerialNumber, Long residentSerialNumber, Date date, String emailAddress, String phoneNumber);

    @Transactional
    @Modifying
    @Query("delete BirthDeathReportResident b " +
            "where b.reportResident.residentSerialNumber = ?1 and b.pk.residentSerialNumber = ?2")
    void deleteBirthDeathReportResident(Long reportResidentSerialNumber, Long residentSerialNumber);
}
