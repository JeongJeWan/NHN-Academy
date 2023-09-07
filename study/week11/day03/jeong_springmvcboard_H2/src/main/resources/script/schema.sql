CREATE TABLE IF NOT EXISTS `users` (
    `user_id` VARCHAR(255) NOT NULL COMMENT '사용자 아이디' primary key,
    `user_password` VARCHAR(255) NOT NULL COMMENT '사용자 비밀번호',
    `user_name` VARCHAR(255) NOT NULL COMMENT '사용자 이름',
    `profile_file_name` VARCHAR(255) NOT NULL COMMENT '사용자 프로필',
    `created_at` datetime NOT NULL default current_timestamp COMMENT '등록 날짜'
);


CREATE TABLE IF NOT EXISTS `posts` (
    `post_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ai' primary key,
    `title` VARCHAR(255) NOT NULL COMMENT '제목',
    `content` text NOT NULL COMMENT '내용',
    `writer_user_id` VARCHAR(255) NOT NULL COMMENT '사용자 아이디',
    `write_time` datetime NOT NULL default current_timestamp COMMENT '작성 시간',
    `view_count` INT NOT NULL COMMENT '조회 수'
);


merge into `users` key (`user_id`) values ('user1', '1234', 'marco', '프로필1', now());
merge into `users` key (`user_id`) values ('user2', '5678', 'jeong', '프로필2', now());
merge into `users` key (`user_id`) values ('user3', '1234', 'dongmyo', '프로필1', now());
merge into `users` key (`user_id`) values ('user4', '5678', 'jordan', '프로필2', now());
merge into `users` key (`user_id`) values ('user5', '1234', 'comtin', '프로필1', now());
merge into `users` key (`user_id`) values ('user6', '5678', 'yoda', '프로필2', now());

merge into `posts` key (`post_id`) values ('1', 'King Of Man', 'I am a King', 'user1', now(), 1);
merge into `posts` key (`post_id`) values ('2', 'King Of Man1', 'I am a King', 'user2', now(), 1);
merge into `posts` key (`post_id`) values ('3', 'King Of Man2', 'I am a King', 'user1', now(), 1);
merge into `posts` key (`post_id`) values ('4', 'King Of Man3', 'I am a King', 'user2', now(), 1);
merge into `posts` key (`post_id`) values ('5', 'King Of Man4', 'I am a King', 'user2', now(), 1);
merge into `posts` key (`post_id`) values ('5', 'King Of Man5', 'I am a King', 'user1', now(), 1);
merge into `posts` key (`post_id`) values ('6', 'King Of Man6', 'I am a King', 'user2', now(), 1);



