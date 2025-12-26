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
DROP DATABASE IF EXISTS `mcquaids_trailers`;
CREATE DATABASE IF NOT EXISTS `mcquaids_trailers` /*!40100 DEFAULT CHARACTER SET armscii8 COLLATE armscii8_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mcquaids_trailers`;

-- Dumping structure for procedure mcquaids_trailers.AddUserAndCustomer
DROP PROCEDURE IF EXISTS `AddUserAndCustomer`;
DELIMITER //
CREATE PROCEDURE `AddUserAndCustomer`(
	IN `p_UserID` VARCHAR(50),
	IN `p_FirstName` VARCHAR(50),
	IN `p_LastName` VARCHAR(50),
	IN `p_Phone` VARCHAR(12),
	IN `p_Email` VARCHAR(25),
	IN `p_Street` VARCHAR(25),
	IN `p_City` VARCHAR(25),
	IN `p_Province` VARCHAR(25),
	IN `p_Country` VARCHAR(25),
	IN `p_PostalCode` VARCHAR(7),
	IN `p_Notes` VARCHAR(255)
)
BEGIN
 
    START TRANSACTION;

    -- Insert into user table
    INSERT INTO `user` (
        `UserID`, `FirstName`, `LastName`, `Phone`, `Email`, `street`, `City`, `Province`, `Country`, `PostalCode`
    ) VALUES (
        p_UserID, p_FirstName, p_LastName, p_Phone, p_Email, p_Street, p_City, p_Province, p_Country, p_PostalCode
    );

    -- Insert into customer table
    INSERT INTO `customer` (
        `UserID`, `Notes`
    ) VALUES (
        p_UserID, p_Notes
    );

    COMMIT;
END//
DELIMITER ;

-- Dumping structure for table mcquaids_trailers.codetype
DROP TABLE IF EXISTS `codetype`;
CREATE TABLE IF NOT EXISTS `codetype` (
  `ID` int NOT NULL,
  `EnglishDescription` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `IsTypeOfEquipment` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin2;

-- Dumping data for table mcquaids_trailers.codetype: ~20 rows (approximately)
INSERT INTO `codetype` (`ID`, `EnglishDescription`, `IsTypeOfEquipment`) VALUES
	(1003, 'Flatbed', 'Yes'),
	(1006, 'Province', 'No'),
	(1005, 'Forklift', 'Yes'),
	(1019, 'Container Types', 'No'),
	(1002, 'Trailer', 'Yes'),
	(1007, 'Container Condition', 'No'),
	(1008, 'Availability Status', 'No'),
	(1009, 'Condition Status', 'No'),
	(1010, 'Maintenance Status', 'No'),
	(1011, 'Cleaning Status', 'No'),
	(1012, 'Lease Status', 'No'),
	(1013, 'Lease Termination Reason Code', 'No'),
	(1014, 'Trailer Lengths', 'No'),
	(1015, 'Door Locations', 'No'),
	(1016, 'Door Types', 'No'),
	(1017, 'Floor Types', 'No'),
	(1018, 'Axel Types', 'No'),
	(1004, 'Container', 'Yes'),
	(1020, 'Container Sizes', '0'),
	(1021, 'Container Doors', 'No');

-- Dumping structure for table mcquaids_trailers.codevalue
DROP TABLE IF EXISTS `codevalue`;
CREATE TABLE IF NOT EXISTS `codevalue` (
  `CodeType` int unsigned NOT NULL,
  `CodeValue` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `EnglishDescription` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `correct` varchar(50) CHARACTER SET armscii8 COLLATE armscii8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Dumping data for table mcquaids_trailers.codevalue: ~92 rows (approximately)
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
	(1019, '1019-01', 'New', 'Yes'),
	(1019, '1019-02', 'Used', 'Yes'),
	(1019, '1019-03', 'ICCL', 'Yes'),
	(1019, '1019-04', 'EOL', 'Yes'),
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
	(1010, '1010-01', 'Needs Maintenance', NULL),
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
	(1013, '1013-03', 'Non-Payment', NULL),
	(1014, '20', '20', NULL),
	(1014, '30', '30', NULL),
	(1014, '32', '32', NULL),
	(1014, '34', '34', NULL),
	(1014, '36', '36', NULL),
	(1014, '40', '40', NULL),
	(1014, '45', '45', NULL),
	(1014, '48', '48', NULL),
	(1014, '53', '53', NULL),
	(1015, '1015-01', 'Standard', NULL),
	(1015, '1015-02', 'Side', NULL),
	(1015, '1015-03', 'Double', NULL),
	(1016, '1016-01', 'Rollup', NULL),
	(1016, '1016-02', 'Barn', NULL),
	(1016, '1016-03', 'Tribarn', NULL),
	(1017, '1017-01', 'Wood', NULL),
	(1017, '1017-02', 'Aluminum', NULL),
	(1017, '1017-03', 'Steel', NULL),
	(1017, '1017-04', 'Corrugated', NULL),
	(1018, '1018-01', '0', NULL),
	(1018, '1018-02', '1', NULL),
	(1018, '1018-03', '2', NULL),
	(1018, '1018-04', '3', NULL),
	(1004, '1004-01', 'Standard', NULL),
	(1004, '1004-02', 'High Cube', NULL),
	(1004, '1004-03', 'Reefer', NULL),
	(1004, '1004-04', 'High Cube Reefer', NULL),
	(1020, '1020-10', '10', NULL),
	(1020, '1020-20', '20', NULL),
	(1020, '1020-40', '40', NULL),
	(1020, '1020-53', '53', NULL),
	(1020, '1020-40HQ', '40HQ', NULL),
	(1020, '1020-40R', '40R', NULL),
	(1020, '1020-45R', '45R', NULL),
	(1020, '1020-48R', '48R', NULL),
	(1020, '1020-53HQ', '53HQ', NULL),
	(1021, '1021-01', 'Cargo Doors (Standard)', NULL),
	(1021, '1021-02', 'Full-Length Doors', NULL),
	(1021, '1021-03', 'Man Doors', NULL),
	(1021, '1021-04', 'Storefront Doors', NULL),
	(1021, '1021-05', 'Roll-Up Doors', NULL);

-- Dumping structure for table mcquaids_trailers.customer
DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `UserID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Notes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `CreatedDateTime` timestamp NOT NULL DEFAULT (curdate()),
  `CreatedUserID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`UserID`) USING BTREE,
  CONSTRAINT `FK_customer_user` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Dumping data for table mcquaids_trailers.customer: ~4 rows (approximately)
INSERT INTO `customer` (`UserID`, `Notes`, `CreatedDateTime`, `CreatedUserID`) VALUES
	('1004', 'Testing', '2024-06-03 19:44:27', 'SDBANKS'),
	('dsfgd', 'rhtrtr', '2024-07-24 03:00:00', ''),
	('fmbanks', NULL, '2024-07-25 03:00:00', ''),
	('ttt1', NULL, '2024-07-24 03:00:00', '');

-- Dumping structure for table mcquaids_trailers.equipment
DROP TABLE IF EXISTS `equipment`;
CREATE TABLE IF NOT EXISTS `equipment` (
  `EquipmentNumber` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `EquipmentCategory` int NOT NULL DEFAULT (0),
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
  PRIMARY KEY (`EquipmentNumber`),
  KEY `FK_equipment_equipment_categories` (`EquipmentCategory`),
  CONSTRAINT `FK_equipment_equipment_categories` FOREIGN KEY (`EquipmentCategory`) REFERENCES `equipment_categories` (`CategoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Dumping data for table mcquaids_trailers.equipment: ~6 rows (approximately)
INSERT INTO `equipment` (`EquipmentNumber`, `EquipmentCategory`, `EquipmentType`, `EquipmentSubType`, `SerialNumber`, `Manufacturer`, `ManufacturedDate`, `PurchasePrice`, `PurchaseDate`, `SpecialNotes`, `InspectionDate`, `Properties`, `AvailabilityStatusCode`, `ConditionStatusCode`, `MaintenanceStatusCode`, `CleaningStatusCode`, `BookingStatusCode`) VALUES
	('342', 1001, 1002, '1002-01', '12345', 'Someone', '2015-03-25', 2010.56, '2023-05-27', 'Testing  Special Notes  Test 3', '2024-07-02', '{"axel": ["1018-04"], "floor": ["1017-03"], "colour": ["Yellow"], "length": ["34"], "tieDown": ["true"], "doorType": ["1016-02"], "insulated": ["true"], "doorLocation": ["1015-03"]}', '1008-02', '1009-02', '1010-03', NULL, NULL),
	('343', 1001, 1005, '1005-03', '12345-343', NULL, '2023-08-20', NULL, NULL, 'Testing SP 12', '2023-08-20', '{"axel": "", "size": 0, "floor": "", "width": 0, "colour": "", "length": 0, "tieDown": false, "insulated": false, "doorLocation": ""}', '1008-02', NULL, NULL, NULL, NULL),
	('344', 1001, 1002, '1002-01', '654321', 'ME', '2018-06-05', 8000, '2024-07-01', 'testing SPs', '2024-07-12', '{"axel": ["1018-03"], "floor": ["1017-03"], "colour": ["Green"], "length": ["34"], "tieDown": "false", "doorType": ["1016-03"], "insulated": ["true"], "doorLocation": ["1015-03"]}', '1008-02', '1009-02', '1010-03', NULL, NULL),
	('555', 1001, 1005, '1005-05', '234234', NULL, '2020-01-02', NULL, NULL, 'Need to keep a close look at the brakes', '2021-05-09', '{"size": 4, "floor": "sfsd", "fuelType": "diesel", "manufacturer": "sadfs"}', '1008-01', NULL, NULL, NULL, NULL),
	('600', 1001, 1003, '1003-03', '12312', NULL, '2018-02-05', NULL, NULL, 'testing flatbeds', '2024-05-23', '{"size": 3000, "floor": "wood", "fuelType": "diesel", "manufacturer": "dsfs"}', '1008-02', NULL, NULL, NULL, NULL),
	('F111', 1001, 1004, '1004-01', '12345', 'SSRTB Inc', '2024-05-24', 0, NULL, 'werwer -3234234', '2024-04-05', '{"size": ["1020-53HQ"], "Doors": ["1021-01"], "Weight": [""], "Capacity": [""]}', '1008-03', '', '', NULL, NULL);

-- Dumping structure for table mcquaids_trailers.equipment_categories
DROP TABLE IF EXISTS `equipment_categories`;
CREATE TABLE IF NOT EXISTS `equipment_categories` (
  `CategoryID` int NOT NULL DEFAULT '0',
  `EquipmentType` int NOT NULL DEFAULT '0' COMMENT 'Forklift, Furntiture, Container, Landoll',
  `EquipmentSubType` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Breaks down the Equipment Type ',
  `Properties` json DEFAULT NULL COMMENT 'Stores equipment specific properties',
  `HaulingEquipment` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Breaks down the Equipment Type ',
  UNIQUE KEY `PrimaryKey` (`CategoryID`),
  KEY `EquipmentCategory` (`EquipmentSubType`,`EquipmentType`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Dumping data for table mcquaids_trailers.equipment_categories: ~1 rows (approximately)
INSERT INTO `equipment_categories` (`CategoryID`, `EquipmentType`, `EquipmentSubType`, `Properties`, `HaulingEquipment`) VALUES
	(1001, 1002, '1002-01', NULL, NULL);

-- Dumping structure for table mcquaids_trailers.equipment_location
DROP TABLE IF EXISTS `equipment_location`;
CREATE TABLE IF NOT EXISTS `equipment_location` (
  `equipmentNumber` varchar(8) COLLATE armscii8_bin NOT NULL,
  `locationID` int NOT NULL,
  `locationDescription` varchar(255) COLLATE armscii8_bin DEFAULT NULL,
  `street` varchar(50) COLLATE armscii8_bin DEFAULT NULL,
  `city` varchar(50) COLLATE armscii8_bin DEFAULT NULL,
  `provinceCode` varchar(2) COLLATE armscii8_bin DEFAULT NULL,
  `postalCode` varchar(7) COLLATE armscii8_bin DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `createdDatetime` datetime DEFAULT NULL,
  `createdUserID` varchar(50) COLLATE armscii8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Dumping data for table mcquaids_trailers.equipment_location: ~1 rows (approximately)
INSERT INTO `equipment_location` (`equipmentNumber`, `locationID`, `locationDescription`, `street`, `city`, `provinceCode`, `postalCode`, `latitude`, `longitude`, `createdDatetime`, `createdUserID`) VALUES
	('342', 10001, 'McQuads Allen Street', 'Allen Street', 'Charlottetown', 'PE', NULL, NULL, NULL, '2024-07-29 16:24:52', 'SDABNKS');

-- Dumping structure for table mcquaids_trailers.error_log
DROP TABLE IF EXISTS `error_log`;
CREATE TABLE IF NOT EXISTS `error_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `error_message` text COLLATE armscii8_bin,
  `error_code` int DEFAULT NULL,
  `error_state` char(5) COLLATE armscii8_bin DEFAULT NULL,
  `error_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Dumping data for table mcquaids_trailers.error_log: ~0 rows (approximately)

-- Dumping structure for procedure mcquaids_trailers.GetEquipmentReport
DROP PROCEDURE IF EXISTS `GetEquipmentReport`;
DELIMITER //
CREATE PROCEDURE `GetEquipmentReport`()
BEGIN
    SELECT 
        EquipmentTypeText,
        EquipmentSubTypeText,
        COUNT(*) AS TotalCount,
        SUM(CASE WHEN AvailabilityStatusCode = '1008-01' THEN 1 ELSE 0 END) AS AvailableCount,
        SUM(CASE WHEN MaintenanceStatusCode = '1010-01' THEN 1 ELSE 0 END) AS MaintenanceCount,
        SUM(CASE WHEN ConditionStatusCode = '1009-01' THEN 1 ELSE 0 END) AS GoodConditionCount,
        SUM(CASE WHEN ConditionStatusCode = '1009-02' THEN 1 ELSE 0 END) AS FairConditionCount,
        SUM(CASE WHEN ConditionStatusCode = '1009-03' THEN 1 ELSE 0 END) AS PoorConditionCount,
        '' AS TotalType
    FROM 
        qryequipmentquerydto
    GROUP BY 
        EquipmentType, EquipmentSubType

    UNION ALL

    SELECT 
        EquipmentTypeText,
        NULL AS EquipmentSubTypeText,
        COUNT(*) AS TotalCount,
        SUM(CASE WHEN AvailabilityStatusCode = '1008-01' THEN 1 ELSE 0 END) AS AvailableCount,
        SUM(CASE WHEN MaintenanceStatusCode = '1010-01' THEN 1 ELSE 0 END) AS MaintenanceCount,
        SUM(CASE WHEN ConditionStatusCode = '1009-01' THEN 1 ELSE 0 END) AS GoodConditionCount,
        SUM(CASE WHEN ConditionStatusCode = '1009-02' THEN 1 ELSE 0 END) AS FairConditionCount,
        SUM(CASE WHEN ConditionStatusCode = '1009-03' THEN 1 ELSE 0 END) AS PoorConditionCount,
        'Total' AS TotalType
    FROM 
        qryequipmentquerydto
    GROUP BY 
        EquipmentType

    ORDER BY 
        EquipmentTypeText, 
        EquipmentSubTypeText;
END//
DELIMITER ;

-- Dumping structure for table mcquaids_trailers.lease
DROP TABLE IF EXISTS `lease`;
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
  CONSTRAINT `FK_lease_customer` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin COMMENT='This will be the customer that has an arrangement to lease one or more pieces of equipment';

-- Dumping data for table mcquaids_trailers.lease: ~2 rows (approximately)
INSERT INTO `lease` (`CustomerID`, `LeaseID`, `LeaseSignDate`, `LeaseTerminationDate`, `LeaseTerminationReasonCode`, `LeaseStartDate`, `LeaseEndDate`, `LeaseStatusCode`, `Instructions`) VALUES
	('1004', '0001', '2024-06-04', '2035-01-01', '1013-01', '2024-06-04', '2025-06-04', '1012-02', 'Testing Number 34'),
	('1004', '0002', '2024-06-04', '2024-06-04', '1013-03', '2024-05-04', '2027-01-02', '1012-01', 'Testing');

-- Dumping structure for view mcquaids_trailers.leased_equipment_view
DROP VIEW IF EXISTS `leased_equipment_view`;
-- Creating temporary table to overcome VIEW dependency errors
CREATE TABLE `leased_equipment_view` 
) ENGINE=MyISAM;

-- Dumping structure for table mcquaids_trailers.lease_equipment
DROP TABLE IF EXISTS `lease_equipment`;
CREATE TABLE IF NOT EXISTS `lease_equipment` (
  `LeaseID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `EquipmentNumber` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `DateAddedToLease` date DEFAULT NULL,
  `DateRemovedFromLease` date DEFAULT NULL,
  `Notes` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`LeaseID`,`EquipmentNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin COMMENT='This table will display what equipment are associated with the lease.';

-- Dumping data for table mcquaids_trailers.lease_equipment: ~3 rows (approximately)
INSERT INTO `lease_equipment` (`LeaseID`, `EquipmentNumber`, `DateAddedToLease`, `DateRemovedFromLease`, `Notes`) VALUES
	('0001', '342', '2024-06-18', NULL, 'Need Additional stuff'),
	('0001', '343', '2024-06-20', NULL, NULL),
	('0002', '600', '2024-06-23', NULL, NULL);

-- Dumping structure for procedure mcquaids_trailers.ListEquipmentPastOrUpcomingAnniversary
DROP PROCEDURE IF EXISTS `ListEquipmentPastOrUpcomingAnniversary`;
DELIMITER //
CREATE PROCEDURE `ListEquipmentPastOrUpcomingAnniversary`(
	IN `days` INT
)
BEGIN
    SELECT
        equipmentNumber,
        equipmentTypeText,
        equipmentSubtypeText,
        InspectionDate, 
        DATE_ADD(InspectionDate, INTERVAL 1 YEAR) AS InspectionExpiryDate,
        ConditionStatusCode,
        CASE 
            WHEN DATE_ADD(InspectionDate, INTERVAL 1 YEAR) < CURDATE() THEN 'Expired'
            ELSE ''
        END AS InspectionStatus,
        CASE 
            WHEN DATE_ADD(InspectionDate, INTERVAL 1 YEAR) >= CURDATE() THEN DATEDIFF(DATE_ADD(InspectionDate, INTERVAL 1 YEAR), CURDATE())
            ELSE NULL
        END AS DaysUntilExpiration
    FROM 
        qryequipmentquerydto
    WHERE 
        DATE_ADD(InspectionDate, INTERVAL 1 YEAR) < CURDATE()
        OR DATE_ADD(InspectionDate, INTERVAL 1 YEAR) < DATE_ADD(CURDATE(), INTERVAL days DAY);
END//
DELIMITER ;

-- Dumping structure for view mcquaids_trailers.qryequipmentdetails
DROP VIEW IF EXISTS `qryequipmentdetails`;
-- Creating temporary table to overcome VIEW dependency errors
CREATE TABLE `qryequipmentdetails` (
	`EquipmentNumber` VARCHAR(8) NOT NULL COLLATE 'utf8mb4_unicode_ci',
	`EquipmentType` INT(10) NOT NULL COMMENT 'Forklift, Furntiture, Container, Landoll',
	`EquipmentSubType` VARCHAR(7) NULL COMMENT 'Breaks down the Equipment Type ' COLLATE 'utf8mb4_unicode_ci',
	`equipmentTypeText` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci',
	`equipmentSubTypeText` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci',
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
	`bookingStatusCodeText` VARCHAR(50) NULL COLLATE 'utf8mb4_unicode_ci',
	`leasedEquipmentNotes` VARCHAR(100) NULL COLLATE 'utf8mb4_unicode_ci'
) ENGINE=MyISAM;

-- Dumping structure for table mcquaids_trailers.user
DROP TABLE IF EXISTS `user`;
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

-- Dumping data for table mcquaids_trailers.user: ~7 rows (approximately)
INSERT INTO `user` (`UserID`, `FirstName`, `LastName`, `Phone`, `Email`, `street`, `City`, `Province`, `Country`, `PostalCode`) VALUES
	('1001', 'Steven', 'Banks', '902-892-6462', 'steven.banks@gmail.com', '28 Ferndale Drive', 'Charlottetown', 'PE', 'Canada', NULL),
	('1002', 'Sharon', 'Banks', '902-330-8350', 'steven.banks@gmail.com', '28 Ferndale Drive', 'Charlottetown', 'PE', 'Canada', NULL),
	('1003', 'Jim', 'Banks', '902-314-1800', 'jim.banks@apm.ca', '34 Boonies', 'Moncton', 'NB', 'Canada', 'J6G 3F4'),
	('1004', 'Greg', 'McQuaid', '902-318-3434', 'greg@mcquaids.ca', 'Out in Sticks', 'Brackley', 'PE', 'Canada', NULL),
	('dsfgd', 'dfgd', 'dgd', '9029026462', 'sd@hotmail.com', 'awfwe', 'werwe', 'PE', 'Canada', 'c1a6j3'),
	('fmbanks', 'Freda', 'Banks', '902-892-1659', 'fredabanks@hotmail.com', '28 Ferndale Drive', 'Charlottetown', 'PE', 'Canada', NULL),
	('ttt1', 'Derwin', 'Banks', '902-566-9090', 'derwin.banks@hotmail.com', 'seefew', 'sdfsd', 'PE', 'Canada', NULL);

-- Removing temporary table and create final VIEW structure
DROP TABLE IF EXISTS `leased_equipment_view`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `leased_equipment_view` AS select `le`.`LeaseID` AS `LeaseID`,`le`.`EquipmentNumber` AS `EquipmentNumber`,`le`.`DateAddedToLease` AS `DateAddedToLease`,`le`.`DateRemovedFromLease` AS `DateRemovedFromLease`,`le`.`Notes` AS `Notes`,`q`.`EquipmentType` AS `EquipmentType`,`q`.`EquipmentSubType` AS `EquipmentSubType`,`q`.`SerialNumber` AS `SerialNumber`,`q`.`Manufacturer` AS `Manufacturer`,`q`.`ManufacturedDate` AS `ManufacturedDate`,`q`.`PurchasePrice` AS `PurchasePrice`,`q`.`PurchaseDate` AS `PurchaseDate`,`q`.`SpecialNotes` AS `SpecialNotes`,`q`.`InspectionDate` AS `InspectionDate`,`q`.`Properties` AS `Properties`,`q`.`AvailabilityStatusCode` AS `AvailabilityStatusCode`,`q`.`ConditionStatusCode` AS `ConditionStatusCode`,`q`.`MaintenanceStatusCode` AS `MaintenanceStatusCode`,`q`.`CleaningStatusCode` AS `CleaningStatusCode`,`q`.`BookingStatusCode` AS `BookingStatusCode`,`q`.`availabilityStatusText` AS `availabilityStatusText`,`q`.`conditionStatusText` AS `conditionStatusText`,`q`.`maintenanceStatusText` AS `maintenanceStatusText`,`q`.`cleaningStatusTest` AS `cleaningStatusTest`,`q`.`equipmentTypeText` AS `equipmentTypeText`,`q`.`equipmentSubTypeText` AS `equipmentSubTypeText`,`q`.`bookingStatusCodeText` AS `bookingStatusCodeText` from (`lease_equipment` `le` join `qryequipmentquerydto` `q` on((`le`.`EquipmentNumber` = `q`.`EquipmentNumber`)));

-- Removing temporary table and create final VIEW structure
DROP TABLE IF EXISTS `qryequipmentdetails`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `qryequipmentdetails` AS select `equipment`.`EquipmentNumber` AS `EquipmentNumber`,`equipment`.`EquipmentType` AS `EquipmentType`,`equipment`.`EquipmentSubType` AS `EquipmentSubType`,`cv5`.`EnglishDescription` AS `equipmentTypeText`,`cv6`.`EnglishDescription` AS `equipmentSubTypeText`,`equipment`.`SerialNumber` AS `SerialNumber`,`equipment`.`Manufacturer` AS `Manufacturer`,`equipment`.`ManufacturedDate` AS `ManufacturedDate`,`equipment`.`PurchasePrice` AS `PurchasePrice`,`equipment`.`PurchaseDate` AS `PurchaseDate`,`equipment`.`SpecialNotes` AS `SpecialNotes`,`equipment`.`InspectionDate` AS `InspectionDate`,`equipment`.`Properties` AS `Properties`,`equipment`.`AvailabilityStatusCode` AS `AvailabilityStatusCode`,`equipment`.`ConditionStatusCode` AS `ConditionStatusCode`,`equipment`.`MaintenanceStatusCode` AS `MaintenanceStatusCode`,`equipment`.`CleaningStatusCode` AS `CleaningStatusCode`,`equipment`.`BookingStatusCode` AS `BookingStatusCode`,`cv1`.`EnglishDescription` AS `availabilityStatusText`,`cv2`.`EnglishDescription` AS `conditionStatusText`,`cv3`.`EnglishDescription` AS `maintenanceStatusText`,`cv4`.`EnglishDescription` AS `cleaningStatusTest`,`cv7`.`EnglishDescription` AS `bookingStatusCodeText`,`lease_equipment`.`Notes` AS `leasedEquipmentNotes` from ((((((((`equipment` left join `codevalue` `cv1` on((`equipment`.`AvailabilityStatusCode` = `cv1`.`CodeValue`))) left join `codevalue` `cv2` on((`equipment`.`ConditionStatusCode` = `cv2`.`CodeValue`))) left join `codevalue` `cv3` on((`equipment`.`MaintenanceStatusCode` = `cv3`.`CodeValue`))) left join `codevalue` `cv4` on((`equipment`.`CleaningStatusCode` = `cv4`.`CodeValue`))) left join `codetype` `cv5` on((`equipment`.`EquipmentType` = `cv5`.`ID`))) left join `codevalue` `cv6` on((`equipment`.`EquipmentSubType` = `cv6`.`CodeValue`))) left join `codevalue` `cv7` on((`equipment`.`BookingStatusCode` = `cv7`.`CodeValue`))) left join `lease_equipment` on((`equipment`.`EquipmentNumber` = `lease_equipment`.`EquipmentNumber`))) group by `equipment`.`EquipmentNumber`;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
