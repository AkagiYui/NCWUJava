CREATE TABLE `staff`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `username`   varchar(255) DEFAULT NULL,
    `password`   varchar(255) DEFAULT NULL,
    `email`      varchar(255) DEFAULT NULL,
    `nickname`   varchar(255) DEFAULT NULL,
    `is_manager` tinyblob,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

