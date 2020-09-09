-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema orderbook
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema orderbook
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `orderbook` DEFAULT CHARACTER SET utf8 ;
USE `orderbook` ;

-- -----------------------------------------------------
-- Table `orderbook`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orderbook`.`order` (
  `orderId` INT NOT NULL AUTO_INCREMENT,
  `size` INT NOT NULL,
  `side` TINYINT NOT NULL,
  `partyId` INT NOT NULL,
  `time` DATETIME NOT NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  `offerPrice` DECIMAL(8,2) NOT NULL,
  `symbol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`orderId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `orderbook`.`transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orderbook`.`transaction` (
  `transactionId` INT NOT NULL AUTO_INCREMENT,
  `buyOrderId` INT NOT NULL,
  `sellOrderId` INT NOT NULL,
  `time` DATETIME NOT NULL,
  `finalPrice` DECIMAL(8,2) NOT NULL,
  `finalAmount` INT NOT NULL,
  `finalSymbol` VARCHAR(45) NOT NULL,
  INDEX `fk_order_has_order_order1_idx` (`sellOrderId` ASC) VISIBLE,
  INDEX `fk_order_has_order_order_idx` (`buyOrderId` ASC) VISIBLE,
  PRIMARY KEY (`transactionId`),
  CONSTRAINT `fk_order_has_order_order`
    FOREIGN KEY (`buyOrderId`)
    REFERENCES `orderbook`.`order` (`orderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_has_order_order1`
    FOREIGN KEY (`sellOrderId`)
    REFERENCES `orderbook`.`order` (`orderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
