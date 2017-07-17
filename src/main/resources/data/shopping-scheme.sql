
BEGIN
;

DROP TABLE
IF EXISTS `order`;

DROP TABLE
IF EXISTS `user`;

DROP TABLE
IF EXISTS `role`;

DROP TABLE
IF EXISTS `product`;

DROP TABLE
IF EXISTS `repertory`;

DROP TABLE
IF EXISTS `regular`;

DROP TABLE
IF EXISTS `category`;

CREATE TABLE `role` (
	`id` INT (11) UNSIGNED NOT NULL auto_increment,
	`role_name` VARCHAR (20) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `role_name` (`role_name`)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

CREATE TABLE `user` (
	`id` INT (11) UNSIGNED NOT NULL auto_increment,
	`username` VARCHAR (50) NOT NULL,
	`password` VARCHAR (50) NOT NULL,
	`role_id` INT (11) UNSIGNED NOT NULL,
	`name` VARCHAR (100) DEFAULT NULL,
	`phone` VARCHAR (12) DEFAULT NULL,
	`address` VARCHAR (200) DEFAULT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `username` (`username`),
	KEY `role_id` (`role_id`),
	CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

CREATE TABLE `category` (
	`id` INT (11) UNSIGNED NOT NULL auto_increment,
	`name` VARCHAR (20) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `name` (`name`)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

CREATE TABLE `regular` (
	`id` INT (11) UNSIGNED NOT NULL auto_increment,
	`discount` DECIMAL (3, 2) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

CREATE TABLE `repertory` (
	`id` INT (11) UNSIGNED NOT NULL auto_increment,
	`count` INT (11) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

CREATE TABLE `product` (
	`id` INT (11) UNSIGNED NOT NULL auto_increment,
	`name` VARCHAR (100) NOT NULL,
	`description` VARCHAR (1024) NOT NULL,
	`price` DECIMAL (7, 2) NOT NULL,
	`img_url` VARCHAR (1024) DEFAULT NULL,
	`category_id` INT (11) UNSIGNED NOT NULL,
	`repertory_id` INT (11) UNSIGNED NOT NULL,
	`regular_id` INT (11) UNSIGNED NOT NULL,
	PRIMARY KEY (`id`),
	KEY `category_id` (`category_id`),
	KEY `repertory_id` (`repertory_id`),
	KEY `regular_id` (`regular_id`),
	CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
	CONSTRAINT `product_ibfk_2` FOREIGN KEY (`repertory_id`) REFERENCES `repertory` (`id`),
	CONSTRAINT `product_ibfk_3` FOREIGN KEY (`regular_id`) REFERENCES `regular` (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

CREATE TABLE `order` (
	`id` INT (11) UNSIGNED NOT NULL auto_increment,
	`time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`price` DECIMAL (7, 2) NOT NULL,
	`state` VARCHAR (15) NOT NULL,
	`user_id` INT (11) UNSIGNED,
	`product_id` INT (11) UNSIGNED NOT NULL,
	`user_remark` VARCHAR (500) DEFAULT NULL,
	`businessman_remark` VARCHAR (500) DEFAULT NULL,
	`success` TINYINT (4) NOT NULL DEFAULT '0',
	`addressee` VARCHAR (100) NOT NULL,
	`phone` VARCHAR (12) NOT NULL,
	`address` VARCHAR (200) NOT NULL,
	PRIMARY KEY (`id`),
	KEY `user_id` (`user_id`),
	KEY `product_id` (`product_id`),
	CONSTRAINT `order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL,
	CONSTRAINT `order_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

COMMIT;

BEGIN;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('2', '产品销售商');
INSERT INTO `role` VALUES ('1', '普通用户');
INSERT INTO `role` VALUES ('3', '系统管理员');

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'root', 'E10ADC3949BA59ABBE56E057F20F883E', '3', null, null, null);

COMMIT;