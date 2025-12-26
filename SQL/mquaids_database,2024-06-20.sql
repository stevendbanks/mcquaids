-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.19 - MySQL Community Server - GPL
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
CREATE DATABASE IF NOT EXISTS `mcquaids_trailers` /*!40100 DEFAULT CHARACTER SET armscii8 COLLATE armscii8_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mcquaids_trailers`;

-- Dumping structure for table mcquaids_trailers.codetype
CREATE TABLE IF NOT EXISTS `codetype` (
  `ID` int NOT NULL,
  `EnglishDescription` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `IsTypeOfEquipment` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin2;

-- Dumping data for table mcquaids_trailers.codetype: ~11 rows (approximately)
INSERT INTO `codetype` (`ID`, `EnglishDescription`, `IsTypeOfEquipment`) VALUES
	(1003, 'Flatbed', 'Yes'),
	(1006, 'Province', 'No'),
	(1005, 'Forklift', 'Yes'),
	(1004, 'Container', 'Yes'),
	(1002, 'Trailer', 'Yes'),
	(1007, 'Container Condition', 'No'),
	(1008, 'Availability Status', 'No'),
	(1009, 'Condition Status', 'No'),
	(1010, 'Maintenance Status', 'No'),
	(1011, 'Cleaning Status', 'No'),
	(1012, 'Lease Status', 'No'),
	(1013, 'Lease Termination Reason Code', 'No');

-- Dumping structure for table mcquaids_trailers.codevalue
CREATE TABLE IF NOT EXISTS `codevalue` (
  `CodeType` int unsigned NOT NULL,
  `CodeValue` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `EnglishDescription` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `correct` varchar(50) CHARACTER SET armscii8 COLLATE armscii8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Dumping data for table mcquaids_trailers.codevalue: ~47 rows (approximately)
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
	(1004, '1004-04', 'EOL', 'Yes'),
	(1002, '1002-06', 'Reefer', 'Yes'),
	(1007, '1007-01', 'New', 'Yes'),
	(1007, '1007-02', 'Painted', 'Yes'),
	(1007, '1007-03', 'EOL', 'Yes'),
	(1007, '1007-04', 'Used', 'Yes'),
	(1008, '1008-01', 'Available', NULL),
	(1008, '1008-02', 'Rented', NULL),
	(1008, '1008-03', 'Under Maintenance', NULL),
	(1009, '1009-01', 'Excellent', NULL),
	(1009, '1009-02', 'Good', NULL),
	(1009, '1009-03', 'Fair', NULL),
	(1009, '1009-04', 'Poor', NULL),
	(1010, '1010-01', 'Due for maintenance', NULL),
	(1010, '1010-02', 'Under Maintenance', NULL),
	(1010, '1010-03', 'Maintenance Completed', NULL),
	(1011, '1011-01', 'Clean', NULL),
	(1011, '1011-02', 'Needs Cleaning', NULL),
	(1011, '1011-03', 'Cleaning In Progress', NULL),
	(1008, '1008-31', 'Rented - Pending Confirmation', NULL),
	(1012, '1012-01', 'Reserved', NULL),
	(1012, '1012-02', 'Signed', NULL),
	(1012, '1012-03', 'In-Progress', NULL),
	(1012, '1012-04', 'Completed', NULL),
	(1013, '1013-01', 'Lease was Completed', NULL),
	(1013, '1013-02', 'Cancelled', NULL),
	(1013, '1013-03', 'Non-Payment', NULL);

-- Dumping structure for table mcquaids_trailers.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `CustomerID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `UserID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Notes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CreatedDateTime` datetime NOT NULL,
  `CreatedUserID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`CustomerID`),
  KEY `FK_customer_user` (`UserID`),
  CONSTRAINT `FK_customer_user` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Dumping data for table mcquaids_trailers.customer: ~0 rows (approximately)
INSERT INTO `customer` (`CustomerID`, `UserID`, `Notes`, `CreatedDateTime`, `CreatedUserID`) VALUES
	('1004', '1004', 'Testing', '2024-06-03 16:44:27', 'SDBANKs');

-- Dumping structure for table mcquaids_trailers.equipment
CREATE TABLE IF NOT EXISTS `equipment` (
  `EquipmentNumber` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `EquipmentType` int NOT NULL DEFAULT '0' COMMENT 'Forklift, Furntiture, Container, Landoll',
  `EquipmentSubType` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Breaks down the Equipment Type ',
  `SerialNumber` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Manufacturer` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ManufacturedDate` date DEFAULT NULL,
  `PurchasePrice` double DEFAULT NULL,
  `PurchaseDate` date DEFAULT NULL,
  `SpecialNotes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `InspectionDate` date DEFAULT NULL COMMENT 'The most recent inspection Date.',
  `Properties` json DEFAULT NULL COMMENT 'Stores equipment specific properties',
  `AvailabilityStatusCode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ConditionStatusCode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `MaintenanceStatusCode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `CleaningStatusCode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `BookingStatusCode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`EquipmentNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Dumping data for table mcquaids_trailers.equipment: ~6 rows (approximately)
INSERT INTO `equipment` (`EquipmentNumber`, `EquipmentType`, `EquipmentSubType`, `SerialNumber`, `Manufacturer`, `ManufacturedDate`, `PurchasePrice`, `PurchaseDate`, `SpecialNotes`, `InspectionDate`, `Properties`, `AvailabilityStatusCode`, `ConditionStatusCode`, `MaintenanceStatusCode`, `CleaningStatusCode`, `BookingStatusCode`) VALUES
	('342', 1002, '1002-01', '12345', 'Someone', NULL, 2010, '2023-05-27', 'Testing  Special Notes  Test 3', NULL, '{"axel": "5", "size": 600, "floor": "Wood", "width": 34, "colour": "Black", "length": 66, "tieDown": true, "insulated": false, "doorLocation": "Side"}', '1008-01', '1009-02', '1010-03', NULL, NULL),
	('343', 1005, '1005-03', '12345-343', NULL, '2024-05-22', NULL, NULL, 'Testing SP 12', '2024-05-22', '{"axel": "", "size": 0, "floor": "", "width": 0, "colour": "", "length": 0, "tieDown": false, "insulated": false, "doorLocation": ""}', NULL, NULL, NULL, NULL, NULL),
	('344', 1005, '1005-01', '324', NULL, '2024-05-20', NULL, NULL, 'First forklift test', '2024-05-23', '{"size": 4, "floor": "sfsd", "fuelType": "diesel", "manufacturer": "sadfs"}', NULL, NULL, NULL, NULL, NULL),
	('555', 1005, '1005-05', '234234', NULL, '2020-01-02', NULL, NULL, 'Need to keep a close look at the brakes', '2021-05-09', '{"size": 4, "floor": "sfsd", "fuelType": "diesel", "manufacturer": "sadfs"}', NULL, NULL, NULL, NULL, NULL),
	('600', 1003, '1003-03', '12312', NULL, '2018-02-05', NULL, NULL, 'testing flatbeds', '2024-05-23', '{"size": 3000, "floor": "wood", "fuelType": "diesel", "manufacturer": "dsfs"}', NULL, NULL, NULL, NULL, NULL),
	('F111', 1004, '1004-01', '12345', 'SSRTB Inc', '2024-05-24', NULL, NULL, 'werwer -3234234', '2024-04-05', '{"size": 34, "floor": "werw", "fuelType": "343"}', NULL, NULL, NULL, NULL, NULL);

-- Dumping structure for table mcquaids_trailers.lease
CREATE TABLE IF NOT EXISTS `lease` (
  `CustomerID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `LeaseID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `LeaseSignDate` date DEFAULT NULL,
  `LeaseTerminationDate` date DEFAULT NULL,
  `LeaseTerminationReasonCode` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `LeaseStartDate` date DEFAULT NULL,
  `LeaseEndDate` date DEFAULT NULL,
  `LeaseStatusCode` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Instructions` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  KEY `Index 2` (`CustomerID`,`LeaseID`),
  CONSTRAINT `FK_lease_customer` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`CustomerID`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin COMMENT='This will be the customer that has an arrangement to lease one or more pieces of equipment';

-- Dumping data for table mcquaids_trailers.lease: ~2 rows (approximately)
INSERT INTO `lease` (`CustomerID`, `LeaseID`, `LeaseSignDate`, `LeaseTerminationDate`, `LeaseTerminationReasonCode`, `LeaseStartDate`, `LeaseEndDate`, `LeaseStatusCode`, `Instructions`) VALUES
	('1004', '0001', '2024-06-04', '2035-01-01', '1013-01', '2024-06-04', '2025-06-04', '1012-02', 'Testing NUmber 34'),
	('1004', '0002', '2024-06-04', '2024-06-04', '1013-03', '2024-05-04', '2024-07-04', '1012-01', 'Testing');

-- Dumping structure for view mcquaids_trailers.leased_equipment_view
-- Creating temporary table to overcome VIEW dependency errors
CREATE TABLE `leased_equipment_view` (
	`LeaseID` VARCHAR(10) NOT NULL COLLATE 'utf8mb4_unicode_ci',
	`EquipmentNumber` VARCHAR(8) NOT NULL COLLATE 'utf8mb4_unicode_ci',
	`DateAddedToLease` DATE NULL,
	`DateRemovedFromLease` DATE NULL,
	`Notes` VARCHAR(100) NULL COLLATE 'utf8mb4_unicode_ci',
	`EquipmentType` INT(10) NOT NULL COMMENT 'Forklift, Furntiture, Container, Landoll',
	`EquipmentSubType` VARCHAR(7) NULL COMMENT 'Breaks down the Equipment Type ' COLLATE 'utf8mb4_unicode_ci',
	`SerialNumber` VARCHAR(16) NULL COLLATE 'utf8mb4_unicode_ci',
	`Manufacturer` VARCHAR(30) NULL COLLATE 'utf8mb4_unicode_ci',
	`ManufacturedDate` DATE NULL,
	`PurchasePrice` DOUBLE NULL,
	`PurchaseDate` DATE NULL,
	`SpecialNotes` VARCHAR(255) NULL COLLATE 'utf8mb4_unicode_ci',
	`InspectionDate` DATE NULL COMMENT 'The most recent inspection Date.',
	`Properties` JSON NULL COMMENT 'Stores equipment specific properties',
	`AvailabilityStatusCode` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci',
	`ConditionStatusCode` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci',
	`MaintenanceStatusCode` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci',
	`CleaningStatusCode` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci',
	`BookingStatusCode` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci',
	`availabilityStatusText` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci',
	`conditionStatusText` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci',
	`maintenanceStatusText` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci',
	`cleaningStatusTest` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci',
	`equipmentTypeText` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci',
	`equipmentSubTypeText` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci',
	`bookingStatusCodeText` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci'
) ENGINE=MyISAM;

-- Dumping structure for table mcquaids_trailers.lease_equipment
CREATE TABLE IF NOT EXISTS `lease_equipment` (
  `LeaseID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `EquipmentNumber` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `DateAddedToLease` date DEFAULT NULL,
  `DateRemovedFromLease` date DEFAULT NULL,
  `Notes` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`LeaseID`,`EquipmentNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin COMMENT='This table will display what equipment are associated with the lease.';

-- Dumping data for table mcquaids_trailers.lease_equipment: ~2 rows (approximately)
INSERT INTO `lease_equipment` (`LeaseID`, `EquipmentNumber`, `DateAddedToLease`, `DateRemovedFromLease`, `Notes`) VALUES
	('0001', '342', '2024-06-18', NULL, NULL),
	('0001', '343', '2024-06-20', NULL, NULL),
	('0001', '500', '2024-06-20', NULL, NULL);

-- Dumping structure for view mcquaids_trailers.qryequipmentquerydto
-- Creating temporary table to overcome VIEW dependency errors
CREATE TABLE `qryequipmentquerydto` (
	`EquipmentNumber` VARCHAR(8) NOT NULL COLLATE 'utf8mb4_unicode_ci',
	`EquipmentType` INT(10) NOT NULL COMMENT 'Forklift, Furntiture, Container, Landoll',
	`EquipmentSubType` VARCHAR(7) NULL COMMENT 'Breaks down the Equipment Type ' COLLATE 'utf8mb4_unicode_ci',
	`SerialNumber` VARCHAR(16) NULL COLLATE 'utf8mb4_unicode_ci',
	`Manufacturer` VARCHAR(30) NULL COLLATE 'utf8mb4_unicode_ci',
	`ManufacturedDate` DATE NULL,
	`PurchasePrice` DOUBLE NULL,
	`PurchaseDate` DATE NULL,
	`SpecialNotes` VARCHAR(255) NULL COLLATE 'utf8mb4_unicode_ci',
	`InspectionDate` DATE NULL COMMENT 'The most recent inspection Date.',
	`Properties` JSON NULL COMMENT 'Stores equipment specific properties',
	`AvailabilityStatusCode` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci',
	`ConditionStatusCode` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci',
	`MaintenanceStatusCode` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci',
	`CleaningStatusCode` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci',
	`BookingStatusCode` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci',
	`availabilityStatusText` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci',
	`conditionStatusText` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci',
	`maintenanceStatusText` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci',
	`cleaningStatusTest` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci',
	`equipmentTypeText` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci',
	`equipmentSubTypeText` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci',
	`bookingStatusCodeText` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci'
) ENGINE=MyISAM;

-- Dumping structure for table mcquaids_trailers.user
CREATE TABLE IF NOT EXISTS `user` (
  `UserID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `FirstName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `LastName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Phone` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Email` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `street` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `City` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Province` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Country` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PostalCode` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Dumping data for table mcquaids_trailers.user: ~4 rows (approximately)
INSERT INTO `user` (`UserID`, `FirstName`, `LastName`, `Phone`, `Email`, `street`, `City`, `Province`, `Country`, `PostalCode`) VALUES
	('1001', 'Steven', 'Banks', '902-892-6462', 'steven.banks@gmail.com', '28 Ferndale Drive', 'Charlottetown', 'PE', 'Canada', 'C1A 6J3'),
	('1002', 'Sharon', 'Banks', '902-330-8350', 'steven.banks@gmail.com', '28 Ferndale Drive', 'Charlottetown', 'PE', 'Canada', NULL),
	('1003', 'Jim', 'Banks', '902-314-1800', 'jim.banks@apm.ca', '34 Boonies', 'Moncton', 'NB', 'Canada', 'J6G 3F4'),
	('1004', 'Greg', 'McQuaid', '902-318-3434', 'greg@mcquaids.ca', 'Out in Sticks', 'Brackley', 'PE', 'Canada', NULL);

-- Removing temporary table and create final VIEW structure
DROP TABLE IF EXISTS `leased_equipment_view`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `leased_equipment_view` AS select `le`.`LeaseID` AS `LeaseID`,`le`.`EquipmentNumber` AS `EquipmentNumber`,`le`.`DateAddedToLease` AS `DateAddedToLease`,`le`.`DateRemovedFromLease` AS `DateRemovedFromLease`,`le`.`Notes` AS `Notes`,`q`.`EquipmentType` AS `EquipmentType`,`q`.`EquipmentSubType` AS `EquipmentSubType`,`q`.`SerialNumber` AS `SerialNumber`,`q`.`Manufacturer` AS `Manufacturer`,`q`.`ManufacturedDate` AS `ManufacturedDate`,`q`.`PurchasePrice` AS `PurchasePrice`,`q`.`PurchaseDate` AS `PurchaseDate`,`q`.`SpecialNotes` AS `SpecialNotes`,`q`.`InspectionDate` AS `InspectionDate`,`q`.`Properties` AS `Properties`,`q`.`AvailabilityStatusCode` AS `AvailabilityStatusCode`,`q`.`ConditionStatusCode` AS `ConditionStatusCode`,`q`.`MaintenanceStatusCode` AS `MaintenanceStatusCode`,`q`.`CleaningStatusCode` AS `CleaningStatusCode`,`q`.`BookingStatusCode` AS `BookingStatusCode`,`q`.`availabilityStatusText` AS `availabilityStatusText`,`q`.`conditionStatusText` AS `conditionStatusText`,`q`.`maintenanceStatusText` AS `maintenanceStatusText`,`q`.`cleaningStatusTest` AS `cleaningStatusTest`,`q`.`equipmentTypeText` AS `equipmentTypeText`,`q`.`equipmentSubTypeText` AS `equipmentSubTypeText`,`q`.`bookingStatusCodeText` AS `bookingStatusCodeText` from (`lease_equipment` `le` join `qryequipmentquerydto` `q` on((`le`.`EquipmentNumber` = `q`.`EquipmentNumber`)));

-- Removing temporary table and create final VIEW structure
DROP TABLE IF EXISTS `qryequipmentquerydto`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `qryequipmentquerydto` AS select `equipment`.`EquipmentNumber` AS `EquipmentNumber`,`equipment`.`EquipmentType` AS `EquipmentType`,`equipment`.`EquipmentSubType` AS `EquipmentSubType`,`equipment`.`SerialNumber` AS `SerialNumber`,`equipment`.`Manufacturer` AS `Manufacturer`,`equipment`.`ManufacturedDate` AS `ManufacturedDate`,`equipment`.`PurchasePrice` AS `PurchasePrice`,`equipment`.`PurchaseDate` AS `PurchaseDate`,`equipment`.`SpecialNotes` AS `SpecialNotes`,`equipment`.`InspectionDate` AS `InspectionDate`,`equipment`.`Properties` AS `Properties`,`equipment`.`AvailabilityStatusCode` AS `AvailabilityStatusCode`,`equipment`.`ConditionStatusCode` AS `ConditionStatusCode`,`equipment`.`MaintenanceStatusCode` AS `MaintenanceStatusCode`,`equipment`.`CleaningStatusCode` AS `CleaningStatusCode`,`equipment`.`BookingStatusCode` AS `BookingStatusCode`,`cv1`.`EnglishDescription` AS `availabilityStatusText`,`cv2`.`EnglishDescription` AS `conditionStatusText`,`cv3`.`EnglishDescription` AS `maintenanceStatusText`,`cv4`.`EnglishDescription` AS `cleaningStatusTest`,`cv5`.`EnglishDescription` AS `equipmentTypeText`,`cv6`.`EnglishDescription` AS `equipmentSubTypeText`,`cv7`.`EnglishDescription` AS `bookingStatusCodeText` from (((((((`equipment` left join `codevalue` `cv1` on((`equipment`.`AvailabilityStatusCode` = `cv1`.`CodeValue`))) left join `codevalue` `cv2` on((`equipment`.`ConditionStatusCode` = `cv2`.`CodeValue`))) left join `codevalue` `cv3` on((`equipment`.`MaintenanceStatusCode` = `cv3`.`CodeValue`))) left join `codevalue` `cv4` on((`equipment`.`CleaningStatusCode` = `cv4`.`CodeValue`))) left join `codetype` `cv5` on((`equipment`.`EquipmentType` = `cv5`.`ID`))) left join `codevalue` `cv6` on((`equipment`.`EquipmentSubType` = `cv6`.`CodeValue`))) left join `codevalue` `cv7` on((`equipment`.`BookingStatusCode` = `cv7`.`CodeValue`))) group by `equipment`.`EquipmentNumber`;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
