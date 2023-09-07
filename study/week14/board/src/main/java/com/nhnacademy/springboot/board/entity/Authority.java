package com.nhnacademy.springboot.board.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "BoardAuthority")
public class Authority {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column
    private String authority;
}
