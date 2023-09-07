package com.nhnacademy.security.dto.resident;

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
