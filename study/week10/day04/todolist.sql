-- DROP DATABASE todolist;
CREATE DATABASE todolist;
use todolist;

--
-- 테이블 구조 `event`
--

CREATE TABLE `event` (
  `id` bigint NOT NULL COMMENT 'ai',
  `subject` varchar(255) NOT NULL COMMENT '이벤트 명',
  `user_id` varchar(255) NOT NULL COMMENT '유저 아이디',
  `event_at` date NOT NULL COMMENT '이벤트 일자',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

desc event;

--
-- 덤프된 테이블의 인덱스
--

--
-- 테이블의 인덱스 `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_user_id_event_at` (`user_id`,`event_at`) USING BTREE;

--
-- 덤프된 테이블의 AUTO_INCREMENT
--

--
-- 테이블의 AUTO_INCREMENT `event`
--
ALTER TABLE `event`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ai';
COMMIT;

SELECT * from event order by event.id desc;

select id, subject, event_at, created_at
        from event
        where YEAR(event_at) = 2023
        AND MONTH(event_at) = 5
        AND DAY(event_at) = 6;

select id, subject, CAST(user_id AS CHAR) AS user_id, event_at, created_at
        from event
        where id=1;





