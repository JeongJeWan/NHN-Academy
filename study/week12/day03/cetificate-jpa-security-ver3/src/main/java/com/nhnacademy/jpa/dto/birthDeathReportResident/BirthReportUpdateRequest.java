package com.nhnacademy.jpa.dto.birthDeathReportResident;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BirthReportUpdateRequest {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDeathReportDate;
    private String emailAddress;
    private String phoneNumber;
}
