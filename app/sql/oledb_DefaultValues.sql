-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 19, 2018 at 10:13 AM
-- Server version: 5.7.11
-- PHP Version: 7.0.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `oledb`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `username` varchar(20) NOT NULL,
  `password` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES
('Ole', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `league`
--

CREATE TABLE `league` (
  `leagueId` varchar(20) NOT NULL,
  `tournamentId` varchar(20) NOT NULL,
  `pointsAllocated` int(11) NOT NULL,
  `leagueName` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `leagueteams`
--

CREATE TABLE `leagueteams` (
  `teamId` varchar(20) NOT NULL,
  `leagueId` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `log`
--

CREATE TABLE `log` (
  `logId` varchar(45) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `leagueId` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `match`
--

CREATE TABLE `match` (
  `matchId` varchar(20) NOT NULL,
  `tournamentId` varchar(20) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `team1` varchar(45) NOT NULL,
  `team2` varchar(45) NOT NULL,
  `team1_score` int(11) DEFAULT NULL,
  `team2_score` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `matchlog`
--

CREATE TABLE `matchlog` (
  `logId` varchar(20) NOT NULL,
  `team1_prediction` int(11) DEFAULT NULL,
  `team2_prediction` int(11) DEFAULT NULL,
  `points` int(11) NOT NULL,
  `doubleIt` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `privateleague`
--

CREATE TABLE `privateleague` (
  `leagueId` varchar(20) NOT NULL,
  `prize` varchar(20) NOT NULL,
  `password` varchar(10) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `publicleague`
--

CREATE TABLE `publicleague` (
  `leagueId` varchar(20) NOT NULL,
  `prize` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `specials`
--

CREATE TABLE `specials` (
  `specialsId` int(20) NOT NULL,
  `description` varchar(200) NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `specials`
--

INSERT INTO `specials` (`specialsId`, `description`, `status`) VALUES
(1, 'Top Scorer', 'N'),
(2, 'Top Assist', 'N'),
(3, 'Golden Glove', 'N'),
(4, 'Best Young Player', 'N'),
(5, 'First Red card of Tournament', 'N'),
(6, 'Best Player', 'N'),
(7, 'Winner of Competition  (team)', 'N'),
(8, 'Team with Most Number of Goals', 'F'),
(9, 'Number of Goals scored by Winner of Compt', 'N'),
(10, 'Team with Least Number of Goals Conceded', 'N'),
(11, 'Team with Most Number of Goals Conceded', 'N'),
(12, 'Team with Least Number of Goals Scored', 'N'),
(13, 'Outsider to achieve top 4', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `specialslog`
--

CREATE TABLE `specialslog` (
  `logid` varchar(20) NOT NULL,
  `specialsId` int(20) NOT NULL,
  `prediction` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `team`
--

CREATE TABLE `team` (
  `teamId` varchar(20) NOT NULL,
  `teamName` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tournament`
--

CREATE TABLE `tournament` (
  `tournamentId` varchar(20) NOT NULL,
  `name` varchar(40) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(20) NOT NULL,
  `name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `dob` date NOT NULL,
  `country` varchar(20) NOT NULL,
  `contactNo` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `favoriteTeam` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `name`, `password`, `dob`, `country`, `contactNo`, `email`, `favoriteTeam`) VALUES
('fghj', 'fghj', 'ghjk', '2018-10-03', 'Malaysia', '6789', 'ghn@gmail.com', 'Liverpool'),
('Jill', 'Jill Ma', 'jmmj78', '1991-10-08', 'Singapore', '86892781', 'jm89@gmail.com', 'Liverpool'),
('Leon', 'Leon Tan', 'lt12', '1989-10-01', 'Singapore', '98762819', 'lt67@gmail.com', 'Arsenal');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `league`
--
ALTER TABLE `league`
  ADD PRIMARY KEY (`leagueId`),
  ADD KEY `tournamentId_idx_league` (`tournamentId`);

--
-- Indexes for table `leagueteams`
--
ALTER TABLE `leagueteams`
  ADD PRIMARY KEY (`teamId`,`leagueId`),
  ADD KEY `leagueId_idx` (`leagueId`);

--
-- Indexes for table `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`logId`),
  ADD KEY `leagueId_id_log` (`leagueId`),
  ADD KEY `username_fk_log` (`username`);

--
-- Indexes for table `match`
--
ALTER TABLE `match`
  ADD PRIMARY KEY (`matchId`),
  ADD KEY `torunamentId_idx` (`tournamentId`);

--
-- Indexes for table `matchlog`
--
ALTER TABLE `matchlog`
  ADD PRIMARY KEY (`logId`);

--
-- Indexes for table `privateleague`
--
ALTER TABLE `privateleague`
  ADD PRIMARY KEY (`leagueId`);

--
-- Indexes for table `publicleague`
--
ALTER TABLE `publicleague`
  ADD PRIMARY KEY (`leagueId`);

--
-- Indexes for table `specials`
--
ALTER TABLE `specials`
  ADD PRIMARY KEY (`specialsId`);

--
-- Indexes for table `specialslog`
--
ALTER TABLE `specialslog`
  ADD PRIMARY KEY (`logid`,`specialsId`),
  ADD KEY `specialsId_idx` (`specialsId`);

--
-- Indexes for table `team`
--
ALTER TABLE `team`
  ADD PRIMARY KEY (`teamId`);

--
-- Indexes for table `tournament`
--
ALTER TABLE `tournament`
  ADD PRIMARY KEY (`tournamentId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `specials`
--
ALTER TABLE `specials`
  MODIFY `specialsId` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `specialslog`
--
ALTER TABLE `specialslog`
  MODIFY `specialsId` int(20) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `league`
--
ALTER TABLE `league`
  ADD CONSTRAINT `tournamentId_fk_league` FOREIGN KEY (`tournamentId`) REFERENCES `tournament` (`tournamentId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `leagueteams`
--
ALTER TABLE `leagueteams`
  ADD CONSTRAINT `leagueId_fk_leagueTeams` FOREIGN KEY (`leagueId`) REFERENCES `league` (`leagueId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `teamId_fk_leagueTeams` FOREIGN KEY (`teamId`) REFERENCES `team` (`teamId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `log`
--
ALTER TABLE `log`
  ADD CONSTRAINT `leagueId_fk_log` FOREIGN KEY (`leagueId`) REFERENCES `league` (`leagueId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `username_fk_log` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `match`
--
ALTER TABLE `match`
  ADD CONSTRAINT `torunamentId_fk_match` FOREIGN KEY (`tournamentId`) REFERENCES `tournament` (`tournamentId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `matchlog`
--
ALTER TABLE `matchlog`
  ADD CONSTRAINT `logId_fk_matchlog` FOREIGN KEY (`logId`) REFERENCES `log` (`logId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `privateleague`
--
ALTER TABLE `privateleague`
  ADD CONSTRAINT `leagueId_fk_private` FOREIGN KEY (`leagueId`) REFERENCES `league` (`leagueId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `publicleague`
--
ALTER TABLE `publicleague`
  ADD CONSTRAINT `leagueId_fk_public` FOREIGN KEY (`leagueId`) REFERENCES `league` (`leagueId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `specialslog`
--
ALTER TABLE `specialslog`
  ADD CONSTRAINT `logid_fk_specials` FOREIGN KEY (`logid`) REFERENCES `log` (`logId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `specialsId_fk` FOREIGN KEY (`specialsId`) REFERENCES `specials` (`specialsId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
