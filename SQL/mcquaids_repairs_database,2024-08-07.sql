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


-- Dumping database structure for mcquaids-repairs
CREATE DATABASE IF NOT EXISTS `mcquaids-repairs` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mcquaids-repairs`;

-- Dumping structure for table mcquaids-repairs.jobs
CREATE TABLE IF NOT EXISTS `jobs` (
  `id` varchar(16) NOT NULL,
  `job` varchar(255) DEFAULT NULL,
  `jobType` varchar(32) NOT NULL,
  `jobDescription` varchar(32) NOT NULL,
  `jobUserAssigned` varchar(32) NOT NULL,
  `jobContact` varchar(32) NOT NULL,
  `jobContactPhone` varchar(32) NOT NULL,
  `jobAddress` varchar(32) NOT NULL,
  `jobEquipmentRequired` varchar(32) DEFAULT NULL,
  `jobCompleteByDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `jobUserCreatedBy` varchar(32) NOT NULL,
  `jobCreatedDateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `jobUserUpdatedBy` varchar(32) NOT NULL,
  `jobUpdateDateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mcquaids-repairs.jobs: ~0 rows (approximately)

-- Dumping structure for table mcquaids-repairs.repairs
CREATE TABLE IF NOT EXISTS `repairs` (
  `id` varchar(16) NOT NULL,
  `vehicleID` varchar(255) DEFAULT NULL,
  `jobID` varchar(32) NOT NULL,
  `repairType` varchar(32) NOT NULL,
  `repairSide` varchar(32) NOT NULL,
  `repairAxel` varchar(32) NOT NULL,
  `repairTire` varchar(32) NOT NULL,
  `repairDoorProblems` varchar(32) NOT NULL,
  `repairLightProblems` varchar(32) DEFAULT NULL,
  `jobCompleteByDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `jobUserCreatedBy` varchar(32) NOT NULL,
  `jobCreatedDateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `jobUserUpdatedBy` varchar(32) NOT NULL,
  `jobUpdateDateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mcquaids-repairs.repairs: ~0 rows (approximately)

-- Dumping structure for table mcquaids-repairs.user
CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mcquaids-repairs.user: ~1 rows (approximately)
INSERT INTO `user` (`username`, `email`, `password`, `create_time`) VALUES
	('sdbanks', 'steven.banks@gmail.com', 'ssrtb', '2021-12-18 19:03:39');

-- Dumping structure for table mcquaids-repairs.vehicles
CREATE TABLE IF NOT EXISTS `vehicles` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `RegNo` varchar(50) DEFAULT NULL,
  `RegDate` varchar(50) DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `Make` varchar(50) DEFAULT NULL,
  `Model` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Dumping data for table mcquaids-repairs.vehicles: ~0 rows (approximately)

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
