package com.nhnacademy.jpa.repository.resident;

import com.nhnacademy.jpa.config.RootConfig;
import com.nhnacademy.jpa.config.WebConfig;
import com.nhnacademy.jpa.entity.Resident;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
public class ResidentRepositoryTest {

    @Autowired
    private ResidentRepository residentRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    @BeforeEach
//    public void setUp(){
//        Resident resident = new Resident();
//        resident.setResidentSerialNumber(4L);
//        resident.setName("남기준");
//        resident.setResidentRegistrationNumber("790510-1234564");
//        resident.setGenderCode("남");
//        resident.setBirthDate(LocalDateTime.parse("1979-05-10T20:45:00"));
//        resident.setBirthPlaceCode("병원");
//        resident.setRegistrationBaseAddress("경기도 성남시 분당구 대왕판교로645번길");
//        resident.setDeathDate(null);
//        resident.setDeathPlaceCode(null);
//        resident.setDeathPlaceAddress(null);
//        resident.setId("user4");
//        resident.setPassword(passwordEncoder.encode("1234"));
//        resident.setEmail("user3@nhn.com");
//
//        residentRepository.save(resident);
//    }


    @Test
    @DisplayName("주민 업데이트")
    void updateResident() {
        Long residentSerialNumber = 4L;
        String name = "변경된 남기준";
        LocalDateTime deathDate = LocalDateTime.now();
        String deathCode = "고속도로";
        String deathAddress = "서해안 고속도로";

        residentRepository.updateResident(residentSerialNumber, name, deathDate, deathCode, deathAddress);

        Optional<Resident> updateResident = residentRepository.findById(residentSerialNumber);

        assertThat(updateResident).isPresent();
        assertThat(updateResident.get().getDeathPlaceCode()).isEqualTo(deathCode);
        assertThat(updateResident.get().getDeathPlaceAddress()).isEqualTo(deathAddress);
    }

    @Test
    void deleteByResidentSerialNumber() {

        Long serialNumber = 8L;

        residentRepository.deleteByResidentSerialNumber(serialNumber);

        assertThat(residentRepository.findById(serialNumber)).isEmpty();

    }

    @Test
    void getAllBy() {
        Pageable pageable = Pageable.ofSize(5);
        Page<Resident> resident = residentRepository.getAllBy(pageable);

        resident.stream().forEach(System.out::println);
    }

    @Test
    void findById() {
        String id = "user4";

        Optional<Resident> resident = residentRepository.findById(id);

        assertThat(resident.get().getName()).isEqualTo("남기준");
    }
}