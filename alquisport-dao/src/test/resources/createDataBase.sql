-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.16


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema pistea
--

CREATE DATABASE IF NOT EXISTS pistea;
USE pistea;

--
-- Definition of table `administrator_sportfacility`
--

DROP TABLE IF EXISTS `administrator_sportfacility`;
CREATE TABLE `administrator_sportfacility` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ADMINISTRATOR` bigint(20) NOT NULL,
  `SportFacility` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK103DF3E53194AD0E` (`SportFacility`),
  KEY `FK103DF3E591540A5A` (`ADMINISTRATOR`),
  CONSTRAINT `FK103DF3E591540A5A` FOREIGN KEY (`ADMINISTRATOR`) REFERENCES `user` (`ID`),
  CONSTRAINT `FK103DF3E53194AD0E` FOREIGN KEY (`SportFacility`) REFERENCES `sport_facility` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `administrator_sportfacility`
--

/*!40000 ALTER TABLE `administrator_sportfacility` DISABLE KEYS */;
/*!40000 ALTER TABLE `administrator_sportfacility` ENABLE KEYS */;


--
-- Definition of table `audit_trail`
--

DROP TABLE IF EXISTS `audit_trail`;
CREATE TABLE `audit_trail` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE` datetime DEFAULT NULL,
  `DESCRIPTION` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `audit_trail`
--

/*!40000 ALTER TABLE `audit_trail` DISABLE KEYS */;
/*!40000 ALTER TABLE `audit_trail` ENABLE KEYS */;


--
-- Definition of table `company`
--

DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CIF` varchar(9) NOT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `ZIP_CODE` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  `ID_MUNICIPALITY` bigint(20) NOT NULL,
  `ID_PROVINCE` bigint(20) NOT NULL,
  `ID_ZONE` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK6372C85DF2112CDC` (`ID_MUNICIPALITY`),
  KEY `FK6372C85DE35F0F3C` (`ID_ZONE`),
  KEY `FK6372C85D18068C44` (`ID_PROVINCE`),
  CONSTRAINT `FK6372C85D18068C44` FOREIGN KEY (`ID_PROVINCE`) REFERENCES `province` (`ID`),
  CONSTRAINT `FK6372C85DE35F0F3C` FOREIGN KEY (`ID_ZONE`) REFERENCES `zone` (`ID`),
  CONSTRAINT `FK6372C85DF2112CDC` FOREIGN KEY (`ID_MUNICIPALITY`) REFERENCES `municipality` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company`
--

/*!40000 ALTER TABLE `company` DISABLE KEYS */;
/*!40000 ALTER TABLE `company` ENABLE KEYS */;


--
-- Definition of table `coupon`
--

DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(2) NOT NULL,
  `DISCOUNT_QUANTITY` float NOT NULL,
  `DISCOUNT_TYPE` int(11) NOT NULL,
  `MAX_TIMES_USE` int(11) NOT NULL,
  `TIMES_USED` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `coupon`
--

--
-- Definition of table `coupon_used`
--

DROP TABLE IF EXISTS `coupon_used`;
CREATE TABLE `coupon_used` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ID_USER` bigint(20) DEFAULT NULL,
  `ID_COUPON` bigint(20) DEFAULT NULL,
  `ID_RENT` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKE924783631D041` (`ID_RENT`),
  KEY `FKE924783639600F30` (`ID_COUPON`),
  KEY `FKE9247836E6FAD77A` (`ID_USER`),
  CONSTRAINT `FKE9247836E6FAD77A` FOREIGN KEY (`ID_USER`) REFERENCES `user` (`ID`),
  CONSTRAINT `FKE924783631D041` FOREIGN KEY (`ID_RENT`) REFERENCES `rent` (`ID`),
  CONSTRAINT `FKE924783639600F30` FOREIGN KEY (`ID_COUPON`) REFERENCES `coupon` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `coupon_used`
--

/*!40000 ALTER TABLE `coupon_used` DISABLE KEYS */;
/*!40000 ALTER TABLE `coupon_used` ENABLE KEYS */;


--
-- Definition of table `court`
--

