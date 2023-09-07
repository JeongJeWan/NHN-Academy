package com.nhnacademy.springmvcboard.domain;


import javax.persistence.*;
import java.time.LocalDateTime;

/*
CREATE TABLE `posts` (
	`post_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ai' primary key,
    `title` varchar(255) NOT NULL COMMENT '제목',
    `content` text NOT NULL COMMENT '내용',
    `writer_user_id` varchar(255) NOT NULL COMMENT '사용자 아이디',
    `write_time` datetime NOT NULL default current_timestamp COMMENT '작성 시간',
    `view_count` INT NOT NULL COMMENT '조회 수'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 */
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;
    private String title;
    private String content;
    @Column(name = "writer_user_id")
    private String writerUserId;
    @Column(name = "write_time")
    private LocalDateTime writeTime;
    @Column(name = "view_count")
    private int viewCount;

    public Post() {

    }

    public Post(Long postId, String title, String content, String writerUserId, int viewCount) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.writerUserId = writerUserId;
        this.writeTime = LocalDateTime.now();
        this.viewCount = viewCount;
    }

    public Post(String title, String content, String writerUserId) {
        this.title = title;
        this.content = content;
        this.writerUserId =writerUserId;
        this.writeTime = LocalDateTime.now();
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriterUserId() {
        return writerUserId;
    }

    public void setWriterUserId(String writerUserId) {
        this.writerUserId = writerUserId;
    }

    public LocalDateTime getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(LocalDateTime writeTime) {
        this.writeTime = writeTime;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public void increaViewCount() {viewCount++;}
}
