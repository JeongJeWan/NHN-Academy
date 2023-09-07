package com.nhnacademy.jpa.dto.householdMovementAddress;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class HouseholdMovementAddressDto {
    private Long householdSerialNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date houseMovementReportDate;
    private String houseMovementAddress;
    private String lastAddressYn;

    @QueryProjection
    public HouseholdMovementAddressDto(Long householdSerialNumber, Date houseMovementReportDate, String houseMovementAddress, String lastAddressYn) {
        this.householdSerialNumber = householdSerialNumber;
        this.houseMovementReportDate = houseMovementReportDate;
        this.houseMovementAddress = houseMovementAddress;
        this.lastAddressYn = lastAddressYn;
    }
}
