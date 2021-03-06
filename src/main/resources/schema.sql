DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `id`    bigint NOT NULL AUTO_INCREMENT,
    `email` varchar(255) DEFAULT NULL,
    `name`  varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`email`)
);

DROP TABLE IF EXISTS `events`;
CREATE TABLE `events`
(
    `id`    bigint NOT NULL AUTO_INCREMENT,
    `date`  datetime(6) DEFAULT NULL,
    `price` double NOT NULL,
    `title` varchar(255)   DEFAULT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `tickets`;
CREATE TABLE `tickets`
(
    `id`       bigint NOT NULL AUTO_INCREMENT,
    `category` int DEFAULT NULL,
    `event_id` bigint NOT NULL,
    `place`    int    NOT NULL,
    `user_id`  bigint NOT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `user_accounts`;
CREATE TABLE `user_accounts`
(
    `id`      bigint NOT NULL AUTO_INCREMENT,
    `user_id` bigint NOT NULL,
    `amount`  decimal(19, 2) DEFAULT NULL,
    PRIMARY KEY (`id`)
);