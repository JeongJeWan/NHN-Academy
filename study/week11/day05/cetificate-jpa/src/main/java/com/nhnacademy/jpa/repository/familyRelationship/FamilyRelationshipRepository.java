package com.nhnacademy.jpa.repository.familyRelationship;

import com.nhnacademy.jpa.entity.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface FamilyRelationshipRepository extends FamilyRelationshipRepositoryCustom, JpaRepository<FamilyRelationship, FamilyRelationship.Pk> {

    @Query(value = "select * family_relationship " +
            "where base_resident_serial_number = ?1 and family_relationship_code = '부'", nativeQuery = true)
    FamilyRelationship getFatherRelationshipBy(Long baseResidentSerialNumber);

    @Query(value = "select * from family_relationship " +
            "where base_resident_serial_number = ?1 and family_relationship_code = '모'", nativeQuery = true)
    FamilyRelationship getMotherRelationshipBy(Long baseResidentSerialNumber);

    @Transactional
    @Modifying
    @Query("update FamilyRelationship f " +
            "set f.familyRelationshipCode = ?3 " +
            "where f.pk.baseResidentSerialNumber = ?1 and f.pk.familyResidentSerialNumber = ?2")
    void updateFamilyRelationship(Long baseResidentSerialNumber, Long familyResidentSerialNumber, String familyRelationshipCode);

    @Transactional
    @Modifying
    @Query("delete from FamilyRelationship f " +
            "where f.pk.baseResidentSerialNumber = ?1 and f.pk.familyResidentSerialNumber = ?2")
    void deleteFamilyRelationship(Long baseResidentSerialNumber, Long familyResidentSerialNumber);
}
