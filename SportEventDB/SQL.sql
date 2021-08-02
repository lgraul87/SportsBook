-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.5.4-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para sportevent
CREATE DATABASE IF NOT EXISTS `sportevent` /*!40100 DEFAULT CHARACTER SET armscii8 COLLATE armscii8_bin */;
USE `sportevent`;

-- Volcando estructura para tabla sportevent.sport_event
CREATE TABLE IF NOT EXISTS `sport_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `collaborators` varchar(90) COLLATE armscii8_bin NOT NULL,
  `date` datetime(6) NOT NULL,
  `location` varchar(90) COLLATE armscii8_bin NOT NULL,
  `name` varchar(90) COLLATE armscii8_bin NOT NULL,
  `participants` varchar(90) COLLATE armscii8_bin NOT NULL,
  `score` varchar(255) COLLATE armscii8_bin NOT NULL,
  `time` varchar(90) COLLATE armscii8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- La exportación de datos fue deseleccionada.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
