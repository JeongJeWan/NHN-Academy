package com.nhnacademy.jpa.dto.resident;

import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResidentAccountRegister {
    private String id;
    private String password;
    private String email;
}
