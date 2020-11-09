CREATE DATABASE IF NOT EXISTS monster;
USE monster;
CREATE TABLE IF NOT EXISTS monsters (
  name VARCHAR(45) NOT NULL,
  size VARCHAR(40) DEFAULT NULL,
  type VARCHAR(40) DEFAULT NULL,
  subtype VARCHAR(40) DEFAULT NULL,
  `law v chaos` ENUM('Chaotic', 'Neutral', 'Any', 'Lawful', 'Varies'),
  `good v evil` ENUM('Good', 'Evil', 'Any', 'Neutral', 'Varies'),
  cr VARCHAR(10) DEFAULT NULL,
  `exp` VARCHAR(15) DEFAULT NULL,
  biome VARCHAR(40) DEFAULT NULL,
  source VARCHAR(40) DEFAULT NULL,
  page INT(4) DEFAULT NULL,
  PRIMARY KEY(name)
);
CREATE USER 'jared'@'127.0.0.1' IDENTIFIED BY 'super03';
GRANT ALL PRIVILEGES ON monster.* TO 'jared'@'127.0.0.1';
FLUSH PRIVILEGES;
