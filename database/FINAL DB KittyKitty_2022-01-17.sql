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
) ENGINE=InnoDB AUTO_INCREMENT=232 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table kittykitty.comments: ~9 rows (approximately)
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT IGNORE INTO `comments` (`commentId`, `relatesToVideoId`, `postedByUsername`, `content`, `likes`, `dislikes`, `responseToCommentId`, `timeOfPosting`) VALUES
	(202, 1, 'xQc', '1234', 0, 0, -1, 1642010722875),
	(203, 1, 'xQc', '123', 0, 0, 202, 1642010726675),
	(219, 1, 'Sabaton', 'yeeee', 0, 0, 202, 1642012771378),
	(220, 1, 'G H', 'ye', 1, 0, 202, 1642017380511),
	(221, 1, 'HidingFox', '12345', 0, 0, 202, 1642017503230),
	(222, 1, 'HidingFox', 'I WILL RULE THE UNIVERSE', 0, 0, 202, 1642017591310),
	(223, 1, 'HidingFox', 'HAHAHA', 0, 0, 202, 1642017655614),
	(224, 1, 'HidingFox', 'bbbbbbbbbbbbbb', 0, 0, 202, 1642017688839),
	(225, 1, 'HidingFox', 'jjjjjjjjjjj', 0, 0, 202, 1642017779582);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;

