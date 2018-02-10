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

 Date: 09/02/2018 23:33:03
*/


-- ----------------------------
-- Table structure for message_area
-- ----------------------------
DROP TABLE IF EXISTS "public"."message_area";
CREATE TABLE "public"."message_area" (
  "message_id" int4 NOT NULL DEFAULT nextval('message_area_message_id_seq'::regclass),
  "message_parent" int4 NOT NULL,
  "message_belong" int4 NOT NULL,
  "message_content" text COLLATE "pg_catalog"."default" NOT NULL,
  "message_date" timestamp(6) NOT NULL,
  "message_email" varchar(40) COLLATE "pg_catalog"."default",
  "message_username" varchar(40) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Primary Key structure for table message_area
-- ----------------------------
ALTER TABLE "public"."message_area" ADD CONSTRAINT "message_area_pkey" PRIMARY KEY ("message_id");
