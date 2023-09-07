package com.nhnacademy.springmvcboard.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NamedEntityGraphs({
        @NamedEntityGraph(name = "entityUserWithEntityPost", attributeNodes = {
                @NamedAttributeNode("post")
        })
})
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class EntityUser {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "profile_file_name")
    private String profileFileName;
    @JsonFormat(pattern = "yyyy-MM-dd:HH-mm-ss")
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Transient
    private MultipartFile profileFile;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<EntityPost> post;
}
