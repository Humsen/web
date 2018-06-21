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

 Date: 07/03/2018 09:59:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for visit_total
-- ----------------------------
DROP TABLE IF EXISTS `visit_total`;
CREATE TABLE `visit_total`  (
  `visit_id` int(32) NOT NULL AUTO_INCREMENT,
  `visit_date` date NULL DEFAULT NULL,
  `visit_count` int(32) NULL DEFAULT NULL,
  PRIMARY KEY (`visit_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
