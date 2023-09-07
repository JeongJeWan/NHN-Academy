package com.nhnacademy.jpa.dto.householdMovementAddress;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HouseholdMovementAddressCreateRequest {

    private Date houseMovementReportDate;
    private String houseMovementAddress;
    private String lastAddressYn;
}
