CREATE TABLE IF NOT EXISTS`qus_request` (
  `uuid` varchar(200) NOT NULL,
  `title` varchar(200) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `phone_num` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `age` varchar(45) DEFAULT NULL,
  `sex` varchar(45) DEFAULT NULL,
  `qus` varchar(500) DEFAULT NULL,
  `options_ans` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ;