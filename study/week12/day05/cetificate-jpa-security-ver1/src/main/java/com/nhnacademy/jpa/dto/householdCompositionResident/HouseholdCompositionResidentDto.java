package com.nhnacademy.jpa.dto.householdCompositionResident;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class HouseholdCompositionResidentDto {
    private Long householdSerialNumber;
    private Long residentSerialNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date reportDate;
    private String householdRelationshipCode;
    private String householdCompositionChangeReasonCode;

    @QueryProjection
    public HouseholdCompositionResidentDto(Long householdSerialNumber, Long residentSerialNumber, Date reportDate, String householdRelationshipCode, String householdCompositionChangeReasonCode) {
        this.householdSerialNumber = householdSerialNumber;
        this.residentSerialNumber = residentSerialNumber;
        this.reportDate = reportDate;
        this.householdRelationshipCode = householdRelationshipCode;
        this.householdCompositionChangeReasonCode = householdCompositionChangeReasonCode;
    }
}
