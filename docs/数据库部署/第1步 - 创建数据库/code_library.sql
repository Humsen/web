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

 Date: 07/03/2018 09:59:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for code_library
-- ----------------------------
DROP TABLE IF EXISTS `code_library`;
CREATE TABLE `code_library`  (
  `code_id` int(32) NOT NULL AUTO_INCREMENT,
  `code_title` char(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `code_author` char(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `code_date` timestamp(0) NULL DEFAULT NULL,
  `code_read` int(32) NULL DEFAULT NULL,
  `code_summary` char(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `code_html_content` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `code_md_content` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `code_label` char(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `code_delete` int(32) NULL DEFAULT NULL,
  `code_category` int(32) NULL DEFAULT NULL,
  PRIMARY KEY (`code_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
