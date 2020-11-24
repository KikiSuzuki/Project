CREATE DATABASE  IF NOT EXISTS `diploma` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `diploma`;
-- MySQL dump 10.13  Distrib 8.0.18, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: diploma
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friends` (
  `friends_id` int(11) NOT NULL AUTO_INCREMENT,
  `user1_id` int(11) NOT NULL,
  `user2_id` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`friends_id`),
  KEY `user1_id` (`user1_id`,`user2_id`),
  KEY `user2_id_idx` (`user2_id`),
  CONSTRAINT `user1_id` FOREIGN KEY (`user1_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `user2_id` FOREIGN KEY (`user2_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
INSERT INTO `friends` VALUES (2,3,4,1),(11,1,4,0),(13,3,1,0),(14,1,2,1);
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request` (
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `lvl` int(11) NOT NULL,
  `txt` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `longitude` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `latitude` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `status` int(11) NOT NULL,
  `time` mediumtext COLLATE utf8mb4_general_ci,
  PRIMARY KEY (`request_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (154,1,3,NULL,'71.4167','51.1667',0,'1590266141171'),(160,1,3,NULL,'51.9221','47.0972',1,'1590566010766'),(161,1,3,'aaaa','51.9221','47.0972',1,'1590566149003'),(162,1,3,NULL,'51.9221','47.0972',1,'1590566258283'),(163,1,3,NULL,'51.9221','47.0972',0,'1590566346747'),(164,1,3,'bbb','51.9221','47.0972',1,'1590566407079'),(165,1,3,NULL,'51.9221','47.0972',1,'1590567938672'),(167,1,3,'ccc','51.9221','47.0972',1,'1590568817676'),(168,1,3,'ddd','51.9221','47.0972',1,'1590569393242'),(169,1,1,NULL,'51.9221','47.0972',0,'1590586666277'),(170,1,3,NULL,'51.9221','47.0972',0,'1590586732019'),(171,1,3,NULL,'51.9221','47.0972',1,'1590586878415'),(172,1,3,'www','71.4167','51.1667',1,'1590742618870'),(174,1,3,'aaa','51.92229','47.097066',0,'1590763602958'),(175,1,3,'AAAA','71.4167','51.1667',0,'1591600577982');
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `response`
--

DROP TABLE IF EXISTS `response`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `response` (
  `response_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `request_id` int(11) NOT NULL,
  PRIMARY KEY (`response_id`),
  KEY `response_user_user_id_fk` (`user_id`),
  CONSTRAINT `response_user_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `response`
--

LOCK TABLES `response` WRITE;
/*!40000 ALTER TABLE `response` DISABLE KEYS */;
INSERT INTO `response` VALUES (1,1,160),(2,1,161),(3,1,162),(4,1,164),(5,1,165),(6,1,167),(7,1,168),(8,1,171),(10,4,173);
/*!40000 ALTER TABLE `response` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `gender` int(11) DEFAULT NULL,
  `dob` date NOT NULL,
  `question` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `answer` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `about` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `pwd` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `photopath` varchar(1000) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `peer` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_login_uindex` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'kiki','Lyubov',1,'1999-04-28','first dog\'s name','Tuzik','Kkk','1234','https://wx.qlogo.cn/mmopen/vi_32/ia2dE47HqeibNDdgJnH80bC2POQTFa3dbbUnib8ykx4gcHTaUZVRlHlI3ONYzGxjGFbqkfSI6rLVQiay1cUZIOon8Q/132',''),(2,'Mike','Mike',1,'1998-03-12','first dog\'s name','Tuzik','...','123','https://wx.qlogo.cn/mmopen/vi_32/ia2dE47HqeibNDdgJnH80bC2POQTFa3dbbUnib8ykx4gcHTaUZVRlHlI3ONYzGxjGFbqkfSI6rLVQiay1cUZIOon8Q/132',NULL),(3,'Gal','Gal',2,'2000-04-01','first dog\'s name','Tuzik','...','123','https://wx.qlogo.cn/mmopen/vi_32/ia2dE47HqeibNDdgJnH80bC2POQTFa3dbbUnib8ykx4gcHTaUZVRlHlI3ONYzGxjGFbqkfSI6rLVQiay1cUZIOon8Q/132',NULL),(4,'A','A',1,'1999-06-02','first dog\'s name','Tuzik','...','123',NULL,NULL),(9,'B','Ai?',1,'2020-02-01','B','B','B','B','https://wx.qlogo.cn/mmopen/vi_32/ia2dE47HqeibNDdgJnH80bC2POQTFa3dbbUnib8ykx4gcHTaUZVRlHlI3ONYzGxjGFbqkfSI6rLVQiay1cUZIOon8Q/132',NULL),(14,'s','Ai?',1,'2020-02-01','s','s','s','s','https://wx.qlogo.cn/mmopen/vi_32/ia2dE47HqeibNDdgJnH80bC2POQTFa3dbbUnib8ykx4gcHTaUZVRlHlI3ONYzGxjGFbqkfSI6rLVQiay1cUZIOon8Q/132',NULL),(15,'w','Ai?',1,'2012-05-01','w','w','w','w','https://wx.qlogo.cn/mmopen/vi_32/ia2dE47HqeibNDdgJnH80bC2POQTFa3dbbUnib8ykx4gcHTaUZVRlHlI3ONYzGxjGFbqkfSI6rLVQiay1cUZIOon8Q/132',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'diploma'
--

--
-- Dumping routines for database 'diploma'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-13 19:35:53
