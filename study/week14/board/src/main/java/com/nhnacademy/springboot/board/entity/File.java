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
@Table(name = "BoardFiles")
public class File {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
