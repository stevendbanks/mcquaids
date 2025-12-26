-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.62 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             12.7.0.6850
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for mcquaids_trailers
DROP DATABASE IF EXISTS `mcquaids_trailers`;
CREATE DATABASE IF NOT EXISTS `mcquaids_trailers` /*!40100 DEFAULT CHARACTER SET armscii8 COLLATE armscii8_bin */;
USE `mcquaids_trailers`;

-- Dumping structure for table mcquaids_trailers.codetype
DROP TABLE IF EXISTS `codetype`;
CREATE TABLE IF NOT EXISTS `codetype` (
  `ID` int(4) NOT NULL,
  `EnglishDescription` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `IsTypeOfEquipment` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin2;

-- Dumping data for table mcquaids_trailers.codetype: ~5 rows (approximately)
INSERT INTO `codetype` (`ID`, `EnglishDescription`, `IsTypeOfEquipment`) VALUES
	(1003, 'Flatbed', 'Yes'),
	(1006, 'Province', 'No'),
	(1005, 'Forklift', 'Yes'),
	(1004, 'Container', 'Yes'),
	(1002, 'Trailer', 'Yes');

-- Dumping structure for table mcquaids_trailers.codevalue
DROP TABLE IF EXISTS `codevalue`;
CREATE TABLE IF NOT EXISTS `codevalue` (
  `CodeType` int(4) unsigned NOT NULL,
  `CodeValue` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `EnglishDescription` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `correct` varchar(50) COLLATE armscii8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Dumping data for table mcquaids_trailers.codevalue: ~25 rows (approximately)
INSERT INTO `codevalue` (`CodeType`, `CodeValue`, `EnglishDescription`, `correct`) VALUES
	(1002, '1002-02', 'Furniture', 'Yes'),
	(1003, '1003-02', 'Quickloads', 'Yes'),
	(1002, '1002-01', 'General', 'Yes'),
	(1006, 'PE', 'Prince Edward Island', 'Yes'),
	(1006, 'NB', 'New Brunswick', 'Yes'),
	(1006, 'NS', 'Nova Scotia', 'Yes'),
	(1006, 'NL', 'Newfoundland and Labrador', 'Yes'),
	(1006, 'QC', 'Quebec', 'Yes'),
	(1006, 'ON', 'Ontario', 'Yes'),
	(1002, '1002-03', 'Office', 'Yes'),
	(1002, '1002-04', 'Construction', 'Yes'),
	(1002, '1002-05', 'EOL', 'Yes'),
	(1003, '1003-01', 'Lowboy', 'Yes'),
	(1003, '1003-03', 'Standard', 'Yes'),
	(1003, '1003-04', 'Landoll', 'Yes'),
	(1005, '1005-05', '48V', 'Yes'),
	(1005, '1005-04', '24V', 'Yes'),
	(1005, '1005-03', '36V', 'Yes'),
	(1005, '1005-02', 'LPG', 'Yes'),
	(1005, '1005-01', 'Gas', 'Yes'),
	(1005, '1005-06', 'Diesel', 'Yes'),
	(1004, '1004-01', 'New', 'Yes'),
	(1004, '1004-02', 'Used', 'Yes'),
	(1004, '1004-03', 'ICCL', 'Yes'),
	(1004, '1004-04', 'EOL', 'Yes');

-- Dumping structure for table mcquaids_trailers.customer
DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `CustomerID` int(11) NOT NULL,
  `Notes` varchar(255) COLLATE armscii8_bin NOT NULL,
  `CreatedDateTime` datetime NOT NULL,
  `CreatedUserID` varchar(50) COLLATE armscii8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Dumping data for table mcquaids_trailers.customer: ~0 rows (approximately)

-- Dumping structure for table mcquaids_trailers.equipment
DROP TABLE IF EXISTS `equipment`;
CREATE TABLE IF NOT EXISTS `equipment` (
  `EquipmentNumber` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `EquipmentType` int(4) NOT NULL DEFAULT '0' COMMENT 'Forklift, Furntiture, Container, Landoll',
  `EquipmentSubType` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Breaks down the Equipment Type ',
  `SerialNumber` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ManufacturedDate` date DEFAULT NULL,
  `SpecialNotes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `InspectionDate` date DEFAULT NULL,
  PRIMARY KEY (`EquipmentNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Dumping data for table mcquaids_trailers.equipment: ~3 rows (approximately)
INSERT INTO `equipment` (`EquipmentNumber`, `EquipmentType`, `EquipmentSubType`, `SerialNumber`, `ManufacturedDate`, `SpecialNotes`, `InspectionDate`) VALUES
	('342', 1002, '1002-01', '12345', '2024-01-20', 'Testing  Special Notes', '2024-01-03'),
	('343', 1002, '1002-01', '12345-343', NULL, 'Testing SP 12', NULL),
	('344', 1005, '1005-01', NULL, '2024-05-20', 'First forklift', NULL);

-- Dumping structure for table mcquaids_trailers.forklift
DROP TABLE IF EXISTS `forklift`;
CREATE TABLE IF NOT EXISTS `forklift` (
  `equipmentNumber` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `floor` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `manufacturer` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `size` int(11) DEFAULT '0',
  `fuelType` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`equipmentNumber`),
  CONSTRAINT `FK_forklift_equipment` FOREIGN KEY (`equipmentNumber`) REFERENCES `equipment` (`EquipmentNumber`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Dumping data for table mcquaids_trailers.forklift: ~1 rows (approximately)
INSERT INTO `forklift` (`equipmentNumber`, `floor`, `manufacturer`, `size`, `fuelType`) VALUES
	('344', 't', 'Toshiba', 300, '48V');

-- Dumping structure for table mcquaids_trailers.trailer
DROP TABLE IF EXISTS `trailer`;
CREATE TABLE IF NOT EXISTS `trailer` (
  `EquipmentNumber` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Size` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `DoorLocation` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `Floor` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `Axel` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `insulated` tinyint(1) DEFAULT NULL,
  `TieDown` tinyint(1) DEFAULT NULL,
  `Colour` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '0',
  PRIMARY KEY (`EquipmentNumber`),
  CONSTRAINT `FK_trailer_equipment` FOREIGN KEY (`EquipmentNumber`) REFERENCES `equipment` (`EquipmentNumber`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Dumping data for table mcquaids_trailers.trailer: ~2 rows (approximately)
INSERT INTO `trailer` (`EquipmentNumber`, `Size`, `DoorLocation`, `Floor`, `Axel`, `insulated`, `TieDown`, `Colour`) VALUES
	('342', '4000', 'b', 'y', '4', 0, 0, 'Blue'),
	('343', '4000', 'b', 'y', '4', 0, 1, 'Yellow');

-- Dumping structure for table mcquaids_trailers.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `ID` int(4) DEFAULT NULL,
  `CustomerID` int(4) DEFAULT NULL,
  `EmployeeID` int(4) DEFAULT NULL,
  `FirstName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `LastName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Phone` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Email` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `street` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `City` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Province` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Country` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PostalCode` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Dumping data for table mcquaids_trailers.user: ~4 rows (approximately)
INSERT INTO `user` (`ID`, `CustomerID`, `EmployeeID`, `FirstName`, `LastName`, `Phone`, `Email`, `street`, `City`, `Province`, `Country`, `PostalCode`) VALUES
	(1001, 1001, 1001, 'Steven', 'Banks', '902-892-6462', 'steven.banks@gmail.com', '28 Ferndale Drive', 'Charlottetown', 'PE', 'Canada', 'C1A 6J3'),
	(1002, 1002, 1002, 'Sharon', 'Banks', '902-330-8350', 'steven.banks@gmail.com', '28 Ferndale Drive', 'Charlottetown', 'PE', 'Canada', NULL),
	(1003, 1003, 1003, 'Jim', 'Banks', '902-314-1800', 'jim.banks@apm.ca', '34 Boonies', 'Moncton', 'NB', 'Canada', 'J6G 3F4'),
	(1004, 1004, 1004, 'Greg', 'McQuaid', '902-318-3434', 'greg@mcquaids.ca', 'Out in Sticks', 'Brackley', 'PE', 'Canada', NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
