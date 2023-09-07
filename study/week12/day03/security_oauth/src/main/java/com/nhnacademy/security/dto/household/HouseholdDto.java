package com.nhnacademy.security.dto.household;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Date;

@Getter
@Setter
@NoRepositoryBean
@ToString
public class HouseholdDto {

    private Long householdSerialNumber;
    private Long residentSerialNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date householdCompositionDate;
    private String householdCompositionReasonCode;
    private String currentHouseMovementAddress;

    @QueryProjection
    public HouseholdDto(Long householdSerialNumber, Long residentSerialNumber, Date householdCompositionDate, String householdCompositionReasonCode, String currentHouseMovementAddress) {
        this.householdSerialNumber = householdSerialNumber;
        this.residentSerialNumber = residentSerialNumber;
        this.householdCompositionDate = householdCompositionDate;
        this.householdCompositionReasonCode = householdCompositionReasonCode;
        this.currentHouseMovementAddress = currentHouseMovementAddress;
    }
}
