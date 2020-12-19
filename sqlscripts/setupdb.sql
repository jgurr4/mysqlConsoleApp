CREATE DATABASE IF NOT EXISTS monster;
USE monster;
CREATE TABLE IF NOT EXISTS monsters (
  name VARCHAR(45),
  hp TINYINT UNSIGNED NOT NULL,
  `exp` INT UNSIGNED DEFAULT 10,
  biome VARCHAR(40) DEFAULT NULL,
  defense TINYINT UNSIGNED NOT NULL,
  strong_against ENUM('lightning', 'fire', 'ice', 'wind', 'water', 'earth', 'crushing', 'piercing', 'poison', 'none') default 'none',
  weak_against ENUM('lightning', 'fire', 'ice', 'wind', 'water', 'earth', 'crushing', 'piercing', 'poison', 'none') default 'none',
  PRIMARY KEY(name)
);
CREATE TABLE IF NOT EXISTS abilities (
  monster_name VARCHAR(45) NOT NULL,
  no1 VARCHAR(30) NOT NULL,
  no1_damage SMALLINT UNSIGNED NOT NULL,
  no2 VARCHAR(30) NOT NULL,
  no2_damage SMALLINT UNSIGNED NOT NULL,
  no3 VARCHAR(30) NULL,
  no3_damage SMALLINT UNSIGNED NULL,
  no4 VARCHAR(30) NULL,
  no4_damage SMALLINT UNSIGNED NULL,
  no5 VARCHAR(30) NULL,
  no5_damage SMALLINT UNSIGNED NULL,
  no6 VARCHAR(30) NULL,
  no6_damage SMALLINT UNSIGNED NULL,
  CONSTRAINT `fk_monster_name` FOREIGN KEY (`monster_name`) REFERENCES `monsters` (`name`) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE USER IF NOT EXISTS 'jaredgurr'@'127.0.0.1' IDENTIFIED BY 'super03';
CREATE USER IF NOT EXISTS 'sqlApp'@'127.0.0.1' IDENTIFIED BY 'hi07';
GRANT ALL PRIVILEGES ON *.* TO 'jaredgurr'@'127.0.0.1';
GRANT SELECT ON monster.monster_list TO 'sqlApp'@'127.0.0.1';
FLUSH PRIVILEGES;
