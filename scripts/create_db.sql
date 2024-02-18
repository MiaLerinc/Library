CREATE DATABASE  IF NOT EXISTS `library_directory`;

USE `library_directory`;

DROP TABLE IF EXISTS `author`;

CREATE TABLE `author` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `nationality` varchar(45) DEFAULT NULL,
  `about_author` varchar(1000) DEFAULT NULL,
   PRIMARY KEY (`id`),
   CONSTRAINT author_constraint UNIQUE (first_name, last_name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



DROP TABLE IF EXISTS `publisher`;

CREATE TABLE `publisher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
   PRIMARY KEY (`id`),
   CONSTRAINT publisher_constraint UNIQUE (name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `pin` varchar(20) NOT NULL,
  `address` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `birth_date` date NOT NULL,
  `town` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `enrollment_date` date NOT NULL,
  `active` bit NOT NULL,
   PRIMARY KEY (`id`),
   CONSTRAINT member_constraint UNIQUE (pin)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `book_type`;

CREATE TABLE `book_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `language` varchar(20) NOT NULL,
  `year_of_publication` varchar(4) NOT NULL,
  `short_description` varchar(1000) DEFAULT NULL,
  `author_id` int NOT NULL,
  `publisher_id` int NOT NULL,
   PRIMARY KEY (`id`),

  KEY `FK_AUTHOR_idx` (`author_id`),
  CONSTRAINT `FK_AUTHOR` FOREIGN KEY (`author_id`) 
  REFERENCES `author` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,

  KEY `FK_PUBLISHER_idx` (`publisher_id`),
  CONSTRAINT `FK_PUBLISHER` FOREIGN KEY (`publisher_id`) 
  REFERENCES `publisher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION


) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `lending_time` datetime DEFAULT NULL,
  `lent` bit NOT NULL,
  `member_id` int DEFAULT NULL,
  `book_type_id` int NOT NULL,
   PRIMARY KEY (`id`),

  KEY `FK_MEMBER_idx` (`member_id`),
  CONSTRAINT `FK_MEMBER` FOREIGN KEY (`member_id`) 
  REFERENCES `member` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,

  KEY `FK_BOOK_TYPE_idx` (`book_type_id`),
  CONSTRAINT `FK_BOOK_TYPE` FOREIGN KEY (`book_type_id`) 
  REFERENCES `book_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `book_type_genre` (
  `book_type_id` INT NOT NULL, 
  `genre` VARCHAR(45) NOT NULL, 
  PRIMARY KEY (`book_type_id`, `genre`), 
  CONSTRAINT `FK_BOOK_TYPE_GENRE` FOREIGN KEY (`book_type_id`) REFERENCES `book_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` bit NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

