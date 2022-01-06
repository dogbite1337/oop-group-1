# ************************************************************
# Sequel Ace SQL dump
# Version 20021
#
# https://sequel-ace.com/
# https://github.com/Sequel-Ace/Sequel-Ace
#
# Host: 127.0.0.1 (MySQL 8.0.26)
# Database: KittyKitty
# Generation Time: 2022-01-06 11:27:09 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE='NO_AUTO_VALUE_ON_ZERO', SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table videos
# ------------------------------------------------------------

DROP TABLE IF EXISTS `videos`;

CREATE TABLE `videos` (
  `videoId` int unsigned NOT NULL AUTO_INCREMENT,
  `userId` int unsigned NOT NULL,
  `uploadDate` datetime NOT NULL,
  `videoURL` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `title` text NOT NULL,
  `description` text,
  `views` int unsigned NOT NULL DEFAULT '0',
  `postedByUsername` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `likes` int unsigned NOT NULL DEFAULT '0',
  `dislikes` int unsigned NOT NULL DEFAULT '0',
  `stars` int unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`videoId`) USING BTREE,
  KEY `userId` (`userId`) USING BTREE,
  CONSTRAINT `videos_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `videos` WRITE;
/*!40000 ALTER TABLE `videos` DISABLE KEYS */;

INSERT INTO `videos` (`videoId`, `userId`, `uploadDate`, `videoURL`, `title`, `description`, `views`, `postedByUsername`, `likes`, `dislikes`, `stars`)
VALUES
	(15,9,'2022-01-06 12:25:33','https://www.youtube.com/watch?v=eSZtWP4hex0','another','another',0,'matt',0,0,0);

/*!40000 ALTER TABLE `videos` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
