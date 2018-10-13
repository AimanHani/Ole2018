-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema oledb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema oledb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `oledb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `oledb` ;

-- -----------------------------------------------------
-- Table `oledb`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oledb`.`User` (
  `username` VARCHAR(20) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `dob` DATE NOT NULL,
  `country` VARCHAR(20) NOT NULL,
  `contactNo` VARCHAR(10) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `favoriteTeam` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oledb`.`Tournament`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oledb`.`Tournament` (
  `tournamentId` VARCHAR(20) NOT NULL,
  `name` VARCHAR(40) NOT NULL,
  `startDate` DATE NOT NULL,
  `endDate` DATE NOT NULL,
  PRIMARY KEY (`tournamentId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oledb`.`League`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oledb`.`League` (
  `leagueId` VARCHAR(20) NOT NULL,
  `tournamentId` VARCHAR(20) NOT NULL,
  `pointsAllocated` INT NOT NULL,
  PRIMARY KEY (`leagueId`),
  INDEX `tournamentId_idx_league` (`tournamentId` ASC),
  CONSTRAINT `tournamentId_fk_league`
    FOREIGN KEY (`tournamentId`)
    REFERENCES `oledb`.`Tournament` (`tournamentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oledb`.`PublicLeague`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oledb`.`PublicLeague` (
  `leagueId` VARCHAR(20) NOT NULL,
  `leagueName` VARCHAR(20) NOT NULL,
  `prize` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`leagueId`),
  CONSTRAINT `leagueId_fk_public`
    FOREIGN KEY (`leagueId`)
    REFERENCES `oledb`.`League` (`leagueId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oledb`.`Match`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oledb`.`Match` (
  `matchId` VARCHAR(20) NOT NULL,
  `tournamentId` VARCHAR(20) NOT NULL,
  `date` DATE NOT NULL,
  `time` TIME NOT NULL,
  `team1` VARCHAR(45) NOT NULL,
  `team2` VARCHAR(45) NOT NULL,
  `team1_score` INT NULL,
  `team2_score` INT NULL,
  PRIMARY KEY (`matchId`),
  INDEX `torunamentId_idx` (`tournamentId` ASC),
  CONSTRAINT `torunamentId_fk_match`
    FOREIGN KEY (`tournamentId`)
    REFERENCES `oledb`.`Tournament` (`tournamentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oledb`.`Log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oledb`.`Log` (
  `logId` VARCHAR(45) NOT NULL,
  `username` VARCHAR(20) NULL,
  `leagueId` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`logId`),
  INDEX `leagueId_id_log` (`leagueId` ASC),
  CONSTRAINT `username_fk_log`
    FOREIGN KEY (`username`)
    REFERENCES `oledb`.`User` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `leagueId_fk_log`
    FOREIGN KEY (`leagueId`)
    REFERENCES `oledb`.`League` (`leagueId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oledb`.`MatchLog`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oledb`.`MatchLog` (
  `logId` VARCHAR(20) NOT NULL,
  `team1_prediction` INT NULL,
  `team2_prediction` INT NULL,
  `points` INT NOT NULL,
  `doubleIt` TINYINT(1) NULL,
  PRIMARY KEY (`logId`),
  CONSTRAINT `logId_fk_matchlog`
    FOREIGN KEY (`logId`)
    REFERENCES `oledb`.`Log` (`logId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oledb`.`Specials`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oledb`.`Specials` (
  `specialsId` VARCHAR(20) NOT NULL,
  `description` VARCHAR(20) NOT NULL,
  `status` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`specialsId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oledb`.`SpecialsLog`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oledb`.`SpecialsLog` (
  `logid` VARCHAR(20) NOT NULL,
  `specialsId` VARCHAR(20) NOT NULL,
  `prediction` VARCHAR(20) NULL,
  PRIMARY KEY (`logid`, `specialsId`),
  INDEX `specialsId_idx` (`specialsId` ASC),
  CONSTRAINT `logid_fk_specials`
    FOREIGN KEY (`logid`)
    REFERENCES `oledb`.`Log` (`logId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `specialsId_fk`
    FOREIGN KEY (`specialsId`)
    REFERENCES `oledb`.`Specials` (`specialsId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oledb`.`PrivateLeague`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oledb`.`PrivateLeague` (
  `leagueId` VARCHAR(20) NOT NULL,
  `leagueName` VARCHAR(20) NOT NULL,
  `prize` VARCHAR(20) NOT NULL,
  `password` VARCHAR(10) NOT NULL,
  `startDate` DATE NOT NULL,
  `endDate` DATE NOT NULL,
  PRIMARY KEY (`leagueId`),
  CONSTRAINT `leagueId_fk_private`
    FOREIGN KEY (`leagueId`)
    REFERENCES `oledb`.`League` (`leagueId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oledb`.`Team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oledb`.`Team` (
  `teamId` VARCHAR(20) NOT NULL,
  `teamName` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`teamId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oledb`.`LeagueTeams`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oledb`.`LeagueTeams` (
  `teamId` VARCHAR(20) NOT NULL,
  `leagueId` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`teamId`, `leagueId`),
  INDEX `leagueId_idx` (`leagueId` ASC),
  CONSTRAINT `teamId_fk_leagueTeams`
    FOREIGN KEY (`teamId`)
    REFERENCES `oledb`.`Team` (`teamId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `leagueId_fk_leagueTeams`
    FOREIGN KEY (`leagueId`)
    REFERENCES `oledb`.`League` (`leagueId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
