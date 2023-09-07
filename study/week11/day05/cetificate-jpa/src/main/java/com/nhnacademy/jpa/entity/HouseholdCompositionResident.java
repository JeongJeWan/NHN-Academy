package com.nhnacademy.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "household_composition_resident")
public class HouseholdCompositionResident {

    @EmbeddedId
    private Pk pk;

    @MapsId("householdSerialNumber")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "household_serial_number")
    private Household household;

    @MapsId("residentSerialNumber")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    @Column(name = "report_date")
    private Date reportDate;

    @Column(name = "household_relationship_code", length = 20)
    private String householdRelationshipCode;

    @Column(name = "household_composition_change_reason_code", length = 20)
    private String householdCompositionChangeReasonCode;

    @Getter @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {

        @Column(name = "household_serial_number", length = 11)
        private Long householdSerialNumber;

        @Column(name = "resident_serial_number", length = 11)
        private Long residentSerialNumber;
    }
}
