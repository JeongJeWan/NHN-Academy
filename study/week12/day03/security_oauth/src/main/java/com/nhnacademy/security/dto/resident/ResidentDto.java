package com.nhnacademy.security.dto.resident;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
@NoArgsConstructor
public class ResidentDto {
    private Long residentSerialNumber;
    private String name;
    private String residentRegistrationNumber;
    private String genderCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthDate;
    private String birthPlaceCode;
    private String registrationBaseAddress;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deathDate;
    private String deathPlaceCode;
    private String deathPlaceAddress;

    private String id;
    private String password;
    private String email;

    //@QueryProjection은 생성자를 통해 DTO를 조회하는 방법과 함께 사용
    @QueryProjection
    public ResidentDto(Long residentSerialNumber, String name, String residentRegistrationNumber, String genderCode, LocalDateTime birthDate, String birthPlaceCode, String registrationBaseAddress, LocalDateTime deathDate, String deathPlaceCode, String deathPlaceAddress, String id, String password, String email) {
        this.residentSerialNumber = residentSerialNumber;
        this.name = name;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.genderCode = genderCode;
        this.birthDate = birthDate;
        this.birthPlaceCode = birthPlaceCode;
        this.registrationBaseAddress = registrationBaseAddress;
        this.deathDate = deathDate;
        this.deathPlaceCode = deathPlaceCode;
        this.deathPlaceAddress = deathPlaceAddress;
        this.id = id;
        this.password = password;
        this.email = email;
    }

}
