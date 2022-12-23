CREATE TABLE IF NOT EXISTS`qus_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `qus` varchar(200) DEFAULT NULL,
  `options` varchar(200) DEFAULT NULL,
  `multiple_choice` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
  );
