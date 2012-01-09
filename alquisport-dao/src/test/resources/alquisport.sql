CREATE schema `alquisport`;

DROP TABLE IF EXISTS `audit_trail`;
CREATE TABLE `audit_trail` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` text,
  PRIMARY KEY (`ID`)
);

DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
);


DROP TABLE IF EXISTS `municipality`;
CREATE TABLE `municipality` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `ID_PROVINCE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`ID_PROVINCE`) REFERENCES `province` (`ID`)
);

DROP TABLE IF EXISTS `zone`;
CREATE TABLE `zone` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `ID_MUNICIPALITY` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`ID_MUNICIPALITY`) REFERENCES `municipality` (`ID`)
);


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ADDRESS` varchar(255) NOT NULL,
  `TYPE` varchar(255) NOT NULL,
  `ACCOUNT_NUMBER` int(11) NOT NULL,
  `CONTROL_CODE` int(11) NOT NULL,
  `ENTITY_CODE` int(11) NOT NULL,
  `OFFICE_CODE` int(11) NOT NULL,
  `BIRTH_DATE` datetime DEFAULT NULL,
  `CELLPHONE` int(11) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `PHONE` int(11) NOT NULL,
  `ENABLED` tinyint(1) DEFAULT '1',
  `FIRST_LAST_NAME` varchar(255) NOT NULL,
  `LOGIN` varchar(255) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `SECOND_LAST_NAME` varchar(255) NOT NULL,
  `ID_MUNICIPALITY` bigint(20) DEFAULT NULL,
  `ID_PROVINCE` bigint(20) DEFAULT NULL,
  `ID_ZONE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`ID_PROVINCE`) REFERENCES `province` (`ID`),
  FOREIGN KEY (`ID_ZONE`) REFERENCES `zone` (`ID`),
  FOREIGN KEY (`ID_MUNICIPALITY`) REFERENCES `municipality` (`ID`)
);


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
);


DROP TABLE IF EXISTS `sport_facility`;
CREATE TABLE `sport_facility` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ADDRESS` varchar(255) NOT NULL,
  `TYPE` varchar(255) NOT NULL,
  `COLOR_ONE` varchar(2) NOT NULL,
  `COLOR_TWO` varchar(2) NOT NULL,
  `COLOR_THREE` varchar(2) NOT NULL,
  `CELLPHONE` int(11) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `PHONE` int(11) NOT NULL,
  `DESCRIPTION` text,
  `GET_HERE` text,
  `NAME` varchar(255) NOT NULL,
  `STATE` int(11) NOT NULL,
  `ID_MUNICIPALITY` bigint(20) DEFAULT NULL,
  `ID_PROVINCE` bigint(20) DEFAULT NULL,
  `ID_ZONE` bigint(20) DEFAULT NULL,
  `IMAGE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`ID_PROVINCE`) REFERENCES `province` (`ID`),
  FOREIGN KEY (`IMAGE_ID`) REFERENCES `image` (`ID`),
  FOREIGN KEY (`ID_ZONE`) REFERENCES `zone` (`ID`),
  FOREIGN KEY (`ID_MUNICIPALITY`) REFERENCES `municipality` (`ID`)
);




DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator` (
  `CIF` varchar(9) NOT NULL,
  `ADDRESS` varchar(255) NOT NULL,
  `TYPE` varchar(255) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `ID_MUNICIPALITY` bigint(20) DEFAULT NULL,
  `ID_PROVINCE` bigint(20) DEFAULT NULL,
  `ID_ZONE` bigint(20) DEFAULT NULL,
  `ID_SPORTFACILITY` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  FOREIGN KEY (`ID_PROVINCE`) REFERENCES `province` (`ID`),
  FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`),
  FOREIGN KEY (`ID_SPORTFACILITY`) REFERENCES `sport_facility` (`ID`),
  FOREIGN KEY (`ID_ZONE`) REFERENCES `zone` (`ID`),
  FOREIGN KEY (`ID_MUNICIPALITY`) REFERENCES `municipality` (`ID`)
);

DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `AUTHORITY_NAME` varchar(255) NOT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`)
);

DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `USER_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`USER_ID`),
  FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`)
);


DROP TABLE IF EXISTS `court`;
CREATE TABLE `court` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` text,
  `QUANTITY` int(11) DEFAULT NULL,
  `DESCRIPTION` text,
  `DAYS_CLIENT` int(11) DEFAULT NULL,
  `DAYS_MEMBER` int(11) DEFAULT NULL,
  `HOUR_LIGHTS_ON` int(11) DEFAULT NULL,
  `RESERVATION_TYPE` int(11) DEFAULT NULL,
  `STATE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
);


DROP TABLE IF EXISTS `rent`;
CREATE TABLE `rent` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_END` datetime DEFAULT NULL,
  `DATE_START` datetime DEFAULT NULL,
  `PAYMENT_STATE` int(11) DEFAULT NULL,
  `QUANTITY_PAID` int(11) DEFAULT NULL,
  `COURT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`COURT_ID`) REFERENCES `court` (`ID`)
); 


DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(2) NOT NULL,
  `DISCOUNT_QUANTITY` float NOT NULL,
  `DISCOUNT_TYPE` int(11) NOT NULL,
  `MAX_TIMES_USE` int(11) NOT NULL,
  `TIMES_USED` int(11) NOT NULL,
  `ID_USER` bigint(20) DEFAULT NULL,
  `ID_COUPON` bigint(20) DEFAULT NULL,
  `ID_RENT` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`ID_RENT`) REFERENCES `rent` (`ID`),
  FOREIGN KEY (`ID_COUPON`) REFERENCES `coupon` (`ID`),
  FOREIGN KEY (`ID_USER`) REFERENCES `client` (`USER_ID`)
);

DROP TABLE IF EXISTS `feature`;
CREATE TABLE `feature` (
  `ID` bigint(20) NOT NULL,
  `VALUE` varchar(255) NOT NULL,
  `POSITION` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
);


DROP TABLE IF EXISTS `court_feature`;
CREATE TABLE `court_feature` (
  `COURT_ID` bigint(20) NOT NULL,
  `FEATURE_ID` bigint(20) NOT NULL,
  UNIQUE KEY (`FEATURE_ID`),
  FOREIGN KEY (`FEATURE_ID`) REFERENCES `feature` (`ID`),
  FOREIGN KEY (`COURT_ID`) REFERENCES `court` (`ID`)
);


DROP TABLE IF EXISTS `days_closed`;
CREATE TABLE `days_closed` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DAY` int(11) DEFAULT NULL,
  `MONTH` int(11) DEFAULT NULL,
  `YEAR` int(11) DEFAULT NULL,
  `SPORT_FACILITY_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`SPORT_FACILITY_ID`) REFERENCES `sport_facility` (`ID`)
) ;


DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `GROUP_NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ;


