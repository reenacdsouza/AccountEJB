CREATE DATABASE  IF NOT EXISTS `ha07` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ha07`;
-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: localhost    Database: ha07
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `Account`
--

DROP TABLE IF EXISTS `Account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Account` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `account_number` bigint unsigned NOT NULL,
  `CompanyDetails_sort_code` bigint unsigned NOT NULL,
  `AccountType_id` int unsigned NOT NULL,
  `create_date` timestamp NOT NULL,
  `current_balance` decimal(10,2) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `account_number_UNIQUE` (`account_number`),
  KEY `fk_Account_AccountType_id_idx` (`AccountType_id`),
  KEY `fk_Account_CompanyDetails_sort_code_idx` (`CompanyDetails_sort_code`),
  CONSTRAINT `fk_Account_AccountType_id` FOREIGN KEY (`AccountType_id`) REFERENCES `AccountType` (`id`),
  CONSTRAINT `fk_Account_CompanyDetails_sort_code` FOREIGN KEY (`CompanyDetails_sort_code`) REFERENCES `Company_Details` (`sort_code`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account`
--

LOCK TABLES `Account` WRITE;
/*!40000 ALTER TABLE `Account` DISABLE KEYS */;
INSERT INTO `Account` VALUES (1,900000000000,9061348285,1,'2021-07-15 15:37:27',8000.00),(2,900000000001,9061348285,2,'2021-07-15 15:37:53',18000.00),(3,900000000002,9061348285,3,'2021-07-15 15:38:00',8000.00),(4,900000000003,9061348285,1,'2021-07-15 15:38:36',5600.00),(5,900000000004,9061348285,2,'2021-07-15 15:38:42',2100.00),(6,900000000005,9061348285,3,'2021-07-15 15:38:56',3000.00);
/*!40000 ALTER TABLE `Account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AccountTransaction`
--

DROP TABLE IF EXISTS `AccountTransaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `AccountTransaction` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `Account_id` int unsigned NOT NULL,
  `TransactionType_id` int unsigned NOT NULL,
  `transaction_description` varchar(255) NOT NULL,
  `transaction_time` timestamp NOT NULL,
  `debit` decimal(10,2) unsigned NOT NULL,
  `credit` decimal(10,2) unsigned NOT NULL,
  `running_balance` decimal(10,2) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Transaction_TransactionType_idx` (`TransactionType_id`),
  KEY `fk_Transaction_Account_idx` (`Account_id`),
  CONSTRAINT `fk_Transaction_Account` FOREIGN KEY (`Account_id`) REFERENCES `Account` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_Transaction_TransactionType` FOREIGN KEY (`TransactionType_id`) REFERENCES `TransactionType` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AccountTransaction`
--

LOCK TABLES `AccountTransaction` WRITE;
/*!40000 ALTER TABLE `AccountTransaction` DISABLE KEYS */;
INSERT INTO `AccountTransaction` VALUES (1,1,1,'Account opening Deposit','2021-07-15 15:37:27',0.00,2000.00,2000.00),(2,2,1,'Account opening Deposit','2021-07-15 15:37:53',0.00,2000.00,2000.00),(3,3,1,'Account opening Deposit','2021-07-15 15:38:00',0.00,5000.00,5000.00),(4,4,1,'Account opening Deposit','2021-07-15 15:38:36',0.00,1000.00,1000.00),(5,5,1,'Account opening Deposit','2021-07-15 15:38:42',0.00,1000.00,1000.00),(6,6,1,'Account opening Deposit','2021-07-15 15:38:56',0.00,3000.00,3000.00),(7,1,1,'Cash Deposit','2021-07-16 16:07:02',0.00,2000.00,4000.00),(8,2,1,'Cash Deposit','2021-07-16 16:25:37',0.00,6000.00,8000.00),(9,3,1,'Cash Deposit','2021-07-16 16:39:17',0.00,400.00,5400.00),(10,3,1,'Cash Deposit','2021-07-16 16:51:38',0.00,400.00,5800.00),(11,3,1,'Cash Deposit','2021-07-16 16:57:05',0.00,200.00,6000.00),(12,3,1,'Cash Deposit','2021-07-16 17:03:57',0.00,200.00,6200.00),(13,3,1,'Cash Deposit','2021-07-16 17:51:47',0.00,400.00,6600.00),(14,3,1,'Cash Deposit','2021-07-16 18:09:28',0.00,400.00,7000.00),(15,1,1,'Cash Deposit','2021-07-16 18:31:40',0.00,4000.00,8000.00),(16,4,2,'Cash Withdrawal','2021-07-16 18:38:51',0.00,400.00,600.00),(17,3,1,'Cash Deposit','2021-07-16 18:46:12',0.00,1000.00,8000.00),(18,2,1,'Cash Deposit','2021-07-16 18:51:43',0.00,5000.00,13000.00),(19,2,1,'Cash Deposit','2021-07-16 18:51:58',0.00,5000.00,18000.00),(20,4,1,'Cash Deposit','2021-07-16 18:52:17',0.00,5000.00,5600.00),(21,5,1,'Cash Deposit','2021-07-16 18:52:29',0.00,1500.00,2500.00),(22,5,2,'Cash Withdrawal','2021-07-16 18:52:37',0.00,400.00,2100.00);
/*!40000 ALTER TABLE `AccountTransaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AccountType`
--

DROP TABLE IF EXISTS `AccountType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `AccountType` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type_UNIQUE` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AccountType`
--

LOCK TABLES `AccountType` WRITE;
/*!40000 ALTER TABLE `AccountType` DISABLE KEYS */;
INSERT INTO `AccountType` VALUES (1,'British Pound (GBP)'),(3,'Euro (EUR)'),(2,'United States Dollar (USD)');
/*!40000 ALTER TABLE `AccountType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Address`
--

DROP TABLE IF EXISTS `Address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Address` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `street` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `postcode` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Address`
--

LOCK TABLES `Address` WRITE;
/*!40000 ALTER TABLE `Address` DISABLE KEYS */;
INSERT INTO `Address` VALUES (1,'36405 Bellgrove Hill','San Bernardino','92415','California','United States'),(2,'2512 Division Center','Fullerton','92835','California','United States');
/*!40000 ALTER TABLE `Address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Company_Details`
--

DROP TABLE IF EXISTS `Company_Details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Company_Details` (
  `sort_code` bigint unsigned NOT NULL,
  `company_name` varchar(45) NOT NULL,
  `iban` varchar(45) NOT NULL,
  PRIMARY KEY (`sort_code`),
  UNIQUE KEY `branch_name_UNIQUE` (`company_name`),
  UNIQUE KEY `iban_UNIQUE` (`iban`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Company_Details`
--

LOCK TABLES `Company_Details` WRITE;
/*!40000 ALTER TABLE `Company_Details` DISABLE KEYS */;
INSERT INTO `Company_Details` VALUES (9061348285,'iBANEX','FR40 9027 2574 74JN XSH2 X7DV O33');
/*!40000 ALTER TABLE `Company_Details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customer` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `passport_number` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `passport_number_UNIQUE` (`passport_number`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
INSERT INTO `Customer` VALUES (1,'ha07','ha07','Jeromy','Carslake','407743288','3078112523','jcarslake0@ucoz.ru'),(2,'ha01','ha01','Erroll','Bootton','338101973','2596247785','ebootton1@bloglines.com');
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer_has_Account`
--

DROP TABLE IF EXISTS `Customer_has_Account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customer_has_Account` (
  `Customer_id` int unsigned NOT NULL,
  `Account_id` int unsigned NOT NULL,
  PRIMARY KEY (`Customer_id`,`Account_id`),
  KEY `fk_Customer_has_account_Account_id_idx` (`Account_id`),
  CONSTRAINT `fk_Customer_has_account_Account_id` FOREIGN KEY (`Account_id`) REFERENCES `Account` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_Customer_has_account_Customer_id` FOREIGN KEY (`Customer_id`) REFERENCES `Customer` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer_has_Account`
--

LOCK TABLES `Customer_has_Account` WRITE;
/*!40000 ALTER TABLE `Customer_has_Account` DISABLE KEYS */;
INSERT INTO `Customer_has_Account` VALUES (1,1),(1,2),(1,3),(2,4),(2,5),(2,6);
/*!40000 ALTER TABLE `Customer_has_Account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer_has_Address`
--

DROP TABLE IF EXISTS `Customer_has_Address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customer_has_Address` (
  `Customer_id` int unsigned NOT NULL,
  `Address_id` int unsigned NOT NULL,
  PRIMARY KEY (`Customer_id`,`Address_id`),
  KEY `fk_Customer_has_Address_Address_id_idx` (`Address_id`),
  CONSTRAINT `fk_Customer_has_Address_Address_id` FOREIGN KEY (`Address_id`) REFERENCES `Address` (`id`),
  CONSTRAINT `fk_Customer_has_Address_Customer_id` FOREIGN KEY (`Customer_id`) REFERENCES `Customer` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer_has_Address`
--

LOCK TABLES `Customer_has_Address` WRITE;
/*!40000 ALTER TABLE `Customer_has_Address` DISABLE KEYS */;
INSERT INTO `Customer_has_Address` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `Customer_has_Address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer_has_ExternalBiller`
--

