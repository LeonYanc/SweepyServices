DROP TABLE IF EXISTS `LONG_TO_SHORT`;

CREATE TABLE `LONG_TO_SHORT`
(
    `url_id`         BIGINT AUTO_INCREMENT PRIMARY KEY,
    `method`	 VARCHAR(256) NOT NULL,
    `long_url`   VARCHAR(256) NOT NULL,
    `short_url`  VARCHAR(256)  NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;