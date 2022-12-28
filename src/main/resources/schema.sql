CREATE DATABASE IF NOT EXISTS questionnaire_system;

CREATE TABLE IF NOT EXISTS`questions` (
  id int NOT NULL AUTO_INCREMENT,
  title varchar(200) NOT NULL,
  details varchar(200) DEFAULT NULL,
  start_time date DEFAULT NULL,
  end_time date DEFAULT NULL,
  PRIMARY KEY (`id`)
  );
  
 CREATE TABLE IF NOT EXISTS`qus_details` (
  id int NOT NULL AUTO_INCREMENT,
  title varchar(200) DEFAULT NULL,
  qus varchar(200) DEFAULT NULL,
  options varchar(200) DEFAULT NULL,
  multiple_choice tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
  );
  
CREATE TABLE IF NOT EXISTS`qus_request` (
  uuid varchar(200) NOT NULL,
  title varchar(200) DEFAULT NULL,
  name varchar(45) DEFAULT NULL,
  phone_num varchar(45) DEFAULT NULL,
  email varchar(45) DEFAULT NULL,
  age varchar(45) DEFAULT NULL,
  sex varchar(45) DEFAULT NULL,
  qus varchar(500) DEFAULT NULL,
  options_ans varchar(500) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ;