-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ole
-- ------------------------------------------------------
-- Server version	5.7.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('ole','123');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `askole`
--

LOCK TABLES `askole` WRITE;
/*!40000 ALTER TABLE `askole` DISABLE KEYS */;
INSERT INTO `askole` VALUES (1,'dfghjk','fghjkl','Private League','Jill'),(2,'ddddd','laoalla','Private League','Jill'),(3,'hbdskjbcjk','bkdjbdkjbk','Private League','Leon'),(4,'vvvvvv','vvvvvvvv','General','Leon');
/*!40000 ALTER TABLE `askole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `faq`
--

LOCK TABLES `faq` WRITE;
/*!40000 ALTER TABLE `faq` DISABLE KEYS */;
INSERT INTO `faq` VALUES (20,'wwwwww','dddd','General'),(9,'dfghghjhl;','gfdsa','General'),(19,'How to join public league? ','Simply just join. ','Public League'),(17,'how to join','sdfghjk,l,s ','General'),(18,'ssss','ssss','General'),(23,'aaaaa','bbbb','Private League');
/*!40000 ALTER TABLE `faq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `league`
--

LOCK TABLES `league` WRITE;
/*!40000 ALTER TABLE `league` DISABLE KEYS */;
INSERT INTO `league` VALUES (2,'2',9,'Premier League Public League');
/*!40000 ALTER TABLE `league` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `leagueteams`
--

LOCK TABLES `leagueteams` WRITE;
/*!40000 ALTER TABLE `leagueteams` DISABLE KEYS */;
/*!40000 ALTER TABLE `leagueteams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (2,'admin',2,-1);
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `match`
--

LOCK TABLES `match` WRITE;
/*!40000 ALTER TABLE `match` DISABLE KEYS */;
INSERT INTO `match` VALUES ('100','2','2018-09-01','22:00:00','45','37',1,1),('101','2','2018-09-01','22:00:00','48','39',0,1),('102','2','2018-09-02','00:30:00','50','34',2,1),('103','2','2018-09-02','20:30:00','43','42',2,3),('104','2','2018-09-02','23:00:00','38','47',2,1),('105','2','2018-09-15','19:30:00','47','40',1,2),('106','2','2018-09-15','22:00:00','35','46',4,2),('107','2','2018-09-15','22:00:00','49','43',4,1),('108','2','2018-09-15','22:00:00','37','52',0,1),('109','2','2018-09-15','22:00:00','50','36',3,0),('110','2','2018-09-15','22:00:00','34','42',1,2),('111','2','2018-09-16','00:30:00','38','33',1,2),('112','2','2018-09-16','20:30:00','39','44',1,0),('113','2','2018-09-16','23:00:00','45','48',1,3),('114','2','2018-09-18','03:00:00','41','51',2,2),('115','2','2018-09-22','19:30:00','36','38',1,1),('116','2','2018-09-22','22:00:00','44','35',4,0),('117','2','2018-09-22','22:00:00','43','50',0,5),('118','2','2018-09-22','22:00:00','52','34',0,0),('119','2','2018-09-22','22:00:00','46','37',3,1),('120','2','2018-09-22','22:00:00','40','41',3,0),('121','2','2018-09-22','22:00:00','33','39',1,1),('122','2','2018-09-23','00:30:00','51','47',1,2),('123','2','2018-09-23','20:30:00','48','49',0,0),('124','2','2018-09-23','23:00:00','42','45',2,0),('125','2','2018-09-29','19:30:00','48','33',3,1),('126','2','2018-09-29','22:00:00','42','38',2,0),('127','2','2018-09-29','22:00:00','45','36',3,0),('128','2','2018-09-29','22:00:00','37','47',0,2),('129','2','2018-09-29','22:00:00','50','51',2,0),('130','2','2018-09-29','22:00:00','34','46',0,2),('131','2','2018-09-29','22:00:00','39','41',2,0),('132','2','2018-09-30','00:30:00','49','40',1,1),('133','2','2018-09-30','23:00:00','43','44',1,2),('134','2','2018-10-02','03:00:00','35','52',2,1),('135','2','2018-10-06','03:00:00','51','48',1,0),('136','2','2018-10-06','22:00:00','44','37',1,1),('137','2','2018-10-06','22:00:00','52','39',0,1),('138','2','2018-10-07','19:00:00','36','42',1,5),('139','2','2018-10-06','22:00:00','46','45',1,2),('140','2','2018-10-07','23:30:00','40','50',0,0),('141','2','2018-10-07','00:30:00','33','34',3,2),('142','2','2018-10-07','21:15:00','41','49',0,3),('143','2','2018-10-06','22:00:00','47','43',1,0),('144','2','2018-10-06','22:00:00','38','35',0,4),('145','2','2018-10-23','03:00:00','42','46',3,1),('146','2','2018-10-20','22:00:00','35','41',0,0),('147','2','2018-10-20','22:00:00','43','36',4,2),('148','2','2018-10-20','19:30:00','49','33',2,2),('149','2','2018-10-21','23:00:00','45','52',2,0),('150','2','2018-10-21','00:30:00','37','40',0,1),('151','2','2018-10-20','22:00:00','50','44',5,0),('152','2','2018-10-20','22:00:00','34','51',0,1),('153','2','2018-10-20','22:00:00','48','47',0,1),('154','2','2018-10-20','22:00:00','39','38',0,2),('155','2','2018-10-27','22:00:00','51','39',1,0),('156','2','2018-10-28','21:30:00','44','49',0,4),('157','2','2018-10-28','21:30:00','52','42',2,2),('158','2','2018-10-27','22:00:00','36','35',0,3),('159','2','2018-10-28','00:30:00','46','48',1,1),('160','2','2018-10-27','22:00:00','40','43',4,1),('161','2','2018-10-29','00:00:00','33','45',2,1),('162','2','2018-10-27','22:00:00','41','34',0,0),('163','2','2018-10-30','03:00:00','47','50',-1,-1),('164','2','2018-10-27','22:00:00','38','37',3,0),('165','2','2018-11-03','23:00:00','42','40',-1,-1),('166','2','2018-11-03','23:00:00','35','33',-1,-1),('167','2','2018-11-03','23:00:00','43','46',-1,-1),('168','2','2018-11-03','23:00:00','49','52',-1,-1),('169','2','2018-11-03','23:00:00','45','51',-1,-1),('170','2','2018-11-03','23:00:00','37','36',-1,-1),('171','2','2018-11-03','23:00:00','50','41',-1,-1),('172','2','2018-11-03','23:00:00','34','38',-1,-1),('173','2','2018-11-03','23:00:00','48','44',-1,-1),('174','2','2018-11-03','23:00:00','39','47',-1,-1),('175','2','2018-11-10','23:00:00','42','39',-1,-1),('176','2','2018-11-10','23:00:00','43','51',-1,-1),('177','2','2018-11-10','23:00:00','49','45',-1,-1),('178','2','2018-11-10','23:00:00','52','47',-1,-1),('179','2','2018-11-10','23:00:00','37','48',-1,-1),('180','2','2018-11-10','23:00:00','46','44',-1,-1),('181','2','2018-11-10','23:00:00','40','36',-1,-1),('182','2','2018-11-10','23:00:00','50','33',-1,-1),('183','2','2018-11-10','23:00:00','34','35',-1,-1),('184','2','2018-11-10','23:00:00','41','38',-1,-1),('185','2','2018-11-24','23:00:00','35','42',-1,-1),('186','2','2018-11-24','23:00:00','51','46',-1,-1),('187','2','2018-11-24','23:00:00','44','34',-1,-1),('188','2','2018-11-24','23:00:00','45','43',-1,-1),('189','2','2018-11-24','23:00:00','36','41',-1,-1),('190','2','2018-11-24','23:00:00','33','52',-1,-1),('191','2','2018-11-24','23:00:00','47','49',-1,-1),('192','2','2018-11-24','23:00:00','38','40',-1,-1),('193','2','2018-11-24','23:00:00','48','50',-1,-1),('194','2','2018-11-24','23:00:00','39','37',-1,-1),('195','2','2018-12-01','23:00:00','42','47',-1,-1),('196','2','2018-12-01','23:00:00','43','39',-1,-1),('197','2','2018-12-01','23:00:00','49','36',-1,-1),('198','2','2018-12-01','23:00:00','52','44',-1,-1),('199','2','2018-12-01','23:00:00','37','51',-1,-1),('200','2','2018-12-01','23:00:00','46','38',-1,-1),('201','2','2018-12-01','23:00:00','40','45',-1,-1),('202','2','2018-12-01','23:00:00','50','35',-1,-1),('203','2','2018-12-01','23:00:00','34','48',-1,-1),('204','2','2018-12-01','23:00:00','41','33',-1,-1),('205','2','2018-12-05','03:45:00','35','37',-1,-1),('206','2','2018-12-05','03:45:00','51','52',-1,-1),('207','2','2018-12-05','03:45:00','44','40',-1,-1),('208','2','2018-12-05','03:45:00','36','46',-1,-1),('209','2','2018-12-05','03:45:00','38','50',-1,-1),('210','2','2018-12-05','03:45:00','48','43',-1,-1),('211','2','2018-12-05','03:45:00','39','49',-1,-1),('212','2','2018-12-05','04:00:00','33','42',-1,-1),('213','2','2018-12-06','03:45:00','45','34',-1,-1),('214','2','2018-12-06','04:00:00','47','41',-1,-1),('215','2','2018-12-08','23:00:00','42','37',-1,-1),('216','2','2018-12-08','23:00:00','35','40',-1,-1),('217','2','2018-12-08','23:00:00','44','51',-1,-1),('218','2','2018-12-08','23:00:00','43','41',-1,-1),('219','2','2018-12-08','23:00:00','49','50',-1,-1),('220','2','2018-12-08','23:00:00','45','38',-1,-1),('221','2','2018-12-08','23:00:00','46','47',-1,-1),('222','2','2018-12-08','23:00:00','33','36',-1,-1),('223','2','2018-12-08','23:00:00','34','39',-1,-1),('224','2','2018-12-08','23:00:00','48','52',-1,-1),('225','2','2018-12-15','23:00:00','51','49',-1,-1),('226','2','2018-12-15','23:00:00','52','46',-1,-1),('227','2','2018-12-15','23:00:00','36','48',-1,-1),('228','2','2018-12-15','23:00:00','37','34',-1,-1),('229','2','2018-12-15','23:00:00','40','33',-1,-1),('230','2','2018-12-15','23:00:00','50','45',-1,-1),('231','2','2018-12-15','23:00:00','41','42',-1,-1),('232','2','2018-12-15','23:00:00','47','44',-1,-1),('233','2','2018-12-15','23:00:00','38','43',-1,-1),('234','2','2018-12-15','23:00:00','39','35',-1,-1),('235','2','2018-12-22','23:00:00','42','44',-1,-1),('236','2','2018-12-22','23:00:00','35','51',-1,-1),('237','2','2018-12-22','23:00:00','43','33',-1,-1),('238','2','2018-12-22','23:00:00','49','46',-1,-1),('239','2','2018-12-22','23:00:00','45','47',-1,-1),('240','2','2018-12-22','23:00:00','37','41',-1,-1),('241','2','2018-12-22','23:00:00','50','52',-1,-1),('242','2','2018-12-22','23:00:00','34','36',-1,-1),('243','2','2018-12-22','23:00:00','48','38',-1,-1),('244','2','2018-12-22','23:00:00','39','40',-1,-1),('245','2','2018-12-26','23:00:00','51','42',-1,-1),('246','2','2018-12-26','23:00:00','44','45',-1,-1),('247','2','2018-12-26','23:00:00','52','43',-1,-1),('248','2','2018-12-26','23:00:00','36','39',-1,-1),('249','2','2018-12-26','23:00:00','46','50',-1,-1),('250','2','2018-12-26','23:00:00','40','34',-1,-1),('251','2','2018-12-26','23:00:00','33','37',-1,-1),('252','2','2018-12-26','23:00:00','41','48',-1,-1),('253','2','2018-12-26','23:00:00','47','35',-1,-1),('254','2','2018-12-26','23:00:00','38','49',-1,-1),('255','2','2018-12-29','23:00:00','51','45',-1,-1),('256','2','2018-12-29','23:00:00','44','48',-1,-1),('257','2','2018-12-29','23:00:00','52','49',-1,-1),('258','2','2018-12-29','23:00:00','36','37',-1,-1),('259','2','2018-12-29','23:00:00','46','43',-1,-1),('260','2','2018-12-29','23:00:00','40','42',-1,-1),('261','2','2018-12-29','23:00:00','33','35',-1,-1),('262','2','2018-12-29','23:00:00','41','50',-1,-1),('263','2','2018-12-29','23:00:00','47','39',-1,-1),('264','2','2018-12-29','23:00:00','38','34',-1,-1),('265','2','2019-01-01','23:00:00','42','36',-1,-1),('266','2','2019-01-01','23:00:00','35','38',-1,-1),('267','2','2019-01-01','23:00:00','43','47',-1,-1),('268','2','2019-01-01','23:00:00','49','41',-1,-1),('269','2','2019-01-01','23:00:00','45','46',-1,-1),('270','2','2019-01-01','23:00:00','37','44',-1,-1),('271','2','2019-01-01','23:00:00','50','40',-1,-1),('272','2','2019-01-01','23:00:00','34','33',-1,-1),('273','2','2019-01-01','23:00:00','48','51',-1,-1),('274','2','2019-01-01','23:00:00','39','52',-1,-1),('275','2','2019-01-12','23:00:00','51','40',-1,-1),('276','2','2019-01-12','23:00:00','44','36',-1,-1),('277','2','2019-01-12','23:00:00','43','37',-1,-1),('278','2','2019-01-12','23:00:00','49','34',-1,-1),('279','2','2019-01-12','23:00:00','52','38',-1,-1),('280','2','2019-01-12','23:00:00','45','35',-1,-1),('281','2','2019-01-12','23:00:00','46','41',-1,-1),('282','2','2019-01-12','23:00:00','50','39',-1,-1),('283','2','2019-01-12','23:00:00','47','33',-1,-1),('284','2','2019-01-12','23:00:00','48','42',-1,-1),('285','2','2019-01-19','23:00:00','42','49',-1,-1),('286','2','2019-01-19','23:00:00','35','48',-1,-1),('287','2','2019-01-19','23:00:00','36','47',-1,-1),('288','2','2019-01-19','23:00:00','37','50',-1,-1),('289','2','2019-01-19','23:00:00','40','52',-1,-1),('290','2','2019-01-19','23:00:00','33','51',-1,-1),('291','2','2019-01-19','23:00:00','34','43',-1,-1),('292','2','2019-01-19','23:00:00','41','45',-1,-1),('293','2','2019-01-19','23:00:00','38','44',-1,-1),('294','2','2019-01-19','23:00:00','39','46',-1,-1),('295','2','2019-01-30','03:45:00','42','43',-1,-1),('296','2','2019-01-30','03:45:00','35','49',-1,-1),('297','2','2019-01-30','03:45:00','36','51',-1,-1),('298','2','2019-01-30','03:45:00','37','45',-1,-1),('299','2','2019-01-30','03:45:00','39','48',-1,-1),('300','2','2019-01-30','04:00:00','33','44',-1,-1),('301','2','2019-01-31','03:45:00','34','50',-1,-1),('302','2','2019-01-31','03:45:00','41','52',-1,-1),('303','2','2019-01-31','04:00:00','40','46',-1,-1),('304','2','2019-01-31','04:00:00','47','38',-1,-1),('305','2','2019-02-02','23:00:00','51','38',-1,-1),('306','2','2019-02-02','23:00:00','44','41',-1,-1),('307','2','2019-02-02','23:00:00','43','35',-1,-1),('308','2','2019-02-02','23:00:00','49','37',-1,-1),('309','2','2019-02-02','23:00:00','52','36',-1,-1),('310','2','2019-02-02','23:00:00','45','39',-1,-1),('311','2','2019-02-02','23:00:00','46','33',-1,-1),('312','2','2019-02-02','23:00:00','50','42',-1,-1),('313','2','2019-02-02','23:00:00','47','34',-1,-1),('314','2','2019-02-02','23:00:00','48','40',-1,-1),('315','2','2019-02-09','23:00:00','51','44',-1,-1),('316','2','2019-02-09','23:00:00','52','48',-1,-1),('317','2','2019-02-09','23:00:00','36','33',-1,-1),('318','2','2019-02-09','23:00:00','37','42',-1,-1),('319','2','2019-02-09','23:00:00','40','35',-1,-1),('320','2','2019-02-09','23:00:00','50','49',-1,-1),('321','2','2019-02-09','23:00:00','41','43',-1,-1),('322','2','2019-02-09','23:00:00','47','46',-1,-1),('323','2','2019-02-09','23:00:00','38','45',-1,-1),('324','2','2019-02-09','23:00:00','39','34',-1,-1),('325','2','2019-02-23','23:00:00','42','41',-1,-1),('326','2','2019-02-23','23:00:00','35','39',-1,-1),('327','2','2019-02-23','23:00:00','44','47',-1,-1),('328','2','2019-02-23','23:00:00','43','38',-1,-1),('329','2','2019-02-23','23:00:00','49','51',-1,-1),('330','2','2019-02-23','23:00:00','45','50',-1,-1),('331','2','2019-02-23','23:00:00','46','52',-1,-1),('332','2','2019-02-23','23:00:00','33','40',-1,-1),('333','2','2019-02-23','23:00:00','34','37',-1,-1),('334','2','2019-02-23','23:00:00','48','36',-1,-1),('335','2','2019-02-27','03:45:00','42','35',-1,-1),('336','2','2019-02-27','03:45:00','43','45',-1,-1),('337','2','2019-02-27','03:45:00','37','39',-1,-1),('338','2','2019-02-27','03:45:00','46','51',-1,-1),('339','2','2019-02-27','04:00:00','52','33',-1,-1),('340','2','2019-02-28','03:45:00','49','47',-1,-1),('341','2','2019-02-28','03:45:00','34','44',-1,-1),('342','2','2019-02-28','03:45:00','41','36',-1,-1),('343','2','2019-02-28','04:00:00','40','38',-1,-1),('344','2','2019-02-28','04:00:00','50','48',-1,-1),('345','2','2019-03-02','23:00:00','35','50',-1,-1),('346','2','2019-03-02','23:00:00','51','37',-1,-1),('347','2','2019-03-02','23:00:00','44','52',-1,-1),('348','2','2019-03-02','23:00:00','45','40',-1,-1),('349','2','2019-03-02','23:00:00','36','49',-1,-1),('350','2','2019-03-02','23:00:00','33','41',-1,-1),('351','2','2019-03-02','23:00:00','47','42',-1,-1),('352','2','2019-03-02','23:00:00','38','46',-1,-1),('353','2','2019-03-02','23:00:00','48','34',-1,-1),('354','2','2019-03-02','23:00:00','39','43',-1,-1),('355','2','2019-03-09','23:00:00','42','33',-1,-1),('356','2','2019-03-09','23:00:00','43','48',-1,-1),('357','2','2019-03-09','23:00:00','49','39',-1,-1),('358','2','2019-03-09','23:00:00','52','51',-1,-1),('359','2','2019-03-09','23:00:00','37','35',-1,-1),('360','2','2019-03-09','23:00:00','46','36',-1,-1),('361','2','2019-03-09','23:00:00','40','44',-1,-1),('362','2','2019-03-09','23:00:00','50','38',-1,-1),('363','2','2019-03-09','23:00:00','34','45',-1,-1),('364','2','2019-03-09','23:00:00','41','47',-1,-1),('365','2','2019-03-16','23:00:00','35','34',-1,-1),('366','2','2019-03-16','23:00:00','51','43',-1,-1),('367','2','2019-03-16','23:00:00','44','46',-1,-1),('368','2','2019-03-16','23:00:00','45','49',-1,-1),('369','2','2019-03-16','23:00:00','36','40',-1,-1),('370','2','2019-03-16','23:00:00','33','50',-1,-1),('371','2','2019-03-16','23:00:00','47','52',-1,-1),('372','2','2019-03-16','23:00:00','38','41',-1,-1),('373','2','2019-03-16','23:00:00','48','37',-1,-1),('374','2','2019-03-16','23:00:00','39','42',-1,-1),('375','2','2019-03-30','23:00:00','42','34',-1,-1),('376','2','2019-03-30','23:00:00','51','41',-1,-1),('377','2','2019-03-30','23:00:00','44','39',-1,-1),('378','2','2019-03-30','23:00:00','43','49',-1,-1),('379','2','2019-03-30','23:00:00','52','37',-1,-1),('380','2','2019-03-30','23:00:00','36','50',-1,-1),('381','2','2019-03-30','23:00:00','46','35',-1,-1),('382','2','2019-03-30','23:00:00','40','47',-1,-1),('383','2','2019-03-30','23:00:00','33','38',-1,-1),('384','2','2019-03-30','23:00:00','48','45',-1,-1),('385','2','2019-04-06','21:00:00','35','44',-1,-1),('386','2','2019-04-06','21:00:00','49','48',-1,-1),('387','2','2019-04-06','21:00:00','45','42',-1,-1),('388','2','2019-04-06','21:00:00','37','46',-1,-1),('389','2','2019-04-06','21:00:00','50','43',-1,-1),('390','2','2019-04-06','21:00:00','34','52',-1,-1),('391','2','2019-04-06','21:00:00','41','40',-1,-1),('392','2','2019-04-06','21:00:00','47','51',-1,-1),('393','2','2019-04-06','21:00:00','38','36',-1,-1),('394','2','2019-04-06','21:00:00','39','33',-1,-1),('395','2','2019-04-13','21:00:00','51','35',-1,-1),('396','2','2019-04-13','21:00:00','44','43',-1,-1),('397','2','2019-04-13','21:00:00','52','50',-1,-1),('398','2','2019-04-13','21:00:00','36','45',-1,-1),('399','2','2019-04-13','21:00:00','46','34',-1,-1),('400','2','2019-04-13','21:00:00','40','49',-1,-1),('401','2','2019-04-13','21:00:00','33','48',-1,-1),('402','2','2019-04-13','21:00:00','41','39',-1,-1),('403','2','2019-04-13','21:00:00','47','37',-1,-1),('404','2','2019-04-13','21:00:00','38','42',-1,-1),('405','2','2019-04-20','21:00:00','42','52',-1,-1),('406','2','2019-04-20','21:00:00','35','36',-1,-1),('407','2','2019-04-20','21:00:00','43','40',-1,-1),('408','2','2019-04-20','21:00:00','49','44',-1,-1),('409','2','2019-04-20','21:00:00','45','33',-1,-1),('410','2','2019-04-20','21:00:00','37','38',-1,-1),('411','2','2019-04-20','21:00:00','50','47',-1,-1),('412','2','2019-04-20','21:00:00','34','41',-1,-1),('413','2','2019-04-20','21:00:00','48','46',-1,-1),('414','2','2019-04-20','21:00:00','39','51',-1,-1),('415','2','2019-04-27','21:00:00','51','34',-1,-1),('416','2','2019-04-27','21:00:00','44','50',-1,-1),('417','2','2019-04-27','21:00:00','52','45',-1,-1),('418','2','2019-04-27','21:00:00','36','43',-1,-1),('419','2','2019-04-27','21:00:00','46','42',-1,-1),('420','2','2019-04-27','21:00:00','40','37',-1,-1),('421','2','2019-04-27','21:00:00','33','49',-1,-1),('422','2','2019-04-27','21:00:00','41','35',-1,-1),('423','2','2019-04-27','21:00:00','47','48',-1,-1),('424','2','2019-04-27','21:00:00','38','39',-1,-1),('425','2','2019-05-04','21:00:00','42','51',-1,-1),('426','2','2019-05-04','21:00:00','35','47',-1,-1),('427','2','2019-05-04','21:00:00','43','52',-1,-1),('428','2','2019-05-04','21:00:00','49','38',-1,-1),('429','2','2019-05-04','21:00:00','45','44',-1,-1),('430','2','2019-05-04','21:00:00','37','33',-1,-1),('431','2','2019-05-04','21:00:00','50','46',-1,-1),('432','2','2019-05-04','21:00:00','34','40',-1,-1),('433','2','2019-05-04','21:00:00','48','41',-1,-1),('434','2','2019-05-04','21:00:00','39','36',-1,-1),('435','2','2019-05-12','21:00:00','51','50',-1,-1),('436','2','2019-05-12','21:00:00','44','42',-1,-1),('437','2','2019-05-12','21:00:00','52','35',-1,-1),('438','2','2019-05-12','21:00:00','36','34',-1,-1),('439','2','2019-05-12','21:00:00','46','49',-1,-1),('440','2','2019-05-12','21:00:00','40','39',-1,-1),('441','2','2019-05-12','21:00:00','33','43',-1,-1),('442','2','2019-05-12','21:00:00','41','37',-1,-1),('443','2','2019-05-12','21:00:00','47','45',-1,-1),('444','2','2019-05-12','21:00:00','38','48',-1,-1),('65','2','2018-08-11','03:00:00','33','46',2,1),('66','2','2018-08-11','19:30:00','34','47',1,2),('67','2','2018-08-11','22:00:00','35','43',2,0),('68','2','2018-08-11','22:00:00','36','52',0,2),('69','2','2018-08-11','22:00:00','37','49',0,3),('70','2','2018-08-11','22:00:00','38','51',2,0),('71','2','2018-08-12','00:30:00','39','45',2,2),('72','2','2018-08-12','20:30:00','40','48',4,0),('73','2','2018-08-12','20:30:00','41','44',0,0),('74','2','2018-08-12','23:00:00','42','50',0,2),('75','2','2018-08-18','19:30:00','43','34',0,0),('76','2','2018-08-19','20:30:00','44','38',1,3),('77','2','2018-08-18','22:00:00','45','41',2,1),('78','2','2018-08-18','22:00:00','46','39',2,0),('79','2','2018-08-18','22:00:00','47','36',3,1),('80','2','2018-08-18','22:00:00','48','35',1,2),('81','2','2018-08-19','00:30:00','49','42',3,2),('82','2','2018-08-19','20:30:00','50','37',6,1),('83','2','2018-08-19','23:00:00','51','33',3,2),('84','2','2018-08-21','03:00:00','52','40',0,2),('85','2','2018-08-25','19:30:00','39','50',1,1),('86','2','2018-08-25','22:00:00','42','48',3,1),('87','2','2018-08-25','22:00:00','35','45',2,2),('88','2','2018-08-26','23:00:00','36','44',4,2),('89','2','2018-08-25','22:00:00','37','43',0,0),('90','2','2018-08-25','22:00:00','41','46',1,2),('91','2','2018-08-26','00:30:00','40','51',1,0),('92','2','2018-08-26','20:30:00','38','52',2,1),('93','2','2018-08-26','23:00:00','34','49',1,2),('94','2','2018-08-28','03:00:00','33','47',0,3),('95','2','2018-09-01','19:30:00','46','40',1,2),('96','2','2018-09-01','22:00:00','51','36',2,2),('97','2','2018-09-02','23:00:00','44','33',0,2),('98','2','2018-09-01','22:00:00','49','35',2,0),('99','2','2018-09-01','22:00:00','52','41',0,2);
/*!40000 ALTER TABLE `match` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `matcheslog`
--

LOCK TABLES `matcheslog` WRITE;
/*!40000 ALTER TABLE `matcheslog` DISABLE KEYS */;
/*!40000 ALTER TABLE `matcheslog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `privateleague`
--

LOCK TABLES `privateleague` WRITE;
/*!40000 ALTER TABLE `privateleague` DISABLE KEYS */;
/*!40000 ALTER TABLE `privateleague` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `publicleague`
--

LOCK TABLES `publicleague` WRITE;
/*!40000 ALTER TABLE `publicleague` DISABLE KEYS */;
INSERT INTO `publicleague` VALUES (2,'Boots');
/*!40000 ALTER TABLE `publicleague` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `specials`
--

LOCK TABLES `specials` WRITE;
/*!40000 ALTER TABLE `specials` DISABLE KEYS */;
INSERT INTO `specials` VALUES (1,'Top Scorer','N'),(2,'Top Assist','N'),(3,'Golden Glove','N'),(4,'Best Young Player','N'),(5,'First Red card of Tournament','N'),(6,'Best Player','N'),(7,'Winner of Competition  (team)','N'),(8,'Team with Most Number of Goals','N'),(9,'Number of Goals scored by Winner of Compt','N'),(10,'Team with Least Number of Goals Conceded','N'),(11,'Team with Most Number of Goals Conceded','N'),(12,'Team with Least Number of Goals Scored','N'),(13,'Outsider to achieve top 4','N');
/*!40000 ALTER TABLE `specials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `specialslog`
--

LOCK TABLES `specialslog` WRITE;
/*!40000 ALTER TABLE `specialslog` DISABLE KEYS */;
INSERT INTO `specialslog` VALUES (2,4,'-1'),(2,5,'-1'),(2,6,'-1');
/*!40000 ALTER TABLE `specialslog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES ('33','Manchester United'),('34','Newcastle'),('35','Bournemouth'),('36','Fulham'),('37','Huddersfield'),('38','Watford'),('39','Wolves'),('40','Liverpool'),('41','Southampton'),('42','Arsenal'),('43','Cardiff'),('44','Burnley'),('45','Everton'),('46','Leicester'),('47','Tottenham'),('48','West Ham'),('49','Chelsea'),('50','Manchester City'),('51','Brighton'),('52','Crystal Palace');
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tournament`
--

LOCK TABLES `tournament` WRITE;
/*!40000 ALTER TABLE `tournament` DISABLE KEYS */;
INSERT INTO `tournament` VALUES ('2','Premier League','2018-08-10','2019-05-12');
/*!40000 ALTER TABLE `tournament` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','admin','admin','2018-11-03','Singapore','9876543','admin@ole.com','admin'),('Jill','Jill Ma','jmmj78','1991-10-08','Singapore','86892781','jm89@gmail.com','Liverpool'),('Leon','Leon Tan','lt12','1989-10-01','Singapore','98762819','lt67@gmail.com','Arsenal');
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

-- Dump completed on 2019-01-11  2:40:25
