INSERT INTO `BoardAuthority` (`authority`) VALUES ('ADMIN');
INSERT INTO `BoardAuthority` (`authority`) VALUES ('USER');

delete from `BoardAuthority` where id > 0;


INSERT INTO `BoardUsers` (`id`, `password`, `name`, `authority_id`, `created_at`) VALUES ('user', '1234', 'marco', 1, now());
INSERT INTO `BoardUsers` (`id`, `password`, `name`, `authority_id`, `created_at`) VALUES ('admin', '1234', '관리자', 2, now());

delete from `BoardUsers` where id > 0;

insert into `BoardPosts` (`user_id`, `title`, `content`, `created_at`, `modifier`, `updated_at`, `deleted`) values('user', 'a1', 'something', now(), null, null, false);
insert into `BoardPosts` (`user_id`, `title`, `content`, `created_at`, `modifier`, `updated_at`, `deleted`) values('user', 'a2', 'something', now(), null, null, false);
insert into `BoardPosts` (`user_id`, `title`, `content`, `created_at`, `modifier`, `updated_at`, `deleted`) values('user', 'a3', 'something', now(), null, null, false);
insert into `BoardPosts` (`user_id`, `title`, `content`, `created_at`, `modifier`, `updated_at`, `deleted`) values('user', 'a4', 'something', now(), null, null, false);
insert into `BoardPosts` (`user_id`, `title`, `content`, `created_at`, `modifier`, `updated_at`, `deleted`) values('user', 'a5', 'something', now(), null, null, false);
insert into `BoardPosts` (`user_id`, `title`, `content`, `created_at`, `modifier`, `updated_at`, `deleted`) values('user', 'a6', 'something', now(), null, null, false);
insert into `BoardPosts` (`user_id`, `title`, `content`, `created_at`, `modifier`, `updated_at`, `deleted`) values('user', 'a7', 'something', now(), null, null, false);
insert into `BoardPosts` (`user_id`, `title`, `content`, `created_at`, `modifier`, `updated_at`, `deleted`) values('user', 'a8', 'something', now(), null, null, false);
insert into `BoardPosts` (`user_id`, `title`, `content`, `created_at`, `modifier`, `updated_at`, `deleted`) values('user', 'a9', 'something', now(), null, null, false);
insert into `BoardPosts` (`user_id`, `title`, `content`, `created_at`, `modifier`, `updated_at`, `deleted`) values('user', 'a10', 'something', now(), null, null, false);