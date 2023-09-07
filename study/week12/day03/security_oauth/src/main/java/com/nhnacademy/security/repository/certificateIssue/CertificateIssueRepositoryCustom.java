package com.nhnacademy.security.repository.certificateIssue;

import com.nhnacademy.security.dto.certificateIssue.FamilyRelationAndNumber;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface CertificateIssueRepositoryCustom {

    List<FamilyRelationAndNumber> getRelationAndNumber(Long serialNumber);
}
