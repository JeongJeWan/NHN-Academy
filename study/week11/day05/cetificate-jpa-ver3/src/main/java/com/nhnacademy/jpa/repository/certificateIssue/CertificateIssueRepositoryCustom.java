package com.nhnacademy.jpa.repository.certificateIssue;

import com.nhnacademy.jpa.dto.certificateIssue.FamilyRelationAndNumber;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface CertificateIssueRepositoryCustom {

    List<FamilyRelationAndNumber> getRelationAndNumber(Long serialNumber);
}