DROP TABLE IF EXISTS `group_authorities`;
CREATE TABLE `group_authorities` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ID_AUTHORITY` bigint(20) DEFAULT NULL,
  `ID_GROUP` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`ID_GROUP`) REFERENCES `group` (`ID`),
  FOREIGN KEY (`ID_AUTHORITY`) REFERENCES `authority` (`ID`)
) ;

DROP TABLE IF EXISTS `group_members`;
CREATE TABLE `group_members` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `GROUP_ID` bigint(20) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`),
  FOREIGN KEY (`GROUP_ID`) REFERENCES `group` (`ID`)
) ;

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
  FOREIGN KEY (`USER_FROM_ID`) REFERENCES `user` (`ID`),
  FOREIGN KEY (`USER_TO_ID`) REFERENCES `user` (`ID`)
) ;



DROP TABLE IF EXISTS `rent`;
CREATE TABLE `rent` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_END` datetime DEFAULT NULL,
  `DATE_START` datetime DEFAULT NULL,
  `PAYMENT_STATE` int(11) DEFAULT NULL,
  `QUANTITY_PAID` int(11) DEFAULT NULL,
  `COURT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`COURT_ID`) REFERENCES `court` (`ID`)
) ;

DROP TABLE IF EXISTS `rental_client`;
CREATE TABLE `rental_client` (
  `CLIENT_ID` bigint(20) NOT NULL,
  `RENTAL_ID` bigint(20) NOT NULL,
  UNIQUE KEY `RENTAL_ID` (`RENTAL_ID`),
  FOREIGN KEY (`CLIENT_ID`) REFERENCES `client` (`USER_ID`),
  FOREIGN KEY (`RENTAL_ID`) REFERENCES `rent` (`ID`)
) ;


DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `dayOfWeek` varchar(255) DEFAULT NULL,
  `END_HOUR` int(11) DEFAULT NULL,
  `MINUTE_END` int(11) DEFAULT NULL,
  `MINUTE_START` int(11) DEFAULT NULL,
  `PRICE_COURT` float DEFAULT NULL,
  `PRICE_LIGHT` float DEFAULT NULL,
  `START_HOUR` int(11) DEFAULT NULL,
  `COURT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`COURT_ID`) REFERENCES `court` (`ID`)
) ;

DROP TABLE IF EXISTS `sport_facility_feature`;
CREATE TABLE `sport_facility_feature` (
  `SPORT_FACILITY_ID` bigint(20) NOT NULL,
  `FEATURE_ID` bigint(20) NOT NULL,
  UNIQUE KEY (`FEATURE_ID`),
  FOREIGN KEY (`FEATURE_ID`) REFERENCES `feature` (`ID`),
  FOREIGN KEY (`SPORT_FACILITY_ID`) REFERENCES `sport_facility` (`ID`)
) ;

DROP TABLE IF EXISTS `sport_facility_image`;
CREATE TABLE `sport_facility_image` (
  `SPORT_FACILITY_ID` bigint(20) NOT NULL,
  `IMAGE_ID` bigint(20) NOT NULL,
  UNIQUE KEY `IMAGE_ID` (`IMAGE_ID`),
  FOREIGN KEY (`SPORT_FACILITY_ID`) REFERENCES `sport_facility` (`ID`),
  FOREIGN KEY (`IMAGE_ID`) REFERENCES `image` (`ID`)
) ;

DROP TABLE IF EXISTS `sport_facility_member`;
CREATE TABLE `sport_facility_member` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ID_USER` bigint(20) DEFAULT NULL,
  `ID_SPORTFACILITY` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`ID_SPORTFACILITY`) REFERENCES `sport_facility` (`ID`),
  FOREIGN KEY (`ID_USER`) REFERENCES `client` (`USER_ID`)
) ;

DROP TABLE IF EXISTS `sports_event`;
CREATE TABLE `sports_event` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` text,
  `NAME` varchar(255) NOT NULL,
  `ID_SPORTFACILITY` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`ID_SPORTFACILITY`) REFERENCES `sport_facility` (`ID`)
) ;

DROP TABLE IF EXISTS `sports_events_rentals`;
CREATE TABLE `sports_events_rentals` (
  `SPORT_EVENT_ID` bigint(20) NOT NULL,
  `RENTAL_ID` bigint(20) NOT NULL,
  FOREIGN KEY (`RENTAL_ID`) REFERENCES `rent` (`ID`),
  FOREIGN KEY (`SPORT_EVENT_ID`) REFERENCES `sports_event` (`ID`)
) ;

