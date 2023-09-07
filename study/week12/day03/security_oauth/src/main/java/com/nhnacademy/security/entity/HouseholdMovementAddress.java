package com.nhnacademy.security.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "household_movement_address")
public class HouseholdMovementAddress {

    @EmbeddedId
    private Pk pk;

    @Column(name = "house_movement_address", length = 500)
    private String houseMovementAddress;

    @Column(name = "last_address_yn", length = 1)
    private String lastAddressYn;

    @MapsId("householdSerialNumber")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "household_serial_number")
    private Household household;

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {

        @Column(name = "household_serial_number", length = 11)
        private Long householdSerialNumber;

        @Column(name = "house_movement_report_date")
        private Date houseMovementReportDate;
    }



}
