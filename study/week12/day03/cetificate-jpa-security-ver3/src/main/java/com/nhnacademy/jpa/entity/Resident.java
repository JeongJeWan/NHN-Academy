package com.nhnacademy.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "resident")
public class Resident {

    @Id
    @Column(name = "resident_serial_number", length = 11)
    private Long residentSerialNumber;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "resident_registration_number", length = 300)
    private String residentRegistrationNumber;

    @Column(name = "gender_code", length = 20)
    private String genderCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "birth_place_code", length = 20)
    private String birthPlaceCode;

    @Column(name = "registration_base_address", length = 500)
    private String registrationBaseAddress;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name = "death_date")
    private LocalDateTime deathDate;

    @Column(name = "death_place_code", length = 20)
    private String deathPlaceCode;

    @Column(name = "death_place_address", length = 500)
    private String deathPlaceAddress;

    @Column(name = "id")
    private String id;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
}
