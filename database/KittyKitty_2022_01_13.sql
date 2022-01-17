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

-- Dumping data for table kittykitty.comments: ~8 rows (approximately)
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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table kittykitty.searchhistories: ~11 rows (approximately)
/*!40000 ALTER TABLE `searchhistories` DISABLE KEYS */;
INSERT IGNORE INTO `searchhistories` (`historyId`, `userId`, `keyword`, `time`) VALUES
	(9, 9, 'hello', '2022-01-11 17:24:34'),
	(10, 9, 'an', '2022-01-11 17:25:04'),
	(11, 9, 'matt', '2022-01-11 17:25:37'),
	(12, 9, 'mat', '2022-01-11 17:25:44'),
	(13, 9, 'haha', '2022-01-11 17:26:09'),
	(20, 8, 'dog', '2022-01-12 11:11:26'),
	(21, 8, 'total recall', '2022-01-12 11:11:43'),
	(22, 8, 'pavement', '2022-01-12 11:11:49'),
	(23, 8, 'a new query', '2022-01-12 11:12:11'),
	(24, 8, 'KiTtTy', '2022-01-12 11:12:32'),
	(25, 8, 'xQc', '2022-01-12 11:18:52');
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
	(1, 'Sabaton', '60ebf6e22afe105f975ddc810e6cac4599034f40cc25c16da279c36c2b4bcbbe', 'ha', 'https://yt3.ggpht.com/ytc/AKedOLSUZlpXjUZabhxgHrqXr_K0q2nVs14idyRSNKGLPA=s48-c-k-c0x00ffffff-no-rj', 0, 0),
	(2, 'Powerwolf Official', '60ebf6e22afe105f975ddc810e6cac4599034f40cc25c16da279c36c2b4bcbbe', 'haha', 'https://yt3.ggpht.com/ytc/AKedOLTDCjwbnE1ormCiJYuAOMIpW6gRl-8w50-D23CZ=s48-c-k-c0x00ffffff-no-rj', 0, 0),
	(3, 'HidingFox', '60ebf6e22afe105f975ddc810e6cac4599034f40cc25c16da279c36c2b4bcbbe', 'haha', 'https://yt3.ggpht.com/ytc/AKedOLTSEQWar7EIJ7k2CX0hGOhIqf-954jAHiSy9hCr8A=s48-c-k-c0x00ffffff-no-rj', 0, 0),
	(4, 'G H', '60ebf6e22afe105f975ddc810e6cac4599034f40cc25c16da279c36c2b4bcbbe', 'hahaha', 'https://yt3.ggpht.com/ytc/AKedOLTKlJikftoRhKZ-EwgcIlEdQcUgAXj_XggAtq4R0g=s48-c-k-c0x00ffffff-no-rj', 0, 0),
	(5, 'TheAnarchistMuffin', 'af52ad9b053f136f52d275a4f051246c2576b4a35df447c87b1b97ab9ca89758', 'hehehe', 'https://yt3.ggpht.com/ytc/AKedOLTx9L6L_q3b0tjlsuOvAGOBCy47WMsE68PiUZS6=s48-c-k-c0x00ffffff-no-rj', 0, 0),
	(6, 'Loic Suberville', '56f120bc2eb5d7cf1767746511ba7aae1f717331e6228da1476c1227918952a6', 'ererererr', 'https://yt3.ggpht.com/ytc/AKedOLQO5N2joqcK7nrLeATRMV8VHtI1VPrmZList7ixaA=s48-c-k-c0x00ffffff-no-rj', 0, 0),
	(7, 'dillbert', 'cd2ef41c4e805f5463df06adbc92b0a28dc1cd76b795981800fba80ce0d687f5', 'hqhqhq', 'https://yt3.ggpht.com/ytc/AKedOLRY7T-jUCV0GL55NiFLDNz4Wog14R59EQqyKTOj=s48-c-k-c0x00ffffff-no-rj', 0, 0),
	(8, 'xQc', '60ebf6e22afe105f975ddc810e6cac4599034f40cc25c16da279c36c2b4bcbbe', 'ha', 'https://www.ginx.tv/uploads2/Streamers___Twitch/xqc/xQc-twitch-streamer.jpg?ezimgfmt=ng%3Awebp%2Fngcb5%2Frs%3Adevice%2Frscb5-2', 532000, 11),
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
	(1, 9, '2022-01-06 12:25:33', 'https://www.youtube.com/watch?v=axsnM1kH99c', 'hello', 'No u', 152, 'Sabaton', 0, 0, 0),
	(2, 9, '2022-01-06 12:25:33', 'https://www.youtube.com/watch?v=_RalKhxRBC8', 'Sanctified with Dynamite', 'Powerwolf Summer 2021', 0, 'Powerwolf Official', 0, 0, 0),
	(3, 9, '2022-01-06 12:25:33', 'https://www.youtube.com/watch?v=3_0f63HjTAQ', 'Speedrunning Big Iron', 'Biiig iiiiron', 0, 'HidingFox', 0, 0, 0),
	(15, 9, '2022-01-06 12:25:33', 'https://www.youtube.com/watch?v=sS5Zf5NYseo', 'Wasteland 3 - Down In The Valley To Pray (Includes Lyrics)', 'A great song from the Iconic Wasteland 3', 11, 'G H', 0, 0, 0),
	(16, 9, '2022-01-06 13:08:56', 'https://www.youtube.com/watch?v=wy-sVTaZRPk', 'War Cant of Mars (Warhammer 40k)', 'A pretty cool song from WH40k', 49, 'TheAnarchistMuffin', 0, 0, 0),
	(17, 9, '2022-01-06 12:25:33', 'https://www.youtube.com/watch?v=A7QT7_aQrG0', 'How The Pandemic Started', 'How the pandemic truly started', 7, 'Loic Suberville', 0, 0, 0),
	(19, 9, '2022-01-06 12:25:33', 'https://www.youtube.com/watch?v=qoWCB8AUiP4', 'Goldeneye 64 - Watch [Pause Music] - UNCOMPRESSED (Remake 777PROJEKT Original)', 'A remake of Goldeneye Soundtrack', 57, 'matt', 0, 0, 0),
	(20, 8, '2022-01-12 10:59:54', 'https://www.youtube.com/watch?v=3MRSLSQdelI', 'Attack of the Dead Men', 'My video 1', 0, 'xQc', 0, 0, 0),
	(21, 8, '2022-01-12 10:59:54', 'https://www.youtube.com/watch?v=3MRSLSQdelI', 'Attack of the Dead Yoshis', 'My video 2', 0, 'xQc', 0, 0, 0),
	(22, 8, '2022-01-12 10:59:54', 'https://www.youtube.com/watch?v=3MRSLSQdelI', 'Attack of the Dead Dogs', 'My video 3', 1, 'xQc', 0, 0, 0),
	(23, 8, '2022-01-12 10:59:54', 'https://www.youtube.com/watch?v=3MRSLSQdelI', 'Attack of the Dead Cats', 'My video 4', 6, 'xQc', 0, 0, 0);
/*!40000 ALTER TABLE `videos` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
