package com.nhnacademy.jpa.dto.certificateIssue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResidentRegistrationInfo {
    private String relationship;
    private String name;
    private String idNumber;
    private String reportDate;
    private String reasonCode;
}
