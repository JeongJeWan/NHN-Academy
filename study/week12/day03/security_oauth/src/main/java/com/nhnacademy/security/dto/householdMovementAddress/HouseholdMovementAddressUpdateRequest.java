package com.nhnacademy.security.dto.householdMovementAddress;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HouseholdMovementAddressUpdateRequest {

    private String houseMovementAddress;
    private String lastAddressYn;
}
