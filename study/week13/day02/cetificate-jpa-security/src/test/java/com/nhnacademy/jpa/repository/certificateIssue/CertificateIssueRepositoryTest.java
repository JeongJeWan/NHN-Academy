package com.nhnacademy.jpa.repository.certificateIssue;

import com.nhnacademy.jpa.config.RootConfig;
import com.nhnacademy.jpa.config.WebConfig;
import com.nhnacademy.jpa.entity.CertificateIssue;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
@Slf4j
class CertificateIssueRepositoryTest {

    @Autowired
    private CertificateIssueRepository certificateIssueRepository;

    @Test
    void getFamilyCertificate() {
        CertificateIssue certificateIssue = certificateIssueRepository.getFamilyCertificate(4L);

        System.out.println(certificateIssue.toString());

        assertThat(certificateIssue.getResident().getName()).isEqualTo("남기준");
    }

    @Test
    void getResidentRegistrationCertificate() {
        CertificateIssue certificateIssue = certificateIssueRepository.getResidentRegistrationCertificate(4L);

        System.out.println(certificateIssue.toString());

        assertThat(certificateIssue.getResident().getName()).isEqualTo("남기준");

    }
}