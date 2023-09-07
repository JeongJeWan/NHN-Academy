-- DROP DATABASE posts;

CREATE DATABASE posts;
use posts;

CREATE TABLE `user` (
	`user_id` varchar(255) NOT NULL COMMENT '사용자 아이디' primary key,
    `user_password` varchar(255) NOT NULL COMMENT '사용자 비밀번호',
    `user_name` varchar(255) NOT NULL COMMENT '사용자 이름',
    `profile_file_name` varchar(255) NOT NULL COMMENT '사용자 프로필',
    `created_at` datetime NOT NULL default current_timestamp COMMENT '등록 날짜'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

select * from user order by user.created_at desc;


CREATE TABLE `post` (
	`post_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ai' primary key,
    `title` varchar(255) NOT NULL COMMENT '제목',
    `content` text NOT NULL COMMENT '내용',
    `writer_user_id` varchar(255) NOT NULL COMMENT '사용자 아이디',
    `write_time` datetime NOT NULL default current_timestamp COMMENT '작성 시간',
    `view_count` INT NOT NULL COMMENT '조회 수'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

select * from post order by post.post_id desc;


COMMIT;