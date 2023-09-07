CREATE TABLE IF NOT EXISTS `BoardAuthority` (
	`id` INT(3) NOT NULL AUTO_INCREMENT,
    `authority` VARCHAR(10) NOT NULL,
    PRIMARY KEY(`id`)
)ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `BoardUsers` (
	`id` VARCHAR(20) NOT NULL,
    `password` VARCHAR(40) NOT NULL,
    `name` VARCHAR(20) NOT NULL,
    `authority_id` INT(3) NOT NULL,
	`created_at` TIMESTAMP NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY (`authority_id`) REFERENCES `BoardAuthority`(`id`)
)ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `BoardPosts` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,    
    `user_id` VARCHAR(20) NOT NULL,
    `title` VARCHAR(200) NOT NULL,
    `content` VARCHAR(10000) NOT NULL,
    `created_at` TIMESTAMP NOT NULL,
    `modifier` VARCHAR(20),
    `updated_at` TIMESTAMP, 
    `deleted` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY(`id`),
    UNIQUE INDEX `title` (`title`),
    FOREIGN KEY(`user_id`) REFERENCES `BoardUsers`(`id`)
)ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `BoardComments` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `post_id` INT(11) NOT NULL,
    `user_id` VARCHAR(20) NOT NULL,
    `content` VARCHAR(100) NOT NULL,
    `created_at` TIMESTAMP NOT NULL,
    `updated_at` TIMESTAMP,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`post_id`) REFERENCES `BoardPosts` (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `BoardUsers` (`id`)
)ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `BoardFiles` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `post_id` INT(11) NOT NULL,
    `file_name` VARCHAR(1000) NOT NULL,
    `created_at` TIMESTAMP NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY (`post_id`) REFERENCES `BoardPosts` (`id`)
)ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `BoardLikes` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `post_id` INT(11) NOT NULL,
    `user_id` VARCHAR(20) NOT NULL,
    `created_at` TIMESTAMP NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY(`post_id`) REFERENCES `BoardPosts`(`id`),
    FOREIGN KEY(`user_id`) REFERENCES `BoardUsers`(`id`)
)ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `BoardReplies` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `reply_id` INT(11) NOT NULL, 	-- 게시글의 답글 번호 
    `parent_id` INT(11) NOT NULL,	-- 부모 게시글 번호
    `bundle_id` INT(11) NOT NULL,	-- 최상위 부모 게시글 번호
    `bundle_order` INT NOT NULL,	-- 깊이 단계
    PRIMARY KEY(`id`),
    FOREIGN KEY(`bundle_id`) REFERENCES `BoardPosts`(`id`),
    FOREIGN KEY(`reply_id`) REFERENCES `BoardPosts`(`id`)
)ENGINE = InnoDB;