DROP TABLE IF EXISTS `court`;
CREATE TABLE `court` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` text,
  `QUANTITY` int(11) DEFAULT NULL,
  `DESCRIPTION` text,
  `PRICE` double DEFAULT NULL,
  `DAYS_CLIENT` int(11) DEFAULT NULL,
  `DAYS_MEMBER` int(11) DEFAULT NULL,
  `HOUR_LIGHTS_ON` int(11) DEFAULT NULL,
  `RESERVATION_TYPE` int(11) DEFAULT NULL,
  `STATE` int(11) DEFAULT NULL,
  `ID_SPORT_FACILITY` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK3D5596BDDB28DA9` (`ID_SPORT_FACILITY`),
  CONSTRAINT `FK3D5596BDDB28DA9` FOREIGN KEY (`ID_SPORT_FACILITY`) REFERENCES `sport_facility` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `court`
--



--
-- Definition of table `court_day`
--

DROP TABLE IF EXISTS `court_day`;
CREATE TABLE `court_day` (
  `COURT_ID` bigint(20) NOT NULL,
  `DAY_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`COURT_ID`,`DAY_ID`),
  UNIQUE KEY `DAY_ID` (`DAY_ID`),
  KEY `FKFD90E2A85984F0FA` (`DAY_ID`),
  KEY `FKFD90E2A882F8469A` (`COURT_ID`),
  CONSTRAINT `FKFD90E2A882F8469A` FOREIGN KEY (`COURT_ID`) REFERENCES `court` (`ID`),
  CONSTRAINT `FKFD90E2A85984F0FA` FOREIGN KEY (`DAY_ID`) REFERENCES `day` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `court_day`
--

/*!40000 ALTER TABLE `court_day` DISABLE KEYS */;
/*!40000 ALTER TABLE `court_day` ENABLE KEYS */;


--
-- Definition of table `court_feature`
--

DROP TABLE IF EXISTS `court_feature`;
CREATE TABLE `court_feature` (
  `COURT_ID` bigint(20) NOT NULL,
  `FEATURE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`COURT_ID`,`FEATURE_ID`),
  UNIQUE KEY `FEATURE_ID` (`FEATURE_ID`),
  KEY `FK9F046C0293AE1ABA` (`FEATURE_ID`),
  KEY `FK9F046C0282F8469A` (`COURT_ID`),
  CONSTRAINT `FK9F046C0282F8469A` FOREIGN KEY (`COURT_ID`) REFERENCES `court` (`ID`),
  CONSTRAINT `FK9F046C0293AE1ABA` FOREIGN KEY (`FEATURE_ID`) REFERENCES `feature` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `court_feature`
--

--
-- Definition of table `court_special_day`
--

