package com.nhnacademy.jpa.dto.familyRelationship;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FamilyRelationshipCreateRequest {
    private Long familyResidentSerialNumber;
    private String familyRelationshipCode;
}
