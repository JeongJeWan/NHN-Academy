package com.nhnacademy.jpa.repository.birthDeathReportResident;

import com.nhnacademy.jpa.config.RootConfig;
import com.nhnacademy.jpa.config.WebConfig;
import com.nhnacademy.jpa.entity.BirthDeathReportResident;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class BirthDeathRepostRepositoryTest {

    @Autowired
    private BirthDeathRepostRepository birthDeathRepostRepository;


    @Test
    void getBirthReportResidentBy() {
        BirthDeathReportResident birthDeathReportResident = birthDeathRepostRepository.getBirthReportResidentBy(7L);

        assertThat(birthDeathReportResident.getResident().getName()).isEqualTo("남기석");
    }

    @Test
    void getDeathReportResidentBy() {
        BirthDeathReportResident birthDeathReportResident = birthDeathRepostRepository.getDeathReportResidentBy(1L);

        assertThat(birthDeathReportResident.getResident().getName()).isEqualTo("남길동");
    }

    @Test
    void updateBirthDeathReportResident() {
        Long reportResidentSerialNumber = 4L;
        Long residentNumber = 7L;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        String emailAddress = "marco@nhnacademy.com";
        String phoneNumber = "010-2323-3434";
        try {
            date = format.parse("2019-12-30");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        birthDeathRepostRepository.updateBirthDeathReportResident(reportResidentSerialNumber, residentNumber, date, emailAddress, phoneNumber);

        BirthDeathReportResident birthDeathReportResident = birthDeathRepostRepository.getBirthReportResidentBy(residentNumber);

        assertThat(birthDeathReportResident.getEmailAddress()).isEqualTo("marco@nhnacademy.com");
    }

    @Test
    void deleteBirthDeathReportResident() {
        Long reportResidentSerialNumber = 4L;
        Long residentNumber = 7L;

        birthDeathRepostRepository.deleteBirthDeathReportResident(reportResidentSerialNumber, residentNumber);

        BirthDeathReportResident birthDeathReportResident = birthDeathRepostRepository.getBirthReportResidentBy(residentNumber);
        assertThat(birthDeathReportResident).isNull();
    }
}