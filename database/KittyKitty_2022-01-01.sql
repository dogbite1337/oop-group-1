-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.26 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for kittykitty
CREATE DATABASE IF NOT EXISTS `kittykitty` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `kittykitty`;

-- Dumping structure for table kittykitty.comments
CREATE TABLE IF NOT EXISTS `comments` (
  `commentId` int unsigned NOT NULL AUTO_INCREMENT,
  `relatesToVideoId` int unsigned NOT NULL,
  `postedByUsername` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `content` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `likes` int unsigned NOT NULL DEFAULT '0',
  `dislikes` int unsigned NOT NULL DEFAULT '0',
  `responseToCommentId` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`commentId`),
  KEY `relatesToVideoId` (`relatesToVideoId`),
  CONSTRAINT `relatesToVideoId` FOREIGN KEY (`relatesToVideoId`) REFERENCES `videos` (`videoId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table kittykitty.comments: ~0 rows (approximately)
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;

-- Dumping structure for table kittykitty.users
CREATE TABLE IF NOT EXISTS `users` (
  `userId` int unsigned NOT NULL AUTO_INCREMENT,
  `username` text NOT NULL,
  `password` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` text,
  `profileURL` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `subscribers` int unsigned NOT NULL DEFAULT '0',
  `videosPosted` int unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table kittykitty.users: ~8 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT IGNORE INTO `users` (`userId`, `username`, `password`, `description`, `profileURL`, `subscribers`, `videosPosted`) VALUES
	(1, 'guy', '60ebf6e22afe105f975ddc810e6cac4599034f40cc25c16da279c36c2b4bcbbe', 'ha', '', 0, 0),
	(2, 'haha', '60ebf6e22afe105f975ddc810e6cac4599034f40cc25c16da279c36c2b4bcbbe', 'haha', 'https://www.bora.com/fileadmin/website_content/Erleben/Cooking/55_Team_Edition/Rezeptbilder/55_TeamEdition_Canada_Halloumi-Burger.jpg', 0, 0),
	(3, 'hah', '60ebf6e22afe105f975ddc810e6cac4599034f40cc25c16da279c36c2b4bcbbe', 'haha', 'https://www.bora.com/fileadmin/website_content/Erleben/Cooking/55_Team_Edition/Rezeptbilder/55_TeamEdition_Canada_Halloumi-Burger.jpg', 0, 0),
	(4, 'hahahahahaha', '60ebf6e22afe105f975ddc810e6cac4599034f40cc25c16da279c36c2b4bcbbe', 'hahaha', 'https://www.bora.com/fileadmin/website_content/Erleben/Cooking/55_Team_Edition/Rezeptbilder/55_TeamEdition_Canada_Halloumi-Burger.jpg', 0, 0),
	(5, 'heheh', 'af52ad9b053f136f52d275a4f051246c2576b4a35df447c87b1b97ab9ca89758', 'hehehe', 'https://www.bora.com/fileadmin/website_content/Erleben/Cooking/55_Team_Edition/Rezeptbilder/55_TeamEdition_Canada_Halloumi-Burger.jpg', 0, 0),
	(6, 'errr', '56f120bc2eb5d7cf1767746511ba7aae1f717331e6228da1476c1227918952a6', 'ererererr', 'https://www.bora.com/fileadmin/website_content/Erleben/Cooking/55_Team_Edition/Rezeptbilder/55_TeamEdition_Canada_Halloumi-Burger.jpg', 0, 0),
	(7, 'hqqqqqqqq', 'cd2ef41c4e805f5463df06adbc92b0a28dc1cd76b795981800fba80ce0d687f5', 'hqhqhq', 'https://www.bora.com/fileadmin/website_content/Erleben/Cooking/55_Team_Edition/Rezeptbilder/55_TeamEdition_Canada_Halloumi-Burger.jpg', 0, 0),
	(8, 'xQc', '60ebf6e22afe105f975ddc810e6cac4599034f40cc25c16da279c36c2b4bcbbe', 'ha', 'https://www.ginx.tv/uploads2/Streamers___Twitch/xqc/xQc-twitch-streamer.jpg?ezimgfmt=ng%3Awebp%2Fngcb5%2Frs%3Adevice%2Frscb5-2', 532000, 10);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Dumping structure for table kittykitty.videos
CREATE TABLE IF NOT EXISTS `videos` (
  `videoId` int unsigned NOT NULL AUTO_INCREMENT,
  `userId` int unsigned NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table kittykitty.videos: ~10 rows (approximately)
/*!40000 ALTER TABLE `videos` DISABLE KEYS */;
INSERT IGNORE INTO `videos` (`videoId`, `userId`, `videoURL`, `title`, `description`, `views`, `postedByUsername`, `likes`, `dislikes`, `stars`) VALUES
	(2, 1, 'test', 'hah', 'yeah', 0, 'guy', 0, 0, 0),
	(4, 5, 'ye', 'HADOKEN', 'wwwwwww', 0, 'heheh', 0, 0, 0),
	(5, 2, 'qwerty', 'hatter', 'aaaaaaaaa', 0, 'haha', 0, 0, 0),
	(6, 3, 'uuuuuuuu', 'tttttt', 'bbbbb', 0, 'hah', 0, 0, 0),
	(7, 8, 'https://www.youtube.com/watch?v=1SbjK0EPSsM', 'xQc talks about the meaning of "juice"', 'ha', 5655123, 'xQc', 19635, 1845, 635),
	(8, 8, 'ha', 'ha', 'ha', 0, 'xQc', 0, 0, 0),
	(9, 8, 'ha', 'ha', 'ha', 0, 'xQc', 0, 0, 0),
	(10, 8, 'ha', 'ha', 'ha', 0, 'xQc', 0, 0, 0),
	(11, 8, 'ha', 'ha', 'ha', 0, 'xQc', 0, 0, 0),
	(12, 8, 'ha', 'ha', 'ha', 0, 'xQc', 0, 0, 0);
/*!40000 ALTER TABLE `videos` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
