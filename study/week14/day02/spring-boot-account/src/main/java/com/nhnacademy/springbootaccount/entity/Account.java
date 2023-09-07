package com.nhnacademy.springbootaccount.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Account {
    @Id
    private Long id;
    private String number;
    private Integer balance;

    public Account(){

    }

    public Account(Long id, String number, Integer balance) {
        this.id = id;
        this.number = number;
        this.balance = balance;
    }
}
