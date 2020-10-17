-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema repair_agency_test
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema repair_agency_test
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `repair_agency_test` DEFAULT CHARACTER SET utf8 ;
USE `repair_agency_test` ;

-- -----------------------------------------------------
-- Table `repair_agency_test`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `repair_agency_test`.`account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `name` VARCHAR(30) NOT NULL,
  `surname` VARCHAR(35) NOT NULL,
  `role` ENUM('ADMIN', 'CUSTOMER', 'MASTER') NOT NULL DEFAULT 'CUSTOMER',
  `phone` VARCHAR(45) NOT NULL,
  `locale` ENUM('RU', 'EN') NOT NULL DEFAULT 'EN',
  `balance` DECIMAL(45) NULL DEFAULT 0,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `repair_agency_test`.`booking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `repair_agency_test`.`booking` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `price` DECIMAL(45) NULL,
  `customer_comment` TEXT(1024) NULL,
  `device` VARCHAR(60) NULL,
  `creating_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `master_account_id` INT NOT NULL DEFAULT 2,
  `order_status` ENUM('WAITING_FOR_PROCESSING', 'WAITING_FOR_PAYMENT', 'PAID', 'CANCELED', 'IN_WORK', 'READY_TO_ISSUE', 'COMPLETED') NOT NULL DEFAULT 'WAITING_FOR_PROCESSING',
  `user_account_id` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_request_account2_idx` (`master_account_id` ASC) VISIBLE,
  INDEX `fk_order_account1_idx` (`user_account_id` ASC) VISIBLE,
  CONSTRAINT `fk_booking_account2`
    FOREIGN KEY (`master_account_id`)
    REFERENCES `repair_agency_test`.`account` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_booking_account1`
    FOREIGN KEY (`user_account_id`)
    REFERENCES `repair_agency_test`.`account` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `repair_agency_test`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `repair_agency_test`.`payment` (
  `booking_id` INT NOT NULL,
  `payment_value` DECIMAL(45) NOT NULL DEFAULT 0,
  `creating_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `account_id` INT NOT NULL,
  PRIMARY KEY (`booking_id`),
  INDEX `fk_payment_account1_idx` (`account_id` ASC) VISIBLE,
  INDEX `fk_payment_booking1_idx` (`booking_id` ASC) VISIBLE,
  UNIQUE INDEX `booking_id_UNIQUE` (`booking_id` ASC) VISIBLE,
  CONSTRAINT `fk_payment_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `repair_agency_test`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_payment_booking1`
    FOREIGN KEY (`booking_id`)
    REFERENCES `repair_agency_test`.`booking` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `repair_agency_test`.`feedback`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `repair_agency_test`.`feedback` (
  `booking_id` INT NOT NULL,
  `feedback_text` TEXT(500) NULL,
  `creating_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `rate` ENUM('GREAT', 'NORMAL', 'BAD') NULL,
  PRIMARY KEY (`booking_id`),
  UNIQUE INDEX `booking_id_UNIQUE` (`booking_id` ASC) VISIBLE,
  CONSTRAINT `fk_feedback_booking1`
    FOREIGN KEY (`booking_id`)
    REFERENCES `repair_agency_test`.`booking` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `repair_agency_test`.`service_en`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `repair_agency_test`.`service_en` (
  `service_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`service_id`),
  UNIQUE INDEX `id_UNIQUE` (`service_id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `repair_agency_test`.`booking_service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `repair_agency_test`.`booking_service` (
  `booking_id` INT NOT NULL,
  `service_id` INT NOT NULL,
  PRIMARY KEY (`booking_id`, `service_id`),
  INDEX `fk_order_has_service_service1_idx` (`service_id` ASC) VISIBLE,
  INDEX `fk_order_has_service_order1_idx` (`booking_id` ASC) VISIBLE,
  CONSTRAINT `fk_booking_has_service_order1`
    FOREIGN KEY (`booking_id`)
    REFERENCES `repair_agency_test`.`booking` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_booking_has_service_service1`
    FOREIGN KEY (`service_id`)
    REFERENCES `repair_agency_test`.`service_en` (`service_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `repair_agency_test`.`service_ru`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `repair_agency_test`.`service_ru` (
  `service_id` INT NOT NULL,
  `name` VARCHAR(80) NULL,
  PRIMARY KEY (`service_id`),
  CONSTRAINT `fk_table1_service1`
    FOREIGN KEY (`service_id`)
    REFERENCES `repair_agency_test`.`service_en` (`service_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `repair_agency_test`.`account`
-- -----------------------------------------------------
START TRANSACTION;
USE `repair_agency_test`;
INSERT INTO `repair_agency_test`.`account` (`id`, `email`, `password`, `name`, `surname`, `role`, `phone`, `locale`, `balance`) VALUES (1, 'example@gmail.com1', 'adminPass', 'Admin', 'Adminov', '1', '08604653241', 'EN', 0);
INSERT INTO `repair_agency_test`.`account` (`id`, `email`, `password`, `name`, `surname`, `role`, `phone`, `locale`, `balance`) VALUES (2, 'example@gmail.com2', 'masterPass', 'Master', 'Masterov', '3', '08604653241', 'EN', 0);
INSERT INTO `repair_agency_test`.`account` (`id`, `email`, `password`, `name`, `surname`, `role`, `phone`, `locale`, `balance`) VALUES (3, 'example@gmail.com3', 'customerPass', 'Customer', 'Customerov', '2', '08604653241', 'RU', 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `repair_agency_test`.`booking`
-- -----------------------------------------------------
START TRANSACTION;
USE `repair_agency_test`;
INSERT INTO `repair_agency_test`.`booking` (`id`, `price`, `customer_comment`, `device`, `creating_time`, `master_account_id`, `order_status`, `user_account_id`) VALUES (1, 5, '\'this is comment\'', 'device1', NULL, 2, '6', 3);
INSERT INTO `repair_agency_test`.`booking` (`id`, `price`, `customer_comment`, `device`, `creating_time`, `master_account_id`, `order_status`, `user_account_id`) VALUES (2, 100, '\'comment comment\'', 'device2', NULL, 2, '5', 3);
INSERT INTO `repair_agency_test`.`booking` (`id`, `price`, `customer_comment`, `device`, `creating_time`, `master_account_id`, `order_status`, `user_account_id`) VALUES (3, 200, '\'comment comment\'', 'device3', NULL, 2, '4', 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `repair_agency_test`.`payment`
-- -----------------------------------------------------
START TRANSACTION;
USE `repair_agency_test`;
INSERT INTO `repair_agency_test`.`payment` (`booking_id`, `payment_value`, `creating_time`, `account_id`) VALUES (1, 24, NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `repair_agency_test`.`feedback`
-- -----------------------------------------------------
START TRANSACTION;
USE `repair_agency_test`;
INSERT INTO `repair_agency_test`.`feedback` (`booking_id`, `feedback_text`, `creating_time`, `rate`) VALUES (1, 'My Feedback', NULL, 'GREAT');

COMMIT;


-- -----------------------------------------------------
-- Data for table `repair_agency_test`.`service_en`
-- -----------------------------------------------------
START TRANSACTION;
USE `repair_agency_test`;
INSERT INTO `repair_agency_test`.`service_en` (`service_id`, `name`) VALUES (1, 'Diagnostics');
INSERT INTO `repair_agency_test`.`service_en` (`service_id`, `name`) VALUES (2, 'Video card repair');

COMMIT;


-- -----------------------------------------------------
-- Data for table `repair_agency_test`.`booking_service`
-- -----------------------------------------------------
START TRANSACTION;
USE `repair_agency_test`;
INSERT INTO `repair_agency_test`.`booking_service` (`booking_id`, `service_id`) VALUES (1, 1);
INSERT INTO `repair_agency_test`.`booking_service` (`booking_id`, `service_id`) VALUES (1, 2);
INSERT INTO `repair_agency_test`.`booking_service` (`booking_id`, `service_id`) VALUES (2, 2);
INSERT INTO `repair_agency_test`.`booking_service` (`booking_id`, `service_id`) VALUES (3, 1);
INSERT INTO `repair_agency_test`.`booking_service` (`booking_id`, `service_id`) VALUES (3, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `repair_agency_test`.`service_ru`
-- -----------------------------------------------------
START TRANSACTION;
USE `repair_agency_test`;
INSERT INTO `repair_agency_test`.`service_ru` (`service_id`, `name`) VALUES (1, 'Диагностика');
INSERT INTO `repair_agency_test`.`service_ru` (`service_id`, `name`) VALUES (2, 'Замена видеокарты');

COMMIT;

