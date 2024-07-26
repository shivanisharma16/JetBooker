-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: ars
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `flights`
--

DROP TABLE IF EXISTS `flights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flights` (
  `Departure` varchar(20) DEFAULT NULL,
  `Destination` varchar(20) DEFAULT NULL,
  `Flight_Time` time DEFAULT NULL,
  `Landing_Time` time DEFAULT NULL,
  `Flight_Number` varchar(8) NOT NULL,
  `E_Price` int DEFAULT NULL,
  `B_Price` int DEFAULT NULL,
  `PE_Price` int DEFAULT NULL,
  `FC_Price` int DEFAULT NULL,
  `MON` varchar(10) DEFAULT 'FALSE',
  `TUE` varchar(10) DEFAULT 'FALSE',
  `WED` varchar(10) DEFAULT 'FALSE',
  `THU` varchar(10) DEFAULT 'FALSE',
  `FRI` varchar(10) DEFAULT 'FALSE',
  `SAT` varchar(10) DEFAULT 'FALSE',
  `SUN` varchar(10) DEFAULT 'FALSE',
  PRIMARY KEY (`Flight_Number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flights`
--

LOCK TABLES `flights` WRITE;
/*!40000 ALTER TABLE `flights` DISABLE KEYS */;
INSERT INTO `flights` VALUES ('jaipur','bhopal','13:45:00','15:48:00','AZX123',2000,5000,7000,9000,'TRUE','TRUE','FALSE','TRUE','FALSE','FALSE','FALSE'),('Delhi','Kolkata','07:00:00','10:15:00','DEL45K',10000,40000,20000,50000,'TRUE','FALSE','TRUE','FALSE','TRUE','FALSE','TRUE'),('Kolkata','Bhopal','09:00:00','11:20:00','KOBP92',9000,12000,15000,20000,'TRUE','TRUE','TRUE','TRUE','TRUE','TRUE','TRUE'),('Kolkata','Bhopal','16:10:00','17:16:00','KOLB46',8000,20000,25000,50000,'TRUE','TRUE','TRUE','TRUE','TRUE','FALSE','FALSE');
/*!40000 ALTER TABLE `flights` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seats`
--

DROP TABLE IF EXISTS `seats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seats` (
  `date` date NOT NULL,
  `Flight_Number` varchar(10) NOT NULL,
  `e1` varchar(20) DEFAULT '0',
  `e2` varchar(20) DEFAULT '0',
  `e3` varchar(20) DEFAULT '0',
  `e4` varchar(20) DEFAULT '0',
  `e5` varchar(20) DEFAULT '0',
  `e6` varchar(20) DEFAULT '0',
  `e7` varchar(20) DEFAULT '0',
  `e8` varchar(20) DEFAULT '0',
  `e9` varchar(20) DEFAULT '0',
  `e10` varchar(20) DEFAULT '0',
  `b1` varchar(20) DEFAULT '0',
  `b2` varchar(20) DEFAULT '0',
  `b3` varchar(20) DEFAULT '0',
  `b4` varchar(20) DEFAULT '0',
  `b5` varchar(20) DEFAULT '0',
  `b6` varchar(20) DEFAULT '0',
  `b7` varchar(20) DEFAULT '0',
  `b8` varchar(20) DEFAULT '0',
  `b9` varchar(20) DEFAULT '0',
  `b10` varchar(20) DEFAULT '0',
  `p1` varchar(20) DEFAULT '0',
  `p2` varchar(20) DEFAULT '0',
  `p3` varchar(20) DEFAULT '0',
  `p4` varchar(20) DEFAULT '0',
  `p5` varchar(20) DEFAULT '0',
  `p6` varchar(20) DEFAULT '0',
  `p7` varchar(20) DEFAULT '0',
  `p8` varchar(20) DEFAULT '0',
  `p9` varchar(20) DEFAULT '0',
  `p10` varchar(20) DEFAULT '0',
  `f1` varchar(10) DEFAULT '0',
  `f2` varchar(10) DEFAULT '0',
  `f3` varchar(10) DEFAULT '0',
  `f4` varchar(10) DEFAULT '0',
  `f5` varchar(10) DEFAULT '0',
  `f6` varchar(10) DEFAULT '0',
  `f7` varchar(10) DEFAULT '0',
  `f8` varchar(10) DEFAULT '0',
  `f9` varchar(10) DEFAULT '0',
  `f10` varchar(10) DEFAULT '0',
  PRIMARY KEY (`date`,`Flight_Number`),
  KEY `Flight_Number` (`Flight_Number`),
  CONSTRAINT `seats_ibfk_1` FOREIGN KEY (`Flight_Number`) REFERENCES `flights` (`Flight_Number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seats`
--

LOCK TABLES `seats` WRITE;
/*!40000 ALTER TABLE `seats` DISABLE KEYS */;
/*!40000 ALTER TABLE `seats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tickets` (
  `id` varchar(10) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `class` varchar(15) DEFAULT NULL,
  `seat` varchar(3) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Flight_time` time DEFAULT NULL,
  `Flight_Number` varchar(8) DEFAULT NULL,
  `Departure` varchar(15) DEFAULT NULL,
  `Destination` varchar(15) DEFAULT NULL,
  `Email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Flight_Number` (`Flight_Number`),
  KEY `Email` (`Email`),
  CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`Flight_Number`) REFERENCES `flights` (`Flight_Number`),
  CONSTRAINT `tickets_ibfk_2` FOREIGN KEY (`Email`) REFERENCES `user` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets`
--

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES ('1L9K8WGT','p1','Bussiness','b-7','2023-02-28','09:00:00','KOBP92','Kolkata','Bhopal','rangrezsajid998@gmail.com'),('383XQNL0','p1','Bussiness','b-9','2023-02-28','09:00:00','KOBP92','Kolkata','Bhopal','rangrezsajid998@gmail.com'),('CO315IAQ','Sajid Hussain','Bussiness','b-8','2023-02-27','09:00:00','KOBP92','Kolkata','Bhopal','rangrezsajid998@gmail.com'),('GAL2FY38','Sajid Hussain','Bussiness','b-7','2023-02-27','09:00:00','KOBP92','Kolkata','Bhopal','rangrezsajid998@gmail.com'),('GQMY1FF6','p2','Bussiness','b-6','2023-02-28','09:00:00','KOBP92','Kolkata','Bhopal','rangrezsajid998@gmail.com'),('RYG40YCD','p2','Bussiness','b-8','2023-02-28','09:00:00','KOBP92','Kolkata','Bhopal','rangrezsajid998@gmail.com'),('V0BKVIW7','Sajid Hussain','Bussiness','b-9','2023-02-27','09:00:00','KOBP92','Kolkata','Bhopal','rangrezsajid998@gmail.com');
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `First_name` varchar(20) DEFAULT NULL,
  `Last_name` varchar(20) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `Age` int DEFAULT NULL,
  `Mobile` varchar(15) DEFAULT NULL,
  `Password` varchar(20) DEFAULT NULL,
  `Gender` char(10) DEFAULT NULL,
  `email` varchar(30) NOT NULL,
  `Access` varchar(20) DEFAULT 'USER',
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('Sajid','Hussain','2002-06-16',20,'9340827899','Sajid@123','male','rangrezsajid998@gmail.com','ADMIN');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-26 16:45:04
