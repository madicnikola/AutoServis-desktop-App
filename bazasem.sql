/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 10.4.8-MariaDB : Database - seminarskiprosoft
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`seminarskiprosoft` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `seminarskiprosoft`;

/*Table structure for table `autodeo` */

DROP TABLE IF EXISTS `autodeo`;

CREATE TABLE `autodeo` (
  `autodeoID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `proizvodjac` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`autodeoID`),
  CONSTRAINT `autodeo_ibfk_3` FOREIGN KEY (`autodeoID`) REFERENCES `proizvod` (`ProizvodID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `autodeo` */

insert  into `autodeo`(`autodeoID`,`proizvodjac`) values 
(13,'Pirelli'),
(14,'Sava'),
(15,'Opel'),
(16,'BMW'),
(17,'Renault'),
(18,'BMW');

/*Table structure for table `klijent` */

DROP TABLE IF EXISTS `klijent`;

CREATE TABLE `klijent` (
  `klijentID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ime` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `prezime` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `adresa` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `JMBG` varchar(13) COLLATE utf8_unicode_ci NOT NULL,
  `telefon` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`klijentID`)
) ENGINE=InnoDB AUTO_INCREMENT=495 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `klijent` */

insert  into `klijent`(`klijentID`,`ime`,`prezime`,`adresa`,`JMBG`,`telefon`,`email`) values 
(6,'Nikola','Popovic','Stepe Stepanovica 15b','1598665853695','061612616','asfva@avd'),
(9,'Milena','Pavlovic','Petrovacka 45p','7854866958755','0636985487','milena@gmail.com'),
(10,'Milica','Jovanovic','Petra Petrovica 15nj','0805995786026','0648697852','test@test.com'),
(11,'Goran','Jankovic','Pariske Komune 2','7896558585225','0636985698','david@test.com');

/*Table structure for table `korisnik` */

DROP TABLE IF EXISTS `korisnik`;

CREATE TABLE `korisnik` (
  `korisnikID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `jmbg` varchar(13) COLLATE utf8_unicode_ci NOT NULL,
  `ime` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `prezime` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `korisnickoime` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `lozinka` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`korisnikID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `korisnik` */

insert  into `korisnik`(`korisnikID`,`jmbg`,`ime`,`prezime`,`korisnickoime`,`lozinka`) values 
(1,'1405112','Nikola','Madic','admin','202cb962ac59075b964b07152d234b70'),
(2,'12345`','Luka','Scekic','luka','202cb962ac59075b964b07152d234b70'),
(3,'11503','test','test','test','098f6bcd4621d373cade4e832627b4f6');

/*Table structure for table `proizvod` */

DROP TABLE IF EXISTS `proizvod`;

CREATE TABLE `proizvod` (
  `ProizvodID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `vrednost` double NOT NULL,
  PRIMARY KEY (`ProizvodID`)
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `proizvod` */

insert  into `proizvod`(`ProizvodID`,`naziv`,`vrednost`) values 
(9,'Veliki servis',35000),
(11,'mali servis',6000),
(12,'Zamena guma i balansiranje',1600),
(13,'zimska guma R16',10000),
(14,'letnja guma R17',8000),
(15,'hladnjak',24000),
(16,'branik',6000),
(17,'far',5000),
(18,'set kvacila',30000),
(19,'Zamena disk kocnica',5000);

/*Table structure for table `racun` */

DROP TABLE IF EXISTS `racun`;

CREATE TABLE `racun` (
  `racunID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ukupnaVrednost` double NOT NULL,
  `storniran` tinyint(1) NOT NULL,
  `datum` date NOT NULL,
  `korisnikID` int(10) unsigned DEFAULT NULL,
  `klijentID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`racunID`),
  KEY `korisnikID` (`korisnikID`),
  KEY `racun_ibfk_2` (`klijentID`),
  CONSTRAINT `racun_ibfk_1` FOREIGN KEY (`korisnikID`) REFERENCES `korisnik` (`korisnikID`),
  CONSTRAINT `racun_ibfk_2` FOREIGN KEY (`klijentID`) REFERENCES `klijent` (`klijentID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `racun` */

insert  into `racun`(`racunID`,`ukupnaVrednost`,`storniran`,`datum`,`korisnikID`,`klijentID`) values 
(3,105000,0,'2020-02-05',1,11),
(4,7600,1,'2020-02-06',1,11),
(5,71600,0,'2020-02-06',1,10),
(6,35000,1,'2020-02-08',1,6),
(8,36000,1,'2020-02-12',1,9);

/*Table structure for table `stavkaracuna` */

DROP TABLE IF EXISTS `stavkaracuna`;

CREATE TABLE `stavkaracuna` (
  `racunID` int(10) unsigned NOT NULL,
  `RBStavke` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `vrednostStavke` double NOT NULL,
  `kolicina` int(11) NOT NULL,
  `proizvodID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`racunID`,`RBStavke`,`vrednostStavke`,`kolicina`),
  KEY `RBStavke` (`RBStavke`),
  KEY `stavkaracuna_ibfk_2` (`proizvodID`),
  CONSTRAINT `stavkaracuna_ibfk_1` FOREIGN KEY (`racunID`) REFERENCES `racun` (`racunID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stavkaracuna_ibfk_2` FOREIGN KEY (`proizvodID`) REFERENCES `proizvod` (`ProizvodID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `stavkaracuna` */

insert  into `stavkaracuna`(`racunID`,`RBStavke`,`vrednostStavke`,`kolicina`,`proizvodID`) values 
(3,1,35000,3,9),
(5,2,35000,2,9),
(6,1,35000,1,9),
(4,1,6000,1,11),
(8,1,6000,6,11),
(4,2,1600,1,12),
(5,1,1600,1,12);

/*Table structure for table `usluga` */

DROP TABLE IF EXISTS `usluga`;

CREATE TABLE `usluga` (
  `uslugaID` int(10) unsigned NOT NULL,
  `opisUsluge` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`uslugaID`),
  CONSTRAINT `usluga_ibfk_1` FOREIGN KEY (`uslugaID`) REFERENCES `proizvod` (`ProizvodID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `usluga` */

insert  into `usluga`(`uslugaID`,`opisUsluge`) values 
(9,'kompletan servis'),
(11,'zamena ulja i filtera'),
(12,'Zamena guma za cetiri tocka'),
(19,'Ugradnja novih disk kocnica i zamena paknova.');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
