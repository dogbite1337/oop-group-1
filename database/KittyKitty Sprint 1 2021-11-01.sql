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
  `timeOfPosting` bigint unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`commentId`),
  KEY `relatesToVideoId` (`relatesToVideoId`),
  CONSTRAINT `relatesToVideoId` FOREIGN KEY (`relatesToVideoId`) REFERENCES `videos` (`videoId`)
) ENGINE=InnoDB AUTO_INCREMENT=174 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table kittykitty.comments: ~0 rows (approximately)
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT IGNORE INTO `comments` (`commentId`, `relatesToVideoId`, `postedByUsername`, `content`, `likes`, `dislikes`, `responseToCommentId`, `timeOfPosting`) VALUES
	(171, 16, 'xQc', 'qqq', 1, 0, -1, 1641920212749),
	(172, 16, 'xQc', 'hhhh', 0, 1, 171, 1641920251517),
	(173, 16, 'hah', 'i am a burger', 0, 0, -1, 1641921316248);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;

-- Dumping structure for table kittykitty.searchhistories
CREATE TABLE IF NOT EXISTS `searchhistories` (
  `historyId` int unsigned NOT NULL AUTO_INCREMENT,
  `userId` int unsigned NOT NULL,
  `keyword` varchar(50) NOT NULL DEFAULT '',
  `time` timestamp NOT NULL,
  PRIMARY KEY (`historyId`),
  KEY `userId` (`userId`),
  CONSTRAINT `searchhistories_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table kittykitty.searchhistories: ~5 rows (approximately)
/*!40000 ALTER TABLE `searchhistories` DISABLE KEYS */;
INSERT IGNORE INTO `searchhistories` (`historyId`, `userId`, `keyword`, `time`) VALUES
	(9, 9, 'hello', '2022-01-11 17:24:34'),
	(10, 9, 'an', '2022-01-11 17:25:04'),
	(11, 9, 'matt', '2022-01-11 17:25:37'),
	(12, 9, 'mat', '2022-01-11 17:25:44'),
	(13, 9, 'haha', '2022-01-11 17:26:09');
/*!40000 ALTER TABLE `searchhistories` ENABLE KEYS */;

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table kittykitty.users: ~9 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT IGNORE INTO `users` (`userId`, `username`, `password`, `description`, `profileURL`, `subscribers`, `videosPosted`) VALUES
	(1, 'guy', '60ebf6e22afe105f975ddc810e6cac4599034f40cc25c16da279c36c2b4bcbbe', 'ha', '', 0, 0),
	(2, 'haha', '60ebf6e22afe105f975ddc810e6cac4599034f40cc25c16da279c36c2b4bcbbe', 'haha', 'https://www.bora.com/fileadmin/website_content/Erleben/Cooking/55_Team_Edition/Rezeptbilder/55_TeamEdition_Canada_Halloumi-Burger.jpg', 0, 0),
	(3, 'hah', '60ebf6e22afe105f975ddc810e6cac4599034f40cc25c16da279c36c2b4bcbbe', 'haha', 'https://www.bora.com/fileadmin/website_content/Erleben/Cooking/55_Team_Edition/Rezeptbilder/55_TeamEdition_Canada_Halloumi-Burger.jpg', 0, 0),
	(4, 'hahahahahaha', '60ebf6e22afe105f975ddc810e6cac4599034f40cc25c16da279c36c2b4bcbbe', 'hahaha', 'https://www.bora.com/fileadmin/website_content/Erleben/Cooking/55_Team_Edition/Rezeptbilder/55_TeamEdition_Canada_Halloumi-Burger.jpg', 0, 0),
	(5, 'heheh', 'af52ad9b053f136f52d275a4f051246c2576b4a35df447c87b1b97ab9ca89758', 'hehehe', 'https://www.bora.com/fileadmin/website_content/Erleben/Cooking/55_Team_Edition/Rezeptbilder/55_TeamEdition_Canada_Halloumi-Burger.jpg', 0, 0),
	(6, 'errr', '56f120bc2eb5d7cf1767746511ba7aae1f717331e6228da1476c1227918952a6', 'ererererr', 'https://www.bora.com/fileadmin/website_content/Erleben/Cooking/55_Team_Edition/Rezeptbilder/55_TeamEdition_Canada_Halloumi-Burger.jpg', 0, 0),
	(7, 'hqqqqqqqq', 'cd2ef41c4e805f5463df06adbc92b0a28dc1cd76b795981800fba80ce0d687f5', 'hqhqhq', 'https://www.bora.com/fileadmin/website_content/Erleben/Cooking/55_Team_Edition/Rezeptbilder/55_TeamEdition_Canada_Halloumi-Burger.jpg', 0, 0),
	(8, 'xQc', '60ebf6e22afe105f975ddc810e6cac4599034f40cc25c16da279c36c2b4bcbbe', 'ha', 'https://www.ginx.tv/uploads2/Streamers___Twitch/xqc/xQc-twitch-streamer.jpg?ezimgfmt=ng%3Awebp%2Fngcb5%2Frs%3Adevice%2Frscb5-2', 532000, 10),
	(9, 'matt', 'df8a535218c4af5b545b6ba8e6fddb945d7b38c7fa4c1e818e289b75100f0f97', 'matt', 'https://miro.medium.com/max/1200/1*mk1-6aYaf_Bes1E3Imhc0A.jpeg', 0, 0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Dumping structure for table kittykitty.videos
CREATE TABLE IF NOT EXISTS `videos` (
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table kittykitty.videos: ~0 rows (approximately)
/*!40000 ALTER TABLE `videos` DISABLE KEYS */;
INSERT IGNORE INTO `videos` (`videoId`, `userId`, `uploadDate`, `videoURL`, `title`, `description`, `views`, `postedByUsername`, `likes`, `dislikes`, `stars`) VALUES
	(1, 9, '2022-01-06 12:25:33', 'https://www.youtube.com/watch?v=eSZtWP4hex0', 'another', 'MY DESC', 0, 'matt', 26, 5, 0),
	(2, 9, '2022-01-06 12:25:33', 'https://www.youtube.com/watch?v=eSZtWP4hex0', 'another', 'MY DESC', 0, 'matt', 26, 5, 0),
	(3, 9, '2022-01-06 12:25:33', 'https://www.youtube.com/watch?v=eSZtWP4hex0', 'another', 'MY DESC', 0, 'matt', 26, 5, 0),
	(15, 9, '2022-01-06 12:25:33', 'https://www.youtube.com/watch?v=eSZtWP4hex0', 'another', 'another', 10, 'matt', 0, 0, 0),
	(16, 9, '2022-01-06 13:08:56', 'https://www.youtube.com/watch?v=Y8sUrBaUxbo', 'LOTR', 'Iz najz', 32, 'matt', 1, 1, 0),
	(17, 9, '2022-01-06 12:25:33', 'https://www.youtube.com/watch?v=eSZtWP4hex0', 'another', 'MY DESC', 0, 'matt', 26, 5, 0),
	(18, 9, '2022-01-06 12:25:33', 'https://www.youtube.com/watch?v=eSZtWP4hex0', 'another', 'MY DESC', 1, 'matt', 26, 5, 0),
	(19, 9, '2022-01-06 12:25:33', 'https://www.youtube.com/watch?v=eSZtWP4hex0', 'another', 'MY DESC', 0, 'matt', 26, 5, 0);
/*!40000 ALTER TABLE `videos` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
