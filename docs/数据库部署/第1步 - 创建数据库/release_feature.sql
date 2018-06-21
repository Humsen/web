/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云MySQL
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : rm-wz9lp2i9322g0n06zvo.mysql.rds.aliyuncs.com:3306
 Source Schema         : web

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 07/03/2018 09:59:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for release_feature
-- ----------------------------
DROP TABLE IF EXISTS `release_feature`;
CREATE TABLE `release_feature`  (
  `release_id` int(32) NOT NULL AUTO_INCREMENT,
  `release_author` char(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `release_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `release_number` char(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `release_content` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`release_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
