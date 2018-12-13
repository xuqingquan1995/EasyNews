/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : EasyNews

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 29/11/2018 21:52:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_channel
-- ----------------------------
DROP TABLE IF EXISTS `tb_channel`;
CREATE TABLE `tb_channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `channelId` varchar(64) NOT NULL COMMENT '频道id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '频道名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `channelId` (`channelId`),
  KEY `channelId_2` (`channelId`,`name`),
  KEY `name` (`name`),
  KEY `channelId_3` (`channelId`),
  KEY `channelId_4` (`channelId`),
  KEY `channelId_5` (`channelId`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tb_news
-- ----------------------------
DROP TABLE IF EXISTS `tb_news`;
CREATE TABLE `tb_news` (
  `newsId` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(64) NOT NULL,
  `title` varchar(255) NOT NULL,
  `link` varchar(255) NOT NULL,
  `pubDate` varchar(32) NOT NULL,
  `source` varchar(32) NOT NULL,
  `desc` varchar(512) DEFAULT NULL,
  `channelId` varchar(32) NOT NULL,
  `channelName` varchar(32) NOT NULL,
  `nid` varchar(64) DEFAULT NULL,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `html` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`newsId`),
  UNIQUE KEY `tb_news_id_uindex` (`id`),
  KEY `tb_news_ibfk_1` (`channelId`),
  KEY `channelName` (`channelName`),
  KEY `newsId` (`newsId`),
  CONSTRAINT `tb_news_ibfk_1` FOREIGN KEY (`channelId`) REFERENCES `tb_channel` (`channelid`),
  CONSTRAINT `tb_news_ibfk_2` FOREIGN KEY (`channelName`) REFERENCES `tb_channel` (`name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=1137549 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tb_newsImage
-- ----------------------------
DROP TABLE IF EXISTS `tb_newsImage`;
CREATE TABLE `tb_newsImage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `newsId` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  `width` int(11) NOT NULL DEFAULT '0',
  `height` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `tb_newsImage_id_uindex` (`id`),
  UNIQUE KEY `newsId` (`newsId`,`url`) USING BTREE,
  CONSTRAINT `tb_newsimage_ibfk_1` FOREIGN KEY (`newsId`) REFERENCES `tb_news` (`newsid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3933858 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
