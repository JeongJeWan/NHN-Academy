package com.nhnacademy.jpa.repository.resident;

import com.nhnacademy.jpa.entity.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface ResidentRepository extends ResidentRepositoryCustom, JpaRepository<Resident, Long> {

    @Transactional
    @Modifying
    @Query("update Resident r SET r.name = ?2, " +
            "r.deathDate = ?3, r.deathPlaceCode = ?4, " +
            "r.deathPlaceAddress = ?5 " +
            "where r.residentSerialNumber = ?1")
    int updateResident(Long residentSerialNumber, String name, LocalDateTime date, String deathPlaceCode, String deathPlaceAddress);

    @Transactional
    void deleteByResidentSerialNumber(Long residentSerialNumber);

    Page<Resident> getAllBy(Pageable pageable);
}
