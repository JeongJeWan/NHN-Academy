package com.nhnacademy.springbootopenapi.entity;

import lombok.*;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Long id;
    private String number;
    private Integer balance;
}
