-- --------------------------------------------------------
-- Host:                         homeowner.crqhiyzw0xpl.ap-south-1.rds.amazonaws.com
-- Server version:               8.0.17 - Source distribution
-- Server OS:                    Linux
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for homeinsurance
DROP DATABASE IF EXISTS `homeinsurance`;
CREATE DATABASE IF NOT EXISTS `homeinsurance` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `homeinsurance`;

-- Dumping structure for table homeinsurance.COVERAGE_DETAILS
DROP TABLE IF EXISTS `COVERAGE_DETAILS`;
CREATE TABLE IF NOT EXISTS `COVERAGE_DETAILS` (
  `QUOTE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `MONTHLY_PREMIUM` decimal(10,2) NOT NULL,
  `DWELLING_COVERAGE` decimal(10,2) DEFAULT NULL,
  `DETACHED_STRUCTURES` decimal(10,2) DEFAULT NULL,
  `PERSONAL_PROPERTY` decimal(10,2) DEFAULT NULL,
  `ADDITIONAL` decimal(10,2) DEFAULT NULL,
  `MEDICAL` int(11) DEFAULT NULL,
  `DEDUCTIBLE` decimal(10,2) DEFAULT NULL,
  `PROPERTY_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`QUOTE_ID`),
  KEY `PROPERTY_ID` (`PROPERTY_ID`),
  CONSTRAINT `COVERAGE_DETAILS_ibfk_1` FOREIGN KEY (`PROPERTY_ID`) REFERENCES `PROPERTY_INFO` (`PROPERTY_ID`) ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table homeinsurance.HOME_INFO
DROP TABLE IF EXISTS `HOME_INFO`;
CREATE TABLE IF NOT EXISTS `HOME_INFO` (
  `HOME_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADDRESS` varchar(255) NOT NULL,
  `ADDRESS_LINE_2` varchar(255) DEFAULT NULL,
  `STATE` varchar(20) NOT NULL,
  `CITY` varchar(20) NOT NULL,
  `ZIP` varchar(20) NOT NULL,
  `RESIDENCE_TYPE` varchar(10) DEFAULT NULL,
  `HOME_USE` varchar(20) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `QUOTE_ID` int(11) DEFAULT NULL,
  `PROPERTY_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`HOME_ID`),
  KEY `USER_ID` (`USER_ID`),
  KEY `QUOTE_ID` (`QUOTE_ID`),
  CONSTRAINT `HOME_INFO_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `USERS` (`USER_ID`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `HOME_INFO_ibfk_2` FOREIGN KEY (`QUOTE_ID`) REFERENCES `COVERAGE_DETAILS` (`QUOTE_ID`) ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table homeinsurance.HOME_OWNER
DROP TABLE IF EXISTS `HOME_OWNER`;
CREATE TABLE IF NOT EXISTS `HOME_OWNER` (
  `HOME_OWNER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(255) NOT NULL,
  `LAST_NAME` varchar(255) DEFAULT NULL,
  `BIRTH` varchar(20) NOT NULL,
  `SSN` varchar(20) NOT NULL,
  `EMAIL` varchar(50) NOT NULL,
  `RETIRED` varchar(10) DEFAULT NULL,
  `HOME_USE` varchar(20) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  PRIMARY KEY (`HOME_OWNER_ID`),
  KEY `USER_ID` (`USER_ID`),
  CONSTRAINT `HOME_OWNER_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `USERS` (`USER_ID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table homeinsurance.PROPERTY_INFO
DROP TABLE IF EXISTS `PROPERTY_INFO`;
CREATE TABLE IF NOT EXISTS `PROPERTY_INFO` (
  `PROPERTY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `YEAR_WAS_BUILT` varchar(255) NOT NULL,
  `VALUE_OF_HOME` varchar(255) NOT NULL,
  `FOOTAGE` varchar(255) DEFAULT NULL,
  `DWELLING` varchar(20) NOT NULL,
  `ROOF` varchar(20) NOT NULL,
  `BATHS` varchar(20) NOT NULL,
  `HALF_BATHS` varchar(10) DEFAULT NULL,
  `POOL` varchar(10) DEFAULT NULL,
  `GARAGE` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`PROPERTY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table homeinsurance.USERS
DROP TABLE IF EXISTS `USERS`;
CREATE TABLE IF NOT EXISTS `USERS` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(255) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  `USER_ROLE` int(11) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
