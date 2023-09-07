package com.nhnacademy.springmvcboard.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NamedEntityGraphs(@NamedEntityGraph(name = "entityPostWithEntityUser", attributeNodes = {
        @NamedAttributeNode("user")
}))
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "posts")
public class EntityPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;
    private String title;
    private String content;
    @Column(name = "write_time")
    private LocalDateTime writeTime;
    @Column(name = "view_count")
    private int viewCount;

    @ManyToOne
    @JoinColumn(name = "writer_user_id", referencedColumnName = "user_id")
    private EntityUser user;
}
