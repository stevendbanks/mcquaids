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


-- Dumping database structure for ssrtb-jobs
CREATE DATABASE IF NOT EXISTS `ssrtb-jobs` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ssrtb-jobs`;

-- Dumping structure for table ssrtb-jobs.accidents
CREATE TABLE IF NOT EXISTS `accidents` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `SysDate` varchar(50) DEFAULT NULL,
  `Date` varchar(50) DEFAULT NULL,
  `Fleet` varchar(50) DEFAULT NULL,
  `Vehicle` varchar(50) DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `Details` varchar(50) DEFAULT NULL,
  `Driver` varchar(50) DEFAULT NULL,
  `Injured` varchar(50) DEFAULT NULL,
  `Images` varchar(350) DEFAULT NULL,
  `EnteredBy` varchar(50) DEFAULT NULL,
  `DamageToVehicle` varchar(50) DEFAULT NULL,
  `3rdPartyDamages` varchar(50) DEFAULT NULL,
  `Time` varchar(50) DEFAULT NULL,
  `Deaths` varchar(50) DEFAULT NULL,
  `Location` varchar(50) DEFAULT NULL,
  `StatusInjured` varchar(50) DEFAULT NULL,
  `Category` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table ssrtb-jobs.accidents: ~2 rows (approximately)
INSERT INTO `accidents` (`Id`, `SysDate`, `Date`, `Fleet`, `Vehicle`, `Type`, `Details`, `Driver`, `Injured`, `Images`, `EnteredBy`, `DamageToVehicle`, `3rdPartyDamages`, `Time`, `Deaths`, `Location`, `StatusInjured`, `Category`) VALUES
	(4, '2016-08-25 08:45:59', '2016-08-10 00:00:00', 'Container Carriers', 'KR6584', 'Machine', 'Face to face accident with van', 'Keith Nurega', '2', 'screen-shot-2015-08-23-at-4-47-09-pm-e1440373742135.jpg', 'Mark Antony', 'Buffer damaged', 'Windscreen damaged', '08:30:00', '0', 'K8 Highway', 'Minor bruises. Hospitalized', NULL),
	(5, '2016-08-25 08:49:45', '2016-08-17 00:00:00', 'Container Carriers', 'KR6584', 'Machine', 'Face to face hit with another vehicle', 'Keith Nurega', '1', 'Truck-Accident-Lawyer-Columbia-South-Carolina.jpg', 'Mark Antony', 'Front dents', 'Front section fully destroyed. Light post fallen', '09:00:00', '1', 'Manning Town', 'Hand broken. Legs wounded', NULL);

-- Dumping structure for table ssrtb-jobs.carrierusers
CREATE TABLE IF NOT EXISTS `carrierusers` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `username` varchar(300) DEFAULT NULL,
  `password` varchar(300) DEFAULT NULL,
  `email` varchar(300) DEFAULT NULL,
  `fullname` varchar(300) DEFAULT NULL,
  `groupid` varchar(300) DEFAULT NULL,
  `active` int DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table ssrtb-jobs.carrierusers: ~2 rows (approximately)
INSERT INTO `carrierusers` (`ID`, `username`, `password`, `email`, `fullname`, `groupid`, `active`) VALUES
	(5, 'Manager', 'c963aaa9c595d42374231143aaf804e3', 'manager@gmail.com', 'Brian Thomas', 'manager', 1),
	(6, 'Admin', '5220957bbe42406fd40c01e2a39c9312', 'adminfleetco@gmails.com', 'Mark Croos', 'Admin', 1);

-- Dumping structure for table ssrtb-jobs.carrier_uggroups
CREATE TABLE IF NOT EXISTS `carrier_uggroups` (
  `GroupID` int NOT NULL AUTO_INCREMENT,
  `Label` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`GroupID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table ssrtb-jobs.carrier_uggroups: ~3 rows (approximately)
INSERT INTO `carrier_uggroups` (`GroupID`, `Label`) VALUES
	(1, 'manager'),
	(2, 'user'),
	(3, 'viewer');

