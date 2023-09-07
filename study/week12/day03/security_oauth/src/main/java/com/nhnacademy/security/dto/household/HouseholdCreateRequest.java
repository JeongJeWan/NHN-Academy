package com.nhnacademy.security.dto.household;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HouseholdCreateRequest {

    private Long householdSerialNumber;
    private Long householdResidentSerialNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date householdCompositionDate;
    private String householdCompositionReasonCode;
    private String currentHouseMovementAddress;
}