-- Dumping structure for table kittykitty.dislikes
CREATE TABLE IF NOT EXISTS `dislikes` (
  `dislikeId` int unsigned NOT NULL AUTO_INCREMENT,
  `relatesToVideoId` int unsigned NOT NULL,
  `dislikedByUserId` int unsigned NOT NULL,
  `dislikedVideoId` int unsigned NOT NULL,
  `dislikedCommentId` int unsigned NOT NULL,
  PRIMARY KEY (`dislikeId`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table kittykitty.dislikes: ~0 rows (approximately)
/*!40000 ALTER TABLE `dislikes` DISABLE KEYS */;
/*!40000 ALTER TABLE `dislikes` ENABLE KEYS */;

-- Dumping structure for table kittykitty.likes
CREATE TABLE IF NOT EXISTS `likes` (
  `likeId` int unsigned NOT NULL AUTO_INCREMENT,
  `relatesToVideoId` int unsigned NOT NULL,
  `likedByUserId` int unsigned NOT NULL,
  `likedVideoId` int NOT NULL,
  `likedCommentId` int NOT NULL,
  PRIMARY KEY (`likeId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table kittykitty.likes: ~0 rows (approximately)
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;

-- Dumping structure for table kittykitty.searchhistories
CREATE TABLE IF NOT EXISTS `searchhistories` (
  `historyId` int unsigned NOT NULL AUTO_INCREMENT,
  `userId` int unsigned NOT NULL,
  `keyword` varchar(50) NOT NULL DEFAULT '',
  `time` timestamp NOT NULL,
  PRIMARY KEY (`historyId`),
  KEY `userId` (`userId`),
  CONSTRAINT `searchhistories_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table kittykitty.searchhistories: ~11 rows (approximately)
/*!40000 ALTER TABLE `searchhistories` DISABLE KEYS */;
INSERT IGNORE INTO `searchhistories` (`historyId`, `userId`, `keyword`, `time`) VALUES
	(9, 9, 'hello', '2022-01-11 17:24:34'),
	(10, 9, 'an', '2022-01-11 17:25:04'),
	(11, 9, 'matt', '2022-01-11 17:25:37'),
	(12, 9, 'mat', '2022-01-11 17:25:44'),
	(13, 9, 'haha', '2022-01-11 17:26:09'),
	(21, 8, 'total recall', '2022-01-12 11:11:43'),
	(22, 8, 'pavement', '2022-01-12 11:11:49'),
	(23, 8, 'a new query', '2022-01-12 11:12:11'),
	(24, 8, 'KiTtTy', '2022-01-12 11:12:32'),
	(25, 8, 'xQc', '2022-01-12 11:18:52'),
	(26, 8, 'a', '2022-01-17 13:57:02');
/*!40000 ALTER TABLE `searchhistories` ENABLE KEYS */;

-- Dumping structure for table kittykitty.subscribers
CREATE TABLE IF NOT EXISTS `subscribers` (
  `subscriptionId` int unsigned NOT NULL AUTO_INCREMENT,
  `subscribedToId` int unsigned NOT NULL,
  `subscriberId` int unsigned NOT NULL,
  PRIMARY KEY (`subscriptionId`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table kittykitty.subscribers: ~0 rows (approximately)
/*!40000 ALTER TABLE `subscribers` DISABLE KEYS */;
INSERT IGNORE INTO `subscribers` (`subscriptionId`, `subscribedToId`, `subscriberId`) VALUES
	(34, 6, 8);
/*!40000 ALTER TABLE `subscribers` ENABLE KEYS */;

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
	(1, 'Sabaton', '60ebf6e22afe105f975ddc810e6cac4599034f40cc25c16da279c36c2b4bcbbe', 'ha', 'https://yt3.ggpht.com/ytc/AKedOLSUZlpXjUZabhxgHrqXr_K0q2nVs14idyRSNKGLPA=s48-c-k-c0x00ffffff-no-rj', 0, 0),
	(2, 'Powerwolf Official', '60ebf6e22afe105f975ddc810e6cac4599034f40cc25c16da279c36c2b4bcbbe', 'haha', 'https://yt3.ggpht.com/ytc/AKedOLTDCjwbnE1ormCiJYuAOMIpW6gRl-8w50-D23CZ=s48-c-k-c0x00ffffff-no-rj', 0, 0),
	(3, 'HidingFox', '60ebf6e22afe105f975ddc810e6cac4599034f40cc25c16da279c36c2b4bcbbe', 'haha', 'https://yt3.ggpht.com/ytc/AKedOLTSEQWar7EIJ7k2CX0hGOhIqf-954jAHiSy9hCr8A=s48-c-k-c0x00ffffff-no-rj', 0, 0),
	(4, 'G H', '60ebf6e22afe105f975ddc810e6cac4599034f40cc25c16da279c36c2b4bcbbe', 'hahaha', 'https://yt3.ggpht.com/ytc/AKedOLTKlJikftoRhKZ-EwgcIlEdQcUgAXj_XggAtq4R0g=s48-c-k-c0x00ffffff-no-rj', 0, 0),
	(5, 'TheAnarchistMuffin', 'af52ad9b053f136f52d275a4f051246c2576b4a35df447c87b1b97ab9ca89758', 'hehehe', 'https://yt3.ggpht.com/ytc/AKedOLTx9L6L_q3b0tjlsuOvAGOBCy47WMsE68PiUZS6=s48-c-k-c0x00ffffff-no-rj', 0, 0),
	(6, 'Loic Suberville', '56f120bc2eb5d7cf1767746511ba7aae1f717331e6228da1476c1227918952a6', 'ererererr', 'https://yt3.ggpht.com/ytc/AKedOLQO5N2joqcK7nrLeATRMV8VHtI1VPrmZList7ixaA=s48-c-k-c0x00ffffff-no-rj', 1, 0),
	(7, 'dillbert', 'cd2ef41c4e805f5463df06adbc92b0a28dc1cd76b795981800fba80ce0d687f5', 'hqhqhq', 'https://yt3.ggpht.com/ytc/AKedOLRY7T-jUCV0GL55NiFLDNz4Wog14R59EQqyKTOj=s48-c-k-c0x00ffffff-no-rj', 0, 0),
	(8, 'xQc', '60ebf6e22afe105f975ddc810e6cac4599034f40cc25c16da279c36c2b4bcbbe', 'aaaa', 'data:image/jpeg;base64,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', 0, 11),
	(9, '777PROJEKT', 'df8a535218c4af5b545b6ba8e6fddb945d7b38c7fa4c1e818e289b75100f0f97', 'matt', 'https://yt3.ggpht.com/VWDbpQL7ntDOtzvZyTGcgTdR3b-Z4kJ6wwnkYmE5lKkrkt5by5hvqlILVxLlkmDs85PvxHtXF1E=s48-c-k-c0x00ffffff-no-rj', 0, 0);
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table kittykitty.videos: ~11 rows (approximately)
/*!40000 ALTER TABLE `videos` DISABLE KEYS */;
INSERT IGNORE INTO `videos` (`videoId`, `userId`, `uploadDate`, `videoURL`, `title`, `description`, `views`, `postedByUsername`, `likes`, `dislikes`, `stars`) VALUES
	(1, 1, '2022-01-06 12:25:33', 'https://www.youtube.com/watch?v=axsnM1kH99c', 'hello', 'No u', 152, 'Sabaton', 0, 0, 0),
	(2, 2, '2022-01-06 12:25:33', 'https://www.youtube.com/watch?v=_RalKhxRBC8', 'Sanctified with Dynamite', 'Powerwolf Summer 2021', 0, 'Powerwolf Official', 0, 0, 0),
	(3, 3, '2022-01-06 12:25:33', 'https://www.youtube.com/watch?v=3_0f63HjTAQ', 'Speedrunning Big Iron', 'Biiig iiiiron', 0, 'HidingFox', 0, 0, 0),
	(15, 4, '2022-01-06 12:25:33', 'https://www.youtube.com/watch?v=sS5Zf5NYseo', 'Wasteland 3 - Down In The Valley To Pray (Includes Lyrics)', 'A great song from the Iconic Wasteland 3', 14, 'G H', 0, 0, 0),
	(16, 5, '2022-01-06 13:08:56', 'https://www.youtube.com/watch?v=wy-sVTaZRPk', 'War Cant of Mars (Warhammer 40k)', 'A pretty cool song from WH40k', 59, 'TheAnarchistMuffin', 0, 0, 0),
	(17, 6, '2022-01-06 12:25:33', 'https://www.youtube.com/watch?v=A7QT7_aQrG0', 'How The Pandemic Started', 'How the pandemic truly started', 60, 'Loic Suberville', 0, 0, 0),
	(19, 7, '2022-01-06 12:25:33', 'https://www.youtube.com/watch?v=qoWCB8AUiP4', 'Goldeneye 64 - Watch [Pause Music] - UNCOMPRESSED (Remake 777PROJEKT Original)', 'A remake of Goldeneye Soundtrack', 78, 'matt', 0, 0, 0),
	(20, 8, '2022-01-12 10:59:54', 'https://www.youtube.com/watch?v=3MRSLSQdelI', 'Attack of the Dead Men', 'My video 1', 3, 'xQc', 0, 0, 0),
	(21, 8, '2022-01-12 10:59:54', 'https://www.youtube.com/watch?v=3MRSLSQdelI', 'Attack of the Dead Yoshis', 'My video 2', 4, 'xQc', 0, 0, 0),
	(22, 8, '2022-01-12 10:59:54', 'https://www.youtube.com/watch?v=3MRSLSQdelI', 'Attack of the Dead Dogs', 'My video 3', 1, 'xQc', 0, 0, 0),
	(23, 8, '2022-01-12 10:59:54', 'https://www.youtube.com/watch?v=3MRSLSQdelI', 'Attack of the Dead Cats', 'My video 4', 20, 'xQc', 0, 0, 0);
/*!40000 ALTER TABLE `videos` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
