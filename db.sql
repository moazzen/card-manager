CREATE DATABASE  IF NOT EXISTS `card_manager` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `card_manager`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: card_manager
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pan` bigint NOT NULL,
  `customer_id` int NOT NULL,
  `insert_date` datetime NOT NULL,
  `insert_date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` VALUES (1,6104337564529558,1,'2020-12-17 12:49:24',NULL),(5,6104336525958595,1,'2020-12-17 16:35:34',NULL),(7,6104336525953746,1,'2020-12-17 17:32:03',NULL),(8,6104336525952877,1,'2020-12-17 17:35:40',NULL);
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL,
  `customer_id` int NOT NULL,
  `mobile` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,1,'09123179725');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (35);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transfer`
--

DROP TABLE IF EXISTS `transfer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transfer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `from_pan` bigint NOT NULL,
  `to_pan` bigint NOT NULL,
  `customer_id` int NOT NULL,
  `http_response_code` int NOT NULL,
  `response_code` int DEFAULT NULL,
  `provider` smallint NOT NULL,
  `insert_date` date NOT NULL,
  `insert_date_time` datetime NOT NULL,
  `amount` int NOT NULL,
  KEY `uniq_key` (`id`,`insert_date`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8
/*!50100 PARTITION BY RANGE (year(`insert_date`))
(PARTITION p0 VALUES LESS THAN (2019) ENGINE = InnoDB,
 PARTITION p1 VALUES LESS THAN MAXVALUE ENGINE = InnoDB) */;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfer`
--

LOCK TABLES `transfer` WRITE;
/*!40000 ALTER TABLE `transfer` DISABLE KEYS */;
INSERT INTO `transfer` VALUES (21,6104337564529558,6037564521569332,1,200,NULL,2,'2020-12-18','2020-12-18 18:10:14',100000),(22,6037337564529558,6037564521569332,1,200,NULL,2,'2020-12-18','2020-12-18 18:10:57',100000),(24,6037337564529126,6037564521569332,1,500,NULL,1,'2020-12-18','2020-12-18 18:17:18',100000),(25,6037337564529126,6037564521569332,1,200,NULL,1,'2020-12-18','2020-12-18 19:27:15',100000),(26,6037337564522871,6037564521569332,1,200,NULL,1,'2020-12-19','2020-12-19 22:48:38',100000),(27,6037337564522871,6037564521569332,1,200,NULL,1,'2020-12-19','2020-12-19 22:50:41',100000),(28,6037337564522871,6037564521569332,1,200,NULL,1,'2020-12-19','2020-12-19 22:51:51',100000),(29,6037337564522871,6037564521569332,1,200,NULL,1,'2020-12-19','2020-12-19 23:01:46',100000),(30,6037337564522871,6037564521569332,1,200,NULL,1,'2020-12-19','2020-12-19 23:03:44',100000),(31,6037337564522871,6037564521569332,1,200,NULL,1,'2020-12-19','2020-12-19 23:20:29',100000),(32,6037337564522871,6037564521569332,1,200,NULL,1,'2020-12-20','2020-12-20 21:07:34',100000),(34,6037337564522871,6037564521569332,1,200,NULL,1,'2020-12-21','2020-12-21 00:39:05',100000);
/*!40000 ALTER TABLE `transfer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-21  1:24:13
