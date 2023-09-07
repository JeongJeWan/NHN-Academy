package com.nhnacademy.jpa.repository.householdCompositionResident;

import com.nhnacademy.jpa.config.RootConfig;
import com.nhnacademy.jpa.config.WebConfig;
import com.nhnacademy.jpa.entity.HouseholdCompositionResident;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class HouseholdCompositionResidentRepositoryTest {

    @Autowired
    private HouseholdCompositionResidentRepository householdCompositionResidentRepository;

    @Test
    void deleteHouseholdCompositionResidentBy() {
        householdCompositionResidentRepository.deleteHouseholdCompositionResidentBy(1L);

        List<HouseholdCompositionResident> householdCompositionResidents = householdCompositionResidentRepository.getHouseholdCompositionResidentsBy(1L);
        householdCompositionResidents.stream().forEach(System.out::println);
//
        assertThat(householdCompositionResidents).isEmpty();
    }

    @Test
    void getHouseholdCompositionResidentsBy() {
        List<HouseholdCompositionResident> householdCompositionResidents = householdCompositionResidentRepository.getHouseholdCompositionResidentsBy(1L);
        householdCompositionResidents.stream().forEach(System.out::println);

        assertThat(householdCompositionResidents).hasSize(4);
        assertThat(householdCompositionResidents.get(1).getHouseholdRelationshipCode()).isEqualTo("배우자");
    }

    @Test
    void getHouseholdSerialNumber() {
        Long householdSerialNumber = householdCompositionResidentRepository.getHouseholdSerialNumber(5L);

        assertThat(householdSerialNumber).isEqualTo(1L);
    }
}