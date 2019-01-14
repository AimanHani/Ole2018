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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `username` varchar(20) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `askole`
--

DROP TABLE IF EXISTS `askole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `askole` (
  `askId` int(20) NOT NULL AUTO_INCREMENT,
  `question` varchar(200) NOT NULL,
  `answer` varchar(200) NOT NULL,
  `category` varchar(50) NOT NULL,
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`askId`),
  KEY `username_idx` (`username`),
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `faq`
--

DROP TABLE IF EXISTS `faq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faq` (
  `faqId` int(20) NOT NULL AUTO_INCREMENT,
  `question` varchar(200) NOT NULL,
  `answer` varchar(200) NOT NULL,
  `category` varchar(50) NOT NULL,
  PRIMARY KEY (`faqId`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `league`
--

DROP TABLE IF EXISTS `league`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `league` (
  `leagueId` int(100) NOT NULL AUTO_INCREMENT,
  `tournamentId` varchar(20) NOT NULL,
  `pointsAllocated` int(11) NOT NULL,
  `leagueName` varchar(100) NOT NULL,
  PRIMARY KEY (`leagueId`),
  KEY `tournamentId_idx_league` (`tournamentId`),
  CONSTRAINT `tournamentId_fk_league` FOREIGN KEY (`tournamentId`) REFERENCES `tournament` (`tournamentId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `leagueteams`
--

DROP TABLE IF EXISTS `leagueteams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leagueteams` (
  `teamId` varchar(20) NOT NULL,
  `leagueId` int(100) NOT NULL,
  PRIMARY KEY (`teamId`,`leagueId`),
  KEY `leagueId_idx` (`leagueId`),
  CONSTRAINT `leagueId_fk_leagueTeams` FOREIGN KEY (`leagueId`) REFERENCES `league` (`leagueId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `teamId_fk_leagueTeams` FOREIGN KEY (`teamId`) REFERENCES `team` (`teamId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `logId` int(255) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `leagueId` int(100) NOT NULL,
  `points` int(11) NOT NULL,
  PRIMARY KEY (`logId`),
  KEY `leagueId_id_log` (`leagueId`),
  KEY `username_fk_log` (`username`),
  CONSTRAINT `leagueId_fk_log` FOREIGN KEY (`leagueId`) REFERENCES `league` (`leagueId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `username_fk_log` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `match`
--

DROP TABLE IF EXISTS `match`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `match` (
  `matchId` varchar(20) NOT NULL,
  `tournamentId` varchar(20) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `team1` varchar(45) NOT NULL,
  `team2` varchar(45) NOT NULL,
  `team1_score` int(11) DEFAULT NULL,
  `team2_score` int(11) DEFAULT NULL,
  PRIMARY KEY (`matchId`),
  KEY `torunamentId_idx` (`tournamentId`),
  CONSTRAINT `torunamentId_fk_match` FOREIGN KEY (`tournamentId`) REFERENCES `tournament` (`tournamentId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `matcheslog`
--

DROP TABLE IF EXISTS `matcheslog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matcheslog` (
  `logId` int(255) NOT NULL AUTO_INCREMENT,
  `team1_prediction` int(11) DEFAULT NULL,
  `team2_prediction` int(11) DEFAULT NULL,
  `doubleIt` tinyint(1) DEFAULT NULL,
  `matchId` varchar(20) NOT NULL,
  PRIMARY KEY (`logId`,`matchId`),
  KEY `matchId_fk_matcheslog_idx` (`matchId`),
  CONSTRAINT `logId_fk_matcheslog` FOREIGN KEY (`logId`) REFERENCES `log` (`logId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `matchId_fk_matcheslog` FOREIGN KEY (`matchId`) REFERENCES `match` (`matchId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `privateleague`
--

DROP TABLE IF EXISTS `privateleague`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privateleague` (
  `leagueId` int(100) NOT NULL,
  `prize` varchar(20) NOT NULL,
  `password` varchar(10) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  PRIMARY KEY (`leagueId`),
  CONSTRAINT `leagueId_fk_private` FOREIGN KEY (`leagueId`) REFERENCES `league` (`leagueId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `publicleague`
--

DROP TABLE IF EXISTS `publicleague`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publicleague` (
  `leagueId` int(100) NOT NULL,
  `prize` varchar(20) NOT NULL,
  PRIMARY KEY (`leagueId`),
  CONSTRAINT `leagueId_fk_public` FOREIGN KEY (`leagueId`) REFERENCES `league` (`leagueId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `specials`
--

DROP TABLE IF EXISTS `specials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specials` (
  `specialsId` int(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(200) NOT NULL,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY (`specialsId`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `specialslog`
--

DROP TABLE IF EXISTS `specialslog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specialslog` (
  `logid` int(255) NOT NULL AUTO_INCREMENT,
  `specialsId` int(20) NOT NULL,
  `prediction` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`logid`,`specialsId`),
  KEY `specialsId_idx` (`specialsId`),
  CONSTRAINT `logid_fk_specials` FOREIGN KEY (`logid`) REFERENCES `log` (`logId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `specialsId_fk` FOREIGN KEY (`specialsId`) REFERENCES `specials` (`specialsId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `teamId` varchar(20) NOT NULL,
  `teamName` varchar(20) NOT NULL,
  PRIMARY KEY (`teamId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tournament`
--

DROP TABLE IF EXISTS `tournament`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tournament` (
  `tournamentId` varchar(20) NOT NULL,
  `name` varchar(40) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  PRIMARY KEY (`tournamentId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `username` varchar(20) NOT NULL,
  `name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `dob` date NOT NULL,
  `country` varchar(20) NOT NULL,
  `contactNo` varchar(15) NOT NULL,
  `email` varchar(100) NOT NULL,
  `favoriteTeam` varchar(45) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-11  2:39:16