-- Dumping structure for table ssrtb-jobs.carrier_ugmembers
CREATE TABLE IF NOT EXISTS `carrier_ugmembers` (
  `UserName` varchar(300) NOT NULL,
  `GroupID` int NOT NULL,
  PRIMARY KEY (`UserName`(50),`GroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table ssrtb-jobs.carrier_ugmembers: ~12 rows (approximately)
INSERT INTO `carrier_ugmembers` (`UserName`, `GroupID`) VALUES
	('Admin', -1),
	('Admin', 1),
	('Admin', 2),
	('Admin', 3),
	('Manager', 1),
	('Manager', 2),
	('Manager', 3),
	('User', 2),
	('Vishan', -1),
	('Vishan', 1),
	('Vishan', 2),
	('Vishan', 3);

-- Dumping structure for table ssrtb-jobs.carrier_ugrights
CREATE TABLE IF NOT EXISTS `carrier_ugrights` (
  `TableName` varchar(300) NOT NULL,
  `GroupID` int NOT NULL,
  `AccessMask` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`TableName`(50),`GroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table ssrtb-jobs.carrier_ugrights: ~203 rows (approximately)
INSERT INTO `carrier_ugrights` (`TableName`, `GroupID`, `AccessMask`) VALUES
	('accidents', -1, 'AEDSPI'),
	('accidents', 1, 'AEDSP'),
	('accidents', 2, 'AESP'),
	('accidents', 3, 'SP'),
	('accidents Chart', -1, 'S'),
	('accidents-report', -1, 'AEDSPI'),
	('accidents-report', 1, 'SP'),
	('accidents-report', 2, 'SP'),
	('accidents-report', 3, 'SP'),
	('admin_members', -1, 'ADESPIM'),
	('admin_rights', -1, 'ADESPIM'),
	('admin_users', -1, 'ADESPIM'),
	('availability', -1, 'ASPI'),
	('carrierusers', -1, 'ADESPIM'),
	('creategrn', -2, 'ASP'),
	('creategrn', -1, 'ADESPIM'),
	('creategrn', 1, 'AEDSP'),
	('creategrn', 2, 'ASP'),
	('creategrn', 3, 'SP'),
	('creategrn-addnew', -1, 'AEDSPI'),
	('creategrn-disposal', -1, 'AEDSPI'),
	('creategrn-disposal', 1, 'AEDSP'),
	('creategrn-disposal', 2, 'AESP'),
	('creategrn-disposal', 3, 'SP'),
	('creategrn-disposal-rebuild', -1, 'AEDSPI'),
	('creategrn-disposal-rebuild', 1, 'AEDSP'),
	('creategrn-disposal-rebuild', 2, 'AESP'),
	('creategrn-disposal-rebuild', 3, 'SP'),
	('creategrn-issue', -1, 'AEDSPI'),
	('creategrn-issue', 1, 'AEDSP'),
	('creategrn-issue', 2, 'AESP'),
	('creategrn-issue', 3, 'SP'),
	('creategrn-issue-price', -1, 'AEDSPI'),
	('creategrn-issue-price', 1, 'AEDSP'),
	('creategrn-issue-price', 2, 'ASP'),
	('creategrn-issue-rebuilt', -1, 'AEDSPI'),
	('creategrn-issue-rebuilt', 1, 'AEDSP'),
	('creategrn-issue-rebuilt', 2, 'AESP'),
	('creategrn-issue-rebuilt', 3, 'SP'),
	('creategrn-issue-rebuilt-use', -1, 'AEDSPI'),
	('creategrn-issueofrebuild', -1, 'AEDSPI'),
	('creategrn-issueofrebuiltyres', -1, 'AEDSPI'),
	('creategrn-issuetorebuild', -1, 'AEDSPI'),
	('creategrn-issuetorebuild', 1, 'AEDSP'),
	('creategrn-issuetorebuild', 2, 'AESP'),
	('creategrn-issuetorebuild', 3, 'SP'),
	('creategrn-new', -1, 'AEDSPI'),
	('creategrn-new', 1, 'AEDSP'),
	('creategrn-new', 2, 'ASP'),
	('creategrn-purchase', -1, 'AEDSPI'),
	('creategrn-quick', -1, 'ASPI'),
	('creategrn-rebuilt-issue', -1, 'AEDSPI'),
	('creategrn-receipt', -1, 'ASPI'),
	('creategrn-receive', -1, 'AEDSPI'),
	('creategrn-receive', 1, 'AEDSP'),
	('creategrn-receive', 2, 'AESP'),
	('creategrn-receive', 3, 'SP'),
	('creategrn-receive-rebuilt', -1, 'AEDSPI'),
	('creategrn-receiveafterrebuild', -1, 'AEDSPI'),
	('creategrn-receiveafterrebuild', 1, 'AEDSP'),
	('creategrn-receiveafterrebuild', 2, 'AESP'),
	('creategrn-receiveafterrebuild', 3, 'SP'),
	('creategrn-removal', -1, 'AEDSPI'),
	('creategrn-remove', -1, 'AEDSPI'),
	('creategrn-remove', 1, 'AEDSP'),
	('creategrn-remove', 2, 'AESP'),
	('creategrn-remove', 3, 'SP'),
	('creategrn-remove-other', -1, 'AEDSPI'),
	('creategrn-remove-other', 1, 'AEDSP'),
	('creategrn-remove-other', 2, 'AESP'),
	('creategrn-remove-other', 3, 'SP'),
	('creategrn-stock-balance', -1, 'SP'),
	('creategrn-stock-balance', 1, 'SP'),
	('creategrn-stock-balance', 2, 'SP'),
	('creategrn-stock-balance', 3, 'SP'),
	('creategrn-used', -1, 'AEDSPI'),
	('creategrn-used', 1, 'AEDSP'),
	('creategrn-used', 2, 'ASP'),
	('creategrn1', -1, 'ASPI'),
	('creategrn11', -1, 'ASPI'),
	('Dashboard', -1, 'S'),
	('fleettype', -1, 'AEDSPI'),
	('fleettype', 1, 'ADESP'),
	('fleettype', 2, 'AESP'),
	('fleettype', 3, 'SP'),
	('fuelmaster', -1, 'AEDSPI'),
	('fuelmaster', 1, 'AEDSP'),
	('fuelmaster', 2, 'AESP'),
	('fuelmaster', 3, 'SP'),
	('fuelmaster Chart', -1, 'S'),
	('fuelmaster Chart', 1, 'S'),
	('fuelmaster Chart', 2, 'S'),
	('fuelmaster Chart', 3, 'S'),
	('fuelmaster-avg', -1, 'AEDSPI'),
	('fuelmaster-report', -1, 'SP'),
	('fuelmaster-reporting', -1, 'SP'),
	('fuelmaster-reports', -1, 'SP'),
	('fuelmaster-reports', 1, 'SP'),
	('fuelmaster-reports', 2, 'SP'),
	('fuelmaster-reports', 3, 'SP'),
	('fuelmaster-view', -1, 'AEDSPI'),
	('fuelmaster1', -1, 'AEDSPI'),
	('fuelprices', -1, 'AEDSPI'),
	('fuelprices', 1, 'ADESP'),
	('fuelprices', 2, 'AESP'),
	('fuelprices', 3, 'SP'),
	('fuelstationmaster', -1, 'AEDSPI'),
	('fuelstationmaster', 1, 'ADESP'),
	('fuelstationmaster', 2, 'AESP'),
	('fuelstationmaster', 3, 'SP'),
	('generalmaster', -1, 'AEDSPI'),
	('home', -1, 'AEDSPI'),
	('insurance-payments', -1, 'AEDSPI'),
	('insurance-payments', 1, 'AEDSP'),
	('insurance-payments', 2, 'AESP'),
	('insurance-payments', 3, 'SP'),
	('insurance-payments-report', -1, 'SP'),
	('insuranceclaims', -1, 'AEDSPI'),
	('insuranceclaims', 1, 'AEDSP'),
	('insuranceclaims', 2, 'AESP'),
	('insuranceclaims', 3, 'SP'),
	('insuranceclaims-report', -1, 'SP'),
	('insurancecompany', -1, 'AEDSPI'),
	('insurancecompany', 1, 'ADESP'),
	('insurancecompany', 2, 'AESP'),
	('insurancecompany', 3, 'SP'),
	('inventorymaster', -2, 'ASP'),
	('inventorymaster', -1, 'ADESPIM'),
	('inventorymaster', 1, 'ADESP'),
	('inventorymaster', 2, 'AESP'),
	('inventorymaster', 3, 'SP'),
	('inventorymaster Chart', -1, 'S'),
	('inventorymaster Report', -1, 'SP'),
	('inventorymaster-max', -1, 'AEDSPI'),
	('inventorymaster-pricing', -1, 'M'),
	('inventorymaster-qty', -1, 'M'),
	('maintenenace', -1, 'SP'),
	('maintenenace', 1, 'ADESP'),
	('maintenenace', 2, 'AESP'),
	('maintenenace', 3, 'SP'),
	('maintenenace Chart', -1, 'S'),
	('maintenenace Chart', 1, 'S'),
	('maintenenace Chart', 2, 'S'),
	('maintenenace Chart', 3, 'S'),
	('maintenenace Comp', -1, 'S'),
	('maintenenace-accident-repair', -1, 'AEDSPI'),
	('maintenenace-accident-repair', 1, 'AEDSP'),
	('maintenenace-accident-repair', 2, 'AESP'),
	('maintenenace-accident-repair', 3, 'SP'),
	('maintenenace-accidentrepair', -1, 'AEDSPI'),
	('maintenenace-general-repair', -1, 'AEDSPI'),
	('maintenenace-general-repair', 1, 'AEDSP'),
	('maintenenace-general-repair', 2, 'AESP'),
	('maintenenace-general-repair', 3, 'SP'),
	('maintenenace-generalrepair', -1, 'AEDSPI'),
	('maintenenace-other-maintain', -1, 'AEDSPI'),
	('maintenenace-other-maintain', 1, 'AEDSP'),
	('maintenenace-other-maintain', 2, 'AESP'),
	('maintenenace-regularservice', -1, 'AEDSPI'),
	('maintenenace-regularservice', 1, 'AEDSP'),
	('maintenenace-regularservice', 2, 'AESP'),
	('maintenenace-regularservice', 3, 'SP'),
	('maintenenace-report', -1, 'SPI'),
	('maintenenace-report', 1, 'SP'),
	('maintenenace-report', 2, 'SP'),
	('maxprice', -1, 'AEDSPI'),
	('otherrenewal', -1, 'AEDSP'),
	('otherrenewal', 1, 'AEDSP'),
	('otherrenewal', 2, 'AESP'),
	('otherrenewal', 3, 'SP'),
	('OtherRenewals', -1, 'AEDSPI'),
	('RenewalsMaster', -1, 'AEDSPI'),
	('rnewalmastertable', -1, 'AEDSP'),
	('rnewalmastertable', 1, 'AEDSP'),
	('rnewalmastertable', 2, 'AESP'),
	('rnewalmastertable', 3, 'SP'),
	('servicetypemaster', -1, 'AEDSPI'),
	('servicetypemaster', 1, 'ADESP'),
	('servicetypemaster', 2, 'AESP'),
	('servicetypemaster', 3, 'SP'),
	('stockissues', -1, 'AEDSPI'),
	('stockissues', 1, 'AEDSP'),
	('stockissues', 2, 'ASP'),
	('suppliermaster', -1, 'AEDSPI'),
	('suppliermaster', 1, 'ADESP'),
	('suppliermaster', 2, 'AESP'),
	('suppliermaster', 3, 'SP'),
	('usedornew', -1, 'AEDSPI'),
	('vehiclemaster', -1, 'AEDSPI'),
	('vehiclemaster', 1, 'ADESP'),
	('vehiclemaster', 2, 'AESP'),
	('vehiclemaster', 3, 'SP'),
	('vehiclemaster-fullcost', -1, 'SP'),
	('vehiclemaster-insu', -1, 'AEDSPI'),
	('vehiclemaster-new', -1, 'AEDSPI'),
	('vehiclemaster-report', -1, 'AEDSPI'),
	('vehiclemaster-report', 1, 'SP'),
	('vehiclemaster-report', 2, 'SP'),
	('vehiclemaster-report', 3, 'SP'),
	('vehicletype', -1, 'AEDSPI'),
	('vehicletype', 1, 'ADESP'),
	('vehicletype', 2, 'AESP'),
	('vehicletype', 3, 'SP');

-- Dumping structure for table ssrtb-jobs.creategrn
CREATE TABLE IF NOT EXISTS `creategrn` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ItemCode` varchar(50) DEFAULT NULL,
  `Brand` varchar(50) DEFAULT NULL,
  `Description` varchar(50) DEFAULT NULL,
  `Supplier` varchar(50) DEFAULT NULL,
  `Quantity` varchar(50) DEFAULT NULL,
  `RemovedFrom` varchar(50) DEFAULT NULL,
  `SystemDate` varchar(50) DEFAULT NULL,
  `GRNDate` varchar(50) DEFAULT NULL,
  `EnteredBy` varchar(50) DEFAULT NULL,
  `ApprovedBy` varchar(50) DEFAULT NULL,
  `UnitPrice` varchar(50) DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL,
  `RefNumber` varchar(50) DEFAULT NULL,
  `CurrentStock` varchar(50) DEFAULT NULL,
  `Fleet` varchar(50) DEFAULT NULL,
  `PriceLink` varchar(50) DEFAULT NULL,
  `Cost` varchar(50) DEFAULT NULL,
  `Remarks` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=225 DEFAULT CHARSET=utf8;

-- Dumping data for table ssrtb-jobs.creategrn: ~13 rows (approximately)
INSERT INTO `creategrn` (`ID`, `ItemCode`, `Brand`, `Description`, `Supplier`, `Quantity`, `RemovedFrom`, `SystemDate`, `GRNDate`, `EnteredBy`, `ApprovedBy`, `UnitPrice`, `Status`, `RefNumber`, `CurrentStock`, `Fleet`, `PriceLink`, `Cost`, `Remarks`) VALUES
	(212, '2666TW', 'Ceat', 'Tyres', 'Kushi Tyres', '23', NULL, '2016-08-25 07:26:18', '2016-08-02 00:00:00', 'Mark Antony', NULL, '25', 'Purchase', '538', NULL, 'Cargo Carriers', NULL, '575', NULL),
	(213, '5116519X', 'Toyota', 'Oil Filter', 'Meiken Traders', '11', NULL, '2016-08-25 07:26:56', '2016-08-04 00:00:00', 'Mark Antony', NULL, '360', 'Purchase', '8767', NULL, 'Cargo Carriers', NULL, '3960', NULL),
	(214, '2666TW', 'Ceat', 'Tyres', 'Kushi Tyres', '231', NULL, '2016-08-25 07:27:20', '2016-08-26 00:00:00', 'Mark Antony', NULL, '12.50', 'Purchase', '7788', NULL, 'Cement Carriers', NULL, '2887.5', NULL),
	(215, '54646G', 'Honda', 'Air Filter', 'Antony\'s Hardwares', '8', NULL, '2016-08-25 07:27:53', '2016-08-16 00:00:00', 'Mark Antony', NULL, '125', 'Purchase', '768', NULL, 'Container Carriers', NULL, '1000', NULL),
	(216, '5116519X', 'Toyota', 'Oil Filter', 'Meiken Traders', '25', NULL, '2016-08-25 07:28:11', '2016-08-11 00:00:00', 'Mark Antony', NULL, '35', 'Purchase', '587', NULL, 'Container Carriers', NULL, '875', NULL),
	(217, 'SQ234', 'Caltex', 'Motor Oil', 'NKS Motor Spares', '5', NULL, '2016-08-25 07:28:45', '2016-08-09 00:00:00', 'Mark Antony', NULL, '235', 'Purchase', '28776', NULL, 'Cement Carriers', NULL, '1175', NULL),
	(218, '54646G', 'Honda', 'Air Filter', 'Antony\'s Hardwares', '12', NULL, '2016-08-25 07:29:27', '2016-08-10 00:00:00', 'Mark Antony', NULL, '75', 'Purchase', '868', NULL, 'Cargo Carriers', NULL, '900', NULL),
	(219, '5116519X', 'Toyota', 'Oil Filter', 'Meiken Traders', '-10', 'BF1470', '2016-08-25 08:04:03', '2016-08-09 00:00:00', 'Mark Antony', NULL, '35', 'Issue', NULL, '27', 'Cargo Carriers', '5116519X', NULL, ''),
	(220, 'SQ234', 'Caltex', 'Motor Oil', 'NKS Motor Spares', '-2', 'WK5874', '2016-08-25 08:04:43', '2016-08-09 00:00:00', 'Mark Antony', NULL, '235', 'Issue', NULL, '4', 'Cargo Carriers', 'SQ234', NULL, ''),
	(221, '5116519X', 'Toyota', 'Oil Filter', 'Meiken Traders', '-18', 'KR6584', '2016-08-25 08:09:01', '2016-08-09 00:00:00', 'Mark Antony', NULL, '35', 'Issue', NULL, '26', 'Container Carriers', '5116519X', NULL, ''),
	(222, '2666TW', 'Ceat', 'Tyres', 'Kushi Tyres', '-112', 'EF4771', '2016-08-25 08:09:39', '2016-08-03 00:00:00', 'Mark Antony', NULL, '12.50', 'Issue', NULL, '254', 'Cement Carriers', '2666TW', NULL, ''),
	(223, '54646G', 'Honda', 'Air Filter', 'Antony\'s Hardwares', '-2', 'KI5455', '2016-08-25 08:10:08', '2016-08-02 00:00:00', 'Mark Antony', NULL, '75', 'Issue', NULL, '20', 'Container Carriers', '54646G', NULL, ''),
	(224, '2666TW', 'Ceat', 'Tyres', 'Kushi Tyres', '1', 'WK5874', '2016-08-25 08:12:54', '2016-08-10 00:00:00', 'Mark Antony', NULL, '0', 'Removal', NULL, NULL, 'Cargo Carriers', NULL, '0', NULL);

-- Dumping structure for table ssrtb-jobs.fleettype
CREATE TABLE IF NOT EXISTS `fleettype` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `FleetType` varchar(50) DEFAULT NULL,
  `In-Charge` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Dumping data for table ssrtb-jobs.fleettype: ~3 rows (approximately)
INSERT INTO `fleettype` (`Id`, `FleetType`, `In-Charge`) VALUES
	(1, 'Container Carriers', 'Daniel Thomas'),
	(2, 'Cement Carriers', 'David Brian'),
	(7, 'Cargo Carriers', 'Frank Anderson');

-- Dumping structure for table ssrtb-jobs.fuelmaster
CREATE TABLE IF NOT EXISTS `fuelmaster` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `SystemDate` varchar(50) DEFAULT NULL,
  `FuelDate` varchar(50) DEFAULT NULL,
  `Vehicle` varchar(50) DEFAULT NULL,
  `MeterReading` varchar(50) DEFAULT NULL,
  `LitersPumped` varchar(50) DEFAULT NULL,
  `PricePerLiter` varchar(50) DEFAULT NULL,
  `FuelStation` varchar(50) DEFAULT NULL,
  `Fleet` varchar(50) DEFAULT NULL,
  `LastMileage` varchar(50) DEFAULT NULL,
  `Economy` varchar(50) DEFAULT NULL,
  `FillType` varchar(50) DEFAULT NULL,
  `CouponNo` varchar(50) DEFAULT NULL,
  `Driver` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- Dumping data for table ssrtb-jobs.fuelmaster: ~5 rows (approximately)
INSERT INTO `fuelmaster` (`Id`, `SystemDate`, `FuelDate`, `Vehicle`, `MeterReading`, `LitersPumped`, `PricePerLiter`, `FuelStation`, `Fleet`, `LastMileage`, `Economy`, `FillType`, `CouponNo`, `Driver`) VALUES
	(56, '2016-08-25 06:25:09', '2016-08-01 00:00:00', 'KI5455', '25233', '25', '45', 'Kiaco Fuel Station', 'Container Carriers', '24950', '11.32', 'Full Tank', '355', 'Kumar Sedhi'),
	(57, '2016-08-25 06:36:15', '2016-08-04 00:00:00', 'KI5455', '25850', '35', '45', 'SK Fuel Station', 'Container Carriers', '25300', '15.714285714286', 'Full Tank', '863', 'Kumar Sedhi'),
	(58, '2016-08-25 06:37:24', '2016-08-15 00:00:00', 'KI5455', '26250', '40', '45', 'Kiaco Fuel Station', 'Container Carriers', '25850', '10', 'Full Tank', '7598', 'Kumar Sedhi'),
	(59, '2016-08-25 06:38:17', '2016-08-17 00:00:00', 'KI5455', '26752', '36', '45', 'Kiaco Fuel Station', 'Container Carriers', '26250', '13.944444444444', 'Full Tank', '8585', 'Kumar Sedhi'),
	(60, '2016-08-25 06:39:00', '2016-08-22 00:00:00', 'KI5455', '27124', '41', '45', 'SK Fuel Station', 'Container Carriers', '26752', '9.0731707317073', 'Full Tank', '2577', 'Kumar Sedhi');

-- Dumping structure for table ssrtb-jobs.fuelprices
CREATE TABLE IF NOT EXISTS `fuelprices` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `FuelType` varchar(50) DEFAULT NULL,
  `PricePerLiter` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table ssrtb-jobs.fuelprices: ~2 rows (approximately)
INSERT INTO `fuelprices` (`Id`, `FuelType`, `PricePerLiter`) VALUES
	(1, 'Diesel', '30'),
	(2, 'Petrol', '45');

-- Dumping structure for table ssrtb-jobs.fuelstationmaster
CREATE TABLE IF NOT EXISTS `fuelstationmaster` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `FuelStation` varchar(50) DEFAULT NULL,
  `Address` varchar(50) DEFAULT NULL,
  `ContactNumber` varchar(50) DEFAULT NULL,
  `Deposit` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table ssrtb-jobs.fuelstationmaster: ~2 rows (approximately)
INSERT INTO `fuelstationmaster` (`Id`, `FuelStation`, `Address`, `ContactNumber`, `Deposit`) VALUES
	(1, 'Kiaco Fuel Station', '25E Main Street', '+516546416', '500000'),
	(2, 'SK Fuel Station', '656 Henty Road', '+87484565', '1000000');

-- Dumping structure for table ssrtb-jobs.insurance-payments
CREATE TABLE IF NOT EXISTS `insurance-payments` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `SysDate` varchar(50) DEFAULT NULL,
  `Fleet` varchar(50) DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `VehicleNo` varchar(50) DEFAULT NULL,
  `RenewalDueDate` varchar(50) DEFAULT NULL,
  `Premium` varchar(50) DEFAULT NULL,
  `Cost` varchar(50) DEFAULT NULL,
  `PaymentVoucher` varchar(50) DEFAULT NULL,
  `EnteredBy` varchar(50) DEFAULT NULL,
  `DateofPayment` varchar(50) DEFAULT NULL,
  `Insurer` varchar(50) DEFAULT NULL,
  `Date` varchar(50) DEFAULT NULL,
  `From` varchar(50) DEFAULT NULL,
  `To` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table ssrtb-jobs.insurance-payments: ~1 rows (approximately)
INSERT INTO `insurance-payments` (`Id`, `SysDate`, `Fleet`, `Type`, `VehicleNo`, `RenewalDueDate`, `Premium`, `Cost`, `PaymentVoucher`, `EnteredBy`, `DateofPayment`, `Insurer`, `Date`, `From`, `To`) VALUES
	(5, '2016-08-25 08:58:29', 'Cement Carriers', 'Machine', 'SK3266', '22 June', '3650', NULL, '2578', 'Mark Antony', '2016-08-23 00:00:00', 'Allianz Insurance', NULL, '2016-08-09 00:00:00', '2017-08-23 00:00:00');

-- Dumping structure for table ssrtb-jobs.insuranceclaims
CREATE TABLE IF NOT EXISTS `insuranceclaims` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `SysDate` varchar(50) DEFAULT NULL,
  `Fleet` varchar(50) DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `VehicleNo` varchar(50) DEFAULT NULL,
  `AccidentDate` varchar(50) DEFAULT NULL,
  `Claim` varchar(50) DEFAULT NULL,
  `EnteredBy` varchar(50) DEFAULT NULL,
  `ReceiptNo` varchar(50) DEFAULT NULL,
  `Remarks` varchar(50) DEFAULT NULL,
  `insurer` varchar(50) DEFAULT NULL,
  `Date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table ssrtb-jobs.insuranceclaims: ~1 rows (approximately)
INSERT INTO `insuranceclaims` (`Id`, `SysDate`, `Fleet`, `Type`, `VehicleNo`, `AccidentDate`, `Claim`, `EnteredBy`, `ReceiptNo`, `Remarks`, `insurer`, `Date`) VALUES
	(6, '2016-08-25 09:18:17', 'Cement Carriers', 'Machine', 'SK3266', '4', '2500', 'Mark Antony', '558', '', 'AIA Insurance Plc', '2016-08-18 00:00:00');

-- Dumping structure for table ssrtb-jobs.insurancecompany
CREATE TABLE IF NOT EXISTS `insurancecompany` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) DEFAULT NULL,
  `ContactName` varchar(50) DEFAULT NULL,
  `ContactNo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table ssrtb-jobs.insurancecompany: ~2 rows (approximately)
INSERT INTO `insurancecompany` (`Id`, `Name`, `ContactName`, `ContactNo`) VALUES
	(1, 'Allianz Insurance', 'Ajmal Nsheed', '+289124656'),
	(2, 'AIA Insurance Plc', 'Arundhi Roy', '+54665699');

-- Dumping structure for table ssrtb-jobs.inventorymaster
CREATE TABLE IF NOT EXISTS `inventorymaster` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `ItemID` varchar(50) DEFAULT NULL,
  `Brand` varchar(50) DEFAULT NULL,
  `Description` varchar(50) DEFAULT NULL,
  `Supplier` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table ssrtb-jobs.inventorymaster: ~4 rows (approximately)
INSERT INTO `inventorymaster` (`Id`, `ItemID`, `Brand`, `Description`, `Supplier`) VALUES
	(1, 'SQ234', 'Caltex', 'Motor Oil', 'NKS Motor Spares'),
	(2, '54646G', 'Honda', 'Air Filter', 'Antony\'s Hardwares'),
	(5, '5116519X', 'Toyota', 'Oil Filter', 'Meiken Traders'),
	(6, '2666TW', 'Ceat', 'Tyres', 'Kushi Tyres');

-- Dumping structure for table ssrtb-jobs.maintenenace
CREATE TABLE IF NOT EXISTS `maintenenace` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Fleet` varchar(50) DEFAULT NULL,
  `Vehicle` varchar(50) DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `Supplier` varchar(50) DEFAULT NULL,
  `Cost` varchar(50) DEFAULT NULL,
  `Remarks` varchar(50) DEFAULT NULL,
  `RefNo` varchar(50) DEFAULT NULL,
  `SysDate` varchar(50) DEFAULT NULL,
  `Date` varchar(50) DEFAULT NULL,
  `EnteredBy` varchar(50) DEFAULT NULL,
  `Approval` varchar(50) DEFAULT NULL,
  `MeterReading` varchar(50) DEFAULT NULL,
  `AccidentRef` varchar(50) DEFAULT NULL,
  `PaymentVoucher` varchar(50) DEFAULT NULL,
  `MaintType` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- Dumping data for table ssrtb-jobs.maintenenace: ~2 rows (approximately)
INSERT INTO `maintenenace` (`Id`, `Fleet`, `Vehicle`, `Type`, `Supplier`, `Cost`, `Remarks`, `RefNo`, `SysDate`, `Date`, `EnteredBy`, `Approval`, `MeterReading`, `AccidentRef`, `PaymentVoucher`, `MaintType`) VALUES
	(23, 'Cargo Carriers', 'BF1470', 'Lorry', 'Kushi Tyres', '2500', '', '15', '2016-11-22 12:37:04', '2016-11-22 00:00:00', 'Brian Thomas', NULL, '25402', NULL, '5455', 'Full Service'),
	(24, 'Container Carriers', 'KR6584', 'Machine', 'Meiken Traders', '2540', '', '6554', '2016-11-22 12:37:32', '2016-11-08 00:00:00', 'Brian Thomas', NULL, '25466', NULL, '545', 'Lub Service');

-- Dumping structure for table ssrtb-jobs.otherrenewal
CREATE TABLE IF NOT EXISTS `otherrenewal` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Fleet` varchar(50) DEFAULT NULL,
  `VehicleNo` varchar(50) DEFAULT NULL,
  `VehicleType` varchar(50) DEFAULT NULL,
  `PaymentType` varchar(50) DEFAULT NULL,
  `PaymentDate` varchar(50) DEFAULT NULL,
  `Cost` varchar(50) DEFAULT NULL,
  `SystemDate` varchar(50) DEFAULT NULL,
  `EnteredBy` varchar(50) DEFAULT NULL,
  `PeriodFrom` varchar(50) DEFAULT NULL,
  `PeriodTo` varchar(50) DEFAULT NULL,
  `PaymentRef` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table ssrtb-jobs.otherrenewal: ~1 rows (approximately)
INSERT INTO `otherrenewal` (`ID`, `Fleet`, `VehicleNo`, `VehicleType`, `PaymentType`, `PaymentDate`, `Cost`, `SystemDate`, `EnteredBy`, `PeriodFrom`, `PeriodTo`, `PaymentRef`) VALUES
	(2, 'Cement Carriers', 'SK3266', 'Machine', 'Emission Test', '2016-08-17 00:00:00', '2500', '2016-08-25 09:11:44', 'Mark Antony', '2016-08-16 00:00:00', '2017-08-16 00:00:00', '6336');

-- Dumping structure for table ssrtb-jobs.rnewalmastertable
CREATE TABLE IF NOT EXISTS `rnewalmastertable` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Renewal` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table ssrtb-jobs.rnewalmastertable: ~3 rows (approximately)
INSERT INTO `rnewalmastertable` (`ID`, `Renewal`) VALUES
	(1, 'Fitness Certificate'),
	(2, 'Port Permit'),
	(3, 'Emission Test');

-- Dumping structure for table ssrtb-jobs.servicetypemaster
CREATE TABLE IF NOT EXISTS `servicetypemaster` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- Dumping data for table ssrtb-jobs.servicetypemaster: ~4 rows (approximately)
INSERT INTO `servicetypemaster` (`Id`, `Type`) VALUES
	(1, 'Full Service'),
	(2, 'Lub Service'),
	(3, 'Mechanical Service'),
	(7, 'Other Maintenance');

-- Dumping structure for table ssrtb-jobs.suppliermaster
CREATE TABLE IF NOT EXISTS `suppliermaster` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `SupplierName` varchar(50) DEFAULT NULL,
  `Address` varchar(50) DEFAULT NULL,
  `ContactPerson` varchar(50) DEFAULT NULL,
  `Telephone` varchar(50) DEFAULT NULL,
  `Remarks` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table ssrtb-jobs.suppliermaster: ~4 rows (approximately)
INSERT INTO `suppliermaster` (`Id`, `SupplierName`, `Address`, `ContactPerson`, `Telephone`, `Remarks`) VALUES
	(1, 'NKS Motor Spares', '546 Maek Aveneue', 'Davis Khan', '+3296656565', ''),
	(2, 'Kushi Tyres', 'Jumaira Terras', 'Melani Hans', '+6521799196', ''),
	(3, 'Meiken Traders', '245E Meinx Road', 'Anil Gupta', '+5665786786', ''),
	(4, 'Antony\'s Hardwares', '62 Main Street', 'Mani Hussain', '+2948946115', '');

-- Dumping structure for table ssrtb-jobs.user
CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(16) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(32) NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table ssrtb-jobs.user: ~0 rows (approximately)

-- Dumping structure for table ssrtb-jobs.vehiclemaster
CREATE TABLE IF NOT EXISTS `vehiclemaster` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `RegNo` varchar(50) DEFAULT NULL,
  `Fleet` varchar(50) DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `RegDate` varchar(50) DEFAULT NULL,
  `Cost` varchar(50) DEFAULT NULL,
  `DriverAsigned` varchar(50) DEFAULT NULL,
  `Make` varchar(50) DEFAULT NULL,
  `Model` varchar(50) DEFAULT NULL,
  `InsuranceDue` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- Dumping data for table ssrtb-jobs.vehiclemaster: ~8 rows (approximately)
INSERT INTO `vehiclemaster` (`ID`, `RegNo`, `Fleet`, `Type`, `RegDate`, `Cost`, `DriverAsigned`, `Make`, `Model`, `InsuranceDue`) VALUES
	(1, 'KS1772', 'Container Carriers', 'Machine', '2014-04-09 00:00:00', '545000', 'Keith', 'Mitsubishi', 'MS403318', '10 June'),
	(2, 'EF4771', 'Cement Carriers', 'Machine', '2014-01-16 00:00:00', '850000', 'Hussian', 'Volvo', 'LPW40122', '15 April'),
	(3, 'BF1470', 'Cargo Carriers', 'Lorry', '2011-04-04 00:00:00', '658000', 'Antony Croos', 'Nissan', 'KMX4018E', '15 Jan'),
	(4, 'WK5874', 'Cargo Carriers', 'Lorry', '2015-04-02 00:00:00', '468500', 'Anil Roy', 'Cherry', 'NOSI40133', '15 Dec'),
	(5, 'KR6584', 'Container Carriers', 'Machine', '2014-04-09 00:00:00', '685000', 'Keith Nurega', 'Isuzu', 'UPS40185', '30 April'),
	(6, 'SK3266', 'Cement Carriers', 'Machine', '2013-04-04 00:00:00', '475000', 'Ajith Siva', 'Toyota', 'BSP442018', '22 June'),
	(7, 'KI5455', 'Container Carriers', 'Machine', '2015-04-04 00:00:00', '485000', 'Kumar Sedhi', 'Tata', 'NERS4018', '01 Jan'),
	(8, 'SX1765', 'Cement Carriers', 'Machine', '2012-04-10 00:00:00', '650000', 'Anil Das', 'Tata', 'HTE40184', '19 Dec');

-- Dumping structure for table ssrtb-jobs.vehicletype
CREATE TABLE IF NOT EXISTS `vehicletype` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `VehicleType` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table ssrtb-jobs.vehicletype: ~2 rows (approximately)
INSERT INTO `vehicletype` (`Id`, `VehicleType`) VALUES
	(1, 'Machine'),
	(2, 'Trailer');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
