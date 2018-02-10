/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云
 Source Server Type    : PostgreSQL
 Source Server Version : 90317
 Source Host           : 115.159.64.86:5432
 Source Catalog        : web
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 90317
 File Encoding         : 65001

 Date: 09/02/2018 23:33:21
*/


-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS "public"."user_info";
CREATE TABLE "public"."user_info" (
  "user_id" int4 NOT NULL DEFAULT nextval('user_info_user_id_seq'::regclass),
  "user_name" varchar(15) COLLATE "pg_catalog"."default" NOT NULL,
  "user_password" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "user_email" varchar(25) COLLATE "pg_catalog"."default" NOT NULL,
  "user_phone" varchar(15) COLLATE "pg_catalog"."default",
  "user_age" int4,
  "user_sex" int4,
  "user_address" varchar(50) COLLATE "pg_catalog"."default",
  "user_head_url" varchar(100) COLLATE "pg_catalog"."default",
  "user_nick_name" varchar(30) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Primary Key structure for table user_info
-- ----------------------------
ALTER TABLE "public"."user_info" ADD CONSTRAINT "user_info_pkey" PRIMARY KEY ("user_id");
