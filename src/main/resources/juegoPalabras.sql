-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema juegoPalabras
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema juegoPalabras
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `juegoPalabras` DEFAULT CHARACTER SET utf8 ;
USE `juegoPalabras` ;

-- -----------------------------------------------------
-- Table `juegoPalabras`.`Equipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `juegoPalabras`.`Equipo` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `logo` VARCHAR(50) NOT NULL,
  `fecha_creacion` DATE NOT NULL,
  `fecha_modificacion` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `juegoPalabras`.`Jugador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `juegoPalabras`.`Jugador` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_equipo` INT UNSIGNED NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `correo` VARCHAR(50) NOT NULL,
  `clave` VARCHAR(50) NOT NULL,
  `avatar` VARCHAR(100) NOT NULL,
  `rol` VARCHAR(50) NOT NULL,
  `fecha_creacion` DATE NOT NULL,
  `fecha_modificacion` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Jugador_Equipo1_idx` (`id_equipo` ASC) VISIBLE,
  UNIQUE INDEX `correo_UNIQUE` (`correo` ASC) VISIBLE,
  CONSTRAINT `fk_Jugador_Equipo1`
    FOREIGN KEY (`id_equipo`)
    REFERENCES `juegoPalabras`.`Equipo` (`id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `juegoPalabras`.`Juego`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `juegoPalabras`.`Juego` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `dificultad` ENUM("FACIL", "INTERMEDIO", "DIFICIL") NOT NULL,
  `instrucciones` VARCHAR(200) NOT NULL,
  `fecha_creacion` DATE NOT NULL,
  `fecha_modificacion` DATE NOT NULL,
  `intentos` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `juegoPalabras`.`Partida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `juegoPalabras`.`Partida` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_jugador` INT UNSIGNED NOT NULL,
  `id_juego` INT UNSIGNED NOT NULL,
  `puntos` INT NOT NULL,
  `intentos` INT NOT NULL,
  `palabra` VARCHAR(30) NOT NULL,
  `fecha` DATE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Jugador_has_Juego_Juego1_idx` (`id_juego` ASC) VISIBLE,
  INDEX `fk_Jugador_has_Juego_Jugador_idx` (`id_jugador` ASC) VISIBLE,
  CONSTRAINT `fk_Jugador_has_Juego_Jugador`
    FOREIGN KEY (`id_jugador`)
    REFERENCES `juegoPalabras`.`Jugador` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Jugador_has_Juego_Juego1`
    FOREIGN KEY (`id_juego`)
    REFERENCES `juegoPalabras`.`Juego` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- Inserción de datos para la tabla Equipo
INSERT INTO `juegoPalabras`.`Equipo` (`nombre`, `logo`, `fecha_creacion`, `fecha_modificacion`) VALUES
('Los Tigres', 'tigres.png', NOW(), NOW()),
('Los Leones', 'leones.png', NOW(), NOW()),
('Las Águilas', 'aguilas.png', NOW(), NOW()),
('Los Lobos', 'lobos.png', NOW(), NOW());

-- Inserción de datos para la tabla Jugador
INSERT INTO `juegoPalabras`.`Jugador` (`id_equipo`, `nombre`, `correo`, `clave`, `avatar`, `rol`, `fecha_creacion`, `fecha_modificacion`) VALUES
(1, 'Juan Perez', 'juan.perez@gmail.com', 'clave123', 'avatar1.png', 'USER', NOW(), NOW()),
(2, 'María Rodriguez', 'maria.rodriguez@gmail.com', 'clave456', 'avatar2.png', 'USER', NOW(), NOW()),
(3, 'Carlos Sanchez', 'carlos.sanchez@gmail.com', 'clave789', 'avatar3.png', 'USER', NOW(), NOW()),
(4, 'Ana Garcia', 'ana.garcia@gmail.com', 'clave101', 'avatar4.png', 'USER', NOW(), NOW());

-- Inserción de datos para la tabla Juego
INSERT INTO `juegoPalabras`.`Juego` (`nombre`, `dificultad`, `instrucciones`, `fecha_creacion`, `fecha_modificacion`, `intentos`) VALUES
('Juego 1', 'FACIL', 'Adivina la palabra con las pistas dadas', NOW(), NOW(), 3),
('Juego 2', 'INTERMEDIO', 'Completa las palabras faltantes', NOW(), NOW(), 3),
('Juego 3', 'DIFICIL', 'Encuentra la palabra oculta en la sopa de letras', NOW(), NOW(), 3);

-- Inserción de datos para la tabla Partida
INSERT INTO `juegoPalabras`.`Partida` (`id_jugador`, `id_juego`, `puntos`, `intentos`, `palabra`, `fecha`) VALUES
(1, 1, 10, 3, 'Gato', NOW()),
(2, 1, 8, 3, 'Perro', NOW()),
(3, 2, 9, 3, 'Casa', NOW()),
(4, 3, 7, 3, 'Universidad', NOW());