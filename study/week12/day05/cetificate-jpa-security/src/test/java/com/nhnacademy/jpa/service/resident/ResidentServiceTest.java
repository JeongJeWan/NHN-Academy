package com.nhnacademy.jpa.service.resident;

import com.nhnacademy.jpa.config.RootConfig;
import com.nhnacademy.jpa.config.WebConfig;
import com.nhnacademy.jpa.dto.resident.ResidentCreateRequest;
import com.nhnacademy.jpa.dto.resident.ResidentDto;
import com.nhnacademy.jpa.dto.resident.ResidentUpdateRequest;
import com.nhnacademy.jpa.entity.HouseholdCompositionResident;
import com.nhnacademy.jpa.entity.Resident;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
public class ResidentServiceTest {

    @Autowired
    ResidentService residentService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void getResidentTotalPages() {
    }

    @Test
    void gerResidents() {
        Pageable pageable = Pageable.ofSize(7);
        List<Resident> residents = residentService.gerResidents(pageable);

        assertThat(residents).hasSize(7);
    }

    @Test
    void getResident() {

        ResidentDto resident = residentService.getResident(4L);

        assertThat(resident.getName()).isEqualTo("남기준");
    }

    @Test
    void createResident() {
        ResidentCreateRequest resident = new ResidentCreateRequest();
        resident.setResidentSerialNumber(8L);
        resident.setName("marco");
        resident.setResidentRegistrationNumber("790510-1234564");
        resident.setGenderCode("남");
        resident.setBirthDate(LocalDateTime.parse("1979-05-10T20:45:00"));
        resident.setBirthPlaceCode("병원");
        resident.setRegistrationBaseAddress("경기도 성남시 분당구 대왕판교로645번길");
        resident.setDeathDate(null);
        resident.setDeathPlaceCode(null);
        resident.setDeathPlaceAddress(null);
        resident.setId("user4");
        resident.setPassword(passwordEncoder.encode("1234"));
        resident.setEmail("user3@nhn.com");

        Resident createResident = residentService.createResident(resident);

        System.out.println(createResident);

        assertThat(createResident.getName()).isEqualTo("marco");

    }

    @Test
    void updateResident() {
        ResidentUpdateRequest residentUpdateRequest = new ResidentUpdateRequest();
        residentUpdateRequest.setName("변경된 marco");
        residentUpdateRequest.setDeathDate(LocalDateTime.now());
        residentUpdateRequest.setDeathPlaceAddress("광주광역시 서석동");
        residentUpdateRequest.setDeathPlaceCode("서해안 고속도로");

        ResidentDto updateResident = residentService.updateResident(8L, residentUpdateRequest);

        System.out.println(updateResident);

        assertThat(updateResident.getName()).isEqualTo("변경된 marco");
    }

    @Test
    void deleteResident() {
        residentService.deleteResident(8L);

        assertThat(residentService.getResident(8L)).isNull();
    }


    @Test
    void getFamilies() {
        Long householdeSerialNumber = residentService.getHouseholdSerialNumber(4L);
        List<HouseholdCompositionResident> residents = residentService.getFamilies(householdeSerialNumber);

        residents.stream().forEach(System.out::println);

        assertThat(residents).hasSize(4);
    }
}