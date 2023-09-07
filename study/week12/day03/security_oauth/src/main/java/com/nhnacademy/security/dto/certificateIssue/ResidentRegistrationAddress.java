package com.nhnacademy.security.dto.certificateIssue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResidentRegistrationAddress {
    private String addressComposition;
    private String address;
    private String reportDate;
}
