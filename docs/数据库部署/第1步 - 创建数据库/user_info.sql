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

 Date: 07/03/2018 09:59:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `user_id` int(32) NOT NULL AUTO_INCREMENT,
  `user_name` char(15) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_password` char(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_email` char(25) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_phone` char(15) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `user_age` int(32) NULL DEFAULT NULL,
  `user_sex` int(32) NULL DEFAULT NULL,
  `user_address` char(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `user_head_url` char(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `user_nick_name` char(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
