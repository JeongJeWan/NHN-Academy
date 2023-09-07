package com.nhnacademy.springboot.board.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BoardUsers")
public class User {
    @Id
    @Column(name = "id")
    private String userId;

    @Column
    private String password;

    @Column
    private String name;

    @Column(name = "authority_id")
    private Long authority;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