DROP TABLE IF EXISTS `Customer_has_ExternalBiller`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customer_has_ExternalBiller` (
  `Customer_id` int unsigned NOT NULL,
  `ExternalBiller_id` int unsigned NOT NULL,
  PRIMARY KEY (`Customer_id`,`ExternalBiller_id`),
  KEY `fk_Customer_has_ExternalBiller_ExternalBiller_id_idx` (`ExternalBiller_id`),
  CONSTRAINT `fk_Customer_has_ExternalBiller_Customer_id` FOREIGN KEY (`Customer_id`) REFERENCES `Customer` (`id`),
  CONSTRAINT `fk_Customer_has_ExternalBiller_ExternalBiller_id` FOREIGN KEY (`ExternalBiller_id`) REFERENCES `ExternalBiller` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer_has_ExternalBiller`
--

LOCK TABLES `Customer_has_ExternalBiller` WRITE;
/*!40000 ALTER TABLE `Customer_has_ExternalBiller` DISABLE KEYS */;
INSERT INTO `Customer_has_ExternalBiller` VALUES (1,1),(2,1),(1,2),(2,2);
/*!40000 ALTER TABLE `Customer_has_ExternalBiller` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer_has_ExternalPayee`
--

DROP TABLE IF EXISTS `Customer_has_ExternalPayee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customer_has_ExternalPayee` (
  `Customer_id` int unsigned NOT NULL,
  `ExternalPayee_id` int unsigned NOT NULL,
  PRIMARY KEY (`Customer_id`,`ExternalPayee_id`),
  KEY `fk_Customer_has_ExternalPayee_ExternalPayee_id_idx` (`ExternalPayee_id`),
  CONSTRAINT `fk_Customer_has_ExternalPayee_Customer_id` FOREIGN KEY (`Customer_id`) REFERENCES `Customer` (`id`),
  CONSTRAINT `fk_Customer_has_ExternalPayee_ExternalPayee_id` FOREIGN KEY (`ExternalPayee_id`) REFERENCES `ExternalPayee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer_has_ExternalPayee`
--

LOCK TABLES `Customer_has_ExternalPayee` WRITE;
/*!40000 ALTER TABLE `Customer_has_ExternalPayee` DISABLE KEYS */;
INSERT INTO `Customer_has_ExternalPayee` VALUES (1,1),(2,1),(1,2),(2,2),(1,3),(2,3),(1,4),(2,4);
/*!40000 ALTER TABLE `Customer_has_ExternalPayee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ExternalBiller`
--

DROP TABLE IF EXISTS `ExternalBiller`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ExternalBiller` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `account_number` bigint unsigned NOT NULL,
  `sort_code` bigint NOT NULL,
  `biller_name` varchar(255) NOT NULL,
  `branch_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ExternalBiller`
--

LOCK TABLES `ExternalBiller` WRITE;
/*!40000 ALTER TABLE `ExternalBiller` DISABLE KEYS */;
INSERT INTO `ExternalBiller` VALUES (1,6318679545,6038776400,'Rowney Kenlin','Fivechat'),(2,8008401087,1647001323,'Chaddy Stiddard','Topicblab');
/*!40000 ALTER TABLE `ExternalBiller` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ExternalPayee`
--

DROP TABLE IF EXISTS `ExternalPayee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ExternalPayee` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `account_number` bigint unsigned NOT NULL,
  `sort_code` bigint unsigned NOT NULL,
  `payee_name` varchar(255) NOT NULL,
  `branch_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ExternalPayee`
--

LOCK TABLES `ExternalPayee` WRITE;
/*!40000 ALTER TABLE `ExternalPayee` DISABLE KEYS */;
INSERT INTO `ExternalPayee` VALUES (1,504227146135,519335125,'Teddie Bills','Jazzy'),(2,504837153183,599132097,'Selma Tommis','Gigaclub'),(3,504837993137,410743839,'Allard Izod','Youbridge'),(4,510875495898,573584191,'Vonnie Aspall','Trudoo');
/*!40000 ALTER TABLE `ExternalPayee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TransactionType`
--

DROP TABLE IF EXISTS `TransactionType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TransactionType` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `source_description` varchar(45) NOT NULL,
  `traget_description` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `type_UNIQUE` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TransactionType`
--

LOCK TABLES `TransactionType` WRITE;
/*!40000 ALTER TABLE `TransactionType` DISABLE KEYS */;
INSERT INTO `TransactionType` VALUES (1,'Cash Deposit','Cash','Self Internal Account'),(2,'Cash Withdrawal','Self Internal Account','Cash'),(3,'External Transfer','Self Internal Account','External Payee Account'),(4,'External Deposit','External Account','Self Internal Account'),(5,'Direct Debit','Self Internal Account','External Biller Account'),(6,'Internal Transfer','Self Internal Account','Other\'s Internal Account'),(7,'Internal Deposit','Other\'s Internal Account','Self Internal Account'),(8,'Fees','Self Internal Account','Company'),(9,'Interest','Company','Self Internal Account'),(10,'Self Transfer','Self Internal Account','Self Internal Account'),(11,'Self Deposit','Self Internal Account','Self Internal Account');
/*!40000 ALTER TABLE `TransactionType` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-16 21:15:37