DROP TABLE IF EXISTS `court_special_day`;
CREATE TABLE `court_special_day` (
  `COURT_ID` bigint(20) NOT NULL,
  `DAY_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`COURT_ID`,`DAY_ID`),
  UNIQUE KEY `DAY_ID` (`DAY_ID`),
  KEY `FK51AEFE0275BF9001` (`DAY_ID`),
  KEY `FK51AEFE0282F8469A` (`COURT_ID`),
  CONSTRAINT `FK51AEFE0282F8469A` FOREIGN KEY (`COURT_ID`) REFERENCES `court` (`ID`),
  CONSTRAINT `FK51AEFE0275BF9001` FOREIGN KEY (`DAY_ID`) REFERENCES `day` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `court_special_day`
--

/*!40000 ALTER TABLE `court_special_day` DISABLE KEYS */;
/*!40000 ALTER TABLE `court_special_day` ENABLE KEYS */;


--
-- Definition of table `day`
--

DROP TABLE IF EXISTS `day`;
CREATE TABLE `day` (
  `DAYTYPE` varchar(31) NOT NULL,
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DAY` int(11) DEFAULT NULL,
  `DAY_OF_WEEK` varchar(255) DEFAULT NULL,
  `MONTH_NAME` varchar(255) DEFAULT NULL,
  `ID_SCHEDULE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK1077C3A8B5B12` (`ID_SCHEDULE`),
  CONSTRAINT `FK1077C3A8B5B12` FOREIGN KEY (`ID_SCHEDULE`) REFERENCES `schedule` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `day`
--


--
-- Definition of table `feature`
--

DROP TABLE IF EXISTS `feature`;
CREATE TABLE `feature` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `IDENTIFICATOR` varchar(255) NOT NULL,
  `POSITION` int(11) NOT NULL,
  `VALUE` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `feature`
--

--
-- Definition of table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequences`
--

/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;


--
-- Definition of table `image`
--

DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` text,
  `DISC_PATH` varchar(255) NOT NULL,
  `HEIGHT` int(11) DEFAULT NULL,
  `image` longblob,
  `NAME` varchar(255) NOT NULL,
  `WEIGHT` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `image`
--

/*!40000 ALTER TABLE `image` DISABLE KEYS */;
/*!40000 ALTER TABLE `image` ENABLE KEYS */;


--
-- Definition of table `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_READ` datetime NOT NULL,
  `DATE_SEND` datetime NOT NULL,
  `STATE` int(11) NOT NULL,
  `SUBJECT` varchar(255) NOT NULL,
  `TEXT` text NOT NULL,
  `USER_FROM_ID` bigint(20) DEFAULT NULL,
  `USER_TO_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK63B68BE7F434A6F6` (`USER_TO_ID`),
  KEY `FK63B68BE7B858E867` (`USER_FROM_ID`),
  CONSTRAINT `FK63B68BE7B858E867` FOREIGN KEY (`USER_FROM_ID`) REFERENCES `user` (`ID`),
  CONSTRAINT `FK63B68BE7F434A6F6` FOREIGN KEY (`USER_TO_ID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `message`
--

/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;


--
-- Definition of table `municipality`
--

DROP TABLE IF EXISTS `municipality`;
CREATE TABLE `municipality` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `ID_PROVINCE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK48132C7C18068C44` (`ID_PROVINCE`),
  CONSTRAINT `FK48132C7C18068C44` FOREIGN KEY (`ID_PROVINCE`) REFERENCES `province` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `municipality`
--


--
-- Definition of table `province`
--

DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `province`
--


--
-- Definition of table `rent`
--

DROP TABLE IF EXISTS `rent`;
CREATE TABLE `rent` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_END` datetime DEFAULT NULL,
  `DATE_START` datetime DEFAULT NULL,
  `PAYMENT_STATE` int(11) DEFAULT NULL,
  `QUANTITY_PAID` int(11) DEFAULT NULL,
  `COURT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK26533982F8469A` (`COURT_ID`),
  CONSTRAINT `FK26533982F8469A` FOREIGN KEY (`COURT_ID`) REFERENCES `court` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rent`
--


--
-- Definition of table `rental_client`
--

DROP TABLE IF EXISTS `rental_client`;
CREATE TABLE `rental_client` (
  `CLIENT_ID` bigint(20) NOT NULL,
  `RENTAL_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`CLIENT_ID`,`RENTAL_ID`),
  UNIQUE KEY `RENTAL_ID` (`RENTAL_ID`),
  KEY `FK92F0A34612F82D1A` (`CLIENT_ID`),
  KEY `FK92F0A3462668027A` (`RENTAL_ID`),
  CONSTRAINT `FK92F0A3462668027A` FOREIGN KEY (`RENTAL_ID`) REFERENCES `rent` (`ID`),
  CONSTRAINT `FK92F0A34612F82D1A` FOREIGN KEY (`CLIENT_ID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rental_client`
--

/*!40000 ALTER TABLE `rental_client` DISABLE KEYS */;
/*!40000 ALTER TABLE `rental_client` ENABLE KEYS */;


--
-- Definition of table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--


--
-- Definition of table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `END_HOUR` int(11) DEFAULT NULL,
  `MINUTE_END` int(11) DEFAULT NULL,
  `MINUTE_START` int(11) DEFAULT NULL,
  `START_HOUR` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `schedule`
--



--
-- Definition of table `special_price`
--

DROP TABLE IF EXISTS `special_price`;
CREATE TABLE `special_price` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_FINISH` datetime DEFAULT NULL,
  `DATE_START` datetime DEFAULT NULL,
  `DESCRIPTION` text,
  `END_HOUR` int(11) DEFAULT NULL,
  `MINUTE_END` int(11) DEFAULT NULL,
  `MINUTE_START` int(11) DEFAULT NULL,
  `START_HOUR` int(11) DEFAULT NULL,
  `COURT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKCF8F520382F8469A` (`COURT_ID`),
  CONSTRAINT `FKCF8F520382F8469A` FOREIGN KEY (`COURT_ID`) REFERENCES `court` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `special_price`
--

/*!40000 ALTER TABLE `special_price` DISABLE KEYS */;
/*!40000 ALTER TABLE `special_price` ENABLE KEYS */;


--
-- Definition of table `sport_event_rentals`
--

DROP TABLE IF EXISTS `sport_event_rentals`;
CREATE TABLE `sport_event_rentals` (
  `SPORT_EVENT_ID` bigint(20) NOT NULL,
  `RENTAL_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`SPORT_EVENT_ID`,`RENTAL_ID`),
  UNIQUE KEY `RENTAL_ID` (`RENTAL_ID`),
  KEY `FK9C3C593F6A653466` (`SPORT_EVENT_ID`),
  KEY `FK9C3C593F2668027A` (`RENTAL_ID`),
  CONSTRAINT `FK9C3C593F2668027A` FOREIGN KEY (`RENTAL_ID`) REFERENCES `rent` (`ID`),
  CONSTRAINT `FK9C3C593F6A653466` FOREIGN KEY (`SPORT_EVENT_ID`) REFERENCES `sports_event` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sport_event_rentals`
--



--
-- Definition of table `sport_facility`
--

DROP TABLE IF EXISTS `sport_facility`;
CREATE TABLE `sport_facility` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `ZIP_CODE` varchar(5) DEFAULT NULL,
  `COLOR_ONE` varchar(2) DEFAULT NULL,
  `COLOR_TWO` varchar(2) DEFAULT NULL,
  `COLOR_THREE` varchar(2) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `PHONE1` varchar(15) DEFAULT NULL,
  `PHONE2` varchar(15) DEFAULT NULL,
  `DESCRIPTION` text,
  `GET_HERE` text,
  `NAME` varchar(255) NOT NULL,
  `STATE` int(11) NOT NULL,
  `URL_NAME` varchar(255) NOT NULL,
  `ID_MUNICIPALITY` bigint(20) NOT NULL,
  `ID_PROVINCE` bigint(20) NOT NULL,
  `ID_ZONE` bigint(20) NOT NULL,
  `IMAGE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMAIL` (`EMAIL`),
  KEY `FK4EF05F2E6603749A` (`IMAGE_ID`),
  KEY `FK4EF05F2EF2112CDC` (`ID_MUNICIPALITY`),
  KEY `FK4EF05F2EE35F0F3C` (`ID_ZONE`),
  KEY `FK4EF05F2E18068C44` (`ID_PROVINCE`),
  CONSTRAINT `FK4EF05F2E18068C44` FOREIGN KEY (`ID_PROVINCE`) REFERENCES `province` (`ID`),
  CONSTRAINT `FK4EF05F2E6603749A` FOREIGN KEY (`IMAGE_ID`) REFERENCES `image` (`ID`),
  CONSTRAINT `FK4EF05F2EE35F0F3C` FOREIGN KEY (`ID_ZONE`) REFERENCES `zone` (`ID`),
  CONSTRAINT `FK4EF05F2EF2112CDC` FOREIGN KEY (`ID_MUNICIPALITY`) REFERENCES `municipality` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sport_facility`
--

/*!40000 ALTER TABLE `sport_facility` DISABLE KEYS */;
/*!40000 ALTER TABLE `sport_facility` ENABLE KEYS */;


--
-- Definition of table `sport_facility_day`
--

DROP TABLE IF EXISTS `sport_facility_day`;
CREATE TABLE `sport_facility_day` (
  `SPORT_FACILITY_ID` bigint(20) NOT NULL,
  `DAY_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`SPORT_FACILITY_ID`,`DAY_ID`),
  UNIQUE KEY `DAY_ID` (`DAY_ID`),
  KEY `FK945D4EEB5984F0FA` (`DAY_ID`),
  KEY `FK945D4EEB4F421EE3` (`SPORT_FACILITY_ID`),
  CONSTRAINT `FK945D4EEB4F421EE3` FOREIGN KEY (`SPORT_FACILITY_ID`) REFERENCES `sport_facility` (`ID`),
  CONSTRAINT `FK945D4EEB5984F0FA` FOREIGN KEY (`DAY_ID`) REFERENCES `day` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sport_facility_day`
--

/*!40000 ALTER TABLE `sport_facility_day` DISABLE KEYS */;
/*!40000 ALTER TABLE `sport_facility_day` ENABLE KEYS */;


--
-- Definition of table `sport_facility_features`
--

DROP TABLE IF EXISTS `sport_facility_features`;
CREATE TABLE `sport_facility_features` (
  `SPORT_FACILITY_ID` bigint(20) NOT NULL,
  `FEATURE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`SPORT_FACILITY_ID`,`FEATURE_ID`),
  UNIQUE KEY `FEATURE_ID` (`FEATURE_ID`),
  KEY `FKC7F4DA2E93AE1ABA` (`FEATURE_ID`),
  KEY `FKC7F4DA2E4F421EE3` (`SPORT_FACILITY_ID`),
  CONSTRAINT `FKC7F4DA2E4F421EE3` FOREIGN KEY (`SPORT_FACILITY_ID`) REFERENCES `sport_facility` (`ID`),
  CONSTRAINT `FKC7F4DA2E93AE1ABA` FOREIGN KEY (`FEATURE_ID`) REFERENCES `feature` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sport_facility_features`
--

/*!40000 ALTER TABLE `sport_facility_features` DISABLE KEYS */;
/*!40000 ALTER TABLE `sport_facility_features` ENABLE KEYS */;


--
-- Definition of table `sport_facility_image`
--

DROP TABLE IF EXISTS `sport_facility_image`;
CREATE TABLE `sport_facility_image` (
  `SPORT_FACILITY_ID` bigint(20) NOT NULL,
  `IMAGE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`SPORT_FACILITY_ID`,`IMAGE_ID`),
  UNIQUE KEY `IMAGE_ID` (`IMAGE_ID`),
  KEY `FK7BBEFCCA6603749A` (`IMAGE_ID`),
  KEY `FK7BBEFCCA4F421EE3` (`SPORT_FACILITY_ID`),
  CONSTRAINT `FK7BBEFCCA4F421EE3` FOREIGN KEY (`SPORT_FACILITY_ID`) REFERENCES `sport_facility` (`ID`),
  CONSTRAINT `FK7BBEFCCA6603749A` FOREIGN KEY (`IMAGE_ID`) REFERENCES `image` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sport_facility_image`
--

/*!40000 ALTER TABLE `sport_facility_image` DISABLE KEYS */;
/*!40000 ALTER TABLE `sport_facility_image` ENABLE KEYS */;


--
-- Definition of table `sport_facility_member`
--

DROP TABLE IF EXISTS `sport_facility_member`;
CREATE TABLE `sport_facility_member` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ID_USER` bigint(20) DEFAULT NULL,
  `ID_SPORTFACILITY` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK65F24CCBA16C00EA` (`ID_SPORTFACILITY`),
  KEY `FK65F24CCBE6FAD77A` (`ID_USER`),
  CONSTRAINT `FK65F24CCBE6FAD77A` FOREIGN KEY (`ID_USER`) REFERENCES `user` (`ID`),
  CONSTRAINT `FK65F24CCBA16C00EA` FOREIGN KEY (`ID_SPORTFACILITY`) REFERENCES `sport_facility` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sport_facility_member`
--

/*!40000 ALTER TABLE `sport_facility_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `sport_facility_member` ENABLE KEYS */;


--
-- Definition of table `sport_facility_special_day`
--

DROP TABLE IF EXISTS `sport_facility_special_day`;
CREATE TABLE `sport_facility_special_day` (
  `SPORT_FACILITY_ID` bigint(20) NOT NULL,
  `DAY_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`SPORT_FACILITY_ID`,`DAY_ID`),
  UNIQUE KEY `DAY_ID` (`DAY_ID`),
  KEY `FK938774575BF9001` (`DAY_ID`),
  KEY `FK93877454F421EE3` (`SPORT_FACILITY_ID`),
  CONSTRAINT `FK93877454F421EE3` FOREIGN KEY (`SPORT_FACILITY_ID`) REFERENCES `sport_facility` (`ID`),
  CONSTRAINT `FK938774575BF9001` FOREIGN KEY (`DAY_ID`) REFERENCES `day` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sport_facility_special_day`
--

/*!40000 ALTER TABLE `sport_facility_special_day` DISABLE KEYS */;
/*!40000 ALTER TABLE `sport_facility_special_day` ENABLE KEYS */;


--
-- Definition of table `sports_event`
--

DROP TABLE IF EXISTS `sports_event`;
CREATE TABLE `sports_event` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` text,
  `NAME` varchar(255) NOT NULL,
  `ID_SPORTFACILITY` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKC93EBDBAA16C00EA` (`ID_SPORTFACILITY`),
  CONSTRAINT `FKC93EBDBAA16C00EA` FOREIGN KEY (`ID_SPORTFACILITY`) REFERENCES `sport_facility` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sports_event`
--

--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `USERTYPE` varchar(31) NOT NULL,
  `ID` bigint(20) NOT NULL,
  `ACCOUNT_NON_EXPIRED` char(1) DEFAULT NULL,
  `ACCOUNT_NON_LOCKED` char(1) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `ZIP_CODE` varchar(255) DEFAULT NULL,
  `ACCOUNT_NUMBER` int(11) DEFAULT NULL,
  `CONTROL_CODE` int(11) DEFAULT NULL,
  `ENTITY_CODE` int(11) DEFAULT NULL,
  `OFFICE_CODE` int(11) DEFAULT NULL,
  `BIRTH_DATE` datetime DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `PHONE1` varchar(255) DEFAULT NULL,
  `PHONE2` varchar(255) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `CREDENTIALS_NON_EXPIRED` char(1) DEFAULT NULL,
  `ENABLED` char(1) DEFAULT NULL,
  `FIRST_LASTNAME` varchar(255) NOT NULL,
  `LAST_MODIFIED_DATE` datetime DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `SECOND_LASTNAME` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `ID_MUNICIPALITY` bigint(20) NOT NULL,
  `ID_PROVINCE` bigint(20) NOT NULL,
  `ID_ZONE` bigint(20) NOT NULL,
  `ID_COMPANY` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMAIL` (`EMAIL`),
  KEY `FK27E3CBF1BFF696` (`ID_COMPANY`),
  KEY `FK27E3CBF2112CDC` (`ID_MUNICIPALITY`),
  KEY `FK27E3CBE35F0F3C` (`ID_ZONE`),
  KEY `FK27E3CB18068C44` (`ID_PROVINCE`),
  CONSTRAINT `FK27E3CB18068C44` FOREIGN KEY (`ID_PROVINCE`) REFERENCES `province` (`ID`),
  CONSTRAINT `FK27E3CBE35F0F3C` FOREIGN KEY (`ID_ZONE`) REFERENCES `zone` (`ID`),
  CONSTRAINT `FK27E3CBF1BFF696` FOREIGN KEY (`ID_COMPANY`) REFERENCES `company` (`ID`),
  CONSTRAINT `FK27E3CBF2112CDC` FOREIGN KEY (`ID_MUNICIPALITY`) REFERENCES `municipality` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


--
-- Definition of table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE` bigint(20) NOT NULL,
  `USER` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKBC16F46A810A242C` (`ROLE`),
  KEY `FKBC16F46A810CFAD6` (`USER`),
  CONSTRAINT `FKBC16F46A810CFAD6` FOREIGN KEY (`USER`) REFERENCES `user` (`ID`),
  CONSTRAINT `FKBC16F46A810A242C` FOREIGN KEY (`ROLE`) REFERENCES `role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_role`
--

/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;


--
-- Definition of table `zone`
--

DROP TABLE IF EXISTS `zone`;
CREATE TABLE `zone` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `ID_MUNICIPALITY` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK2A1BACF2112CDC` (`ID_MUNICIPALITY`),
  CONSTRAINT `FK2A1BACF2112CDC` FOREIGN KEY (`ID_MUNICIPALITY`) REFERENCES `municipality` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `zone`
--





/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
