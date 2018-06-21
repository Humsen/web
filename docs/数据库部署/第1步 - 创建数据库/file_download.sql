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

 Date: 07/03/2018 09:59:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file_download
-- ----------------------------
DROP TABLE IF EXISTS `file_download`;
CREATE TABLE `file_download`  (
  `file_id` int(32) NOT NULL AUTO_INCREMENT,
  `file_name` char(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `file_url` char(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `file_upload_date` timestamp(0) NULL DEFAULT NULL,
  `file_download_count` int(32) NULL DEFAULT NULL,
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
