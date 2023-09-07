package com.nhnacademy.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "certificate_issue")
public class CertificateIssue {

    @Id
    @Column(name = "certificate_confirmation_number", length = 20)
    private Long certificateConfirmationNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    @Column(name = "certificate_type_code", length = 20)
    private String certificateTypeCode;

    @Column(name = "certificate_issue_date")
    private Date certificateIssueDate;
}
